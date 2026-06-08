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
import dcd_eng.DCDmod;

public class DragShieldPower extends AbstractPower {
   public static final String POWER_ID = "DragShieldPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DragShieldPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "DragShieldPower";
      this.owner = owner;
      this.amount = amt;
      this.updateDescription();
      this.img = ImageMaster.loadImage("img/powers/DragShieldPower.png");
      this.type = PowerType.BUFF;
   }

   public void stackPower(int stackAmount) {
      this.updateDescription();
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DragShieldPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
   }

   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
      if (damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage <= 6.0F) {
         return 0.0F;
      } else {
         return damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage > 6.0F ? damage - 5.0F : damage;
      }
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (!AbstractDungeon.player.hasPower("DragrederPower")) {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragShieldPower", 1));
      }

      return damageAmount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
      if (DCDmod.RyukiCardLv[1] > 0 && this.owner.hasPower("KamenRideRyukiPower")) {
         int i = DCDmod.RyukiCardLv[1];
         if (i > 20) {
            i = 20;
         }

         this.description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + i * 3 + DESCRIPTIONS[3];
      }

      if (DCDmod.RyukiCardLv[1] >= 20 && this.owner.hasPower("KamenRideRyukiPower")) {
         this.description = this.description + DESCRIPTIONS[1];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DragShieldPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
