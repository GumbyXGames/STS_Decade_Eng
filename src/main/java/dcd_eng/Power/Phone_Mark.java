package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Helper.SpecialFaizBox;

public class Phone_Mark extends AbstractPower {
   public static final String POWER_ID = "Phone_Mark";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public Phone_Mark(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "Phone_Mark";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/Mark.png");
      this.updateDescription();
      this.type = PowerType.BUFF;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner && info.owner == AbstractDungeon.player) {
         this.flash();
         ++SpecialFaizBox.FaizPoint;
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Phone_Mark"));
      }

      return super.onAttacked(info, damageAmount);
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("Phone_Mark");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
