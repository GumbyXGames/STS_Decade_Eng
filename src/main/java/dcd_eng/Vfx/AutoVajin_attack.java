package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialAutoVajin;

public class AutoVajin_attack extends AbstractGameEffect {
   private boolean Start = true;

   public AutoVajin_attack() {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
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
         SpecialAutoVajin.a = 2;
         SpecialAutoVajin.update();
         this.Start = false;
      }

   }

   public void dispose() {
   }
}
