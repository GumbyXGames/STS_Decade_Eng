package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_TitanDefend extends AbstractGameEffect {
   private boolean start = true;

   public Kuuga_TitanDefend() {
      this.duration = 0.76F;
      this.startingDuration = 0.76F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (Decade.cf == 114) {
         this.isDone = true;
      } else if (this.duration < this.startingDuration && this.start) {
         if (Decade.cf == 115) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.state.setAnimation(0, "defend", true);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(115);
         }

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
