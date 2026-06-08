package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.TurnTimer;

public class FAR_SoundTimer extends AbstractGameEffect {
   private String key1;
   private String key2;
   public static boolean isTimer;
   private final String Rider;

   public FAR_SoundTimer(String rider, boolean isTimer) {
      FAR_SoundTimer.isTimer = isTimer;
      switch (rider) {
         case "Decade":
            this.key1 = "decade_OP1.ogg";
            this.key2 = "decade_BGM.ogg";
            this.duration = 55.0F;
            break;
         case "Agito":
            this.key1 = "agito_OP1.ogg";
            this.key2 = "agito_BGM1.ogg";
            this.duration = 72.0F;
      }

      this.Rider = rider;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (isTimer) {
         if (!this.Rider.equals("KamenRide")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new FAR_SoundTimer(this.Rider, false), 0.0F));
         }

         this.isDone = true;
      }

      if (this.duration < 0.0F) {
         TurnTimer.StopBGM(false);
         CardCrawlGame.music.playTempBGM(this.key1);
         isTimer = false;
         this.isDone = true;
      }

   }

   public void render(SpriteBatch spriteBatch) {
   }

   public void dispose() {
   }
}
