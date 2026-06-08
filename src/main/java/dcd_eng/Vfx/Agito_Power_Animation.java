package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractHandAnimation;

public class Agito_Power_Animation extends AbstractGameEffect {
   int stage = 0;
   public static boolean isPlay = false;

   public Agito_Power_Animation() {
      this.duration = 0.63F;
      this.startingDuration = 0.63F;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0 && !isPlay) {
         String AGITO_POWER_ATLAS = "img/char/DCD_Animation/agito/agito_form_p.atlas";
         String AGITO_POWER_JSON = "img/char/DCD_Animation/agito/agito_form_p_p.json";
         new AbstractHandAnimation("AGITO_POWER", AGITO_POWER_ATLAS, AGITO_POWER_JSON, 0.8F, (float)Settings.M_W / 2.0F, (float)Settings.M_H / 3.0F, 0.0F, 0.0F, 1.0F);
         VfxController.Rider_power_H = AbstractHandAnimation.getAnimation("AGITO_POWER");
         VfxController.Rider_power_H.setMovable(false);
         VfxController.Rider_power_H.state.setAnimation(0, "p", false);
         isPlay = true;
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F && isPlay) {
         AbstractHandAnimation.clear("AGITO_POWER");
         isPlay = false;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Rider_power_H = null;
   }
}
