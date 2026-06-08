package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialHibikiTaiko;

public class Hibiki_taikoR2 extends AbstractGameEffect {
   private float x;
   private float y;
   private Texture img = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/taikoR2.png");

   public Hibiki_taikoR2() {
      this.x = SpecialHibikiTaiko.hb.cX - 354.5F;
      this.y = SpecialHibikiTaiko.hb.cY - 63.0F;
      this.duration = 0.3F;
      this.startingDuration = 0.3F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         this.img.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      sb.setColor(this.color);
      sb.draw(this.img, this.x, this.y, 354.5F, 63.0F, 709.0F, 126.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 709, 126, false, false);
   }

   public void dispose() {
   }
}
