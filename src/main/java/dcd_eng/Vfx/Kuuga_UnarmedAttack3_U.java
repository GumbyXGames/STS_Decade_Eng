package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_UnarmedAttack3_U extends AbstractGameEffect {
   private boolean start = true;

   public Kuuga_UnarmedAttack3_U() {
      this.duration = 0.63F;
      this.startingDuration = 0.63F;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         AbstractDungeon.effectsQueue.add(new Kuuga_AllMonsterAttacked());
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(91);
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
