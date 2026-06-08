package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;

public class Kabuto_FAR_sounds extends AbstractGameEffect {
   private boolean sound = true;
   private boolean Bgm = true;

   public Kabuto_FAR_sounds() {
      this.duration = 3.0F;
      this.startingDuration = 3.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.5F && this.Bgm) {
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("kabuto_BGM.ogg");
         }

         this.Bgm = false;
      }

      if (this.duration < 0.0F) {
         CardCrawlGame.sound.playA("FAR_KABUTO", 0.0F);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.sound) {
         CardCrawlGame.sound.playA("FAR", 0.0F);
         this.sound = false;
      }

   }

   public void dispose() {
   }
}
