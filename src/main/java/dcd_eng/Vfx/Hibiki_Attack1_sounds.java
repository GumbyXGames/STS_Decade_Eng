package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Hibiki_Attack1_sounds extends AbstractGameEffect {
   public Hibiki_Attack1_sounds() {
      this.duration = 1.5F;
      this.startingDuration = 1.5F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         CardCrawlGame.sound.playA("hibiki_attack1", 0.0F);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
