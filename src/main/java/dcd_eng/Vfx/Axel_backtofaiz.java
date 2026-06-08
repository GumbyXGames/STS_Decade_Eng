package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialRideBooker;

public class Axel_backtofaiz extends AbstractGameEffect {
   private boolean start = true;

   public Axel_backtofaiz() {
      this.duration = 2.5F;
      this.startingDuration = 2.5F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         if (Decade.cf != 3) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(34);
         }

         SpecialFaizBox.AxelForm = false;
         SpecialRideBooker.isPlayerTurn = true;
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(41);
         SpecialRideBooker.isPlayerTurn = false;
         this.start = false;
      }

   }

   public void dispose() {
   }
}
