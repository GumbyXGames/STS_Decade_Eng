package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_power_t extends AbstractGameEffect {
   private final String form;
   private boolean start = true;

   public Agito_power_t(String form) {
      this.duration = 0.6F;
      this.startingDuration = 0.6F;
      this.form = form;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         this.isDone = true;
         int a = 0;
         switch (this.form) {
            case "t":
               a = 18;
               break;
            case "s":
               a = 182;
               break;
            case "f":
               a = 186;
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(a);
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         int a = 0;
         switch (this.form) {
            case "t":
               a = 19;
               break;
            case "s":
               a = 183;
               break;
            case "f":
               a = 187;
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(a);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
