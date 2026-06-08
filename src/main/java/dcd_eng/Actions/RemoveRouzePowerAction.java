package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveRouzePowerAction extends AbstractGameAction {
   public RemoveRouzePowerAction(AbstractCreature target, AbstractCreature source) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.hasPower("BladeSlashPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeSlashPower"));
         }

         if (this.target.hasPower("BladeBeatPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeBeatPower"));
         }

         if (this.target.hasPower("BladeKickPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeKickPower"));
         } else {
            this.isDone = true;
         }
      }

      this.tickDuration();
   }
}
