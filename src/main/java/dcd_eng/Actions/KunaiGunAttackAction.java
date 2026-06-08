package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class KunaiGunAttackAction extends AbstractGameAction {
   private int damage;
   private int block;
   private static final float DURATION = 0.1F;

   public KunaiGunAttackAction(AbstractCreature target, int damage, int block) {
      this.target = target;
      this.actionType = ActionType.SPECIAL;
      this.damage = damage;
      this.block = block;
      this.duration = 0.1F;
   }

   public void update() {
      if (this.duration == 0.1F && this.target != null) {
         if (KunaiGunDiscardAction.A > 0) {
            for(int i = 0; i < KunaiGunDiscardAction.A; ++i) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            }

            KunaiGunDiscardAction.A = 0;
         }

         if (KunaiGunDiscardAction.D > 0) {
            for(int i = 0; i < KunaiGunDiscardAction.D; ++i) {
               AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
            }

            KunaiGunDiscardAction.D = 0;
         }

         if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
         }
      }

      this.tickDuration();
   }
}
