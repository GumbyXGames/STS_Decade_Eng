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

public class henshin_toKiva extends AbstractGameEffect {
   private int stage;

   public henshin_toKiva() {
      this.duration = 3.2F;
      this.startingDuration = 3.2F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         if (Decade.cf == 2) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(178);
         }

         String HENSHIN_ATLAS = "img/char/DCD_Animation/kiva/kiva_henshin.atlas";
         String JSON = "img/char/DCD_Animation/kiva/kiva_henshin.json";
         new AbstractAnimation("KIVA_HENSHIN", HENSHIN_ATLAS, JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("KIVA_HENSHIN");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.7F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(176);
         ++this.stage;
      }

      if ((double)this.duration < 0.2 && this.stage == 2) {
         AbstractAnimation.clear("KIVA_HENSHIN");
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("KIVA_HENSHIN");
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("kiva_OP1.ogg");
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
