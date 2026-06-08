package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;

public class Faiz_Axel_Timer extends AbstractGameEffect {
   private final float x;
   private final float y;
   private Texture img1;
   private final Texture img2 = ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-190.png");
   private Texture img3;
   private static float TimerTime = 0.1F;
   private static final Texture[] Timer1 = new Texture[]{ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_0-.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_10.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_20.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_30.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_40.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_50.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_60.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_70.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_80.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_90.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-0.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-101.png")};
   private static final Texture[] Timer2 = new Texture[]{ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_-0.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_1.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_2.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_3.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_4.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_5.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_6.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_7.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_8.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_9.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-0.png"), ImageMaster.loadImage("img/char/DCD_Animation/faiz_Axel/timer/Axel_Timer_16810-101.png")};
   private static int n;
   private static int n2;
   private static int stage;

   Faiz_Axel_Timer(float x, float y) {
      this.img1 = Timer2[10];
      this.img3 = Timer1[10];
      this.x = x;
      this.y = y;
      n2 = 9;
      n = 9;
      stage = 0;
      this.duration = 13.05F;
      this.startingDuration = 13.05F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      if (!SpecialFaizBox.BoxOpen) {
         this.duration -= Gdx.graphics.getDeltaTime();
      }

      if (!SpecialFaizBox.AxelForm) {
         this.isDone = true;
         if (Decade.cf != 10 && Decade.cf != 1 && Decade.cf != 3 && Decade.cf != 41 && Decade.cf != 34) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_backtofaiz(), 0.0F));
         }

         SpecialFaizBox.FaizPhone = false;
         SpecialFaizBox.FaizPointer = false;
         SpecialFaizBox.FaizShot = false;
         SpecialFaizBox.FaizEdge = false;
         SpecialFaizBox.FaizPoint = 0;
      }

      if (this.duration < this.startingDuration - 0.35F && !SpecialFaizBox.BoxOpen) {
         if (TimerTime > 0.0F) {
            TimerTime -= Gdx.graphics.getDeltaTime();
         }

         if (TimerTime < 0.0F) {
            this.timer();
         }

         if (stage == 0) {
            CardCrawlGame.sound.playA("axel_timer", 0.0F);
            ++stage;
         }
      }

      if (this.duration < this.startingDuration - 10.55F && stage == 1) {
         CardCrawlGame.sound.playA("axel_timeout", 0.0F);
         SpecialFaizBox.FaizPhone = false;
         SpecialFaizBox.FaizPointer = false;
         SpecialFaizBox.FaizShot = false;
         SpecialFaizBox.FaizEdge = false;
         SpecialFaizBox.FaizPoint = 0;
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_backtofaiz(), 0.0F));
         ++stage;
      }

      if (this.duration < 0.0F) {
         this.dispose();
         this.isDone = true;
      }

   }

   private void timer() {
      if (n2 != 0) {
         TimerTime = 0.1F;
         --n;
         if (n < 0) {
            n = 9;
            --n2;
            switch (n2) {
               case 1:
                  CardCrawlGame.sound.playA("axel_1", 0.0F);
                  break;
               case 2:
                  CardCrawlGame.sound.playA("axel_2", 0.0F);
                  break;
               case 3:
                  CardCrawlGame.sound.playA("axel_3", 0.0F);
                  break;
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
                  CardCrawlGame.sound.playA("axel_timer", 0.0F);
            }
         }

         this.img1 = Timer2[n];
         this.img3 = Timer1[n2];
      } else {
         this.img1 = Timer2[11];
         this.img3 = Timer1[11];
      }

   }

   public void render(SpriteBatch sb) {
      sb.setColor(this.color);
      sb.draw(this.img2, this.x - 100.0F, this.y - 22.0F);
      sb.setColor(this.color);
      sb.draw(this.img1, this.x, this.y);
      sb.draw(this.img3, this.x, this.y);
   }

   public void dispose() {
      this.img1.dispose();
      this.img2.dispose();
      this.img3.dispose();
   }
}
