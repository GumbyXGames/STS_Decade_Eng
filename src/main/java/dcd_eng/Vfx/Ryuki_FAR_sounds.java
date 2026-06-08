package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;

public class Ryuki_FAR_sounds extends AbstractGameEffect {
   private boolean FAR = true;

   public Ryuki_FAR_sounds() {
      this.duration = 3.5F;
      this.startingDuration = 3.5F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.5F && this.FAR) {
         CardCrawlGame.sound.playA("FAR_RYUKI", 0.0F);
         this.FAR = false;
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("ryuki_bgm.wav");
         }
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
