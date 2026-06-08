package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Decade_BlackScreen extends AbstractGameEffect {
   public static boolean isBlackScreen;

   public Decade_BlackScreen(float time) {
      this.duration = time;
      this.startingDuration = time;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         isBlackScreen = true;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         isBlackScreen = false;
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
