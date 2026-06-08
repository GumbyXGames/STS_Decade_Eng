package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialDragreder;

public class Ryuki_defend extends AbstractGameEffect {
   private boolean start = true;

   public Ryuki_defend() {
      this.duration = 1.65F;
      this.startingDuration = 1.65F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F && SpecialDragreder.isDefend) {
         this.isDone = true;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(30);
         SpecialDragreder.isDefend = false;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start && !SpecialDragreder.isDefend) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(24);
         SpecialDragreder.isDefend = true;
         this.start = false;
      }

   }

   public void dispose() {
   }
}
