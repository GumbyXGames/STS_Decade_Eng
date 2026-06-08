package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Power.XuanyunPower;

public class KivaDoggaFARAction extends AbstractGameAction {
   public KivaDoggaFARAction(AbstractCreature m) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.target = m;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.currentBlock <= 0) {
            this.addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new XuanyunPower(this.target), 1));
         } else {
            this.addToTop(new RemoveAllBlockAction(this.target, AbstractDungeon.player));
         }
      }

      this.tickDuration();
   }
}
