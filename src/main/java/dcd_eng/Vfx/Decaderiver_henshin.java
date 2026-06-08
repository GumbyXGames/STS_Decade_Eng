package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class Decaderiver_henshin extends AbstractGameEffect {
   private boolean start = true;
   private int stage;

   public Decaderiver_henshin() {
      this.duration = 5.0F;
      this.startingDuration = 5.0F;
      this.stage = 0;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.04F && this.stage == 0) {
         String DECADE_HENSHIN_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_card.atlas";
         String DECADE_HENSHIN_JSON = "img/char/DCD_Animation/decade/decade_henshin_card.json";
         new AbstractSummonedAnimation("decade_henshin_card", DECADE_HENSHIN_ATLAS, DECADE_HENSHIN_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("decade_henshin_card");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, "card", false);
         ++this.stage;
      }

      if (this.duration < this.startingDuration - 1.87F && this.stage == 1) {
         String DECADE_HENSHIN_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_P.atlas";
         String DECADE_HENSHIN_JSON = "img/char/DCD_Animation/decade/decade_henshin_P.json";
         new AbstractAnimation("decade_henshin_P", DECADE_HENSHIN_ATLAS, DECADE_HENSHIN_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("decade_henshin_P");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "P", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.13F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(144);
         ++this.stage;
      } else if ((double)this.duration < (double)this.startingDuration - 3.09 && this.stage == 3) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(145);
         ++this.stage;
      } else if ((double)this.duration < (double)this.startingDuration - 3.67 && this.stage == 4) {
         String ATLAS = "img/char/DCD_Animation/decade/decade_henshin_effect1.atlas";
         String JSON = "img/char/DCD_Animation/decade/decade_henshin_effect1.json";
         new AbstractAnimation("DECADE_HENSHIN", ATLAS, JSON, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("DECADE_HENSHIN");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(164);
         ++this.stage;
      } else if ((double)this.duration < (double)this.startingDuration - 4.21 && this.stage == 5) {
         AbstractAnimation.clear("DECADE_HENSHIN");
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("decade_henshin_P");
         AbstractSummonedAnimation.clear("decade_henshin_card");
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.sound.playAndLoop("decade_OP1");
         }

         SpecialRideBooker.isPlayerTurn = true;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(2);
         SpecialRideBooker.isPlayerTurn = false;
         this.start = false;
      }

   }

   public void dispose() {
      VfxController.far_effect_S = null;
      VfxController.Henshin_A = null;
   }
}
