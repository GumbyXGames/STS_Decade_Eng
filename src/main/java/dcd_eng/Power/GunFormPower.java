package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class GunFormPower extends AbstractPower {
   public static final String POWER_ID = "GunFormPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean BlastSpecialPower = false;
   private int x = 0;

   public GunFormPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "GunFormPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/RideBooker.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void updateDescription() {
      if (this.amount == -1) {
         this.description = DESCRIPTIONS[0] + 0 + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
      }

   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.cardID.equals("Decade_Blast")) {
         this.BlastSpecialPower = true;
      }

   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (this.BlastSpecialPower) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, this.owner, new WeakPower(target, 2, false), 2));
      }

   }

   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
      if (card.cardID.equals("RideBooker_Shoot")) {
         ++this.x;
         this.amount = this.x;
         this.updateDescription();
      }

      if (card.cardID.equals("Decade_Blast")) {
         this.BlastSpecialPower = false;
         this.updateDescription();
      }

      if (this.x == 3) {
         this.x = 0;
         this.amount = -1;
         boolean Blast = true;

         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("Decade_Blast")) {
               AbstractDungeon.player.discardPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               Blast = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("Decade_Blast") && Blast) {
               AbstractDungeon.player.drawPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               break;
            }
         }

         this.updateDescription();
      }

   }

   public void onRemove() {
      for(int i = 0; i < 3; ++i) {
         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals("RideBooker_Shoot")) {
               AbstractDungeon.player.hand.removeCard(c);
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("RideBooker_Shoot")) {
               AbstractDungeon.player.discardPile.removeCard(c);
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("RideBooker_Shoot")) {
               AbstractDungeon.player.drawPile.removeCard(c);
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.cardID.equals("RideBooker_Shoot")) {
               AbstractDungeon.player.exhaustPile.removeCard(c);
               break;
            }
         }
      }

      for(AbstractCard c : AbstractDungeon.player.hand.group) {
         if (c.cardID.equals("Decade_Blast")) {
            AbstractDungeon.player.hand.removeCard(c);
            break;
         }
      }

      for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
         if (c.cardID.equals("Decade_Blast")) {
            AbstractDungeon.player.discardPile.removeCard(c);
            break;
         }
      }

      for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
         if (c.cardID.equals("Decade_Blast")) {
            AbstractDungeon.player.drawPile.removeCard(c);
            break;
         }
      }

      for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
         if (c.cardID.equals("Decade_Blast")) {
            AbstractDungeon.player.exhaustPile.removeCard(c);
            break;
         }
      }

   }

   public void atStartOfTurn() {
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("GunFormPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
