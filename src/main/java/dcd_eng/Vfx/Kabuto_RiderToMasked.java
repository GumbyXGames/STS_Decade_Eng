package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kabuto_RiderToMasked extends AbstractGameEffect {
   private boolean start = true;

   public Kabuto_RiderToMasked() {
      this.duration = 1.2F;
      this.startingDuration = 1.2F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(61);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         CardCrawlGame.sound.playA("formride", 0.0F);
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(62);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
