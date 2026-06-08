package dcd_eng.Power;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class JackFlightPower extends AbstractPower {
   public static final String POWER_ID = "JackFlightPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public JackFlightPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "JackFlightPower";
      this.owner = owner;
      this.amount = -1;
      this.updateDescription();
      this.img = ImageMaster.loadImage("img/powers/flight.png");
      this.type = PowerType.valueOf("KamenRide");
   }

   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
      return damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS ? damage / 2.0F : damage;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("JackFlightPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
