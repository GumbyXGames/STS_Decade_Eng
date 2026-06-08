package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.UpdateDescriptionAction;
import dcd_eng.Helper.SpecialKivaPowerMeter;

public class KivaDoggaPower extends AbstractPower {
   public static final String POWER_ID = "KivaDoggaPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KivaDoggaPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaDoggaPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KivaDoggaPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void atStartOfTurn() {
      this.updateDescription();
   }

   public int onLoseHp(int damageAmount) {
      if (SpecialKivaPowerMeter.powerMeter > 0) {
         if (damageAmount == 1) {
            --SpecialKivaPowerMeter.powerMeter;
            return 0;
         } else if (damageAmount <= 0) {
            return damageAmount;
         } else {
            int p = SpecialKivaPowerMeter.powerMeter;
            if ((double)p > (double)damageAmount * (double)0.5F) {
               SpecialKivaPowerMeter.powerMeter -= damageAmount / 2;
               return (int)((double)damageAmount * (double)0.5F);
            } else {
               SpecialKivaPowerMeter.powerMeter = 0;
               return damageAmount - p;
            }
         }
      } else {
         return damageAmount;
      }
   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      if (power.ID.equals("KivaHungry3Power")) {
         this.addToBot(new UpdateDescriptionAction(this));
      }

   }

   public void wasHPLost(DamageInfo info, int damageAmount) {
      if (damageAmount > 0 && info.owner != this.owner && info.type == DamageType.NORMAL) {
         this.addToTop(new ApplyPowerAction(info.owner, this.owner, new KivaLacerationPower(info.owner, 5, this.owner), 5));
      }

   }

   public int onHeal(int healAmount) {
      if (this.owner.hasPower("KivaHungry3Power")) {
         SpecialKivaPowerMeter.powerMeter += 3;
      }

      return healAmount;
   }

   public void updateDescription() {
      if (this.owner.hasPower("KivaHungry3Power")) {
         this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaDoggaPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
