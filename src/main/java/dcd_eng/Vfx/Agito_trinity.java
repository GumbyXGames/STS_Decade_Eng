package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_trinity extends AbstractGameEffect {
   private boolean start = true;

   public Agito_trinity() {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         this.isDone = true;
         if (AbstractDungeon.player.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(18);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(17);
         }
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(21);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
