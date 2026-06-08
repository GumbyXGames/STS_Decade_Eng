package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialAutoVajin;

public class AutoVajin_defend extends AbstractGameEffect {
   private boolean Start = true;

   public AutoVajin_defend() {
      this.duration = 0.5F;
      this.startingDuration = 0.5F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         SpecialAutoVajin.a = 5;
         SpecialAutoVajin.update();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.Start) {
         SpecialAutoVajin.a = 4;
         SpecialAutoVajin.update();
         this.Start = false;
      }

   }

   public void dispose() {
   }
}
