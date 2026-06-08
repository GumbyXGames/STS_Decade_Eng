package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class FAR_SoundsAndAnimation extends AbstractGameEffect {
   private final String rider;
   private final float x;
   private final float y;
   private int stage;
   private int a;
   private String FAR_P_ATLAS;
   private String FAR_P_JSON;
   private String sounds;
   private String BGM;
   private final boolean HpBar;

   public FAR_SoundsAndAnimation(float x, float y, String rider, boolean HideHpBar) {
      if (!DCDmod.AnimationTrigger) {
         new AbstractAnimation("FAR", "img/char/DCD_Animation/FAR/FAR0.atlas", "img/char/DCD_Animation/FAR/FAR0.json", 1.0F, x, y + 15.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      }

      this.HpBar = HideHpBar;
      this.x = x;
      this.y = y;
      this.duration = 3.88F;
      this.startingDuration = 3.88F;
      this.stage = 0;
      this.rider = rider;
      switch (rider) {
         case "Kuuga":
            this.FAR_P_ATLAS = "img/char/DCD_Animation/FAR/Kuuga_FAR_P.atlas";
            this.FAR_P_JSON = "img/char/DCD_Animation/FAR/Kuuga_FAR_P.json";
            this.a = 105;
            this.sounds = "FAR_KUUGA";
            break;
         case "Agito":
            this.FAR_P_ATLAS = "img/char/DCD_Animation/FAR/Agito_FAR_P.atlas";
            this.FAR_P_JSON = "img/char/DCD_Animation/FAR/Agito_FAR_P.json";
            this.a = 148;
            this.sounds = "FAR_AGITO";
            this.BGM = "agito_BGM1.ogg";
            break;
         case "Decade":
            this.FAR_P_ATLAS = "img/char/DCD_Animation/FAR/Decade_FAR_P.atlas";
            this.FAR_P_JSON = "img/char/DCD_Animation/FAR/Decade_FAR_P.json";
            this.a = 75;
            this.sounds = "FAR_DCD";
            this.BGM = "decade_BGM.ogg";
      }

   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (!DCDmod.AnimationTrigger) {
         if (this.HpBar) {
            AbstractDungeon.player.hideHealthBar();
            AbstractDungeon.player.flipHorizontal = false;
         }

         AbstractAnimation FAR = AbstractAnimation.getAnimation("FAR");
         FAR.setMovable(false);
         if (this.duration < this.startingDuration && this.stage == 1) {
            AbstractAnimation.changeAnimation(FAR, Decade.FAR0);
            FAR.state.setAnimation(0, "FAR0", false);
            ++this.stage;
         } else if (this.duration < this.startingDuration - 0.5F && this.stage == 2) {
            AbstractAnimation.changeAnimation(FAR, Decade.FAR1);
            FAR.state.setAnimation(0, "FAR1", false);
            ++this.stage;
         } else if (this.duration < this.startingDuration - 1.85F && this.stage == 3) {
            new AbstractAnimation(this.rider, this.FAR_P_ATLAS, this.FAR_P_JSON, 1.0F, this.x, this.y + 40.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
            AbstractAnimation FAR_P = AbstractAnimation.getAnimation(this.rider);
            FAR_P.setMovable(false);
            FAR_P.state.setAnimation(0, "P", false);
            CardCrawlGame.sound.playA(this.sounds, 0.0F);
            if (!DCDmod.BGMTrigger && !this.rider.equals("Kuuga")) {
               CardCrawlGame.music.playTempBGM(this.BGM);
               AbstractDungeon.actionManager.addToTop(new VFXAction(new FAR_SoundTimer(this.rider, true), 0.0F));
            }

            ++this.stage;
         } else if (this.duration < this.startingDuration - 2.05F && this.stage == 4) {
            AbstractAnimation.changeAnimation(FAR, Decade.FAR2);
            FAR.state.setAnimation(0, "FAR2", false);
            ++this.stage;
         }

         if (this.duration < 0.0F) {
            AbstractAnimation.clear(this.rider);
            AbstractAnimation.clear("FAR");
            this.isDone = true;
         }
      } else if (this.duration < 0.0F) {
         this.isDone = true;
         CardCrawlGame.sound.playA(this.sounds, 0.0F);
         if (!DCDmod.BGMTrigger && !this.rider.equals("Kuuga")) {
            CardCrawlGame.music.playTempBGM(this.BGM);
            AbstractDungeon.actionManager.addToTop(new VFXAction(new FAR_SoundTimer(this.rider, true), 0.0F));
         }
      }

   }

   public void render(SpriteBatch sb) {
      if (this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(this.a);
         ++this.stage;
      }

   }

   public void dispose() {
   }
}
