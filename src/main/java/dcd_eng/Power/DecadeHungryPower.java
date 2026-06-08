package dcd_eng.Power;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.KivaVampireAction;

public class DecadeHungryPower extends AbstractPower {
   public static final String POWER_ID = "DecadeHungryPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DecadeHungryPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "DecadeHungryPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/VampireTeeth.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (info.owner == this.owner && target != this.owner && info.type == DamageType.NORMAL) {
         this.addToBot(new KivaVampireAction(this.owner, target, 0.2F));
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DecadeHungryPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
