package dcd_eng.Actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DenORodAction extends AbstractGameAction {
   private AbstractMonster m;

   public DenORodAction(AbstractMonster m) {
      this.actionType = ActionType.DAMAGE;
      this.duration = Settings.ACTION_DUR_FAST;
      this.m = m;
   }

   public void update() {
      this.isDone = false;
      int totalDamage = 0;
      if (!this.m.isDeadOrEscaped()) {
         this.m.createIntent();
         if (ForceIntentAction.attackTest.test(this.m)) {
            int moDamage = (Integer)ReflectionHacks.getPrivate(this.m, AbstractMonster.class, "intentDmg");
            if ((Boolean)ReflectionHacks.getPrivate(this.m, AbstractMonster.class, "isMultiDmg")) {
               moDamage *= (Integer)ReflectionHacks.getPrivate(this.m, AbstractMonster.class, "intentMultiAmt");
            }

            totalDamage += moDamage;
         } else {
            this.isDone = true;
         }
      }

      AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, totalDamage));
      this.isDone = true;
   }
}
