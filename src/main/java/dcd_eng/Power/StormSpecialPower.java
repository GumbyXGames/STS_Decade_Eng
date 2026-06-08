package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class StormSpecialPower extends AbstractPower {
   public static final String POWER_ID = "StormSpecialPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public StormSpecialPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "StormSpecialPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/Mark.png");
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "StormSpecialPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      int x = 0;
      if (this.owner.hasPower("Strength")) {
         x = this.owner.getPower("Strength").amount;
      }

      if (this.amount != 0) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(target, new DamageInfo(this.owner, x * 2, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.owner, this.owner, "StormSpecialPower", 1));
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("StormSpecialPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
