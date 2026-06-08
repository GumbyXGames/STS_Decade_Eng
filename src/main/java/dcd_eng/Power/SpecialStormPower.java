package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SpecialStormPower extends AbstractPower {
   public static final String POWER_ID = "SpecialStormPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public SpecialStormPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "SpecialStormPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/Mark.png");
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "SpecialStormPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   public float atDamageGive(float damage, DamageInfo.DamageType type) {
      if (this.owner.hasPower("Dexterity")) {
         int x = this.owner.getPower("Dexterity").amount;
         return damage + (float)x;
      } else {
         return damage;
      }
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("SpecialStormPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
