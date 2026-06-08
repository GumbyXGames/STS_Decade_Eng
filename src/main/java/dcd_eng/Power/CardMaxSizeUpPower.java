package dcd_eng.Power;

import basemod.BaseMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CardMaxSizeUpPower extends AbstractPower {
   public static final String POWER_ID = "CardMaxSizeUpPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public CardMaxSizeUpPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "CardMaxSizeUpPower";
      this.owner = owner;
      this.amount = amt;
      this.updateDescription();
      this.loadRegion("carddraw");
   }

   public void onRemove() {
      super.onRemove();
      BaseMod.MAX_HAND_SIZE -= this.amount;
   }

   public void onVictory() {
      super.onVictory();
      BaseMod.MAX_HAND_SIZE -= this.amount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + BaseMod.MAX_HAND_SIZE + DESCRIPTIONS[2];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("CardMaxSizeUpPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
