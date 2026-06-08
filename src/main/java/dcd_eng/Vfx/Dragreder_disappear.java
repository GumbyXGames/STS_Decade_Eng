package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialDragreder;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class Dragreder_disappear extends AbstractGameEffect {
   private boolean disappear = true;

   public Dragreder_disappear() {
      this.duration = 3.0F;
      this.startingDuration = 3.0F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         AbstractSummonedAnimation.clear("DRAGREDER");
         SpecialDragreder.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.disappear) {
         SpecialDragreder.DragrederAnimationControl(4);
         this.disappear = false;
      }

   }

   public void dispose() {
   }
}
