package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_defend extends AbstractGameEffect {
   private boolean start = true;

   public Kuuga_defend() {
      this.duration = 0.35F;
      this.startingDuration = 0.35F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (Decade.cf == 114) {
         this.isDone = true;
      } else if (this.duration < this.startingDuration && this.start) {
         if (Decade.cf == 87) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.state.setAnimation(0, "defend", true);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(87);
         }

         this.start = false;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
