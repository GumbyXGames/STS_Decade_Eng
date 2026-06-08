package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Agito_backtodcd;
import dcd_eng.Vfx.Agito_flame_t;
import dcd_eng.Vfx.Agito_storm_t;
import dcd_eng.Vfx.Agito_trinity;
import dcd_eng.Vfx.Allformbacktodcd;

public class KamenRideAgitoPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideAgitoPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int ax = 0;
   private int sx = 0;

   public KamenRideAgitoPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideAgitoPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideAgitoPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         if (Decade.cf != 17 && Decade.cf != 18 && Decade.cf != 19 && Decade.cf != 21) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Agito_backtodcd(), 2.0F));
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2.0F));
         }
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "AgitoLevelPower"));
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (this.owner.hasPower("AgitoStormPower") && this.owner.hasPower("AgitoFlamePower") && !DCDmod.AnimationTrigger) {
         if (card.cardID.equals("Agito_StormHalberd")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_storm_t(), 0.0F));
         } else if (card.cardID.equals("Agito_FlameSaber")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame_t(), 0.0F));
         } else if (card.hasTag(DCDmod.WeaponCard)) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_trinity(), 0.0F));
         }
      }

   }

   public void onAfterCardPlayed(AbstractCard usedCard) {
      if (usedCard.type == CardType.ATTACK) {
         if (!usedCard.cardID.equals("Agito_StormHalberd")) {
            ++this.ax;
         }

         this.updateDescription();
      }

      if (usedCard.type == CardType.SKILL) {
         if (!usedCard.cardID.equals("Agito_FlameSaber")) {
            ++this.sx;
         }

         this.updateDescription();
      }

      if (this.ax >= 5) {
         this.ax -= 5;
         this.updateDescription();
         boolean storm = true;

         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("FormRideStorm")) {
               AbstractDungeon.player.discardPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               storm = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("FormRideStorm") && storm) {
               AbstractDungeon.player.drawPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               storm = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.cardID.equals("FormRideStorm") && storm) {
               AbstractDungeon.player.exhaustPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c.makeCopy());
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               break;
            }
         }

         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new AgitoLevelPower(this.owner, 1), 1));
      }

      if (this.sx >= 5) {
         this.sx -= 5;
         this.updateDescription();
         boolean flame = true;

         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("FormRideFlame")) {
               AbstractDungeon.player.discardPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               flame = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("FormRideFlame") && flame) {
               AbstractDungeon.player.drawPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               flame = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.cardID.equals("FormRideFlame") && flame) {
               AbstractDungeon.player.exhaustPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c.makeCopy());
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               break;
            }
         }

         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new AgitoLevelPower(this.owner, 1), 1));
      }

   }

   public void atStartOfTurn() {
      SpecialRideBooker.isPlayerTurn = true;
   }

   public void atEndOfRound() {
      TurnTimer.atEndOfRound();
   }

   public void onVictory() {
      CardCrawlGame.sound.playA("victory_normal", 0.0F);
      if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
         CardCrawlGame.sound.playA("victory1", 0.0F);
      } else {
         CardCrawlGame.sound.playA("victory2", 0.0F);
      }

      TurnTimer.atNextBattle();
      Decade Decade = (Decade)AbstractDungeon.player;
      Decade.Trickster(3);
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.ax + DESCRIPTIONS[1] + this.sx + DESCRIPTIONS[2];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideAgitoPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
