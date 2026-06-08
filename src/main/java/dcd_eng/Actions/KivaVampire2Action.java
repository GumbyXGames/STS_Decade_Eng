package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class KivaVampire2Action extends AbstractGameAction {
   public KivaVampire2Action(AbstractCreature m) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.target = m;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         this.target.decreaseMaxHealth(5);
         AbstractDungeon.player.increaseMaxHp(5, true);
      }

      this.tickDuration();
   }
}
