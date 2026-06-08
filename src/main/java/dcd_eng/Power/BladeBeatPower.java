package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.ReturnRandomNumberAction2;

public class BladeBeatPower extends AbstractPower {
   public static final String POWER_ID = "BladeBeatPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public BladeBeatPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "BladeBeatPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/BladeBeatPower.png");
      this.updateDescription();
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if ((this.owner.hasPower("KamenRideBladePower") || this.owner.hasPower("DecadeViolentEmotionPower")) && card.hasTag(DCDmod.UnarmedCard)) {
         int x = this.amount;
         if (x > 5) {
            x = 5;
         }

         if (ReturnRandomNumberAction2.ReturnRandomNumber() <= x * 10) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(action.target, action.target, new XuanyunPower(action.target), 1));
         }
      }

   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      this.updateDescription();
   }

   public void updateDescription() {
      int x = this.amount;
      if (x > 5) {
         x = 5;
      }

      if (!this.owner.hasPower("KamenRideBladePower") && !this.owner.hasPower("DecadeViolentEmotionPower")) {
         this.description = DESCRIPTIONS[0] + this.amount * 2 + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0] + this.amount * 2 + DESCRIPTIONS[2] + x + DESCRIPTIONS[3];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("BladeBeatPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
