package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_AllMonsterAttacked_End extends AbstractGameEffect {
   private final String id;

   Kuuga_AllMonsterAttacked_End(String id) {
      this.duration = 0.58F;
      this.startingDuration = 0.58F;
      this.id = id;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         AbstractAnimation.clear(this.id);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
