package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class KivaHungry2Action extends AbstractGameAction {
   public KivaHungry2Action() {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         this.addToBot(new AbstractGameAction() {
            public void update() {
               this.addToBot(new KivaHungryAttackAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), false));
               this.isDone = true;
            }
         });
      }

      this.tickDuration();
   }
}
