package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_flame extends AbstractGameEffect {
   private boolean start = true;

   public Agito_flame() {
      this.duration = 0.75F;
      this.startingDuration = 0.75F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         if (AbstractDungeon.player.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(186);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(185);
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(13);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
