package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialDragreder;

public class Dragreder_appear extends AbstractGameEffect {
   private boolean appear = true;

   public Dragreder_appear() {
      this.duration = 3.0F;
      this.startingDuration = 3.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.5F && this.appear) {
         SpecialDragreder.DragrederAnimationControl(1);
         this.appear = false;
      }

      if (this.duration < 0.0F) {
         SpecialDragreder.DragrederAnimationControl(5);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
