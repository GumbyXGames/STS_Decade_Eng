package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveFormRideAction2 extends AbstractGameAction {
   public RemoveFormRideAction2(AbstractCreature target, AbstractCreature source) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.hasPower("KivaGaruruPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KivaGaruruPower"));
         }

         if (this.target.hasPower("KivaBasshaaPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KivaBasshaaPower"));
         }

         if (this.target.hasPower("KivaDoggaPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KivaDoggaPower"));
         }

         this.isDone = true;
      }

      this.tickDuration();
   }
}
