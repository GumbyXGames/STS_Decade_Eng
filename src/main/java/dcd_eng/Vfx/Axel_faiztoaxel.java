package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialRideBooker;

public class Axel_faiztoaxel extends AbstractGameEffect {
   private boolean Timer = true;
   private boolean start = true;

   public Axel_faiztoaxel() {
      this.duration = 5.7F;
      this.startingDuration = 5.7F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.35F && this.Timer) {
         SpecialFaizBox.AxelForm = true;
         SpecialFaizBox.FaizPhone = false;
         SpecialFaizBox.FaizPointer = true;
         SpecialFaizBox.FaizShot = true;
         SpecialFaizBox.FaizEdge = true;
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_Axel_Timer(AbstractDungeon.player.drawX - 200.0F, AbstractDungeon.player.drawY + 250.0F), 0.0F));
         this.Timer = false;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(40);
         SpecialRideBooker.isPlayerTurn = true;
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(39);
         SpecialRideBooker.isPlayerTurn = false;
         this.start = false;
      }

   }

   public void dispose() {
   }
}
