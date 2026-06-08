package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_backtodcd extends AbstractGameEffect {
   private boolean start = true;

   public Agito_backtodcd() {
      this.duration = 2.0F;
      this.startingDuration = 2.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         this.isDone = true;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(11);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
