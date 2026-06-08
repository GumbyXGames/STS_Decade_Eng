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

public class KivaGaruruPower extends AbstractPower {
   public static final String POWER_ID = "KivaGaruruPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KivaGaruruPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaGaruruPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KivaGaruruPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      if (power.ID.equals("KivaHungry3Power")) {
         this.addToBot(new UpdateDescriptionAction(this));
      }

   }

   public void atStartOfTurn() {
      this.updateDescription();
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (info.owner == this.owner && target != this.owner && info.type == DamageType.NORMAL) {
         int m = 3;
         if (this.owner.hasPower("KivaHungry3Power")) {
            m = 5;
         }

         this.addToTop(new ApplyPowerAction(target, this.owner, new KivaLacerationPower(target, m, this.owner), 3));
      }

   }

   public void updateDescription() {
      if (this.owner.hasPower("KivaHungry3Power")) {
         this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaGaruruPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
