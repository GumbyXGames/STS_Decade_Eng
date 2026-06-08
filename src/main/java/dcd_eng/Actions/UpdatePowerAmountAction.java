package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcd_eng.Helper.SpecialKivaPowerMeter;

public class UpdatePowerAmountAction extends AbstractGameAction {
   private final AbstractPlayer p;

   public UpdatePowerAmountAction(AbstractPlayer p) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.p = p;
   }

   public void update() {
      for(AbstractPower pp : this.p.powers) {
         if (pp.ID.equals("KamenRideKivaPower")) {
            pp.amount = 30;
         }
      }

      SpecialKivaPowerMeter.KivaTrigger = true;
      this.isDone = true;
   }
}
