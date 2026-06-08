package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialDragreder;

public class Dragreder_attack extends AbstractGameEffect {
   private boolean attack = true;

   public Dragreder_attack() {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         if (SpecialDragreder.a != 4) {
            SpecialDragreder.DragrederAnimationControl(5);
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.attack) {
         SpecialDragreder.DragrederAnimationControl(2);
         this.attack = false;
      }

   }

   public void dispose() {
   }
}
