package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kabuto_MaskedToRider extends AbstractGameEffect {
   private boolean start = true;

   public Kabuto_MaskedToRider() {
      this.duration = 1.4F;
      this.startingDuration = 1.4F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(60);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(63);
         CardCrawlGame.sound.playA("formride", 0.0F);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
