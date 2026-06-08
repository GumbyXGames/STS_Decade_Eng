package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcd_eng.Actions.ReturnRandomNumberAction2;
import dcd_eng.Patches.HibikiTaikoKeyEvent;

public class HibikiRollPower extends AbstractPower {
   public static final String POWER_ID = "HibikiRollPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public HibikiRollPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "HibikiRollPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/Track.png");
      this.updateDescription();
   }

   public void updateDescription() {
      if (this.amount >= 2) {
         this.description = DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0];
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner && this.owner.hasPower("KamenRideHibikiPower") && this.amount * 50 >= ReturnRandomNumberAction2.ReturnRandomNumber()) {
         this.flash();
         ++HibikiTaikoKeyEvent.ActionPoint;
      }

      return damageAmount;
   }

   public void atStartOfTurn() {
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "HibikiRollPower"));
      } else {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "HibikiRollPower", this.amount));
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibikiRollPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
