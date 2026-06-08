package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.Helper.SpecialRideBooker;

public class RideBooker_AddCard extends AbstractGameEffect {
   private final AbstractCard c;
   AbstractCard tmp;

   public RideBooker_AddCard(AbstractCard card) {
      this.c = card;
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         this.tmp = this.c.makeSameInstanceOf();
         AbstractDungeon.player.limbo.addToBottom(this.tmp);
         this.tmp.current_x = this.c.current_x;
         this.tmp.current_y = this.c.current_y;
         this.tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
         this.tmp.target_y = (float)Settings.HEIGHT / 2.0F;
         this.tmp.flash();
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.2F) {
         this.tmp.target_x = SpecialRideBooker.hb.cX;
         if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
            this.tmp.target_y = SpecialRideBooker.hb.cY;
         } else {
            this.tmp.target_y = SpecialRideBooker.hb.cY - OverlayMenu.HAND_HIDE_Y;
         }
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         AbstractDungeon.player.limbo.removeCard(this.tmp);
         NewRideBookerCardAction.AllRideGroup.addToBottom(this.c);
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
