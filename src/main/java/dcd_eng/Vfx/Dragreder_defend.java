package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialDragreder;

public class Dragreder_defend extends AbstractGameEffect {
   private boolean defend = true;
   private boolean defendstart = true;

   public Dragreder_defend() {
      this.duration = 3.0F;
      this.startingDuration = 3.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.3F && this.defend) {
         this.defend = false;
         if (SpecialDragreder.a != 4) {
            SpecialDragreder.DragrederAnimationControl(3);
         }
      }

      if (this.duration < 0.0F && SpecialDragreder.isDefend2) {
         if (SpecialDragreder.a != 4) {
            SpecialDragreder.DragrederAnimationControl(5);
            SpecialDragreder.isDefend2 = false;
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.defendstart && !SpecialDragreder.isDefend2 && SpecialDragreder.a != 4) {
         SpecialDragreder.DragrederAnimationControl(6);
         SpecialDragreder.isDefend2 = true;
         this.defendstart = false;
      }

   }

   public void dispose() {
   }
}
