package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;

public class Kiva_FAR_sounds extends AbstractGameEffect {
   private boolean FAR = true;
   private int stage;
   public static boolean OpIsLoop = false;

   public Kiva_FAR_sounds() {
      this.duration = 4.0F;
      this.startingDuration = 4.0F;
      this.color = Color.WHITE.cpy();
      this.stage = 0;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.5F && this.FAR) {
         CardCrawlGame.sound.playA("FAR_KIVA", 0.0F);
         this.FAR = false;
      }

      if (this.duration < 0.0F && this.stage == 0) {
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.sound.playA("kiva_BGM", 0.0F);
            ++this.stage;
            this.duration += 49.0F;
            OpIsLoop = false;
         } else {
            this.isDone = true;
         }
      }

      if (this.duration < 0.0F && this.stage == 1) {
         if (!DCDmod.BGMTrigger && Decade.KamenRide.equals("Kiva") && !OpIsLoop) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.music.playTempBGM("kiva_OP1.ogg");
            OpIsLoop = true;
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
