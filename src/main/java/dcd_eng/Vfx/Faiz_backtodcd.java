package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Faiz_backtodcd extends AbstractGameEffect {
   private boolean faizStart = true;
   private boolean faizEnd = true;

   public Faiz_backtodcd() {
      this.duration = 2.0F;
      this.startingDuration = 2.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.5F && this.faizEnd) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(10);
         this.faizEnd = false;
      }

      if (this.duration < 0.4F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.faizStart) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(37);
         this.faizStart = false;
      }

   }

   public void dispose() {
   }
}
