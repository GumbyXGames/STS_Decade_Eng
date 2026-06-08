package dcd_eng.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Patches.ModBaseClassForSLExample;

public class TimeVentPower extends AbstractPower {
   public static final String POWER_ID = "TimeVentPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public TimeVentPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "TimeVentPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/TimeVentPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onVictory() {
      ModBaseClassForSLExample.timevent = false;
      ModBaseClassForSLExample.TimeVentUpgraded = true;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("TimeVentPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
