package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_dash extends AbstractGameEffect {
   private boolean start = true;

   public Kuuga_dash() {
      this.duration = 0.62F;
      this.startingDuration = 0.62F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(114);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
