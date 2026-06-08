package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractAnimation;

public class henshin_toHibiki extends AbstractGameEffect {
   private int stage;

   public henshin_toHibiki() {
      String HENSHIN3_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_henshin.atlas";
      String JSON3 = "img/char/DCD_Animation/hibiki/hibiki_henshin.json";
      new AbstractAnimation("HIBIKI_HENSHIN3", HENSHIN3_ATLAS, JSON3, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY - 5.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      this.duration = 2.63F;
      this.startingDuration = 2.63F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         VfxController.Henshin_A = AbstractAnimation.getAnimation("HIBIKI_HENSHIN3");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 2.0F) {
         AbstractAnimation.clear("HIBIKI_HENSHIN3");
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 2), 2));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(49);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("hibiki_OP1.ogg");
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
