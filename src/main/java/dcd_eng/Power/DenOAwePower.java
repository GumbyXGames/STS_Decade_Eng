package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class DenOAwePower extends AbstractPower {
   public static final String POWER_ID = "DenOAwePower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DenOAwePower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "DenOAwePower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/awe.png");
      this.type = PowerType.DEBUFF;
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      this.updateDescription();
      if (this.amount <= 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DenOAwePower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
      if (this.owner.hasPower("DenOAwePower") && this.amount > 10) {
         this.amount = 10;
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public void atEndOfRound() {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, this.amount / 2));
   }

   public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
      if (type == DamageType.NORMAL) {
         float x = 1.0F - (float)this.amount / 10.0F;
         damage *= x;
      }

      return super.atDamageFinalGive(damage, type);
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DenOAwePower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
