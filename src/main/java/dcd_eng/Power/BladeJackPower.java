package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.NewRideBookerCardAction;

public class BladeJackPower extends AbstractPower {
   public static final String POWER_ID = "BladeJackPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public BladeJackPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "BladeJackPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/BladeJackPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "JackFlightPower"));
   }

   public void atEndOfTurn(boolean isPlayer) {
      if (this.owner.currentBlock <= 0) {
         AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 8));
      }

      NewRideBookerCardAction.BladeJackAction();
      NewRideBookerCardAction.BladeJackAction();
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("BladeJackPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
