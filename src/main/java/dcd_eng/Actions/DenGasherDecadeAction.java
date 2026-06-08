package dcd_eng.Actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.Arrays;

public class DenGasherDecadeAction extends AbstractGameAction {
   private final int damage;

   public DenGasherDecadeAction(int d) {
      this.actionType = ActionType.DAMAGE;
      this.duration = Settings.ACTION_DUR_FAST;
      this.damage = d;
   }

   public void update() {
      this.isDone = false;
      int totalDamage = 0;

      for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
         if (!mo.isDeadOrEscaped()) {
            mo.createIntent();
            if (ForceIntentAction.attackTest.test(mo)) {
               int moDamage = (Integer)ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentDmg");
               if ((Boolean)ReflectionHacks.getPrivate(mo, AbstractMonster.class, "isMultiDmg")) {
                  moDamage *= (Integer)ReflectionHacks.getPrivate(mo, AbstractMonster.class, "intentMultiAmt");
               }

               totalDamage += moDamage;
            }
         } else {
            this.isDone = true;
         }
      }

      int[] newMultiDamage = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
      Arrays.fill(newMultiDamage, totalDamage + this.damage);
      AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, newMultiDamage, DamageType.NORMAL, AttackEffect.SLASH_HORIZONTAL));
      this.isDone = true;
   }
}
