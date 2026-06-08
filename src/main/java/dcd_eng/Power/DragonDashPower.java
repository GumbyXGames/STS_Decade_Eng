package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import dcd_eng.Vfx.Kuuga_dash;

public class DragonDashPower extends AbstractPower {
   public static final String POWER_ID = "DragonDashPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DragonDashPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "DragonDashPower";
      this.owner = owner;
      this.amount = amt;
      this.updateDescription();
      this.img = ImageMaster.loadImage("img/powers/DragonDashPower.png");
      this.type = PowerType.BUFF;
   }

   public void stackPower(int stackAmount) {
      this.updateDescription();
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DragonDashPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
   }

   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
      return 0.0F;
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner && info.type == DamageType.NORMAL) {
         if (this.owner.hasPower("KamenRideKuugaPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_dash(), 0.0F));
         }

         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragonDashPower", 1));
      }

      return damageAmount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DragonDashPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
