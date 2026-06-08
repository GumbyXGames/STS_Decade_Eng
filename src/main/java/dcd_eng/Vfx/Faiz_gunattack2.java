package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import dcd_eng.Characters.Decade;

public class Faiz_gunattack2 extends AbstractGameEffect {
   private int stage;

   public Faiz_gunattack2() {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(166);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.7F && this.stage == 0) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.0F));
         ++this.stage;
      } else if (this.duration < 0.5F && this.stage == 1) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToTop(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, 12, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            }
         }

         ++this.stage;
      }

      if (this.duration < 0.0F) {
         if (Decade.cf != 3) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(34);
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
