package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialTaikoEffects;

public class Hibiki_taikoaction extends AbstractGameEffect {
   private boolean Start = true;
   private boolean End = true;

   public Hibiki_taikoaction() {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.2F && this.End) {
         if (Decade.cf != 49 && Decade.cf != 3 && Decade.cf != 54) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(53);
         }

         this.End = false;
      }

      if (this.duration < 0.0F) {
         if (SpecialTaikoEffects.a != 4 && !DCDmod.AnimationTrigger) {
            SpecialTaikoEffects.a = 3;
            SpecialTaikoEffects.update();
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.Start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(52);
         if (!DCDmod.AnimationTrigger) {
            SpecialTaikoEffects.a = 2;
            SpecialTaikoEffects.update();
         }

         this.Start = false;
      }

   }

   public void dispose() {
   }
}
