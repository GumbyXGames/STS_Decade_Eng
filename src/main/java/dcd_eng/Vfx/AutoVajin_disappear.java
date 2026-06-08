package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialAutoVajin;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class AutoVajin_disappear extends AbstractGameEffect {
   private int stage = 0;

   public AutoVajin_disappear() {
      this.duration = 0.43F;
      this.startingDuration = 0.43F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.21F && this.stage == 1) {
         CardCrawlGame.sound.playA("autovajindisappear", 0.0F);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractSummonedAnimation.clear("AutoVajin");
         SpecialAutoVajin.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.stage == 0) {
         SpecialAutoVajin.a = 3;
         SpecialAutoVajin.update();
         ++this.stage;
      }

   }

   public void dispose() {
   }
}
