package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class XuanyunResistancePower extends AbstractPower {
   public static final String POWER_ID = "XuanyunResistancePower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int x;

   XuanyunResistancePower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "XuanyunResistancePower";
      this.owner = owner;
      this.amount = amt;
      this.updateDescription();
      this.img = ImageMaster.loadImage("img/powers/unstun.png");
   }

   public void stackPower(int stackAmount) {
      this.fontScale = 8.0F;
      this.amount += stackAmount;
      if (this.owner.hasPower("XuanyunResistancePower") && this.amount >= 3) {
         this.amount = -1;
         this.x = 3;
      }

      this.updateDescription();
   }

   public void atStartOfTurn() {
      super.atStartOfTurn();
      if (this.amount == -1) {
         --this.x;
         if (this.x == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "XuanyunResistancePower"));
         }

         this.updateDescription();
      }

   }

   public void updateDescription() {
      if (this.amount != -1) {
         this.description = DESCRIPTIONS[0];
      } else {
         this.description = DESCRIPTIONS[1] + this.x + DESCRIPTIONS[2];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("XuanyunResistancePower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
