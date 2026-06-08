package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class KivaEatAction extends AbstractGameAction {
   public KivaEatAction(AbstractCreature target, AbstractCreature source) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.target = target;
      this.source = source;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         int damage = this.target.getPower("KivaLacerationPower").amount;
         this.addToTop(new PoisonLoseHpAction(this.target, this.source, damage, AttackEffect.SLASH_HORIZONTAL));
      }

      this.tickDuration();
   }
}
