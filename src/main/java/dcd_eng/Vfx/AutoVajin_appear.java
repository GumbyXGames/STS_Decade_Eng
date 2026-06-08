package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialAutoVajin;

public class AutoVajin_appear extends AbstractGameEffect {
   private boolean start = true;

   public AutoVajin_appear() {
      this.duration = 2.89F;
      this.startingDuration = 2.89F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.89F && this.start) {
         SpecialAutoVajin.AutoVajinTrigger1 = true;
         SpecialAutoVajin.a = 1;
         SpecialAutoVajin.update();
         this.start = false;
      }

      if (this.duration < 0.0F) {
         SpecialAutoVajin.a = 5;
         SpecialAutoVajin.update();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
