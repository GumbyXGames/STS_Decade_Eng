package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.Card.Special.Decade_Slash;

public class DecadeSlashAction extends AbstractGameAction {
   private static final float DURATION = 0.1F;

   public DecadeSlashAction(AbstractCreature m) {
      this.actionType = ActionType.SPECIAL;
      this.duration = 0.1F;
      this.target = m;
   }

   public void update() {
      if (this.duration == 0.1F) {
         if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
            AbstractDungeon.player.hand.addToBottom(new Decade_Slash());
         }

         if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
         }
      }

      this.tickDuration();
   }
}
