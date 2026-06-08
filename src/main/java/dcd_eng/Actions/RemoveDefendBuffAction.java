package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveDefendBuffAction extends AbstractGameAction {
   public RemoveDefendBuffAction(AbstractCreature target, AbstractCreature source) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.hasPower("DecadeBlockPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DecadeBlockPower"));
         }

         if (this.target.hasPower("DashPower1")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DashPower1"));
         }

         if (this.target.hasPower("DashPower2")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "DashPower2"));
         }

         if (this.target.hasPower("Buffer")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "Buffer"));
         }

         if (this.target.hasPower("PegasusDefendPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "PegasusDefendPower"));
         }

         if (this.target.hasPower("IntangiblePlayerPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "IntangiblePlayerPower"));
         } else {
            this.isDone = true;
         }
      }

      this.tickDuration();
   }
}
