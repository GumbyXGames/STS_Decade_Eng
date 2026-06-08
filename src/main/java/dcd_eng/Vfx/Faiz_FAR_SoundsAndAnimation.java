package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Faiz_FAR_SoundsAndAnimation extends AbstractGameEffect {
   private float x;
   private float y;
   private boolean FAR0Start = true;
   private boolean FAR1Start = true;
   private boolean FAR2Start = true;
   private boolean FAR_faizStart = true;
   private boolean start = true;

   public Faiz_FAR_SoundsAndAnimation(float x, float y) {
      if (!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
         new AbstractAnimation("FAR", "img/char/DCD_Animation/FAR/FAR0.atlas", "img/char/DCD_Animation/FAR/FAR0.json", 1.0F, x, y + 15.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      }

      this.x = x;
      this.y = y;
      this.duration = 4.425F;
      this.startingDuration = 4.425F;
   }

   public void update() {
      if (!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
         AbstractDungeon.player.hideHealthBar();
         AbstractDungeon.player.flipHorizontal = false;
         VfxController.far_effect3_A = AbstractAnimation.getAnimation("FAR");
         VfxController.far_effect3_A.setMovable(false);
         this.duration -= Gdx.graphics.getDeltaTime();
         if (this.duration < 4.425F && this.FAR0Start) {
            AbstractAnimation.changeAnimation(VfxController.far_effect3_A, Decade.FAR0);
            VfxController.far_effect3_A.state.setAnimation(0, "FAR0", false);
            this.FAR0Start = false;
         }

         if (this.duration < 3.425F && this.FAR1Start) {
            AbstractAnimation.changeAnimation(VfxController.far_effect3_A, Decade.FAR1);
            VfxController.far_effect3_A.state.setAnimation(0, "FAR1", false);
            this.FAR1Start = false;
         }

         if (this.duration < 2.075F && this.FAR_faizStart) {
            String FAR_FAIZ_ATLAS = "img/char/DCD_Animation/faiz/FAR_faiz.atlas";
            String FAR_FAIZ_JSON = "img/char/DCD_Animation/faiz/FAR_faiz.json";
            new AbstractAnimation("faiz", FAR_FAIZ_ATLAS, FAR_FAIZ_JSON, 1.0F, this.x, this.y + 15.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
            VfxController.far_effect2_A = AbstractAnimation.getAnimation("faiz");
            VfxController.far_effect2_A.setMovable(false);
            VfxController.far_effect2_A.state.setAnimation(0, "FAR_faiz", false);
            this.FAR_faizStart = false;
            CardCrawlGame.sound.playA("FAR_FAIZ", 0.0F);
         }

         if (this.duration < 1.875F && this.FAR2Start) {
            AbstractAnimation.changeAnimation(VfxController.far_effect3_A, Decade.FAR2);
            VfxController.far_effect3_A.state.setAnimation(0, "FAR2", false);
            this.FAR2Start = false;
         }

         if (this.duration < 0.092F) {
            AbstractAnimation.clear("faiz");
            AbstractAnimation.clear("FAR");
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(33);
            this.dispose();
            this.isDone = true;
         }
      } else {
         this.duration -= Gdx.graphics.getDeltaTime();
         if (this.duration < 2.075F && this.FAR_faizStart) {
            CardCrawlGame.sound.playA("FAR_FAIZ", 0.0F);
            this.FAR_faizStart = false;
         }

         if (this.duration < 0.092F) {
            this.dispose();
            this.isDone = true;
         }
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(36);
         this.start = false;
      }

   }

   public void dispose() {
      VfxController.far_effect2_A = null;
      VfxController.far_effect3_A = null;
   }
}
