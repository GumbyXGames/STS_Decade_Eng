package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class KivaVampireAction extends AbstractGameAction {
   private final AbstractCreature source;
   private final float m;

   public KivaVampireAction(AbstractCreature source, AbstractCreature target, float heal) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.source = source;
      this.target = target;
      this.m = heal;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.lastDamageTaken > 0 && this.target != this.source) {
            int healAmount = (int)((float)this.target.lastDamageTaken * this.m);
            if (healAmount <= 0) {
               healAmount = 1;
            }

            this.addToBot(new HealAction(this.source, this.source, healAmount));
         }

         this.isDone = true;
      }

      this.tickDuration();
   }
}
