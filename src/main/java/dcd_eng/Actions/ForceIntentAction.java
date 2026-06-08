package dcd_eng.Actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import dcd_eng.Patches.AbstractCustomCardWithType2;
import java.util.function.Predicate;

public class ForceIntentAction extends AbstractGameAction {
   private AbstractCustomCardWithType2.IntentTypes intentType;
   public static Predicate<AbstractMonster> attackTest = (mo) -> mo.intent == Intent.ATTACK || mo.intent == Intent.ATTACK_DEFEND || mo.intent == Intent.ATTACK_DEBUFF || mo.intent == Intent.ATTACK_BUFF;
   private static Predicate<AbstractMonster> notAttackTest = (mo) -> mo.intent != Intent.ATTACK && mo.intent != Intent.ATTACK_DEFEND && mo.intent != Intent.ATTACK_DEBUFF && mo.intent != Intent.ATTACK_BUFF;
   private AbstractMonster m;

   public ForceIntentAction(AbstractMonster m, AbstractCustomCardWithType2.IntentTypes type) {
      this.m = m;
      this.intentType = type;
   }

   public void update() {
      this.isDone = this.newIntent(this.m, this.intentType);
   }

   private boolean newIntent(AbstractMonster m, AbstractCustomCardWithType2.IntentTypes type) {
      if (!m.id.equals("GiantHead") && !m.id.equals("Maw") && !m.id.equals("BookOfStabbing") && !m.id.equals("CorruptHeart")) {
         Predicate<AbstractMonster> test;
         if (type == AbstractCustomCardWithType2.IntentTypes.ATTACK) {
            test = attackTest;
         } else {
            test = notAttackTest;
         }

         if (test.test(m)) {
            return true;
         } else {
            EnemyMoveInfo originalMove = (EnemyMoveInfo)ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");

            for(int tries = 0; tries < 10; ++tries) {
               m.rollMove();
               m.createIntent();
               if (test.test(m)) {
                  return true;
               }
            }

            m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
            m.createIntent();
            return true;
         }
      } else {
         return true;
      }
   }

   public static void previewNewIntent(AbstractMonster m, AbstractCustomCardWithType2.IntentTypes type) {
      if (!m.id.equals("GiantHead") && !m.id.equals("Maw") && !m.id.equals("BookOfStabbing") && !m.id.equals("CorruptHeart")) {
         Predicate<AbstractMonster> test;
         if (type == AbstractCustomCardWithType2.IntentTypes.ATTACK) {
            test = attackTest;
         } else {
            test = notAttackTest;
         }

         if (!test.test(m)) {
            EnemyMoveInfo originalMove = (EnemyMoveInfo)ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");

            for(int tries = 0; tries < 10; ++tries) {
               m.rollMove();
               m.createIntent();
               if (test.test(m)) {
                  return;
               }
            }

            m.setMove(originalMove.nextMove, originalMove.intent, originalMove.baseDamage, originalMove.multiplier, originalMove.isMultiDamage);
            m.createIntent();
         }
      }
   }
}
