package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UpdateDescriptionAction extends AbstractGameAction {
   private final AbstractPower power;

   public UpdateDescriptionAction(AbstractPower p) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.power = p;
   }

   public void update() {
      this.isDone = false;
      this.power.updateDescription();
      this.isDone = true;
   }
}
