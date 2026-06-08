package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import dcd_eng.DCDmod;

public class RyukiAttackAction extends AbstractGameAction {
   private int increaseHpAmount;
   private DamageInfo info;
   private static final float DURATION = 0.1F;

   public RyukiAttackAction(AbstractCreature target, DamageInfo info, int maxHPAmount, AbstractGameAction.AttackEffect a) {
      this.setValues(target, this.info = info);
      this.increaseHpAmount = maxHPAmount;
      this.actionType = ActionType.DAMAGE;
      this.duration = 0.1F;
      this.attackEffect = a;
   }

   public void update() {
      if (this.duration == 0.1F && this.target != null) {
         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
         this.target.damage(this.info);
         if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {
            if (this.increaseHpAmount == 1) {
               int var10002 = DCDmod.RyukiCardLv[0]++;
               this.increaseHpAmount = 0;
            }

            if (this.increaseHpAmount == 2) {
               int var1 = DCDmod.RyukiCardLv[1]++;
               this.increaseHpAmount = 0;
            }

            if (this.increaseHpAmount == 3) {
               int var2 = DCDmod.RyukiCardLv[2]++;
               this.increaseHpAmount = 0;
            }

            if (this.increaseHpAmount > 0 && !this.target.hasPower("Minion")) {
               if (this.increaseHpAmount == 10) {
                  int var3 = DCDmod.RyukiCardLv[0]++;
                  var3 = DCDmod.RyukiCardLv[1]++;
                  var3 = DCDmod.RyukiCardLv[2]++;
               }

               if (this.increaseHpAmount == 5) {
                  int var6 = DCDmod.RyukiCardLv[0]++;
               }

               AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);
            }
         }

         if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
         }
      }

      this.tickDuration();
   }
}
