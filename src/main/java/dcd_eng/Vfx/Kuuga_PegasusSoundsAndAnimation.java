package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_PegasusSoundsAndAnimation extends AbstractGameEffect {
   private int stage;

   public Kuuga_PegasusSoundsAndAnimation() {
      this.duration = 1.75F;
      this.startingDuration = 1.75F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(108);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.5F && this.stage == 0) {
         CardCrawlGame.sound.playA("pegasus", 0.0F);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         if (Decade.cf != 8) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(4);
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
