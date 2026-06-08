package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_UnarmedAttack6 extends AbstractGameEffect {
   private final int damage;
   private final DamageInfo.DamageType damageType;
   private int stage;
   private AbstractCreature target;

   public Kuuga_UnarmedAttack6(AbstractCreature target, int damage, DamageInfo.DamageType DamageType) {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
      this.target = target;
      this.damage = damage;
      this.damageType = DamageType;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(94);
         ++this.stage;
      } else if (this.duration < 0.6F && this.stage == 1) {
         AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType)));
         AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
