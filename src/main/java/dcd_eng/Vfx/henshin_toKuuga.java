package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Power.SuperRegenPower;

public class henshin_toKuuga extends AbstractGameEffect {
   private int stage;

   public henshin_toKuuga() {
      this.duration = 2.03F;
      this.startingDuration = 2.03F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         String HENSHIN_ATLAS = "img/char/DCD_Animation/kuuga/kuuga_henshin_effect.atlas";
         String HENSHIN_JSON = "img/char/DCD_Animation/kuuga/kuuga_henshin_effect.json";
         new AbstractAnimation("kuuga_henshin", HENSHIN_ATLAS, HENSHIN_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("kuuga_henshin");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.6F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(147);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.2F && this.stage == 2) {
         AbstractAnimation.clear("kuuga_henshin");
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(85);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
         if (AbstractDungeon.player.hasPower("SuperRegenPower")) {
            if (AbstractDungeon.player.getPower("SuperRegenPower").amount <= 4) {
               int m = 5 - AbstractDungeon.player.getPower("SuperRegenPower").amount;
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SuperRegenPower(AbstractDungeon.player, m), m));
            }
         } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SuperRegenPower(AbstractDungeon.player, 5), 5));
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("kuuga_OP1.ogg");
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
