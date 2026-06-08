package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialTaikoEffects;

public class Hibiki_taikoprepare extends AbstractGameEffect {
   private boolean Start = true;

   public Hibiki_taikoprepare() {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.Start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(51);
         if (!DCDmod.AnimationTrigger) {
            SpecialTaikoEffects.a = 1;
            SpecialTaikoEffects.update();
         }

         this.Start = false;
      }

   }

   public void dispose() {
   }
}
