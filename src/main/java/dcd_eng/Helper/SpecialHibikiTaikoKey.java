package dcd_eng.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcd_eng.Patches.HibikiTaikoKeyEvent;

public class SpecialHibikiTaikoKey {
   public static float TimerTime = 0.0F;
   public static int img0 = 0;
   public static int img1 = 0;
   public static int img2 = 0;
   public static int img3 = 0;
   public static int img4 = 0;
   public static Texture[] img = new Texture[]{ImageMaster.loadImage("img/1024/orb-dark.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/CHAKA1.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PON1.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/DON1.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PATA1.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/CHAKA2.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PON2.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/DON2.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PATA2.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/CHAKA3.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PON3.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/DON3.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PATA3.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/CHAKA4.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PON4.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/DON4.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PATA4.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/CHAKA5.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PON5.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/DON5.png"), ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/PATA5.png")};
   public static Texture img00;
   public static Texture img11;
   public static Texture img22;
   public static Texture img33;
   public static Texture img44;
   public static float draw_width;
   public static float draw_height;

   public SpecialHibikiTaikoKey() {
      draw_height = 126.0F;
      draw_width = 709.0F;
   }

   public static void update() {
      if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY && AbstractDungeon.screen.name().equals("NONE")) {
         if (TimerTime > 0.0F) {
            TimerTime -= Gdx.graphics.getDeltaTime();

            for(int i = 0; i < HibikiTaikoKeyEvent.TaikoArray.length; ++i) {
               if (!HibikiTaikoKeyEvent.TaikoArray[i].equals("")) {
                  int x = 0;
                  switch (HibikiTaikoKeyEvent.TaikoArray[i]) {
                     case "CHAKA":
                        x = 1;
                        break;
                     case "PON":
                        x = 2;
                        break;
                     case "DON":
                        x = 3;
                        break;
                     case "PATA":
                        x = 4;
                  }

                  switch (i) {
                     case 0:
                        img0 = x;
                        img00 = img[img0];
                        break;
                     case 1:
                        img1 = x + i * 4;
                        img11 = img[img1];
                        break;
                     case 2:
                        img2 = x + i * 4;
                        img22 = img[img2];
                        break;
                     case 3:
                        img3 = x + i * 4;
                        img33 = img[img3];
                        break;
                     case 4:
                        img4 = x + i * 4;
                        img44 = img[img4];
                  }
               }
            }
         } else if (TimerTime < 0.0F) {
            timer();
         }
      }

   }

   public static void timer() {
      if (!HibikiTaikoKeyEvent.Fever && HibikiTaikoKeyEvent.TaikoNumber >= 3) {
         switch (HibikiTaikoKeyEvent.TaikoArray[0] + HibikiTaikoKeyEvent.TaikoArray[1] + HibikiTaikoKeyEvent.TaikoArray[2] + HibikiTaikoKeyEvent.TaikoArray[3] + HibikiTaikoKeyEvent.TaikoArray[4]) {
            case "PONPONPATAPON":
               CardCrawlGame.sound.playA("Attack", 0.0F);
               HibikiTaikoKeyEvent.TaikoAction("Attack");
               taikocount();
               break;
            case "CHAKACHAKAPATAPON":
               CardCrawlGame.sound.playA("Defend", 0.0F);
               HibikiTaikoKeyEvent.TaikoAction("Defend");
               taikocount();
               break;
            case "PATAPONDONCHAKA":
               CardCrawlGame.sound.playA("Relieve", 0.0F);
               HibikiTaikoKeyEvent.TaikoAction("Relieve");
               taikocount();
               break;
            case "PONPONCHAKACHAKA":
               if (EnergyPanel.totalCount > 0) {
                  CardCrawlGame.sound.playA("Accumulate", 0.0F);
                  HibikiTaikoKeyEvent.TaikoAction("Accumulate");
                  taikocount();
               } else {
                  AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "能量不足，无法触发效果", true));
                  HibikiTaikoKeyEvent.FeverOut(false);
               }
               break;
            case "PATAPONPATAPON":
               if (HibikiTaikoKeyEvent.ActionPoint >= 6) {
                  HibikiTaikoKeyEvent.TaikoAction("Suspend");
                  taikocount();
               } else {
                  HibikiTaikoKeyEvent.FeverOut(false);
               }
               break;
            default:
               HibikiTaikoKeyEvent.FeverOut(false);
         }
      } else {
         HibikiTaikoKeyEvent.FeverOut(true);
      }

      HibikiTaikoKeyEvent.TaikoArray[0] = "";
      HibikiTaikoKeyEvent.TaikoArray[1] = "";
      HibikiTaikoKeyEvent.TaikoArray[2] = "";
      HibikiTaikoKeyEvent.TaikoArray[3] = "";
      HibikiTaikoKeyEvent.TaikoArray[4] = "";
      HibikiTaikoKeyEvent.TaikoNumber = -1;
      TimerTime = 0.0F;
      img00 = img[0];
      img11 = img[0];
      img22 = img[0];
      img33 = img[0];
      img44 = img[0];
   }

   private static void taikocount() {
      ++HibikiTaikoKeyEvent.ComboPoint;
      if (HibikiTaikoKeyEvent.ActionPoint > 0) {
         --HibikiTaikoKeyEvent.ActionPoint;
      }

   }

   public static void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());
      sb.draw(img00, SpecialHibikiTaiko.hb.x - 50.0F, SpecialHibikiTaiko.hb.y - 10.0F);
      sb.draw(img11, SpecialHibikiTaiko.hb.x - 50.0F, SpecialHibikiTaiko.hb.y - 10.0F);
      sb.draw(img22, SpecialHibikiTaiko.hb.x - 50.0F, SpecialHibikiTaiko.hb.y - 10.0F);
      sb.draw(img33, SpecialHibikiTaiko.hb.x - 50.0F, SpecialHibikiTaiko.hb.y - 10.0F);
      sb.draw(img44, SpecialHibikiTaiko.hb.x - 50.0F, SpecialHibikiTaiko.hb.y - 10.0F);
   }

   static {
      img00 = img[img0];
      img11 = img[img1];
      img22 = img[img2];
      img33 = img[img3];
      img44 = img[img4];
   }
}
