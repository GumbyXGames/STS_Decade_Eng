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

public class FormRide_FAR_SoundsAndAnimation extends AbstractGameEffect {
   private final String rider;
   private float x;
   private float y;
   private int stage;
   private int a;
   private String FAR_P_ATLAS;
   private String FAR_P_JSON;
   private String sounds;
   private String BGM;
   private AbstractAnimation FAR = null;
   private AbstractAnimation FAR_P = null;

   public FormRide_FAR_SoundsAndAnimation(float x, float y, String rider) {
      if (!DCDmod.AnimationTrigger) {
         new AbstractAnimation("FAR", "img/char/DCD_Animation/FAR/FAR0.atlas", "img/char/DCD_Animation/FAR/FAR0.json", 1.0F, x, y + 15.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      }

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
         AbstractDungeon.player.hideHealthBar();
         AbstractDungeon.player.flipHorizontal = false;
         this.FAR = AbstractAnimation.getAnimation("FAR");
         this.FAR.setMovable(false);
         if (this.duration < this.startingDuration && this.stage == 1) {
            AbstractAnimation.changeAnimation(this.FAR, Decade.FAR0);
            this.FAR.state.setAnimation(0, "FAR0", false);
            ++this.stage;
         } else if (this.duration < this.startingDuration - 0.5F && this.stage == 2) {
            AbstractAnimation.changeAnimation(this.FAR, Decade.FAR1);
            this.FAR.state.setAnimation(0, "FAR1", false);
            ++this.stage;
         } else if (this.duration < this.startingDuration - 1.85F && this.stage == 3) {
            new AbstractAnimation(this.rider, this.FAR_P_ATLAS, this.FAR_P_JSON, 1.0F, this.x, this.y + 40.0F, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
            this.FAR_P = AbstractAnimation.getAnimation(this.rider);
            this.FAR_P.setMovable(false);
            this.FAR_P.state.setAnimation(0, "P", false);
            CardCrawlGame.sound.playA(this.sounds, 0.0F);
            if (!DCDmod.BGMTrigger && !this.rider.equals("Kuuga")) {
               CardCrawlGame.music.playTempBGM(this.BGM);
            }

            ++this.stage;
         } else if (this.duration < this.startingDuration - 2.05F && this.stage == 4) {
            AbstractAnimation.changeAnimation(this.FAR, Decade.FAR2);
            this.FAR.state.setAnimation(0, "FAR2", false);
            ++this.stage;
         }

         if (this.duration < 0.0F) {
            AbstractAnimation.clear(this.rider);
            AbstractAnimation.clear("FAR");
            this.dispose();
            this.isDone = true;
         }
      } else if (this.duration < 0.0F) {
         CardCrawlGame.sound.playA(this.sounds, 0.0F);
         if (!DCDmod.BGMTrigger && this.rider.equals("Decade")) {
            CardCrawlGame.sound.playAndLoop("decade_BGM");
         }

         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.stage == 0) {
         ++this.stage;
      }

   }

   public void dispose() {
      this.FAR = null;
      this.FAR_P = null;
   }
}
