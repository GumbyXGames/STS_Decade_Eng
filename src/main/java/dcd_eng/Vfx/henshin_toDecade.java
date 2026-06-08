package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractAnimation;

public class henshin_toDecade extends AbstractGameEffect {
   private int stage;

   public henshin_toDecade() {
      this.duration = 2.3F;
      this.startingDuration = 2.3F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(144);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.95F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(145);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.53F && this.stage == 2) {
         String JSON;
         String ATLAS;
         if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
            ATLAS = "img/char/DCD_Animation/decade/decade_henshin_effect2.atlas";
            JSON = "img/char/DCD_Animation/decade/decade_henshin_effect2.json";
            String HENSHIN_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_effect4.atlas";
            String HENSHIN_JSON = "img/char/DCD_Animation/decade/decade_henshin_effect4.json";
            new AbstractAnimation("DECADE_HENSHIN1", HENSHIN_ATLAS, HENSHIN_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
            VfxController.Henshin_A = AbstractAnimation.getAnimation("DECADE_HENSHIN1");
            VfxController.Henshin_A.setMovable(false);
            VfxController.Henshin_A.state.setAnimation(0, "attacked", true);
            SpecialRideBooker.kamenpowerpoint = 1;
         } else {
            ATLAS = "img/char/DCD_Animation/decade/decade_henshin_effect1.atlas";
            JSON = "img/char/DCD_Animation/decade/decade_henshin_effect1.json";
         }

         new AbstractAnimation("DECADE_HENSHIN", ATLAS, JSON, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin1_H = AbstractAnimation.getAnimation("DECADE_HENSHIN");
         VfxController.Henshin1_H.setMovable(false);
         VfxController.Henshin1_H.state.setAnimation(0, "henshin", false);
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(164);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.07F && this.stage == 3) {
         AbstractAnimation.clear("DECADE_HENSHIN");
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("DECADE_HENSHIN1");
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("decade_OP1.ogg");
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
      VfxController.Henshin1_H = null;
   }
}
