package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Blade_backtodcd extends AbstractGameEffect {
   private boolean Start = true;
   private boolean End = true;

   public Blade_backtodcd() {
      this.duration = 2.0F;
      this.startingDuration = 2.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.5F && this.End) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(10);
         this.End = false;
      }

      if (this.duration < 0.4F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.Start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(47);
         this.Start = false;
      }

   }

   public void dispose() {
   }
}
