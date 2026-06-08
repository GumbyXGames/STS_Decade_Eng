package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Orb.FaizEdge;
import dcd_eng.Orb.FaizPhone;
import dcd_eng.Orb.FaizPointer;
import dcd_eng.Orb.FaizShot;
import dcd_eng.Patches.AbstractAnimation;

public class henshin_toFaiz extends AbstractGameEffect {
   private int stage;

   public henshin_toFaiz() {
      this.duration = 3.33F;
      this.startingDuration = 3.33F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         String HENSHIN_ATLAS = "img/char/DCD_Animation/faiz/faiz_henshin_effect.atlas";
         String JSON = "img/char/DCD_Animation/faiz/faiz_henshin_effect.json";
         new AbstractAnimation("FAIZ_HENSHIN", HENSHIN_ATLAS, JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("FAIZ_HENSHIN");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 2.2F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(155);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("FAIZ_HENSHIN");
         boolean hasorbs = false;
         AbstractPlayer p = AbstractDungeon.player;
         if (p.maxOrbs == 0) {
            for(int i = 0; i < 4; ++i) {
               p.increaseMaxOrbSlots(1, true);
            }
         }

         for(AbstractOrb o : p.orbs) {
            if (o instanceof FaizPointer || o instanceof FaizPhone || o instanceof FaizShot || o instanceof FaizEdge) {
               hasorbs = true;
               break;
            }
         }

         if (!hasorbs) {
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new FaizPhone()));
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new FaizPointer()));
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new FaizShot()));
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new FaizEdge()));
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(34);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("faiz_OP1.ogg");
         }

         SpecialRideBooker.isPlayerTurn = true;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Henshin_A = null;
   }
}
