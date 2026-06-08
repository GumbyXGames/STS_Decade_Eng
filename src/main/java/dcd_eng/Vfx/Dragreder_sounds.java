package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Dragreder_sounds extends AbstractGameEffect {
   private boolean advent = true;

   public Dragreder_sounds() {
      this.duration = 2.0F;
      this.startingDuration = 2.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.5F && this.advent) {
         this.advent = false;
         CardCrawlGame.sound.playA("dragreder_advent", 0.0F);
      }

      if (this.duration < 0.0F) {
         CardCrawlGame.sound.playA("dragreder_appear", 0.0F);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
