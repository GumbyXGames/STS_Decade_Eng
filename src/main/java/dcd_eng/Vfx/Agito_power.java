package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_power extends AbstractGameEffect {
   private int stage = 0;

   public Agito_power() {
      this.duration = 1.25F;
      this.startingDuration = 1.25F;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(20);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.6F && this.stage == 1) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_Power_Animation(), 0.0F));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(16);
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
