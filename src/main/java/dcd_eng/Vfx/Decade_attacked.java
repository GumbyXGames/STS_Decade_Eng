package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Decade_attacked extends AbstractGameEffect {
   private boolean start = true;

   public Decade_attacked() {
      this.duration = 0.25F;
      this.startingDuration = 0.25F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(77);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
