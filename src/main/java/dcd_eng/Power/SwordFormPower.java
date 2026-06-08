package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Special.Decade_Slash;

public class SwordFormPower extends AbstractPower {
   public static final String POWER_ID = "SwordFormPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int x = 0;

   public SwordFormPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "SwordFormPower";
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

   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
      if (card.cardID.equals("RideBooker_Attack")) {
         ++this.x;
         this.amount = this.x;
         this.updateDescription();
      }

      if (this.x == 3) {
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Decade_Slash(), 1));
         this.x = 0;
         this.amount = -1;
         this.updateDescription();
      }

   }

   public void onRemove() {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Strength", 1));

      for(int i = 0; i < 3; ++i) {
         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals("RideBooker_Attack")) {
               AbstractDungeon.player.hand.removeCard(c);
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("RideBooker_Attack")) {
               AbstractDungeon.player.discardPile.removeCard(c);
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("RideBooker_Attack")) {
               AbstractDungeon.player.drawPile.removeCard(c);
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.cardID.equals("RideBooker_Attack")) {
               AbstractDungeon.player.exhaustPile.removeCard(c);
               break;
            }
         }
      }

   }

   public void atStartOfTurn() {
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("SwordFormPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
