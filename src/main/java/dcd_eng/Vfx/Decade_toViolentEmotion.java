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
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Decade_toViolentEmotion extends AbstractGameEffect {
   private int stage;

   public Decade_toViolentEmotion() {
      this.duration = 99.0F;
      this.startingDuration = 1.5F;
      this.stage = 0;
   }

   public void update() {
      if (Decade.cf == 1 && this.stage == 0) {
         this.duration = this.startingDuration;
         ++this.stage;
      }

      if (this.duration == this.startingDuration && this.stage == 1) {
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.sound.playA("BGM", 0.0F);
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(165);
         String HENSHIN_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_effect4.atlas";
         String JSON = "img/char/DCD_Animation/decade/decade_henshin_effect4.json";
         new AbstractAnimation("DECADE_HENSHIN1", HENSHIN_ATLAS, JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("DECADE_HENSHIN1");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "attacked", true);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.75F && this.stage == 2) {
         String HENSHIN_ATLAS = "img/char/DCD_Animation/decade/decade_henshin_effect2.atlas";
         String JSON = "img/char/DCD_Animation/decade/decade_henshin_effect2.json";
         new AbstractAnimation("DECADE_HENSHIN2", HENSHIN_ATLAS, JSON, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("DECADE_HENSHIN2");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(164);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.29F && this.stage == 3) {
         AbstractAnimation.clear("DECADE_HENSHIN2");
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("DECADE_HENSHIN1");
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         TurnTimer.StopBGM(false);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("decade_OP2.ogg");
         }

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
