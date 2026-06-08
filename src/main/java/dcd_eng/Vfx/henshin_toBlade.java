package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractAnimation;

public class henshin_toBlade extends AbstractGameEffect {
   private int stage;

   public henshin_toBlade() {
      String HENSHIN3_ATLAS = "img/char/DCD_Animation/blade/blade_henshin1.atlas";
      String JSON3 = "img/char/DCD_Animation/blade/blade_henshin1.json";
      new AbstractAnimation("BLADE_HENSHIN3", HENSHIN3_ATLAS, JSON3, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY - 5.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      String HENSHIN4_ATLAS = "img/char/DCD_Animation/blade/blade_henshin2.atlas";
      String JSON4 = "img/char/DCD_Animation/blade/blade_henshin2.json";
      new AbstractAnimation("BLADE_HENSHIN4", HENSHIN4_ATLAS, JSON4, 1.0F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY - 5.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      this.duration = 4.08F;
      this.startingDuration = 4.08F;
      this.stage = 0;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         VfxController.Henshin_A = AbstractAnimation.getAnimation("BLADE_HENSHIN3");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.0F && this.stage == 1) {
         VfxController.Henshin_A = AbstractAnimation.getAnimation("BLADE_HENSHIN4");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.4F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(151);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.63F && this.stage == 3) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(45);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("BLADE_HENSHIN3");
         AbstractAnimation.clear("BLADE_HENSHIN4");
         SpecialRideBooker.isPlayerTurn = true;
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("blade_OP1.ogg");
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
