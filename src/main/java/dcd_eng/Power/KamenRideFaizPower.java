package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Allformbacktodcd;
import dcd_eng.Vfx.Axel_attack;
import dcd_eng.Vfx.Faiz_backtodcd;

public class KamenRideFaizPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideFaizPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideFaizPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideFaizPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideFaizPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         if (Decade.cf != 39 && Decade.cf != 40 && Decade.cf != 41) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Faiz_backtodcd(), 2.0F));
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2.0F));
         }
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
      SpecialFaizBox.AxelForm = false;

      for(int i = 0; i < 4; ++i) {
         AbstractDungeon.player.decreaseMaxOrbSlots(1);
      }

   }

   public void atStartOfTurn() {
      SpecialRideBooker.isPlayerTurn = true;
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (SpecialFaizBox.AxelForm) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
         if (card.type == CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0.0F));
         }
      }

   }

   public void atEndOfTurn(boolean isPlayer) {
      ++SpecialFaizBox.FaizPoint;
      this.updateDescription();
   }

   public void atEndOfRound() {
      TurnTimer.atEndOfRound();
   }

   public void onVictory() {
      for(int i = 0; i < 4; ++i) {
         AbstractDungeon.player.decreaseMaxOrbSlots(1);
      }

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
      this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideFaizPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
