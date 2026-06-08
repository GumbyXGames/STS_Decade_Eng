package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_UnarmedAttack5 extends AbstractGameEffect {
   private boolean start = true;
   private AbstractCreature target;

   public Kuuga_UnarmedAttack5(AbstractCreature target) {
      this.duration = 0.66F;
      this.startingDuration = 0.66F;
      this.target = target;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(93);
         this.start = false;
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
