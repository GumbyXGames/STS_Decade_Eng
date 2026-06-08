package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveHalfAttributeAction extends AbstractGameAction {
   public RemoveHalfAttributeAction(AbstractCreature target, AbstractCreature source) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.hasPower("Strength") && this.target.getPower("Strength").amount > 0) {
            int x;
            if (this.target.getPower("Strength").amount == 1) {
               x = 1;
            } else {
               x = (int)Math.ceil((double)((float)this.target.getPower("Strength").amount / 2.0F));
            }

            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.target, "Strength", x));
         }

         if (this.target.hasPower("Dexterity") && this.target.getPower("Dexterity").amount > 0) {
            int x;
            if (this.target.getPower("Dexterity").amount == 1) {
               x = 1;
            } else {
               x = (int)Math.ceil((double)((float)this.target.getPower("Dexterity").amount / 2.0F));
            }

            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(this.target, this.target, "Dexterity", x));
         } else {
            this.isDone = true;
         }
      }

      this.tickDuration();
   }
}
