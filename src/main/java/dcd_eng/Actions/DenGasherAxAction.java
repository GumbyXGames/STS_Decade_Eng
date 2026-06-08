package dcd_eng.Actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DenGasherAxAction extends AbstractGameAction {
   private final int damage;
   private AbstractMonster m;

   public DenGasherAxAction(AbstractMonster m, int d) {
      this.actionType = ActionType.DAMAGE;
      this.duration = Settings.ACTION_DUR_FAST;
      this.m = m;
      this.damage = d;
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

      AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, totalDamage * 3 + this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
      this.isDone = true;
   }
}
