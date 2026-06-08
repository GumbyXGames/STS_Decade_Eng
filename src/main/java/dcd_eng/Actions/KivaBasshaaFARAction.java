package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class KivaBasshaaFARAction extends AbstractGameAction {
   public KivaBasshaaFARAction(AbstractCreature m) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.target = m;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST && this.target.currentBlock <= 0) {
         this.addToTop(new RemoveSpecificPowerAction(this.target, this.target, "KivaFrozenPower"));
         this.addToTop(new RemoveSpecificPowerAction(this.target, this.target, "KivaFARFrozenPower"));
      }

      this.tickDuration();
   }
}
