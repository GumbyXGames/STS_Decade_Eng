package dcd_eng.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.Actions.RideBookerCardAction;
import dcd_eng.Characters.Decade;

public class SpecialRideBooker {
   public static Hitbox hb;
   public static Texture img;
   public static Texture img2;
   public static Texture img3;
   public static float cX;
   public static float cY;
   public static float draw_width;
   public static float draw_width2;
   public static float draw_height;
   public static float draw_height2;
   public static boolean haskamenpower = false;
   public static boolean nodecade = false;
   public static boolean decadenextturn = true;
   public static boolean isPlayerTurn = true;
   public static int kamenpowerpoint = 0;
   public static int currentpoint = 0;
   public static int decadepoint = 0;
   public static int jackformpoint = 0;
   public static int axelformpoint = 0;
   public static int kamenpower_decadeVE_point = 1;
   public static String name;
   private static String firstline;
   private static String secenoline;
   public static boolean RideBookerOpen = false;
   private static float TimerTime = 0.0F;
   public static Hitbox[] button = new Hitbox[]{null, null, null, null, null, null, null, null, null, null, null};
   private static boolean ButtonClick = false;
   private static boolean ButtonClickSounds = false;
   private static final Texture[] ButtonImg = new Texture[]{ImageMaster.loadImage("img/char/DCD/RideBooker/all.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/kuuga.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/agito.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/ryuki.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/faiz.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/blade.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/hibiki.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/kabuto.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/deno.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/kiva.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/decade.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/RideBooker.png"), ImageMaster.loadImage("img/char/DCD/RideBooker/RideBooker_Open.png")};
   public static String[] Name = new String[]{"", "", "", "", "", "", "", "", "", "", ""};
   public static String[] FirstLine = new String[]{"", "", "", "", "", "", "", "", "", "", ""};
   private static final UIStrings UIStrings;
   public static final String[] DESCRIPTION;

   public SpecialRideBooker() {
      draw_height = 84.0F;
      draw_width = 88.0F;
      draw_height2 = 1024.0F;
      draw_width2 = 1024.0F;
      hb.move(96.0F * Settings.scale, AbstractDungeon.floorY + 400.0F * Settings.scale);
      button[0].move(965.06F * Settings.scale, 726.5F * Settings.scale);
      button[1].move(1080.72F * Settings.scale, 680.72F * Settings.scale);
      button[2].move(1142.16F * Settings.scale, 587.95F * Settings.scale);
      button[3].move(1140.96F * Settings.scale, 465.06F * Settings.scale);
      button[4].move(1077.1F * Settings.scale, 375.9F * Settings.scale);
      button[5].move(962.65F * Settings.scale, 338.55F * Settings.scale);
      button[6].move(853.01F * Settings.scale, 375.9F * Settings.scale);
      button[7].move(783.13F * Settings.scale, 465.06F * Settings.scale);
      button[8].move(781.92F * Settings.scale, 585.54F * Settings.scale);
      button[9].move(842.16F * Settings.scale, 680.72F * Settings.scale);
      button[10].move(961.44F * Settings.scale, 528.91F * Settings.scale);
   }

   public static void update() {
      if (TimerTime > 0.0F) {
         TimerTime -= Gdx.graphics.getDeltaTime();
      } else {
         TimerTime = 0.0F;
      }

      if (AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
         if (decadenextturn) {
            currentpoint = 0;
         } else {
            currentpoint = decadepoint;
         }

         if (currentpoint > 3) {
            currentpoint = 3;
         }

         haskamenpower = currentpoint < kamenpowerpoint;
      }

      if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY) {
         if (InputHelper.isMouseDown && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && AbstractDungeon.screen.name().equals("NONE")) {
            hb.move((float)InputHelper.mX, (float)InputHelper.mY);
         }

         if (InputHelper.isMouseDown_R && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && (AbstractDungeon.screen.name().equals("NONE") || AbstractDungeon.screen.name().equals("Another2"))) {
            if (AbstractDungeon.player.hasPower("KamenRideDecadePower") && AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
               if (isPlayerTurn) {
                  if (haskamenpower) {
                     RideBookerCardAction.RideBookerCard("Decade");
                     decadenextturn = false;
                     haskamenpower = false;
                  } else {
                     AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "RideBooker使用次数已达上限", true));
                  }
               } else {
                  AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "无法在你的回合外使用RideBooker", true));
               }
            } else if (isPlayerTurn) {
               if (TimerTime == 0.0F) {
                  RideBookerOpen = !RideBookerOpen;
                  if (RideBookerOpen) {
                     img = ButtonImg[12];
                     draw_height = 84.0F;
                     draw_width = 107.0F;
                     AbstractDungeon.isScreenUp = true;
                     AbstractDungeon.screen = CurrentScreen.valueOf("Another2");
                  } else {
                     img = ButtonImg[11];
                     draw_height = 84.0F;
                     draw_width = 88.0F;
                     AbstractDungeon.isScreenUp = false;
                     AbstractDungeon.screen = CurrentScreen.NONE;
                  }

                  System.out.println(hb.cX + "," + hb.cY);
                  TimerTime = 0.5F;
               }
            } else {
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, DESCRIPTION[24], true));
            }
         }

         if (!RideBookerOpen) {
            img = ButtonImg[11];
            draw_height = 84.0F;
            draw_width = 88.0F;
         } else {
            AbstractDungeon.isScreenUp = true;
            int i2 = 0;

            for(int i = 0; i < 11; ++i) {
               if (!InputHelper.isMouseDown && !InputHelper.isMouseDown_R && (float)InputHelper.mX < button[i].x + button[i].width && (float)InputHelper.mX > button[i].x && (float)InputHelper.mY < button[i].y + button[i].height && (float)InputHelper.mY > button[i].y && AbstractDungeon.screen.name().equals("Another2")) {
                  if (i == 10) {
                     switch (Decade.KamenRide) {
                        case "human":
                           secenoline = DESCRIPTION[25];
                           break;
                        case "Decade":
                           secenoline = DESCRIPTION[26];
                           break;
                        case "Ryuki":
                           secenoline = DESCRIPTION[27];
                           break;
                        case "Blade":
                           secenoline = DESCRIPTION[28];
                           break;
                        case "DenO":
                           secenoline = DESCRIPTION[29];
                           break;
                        default:
                           secenoline = "";
                     }

                     TipHelper.renderGenericTip(button[i].x + button[i].width, button[i].y + button[i].height, Name[i], FirstLine[i] + " NL " + secenoline);
                  } else {
                     TipHelper.renderGenericTip(button[i].x + button[i].width, button[i].y + button[i].height, Name[i], FirstLine[i]);
                  }
               }

               if (!InputHelper.isMouseDown_R && (float)InputHelper.mX < button[i].x + button[i].width && (float)InputHelper.mX > button[i].x && (float)InputHelper.mY < button[i].y + button[i].height && (float)InputHelper.mY > button[i].y && AbstractDungeon.screen.name().equals("Another2")) {
                  if (InputHelper.isMouseDown) {
                     img3 = ButtonImg[i];
                     ButtonClick = true;
                     ButtonClickSounds = true;
                  } else {
                     ButtonClick = false;
                     i2 = i;
                  }
               }

               if (ButtonClickSounds && !ButtonClick) {
                  CardCrawlGame.sound.play("UI_CLICK_1");
                  String Rider = Decade.KamenRide;
                  NewRideBookerCardAction.RideBookerCard(Rider, i2);
                  decadenextturn = false;
                  ButtonClickSounds = false;
               }
            }

            if (AbstractDungeon.screen.name().equals("Another2") && TimerTime == 0.0F && (InputHelper.isMouseDown_R || InputHelper.isMouseDown) && !ButtonClick) {
               RideBookerOpen = false;
               img = ButtonImg[11];
               draw_height = 84.0F;
               draw_width = 88.0F;
               AbstractDungeon.isScreenUp = false;
               AbstractDungeon.screen = CurrentScreen.NONE;
            }
         }

         if ((AbstractDungeon.screen.name().equals("NONE") || AbstractDungeon.screen.name().equals("Another2")) && !InputHelper.isMouseDown && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y) {
            if (AbstractDungeon.player.hasPower("KamenRideDecadePower") && AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
               firstline = DESCRIPTION[30];
               secenoline = DESCRIPTION[31];
               TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline + " NL " + secenoline + " NL " + currentpoint + "/" + kamenpowerpoint);
            } else {
               TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline);
            }
         }

         NewRideBookerCardAction.update();
         RideBookerCardAction.update();
      } else {
         img.dispose();
         img2.dispose();
         img3.dispose();
      }

   }

   public static void updatecurrentpoint() {
      ++currentpoint;
   }

   public static void render(SpriteBatch sb) {
      if (RideBookerOpen) {
         sb.setColor(Color.WHITE.cpy());
         sb.draw(img2, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         if (ButtonClick) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(img3, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         }
      }

      sb.setColor(Color.WHITE.cpy());
      sb.draw(img, hb.cX - draw_width / 2.0F, hb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, draw_width, draw_height, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
   }

   static {
      UIStrings = CardCrawlGame.languagePack.getUIString("SpecialRideBooker");
      DESCRIPTION = UIStrings.EXTRA_TEXT;
      hb = new Hitbox(120.0F * Settings.scale, 120.0F * Settings.scale);

      for(int i = 0; i < 10; ++i) {
         button[i] = new Hitbox(98.0F * Settings.scale, 98.0F * Settings.scale);
      }

      button[10] = new Hitbox(269.0F * Settings.scale, 269.0F * Settings.scale);
      img = ButtonImg[11];
      img2 = ImageMaster.loadImage("img/char/DCD/RideBooker/01.png");
      name = DESCRIPTION[0];
      firstline = DESCRIPTION[1];
      secenoline = "";
      Name[0] = DESCRIPTION[2];
      FirstLine[0] = DESCRIPTION[3];
      Name[1] = DESCRIPTION[4];
      FirstLine[1] = DESCRIPTION[5];
      Name[2] = DESCRIPTION[6];
      FirstLine[2] = DESCRIPTION[7];
      Name[3] = DESCRIPTION[8];
      FirstLine[3] = DESCRIPTION[9];
      Name[4] = DESCRIPTION[10];
      FirstLine[4] = DESCRIPTION[11];
      Name[5] = DESCRIPTION[12];
      FirstLine[5] = DESCRIPTION[13];
      Name[6] = DESCRIPTION[14];
      FirstLine[6] = DESCRIPTION[15];
      Name[7] = DESCRIPTION[16];
      FirstLine[7] = DESCRIPTION[17];
      Name[8] = DESCRIPTION[18];
      FirstLine[8] = DESCRIPTION[19];
      Name[9] = DESCRIPTION[20];
      FirstLine[9] = DESCRIPTION[21];
      Name[10] = DESCRIPTION[22];
      FirstLine[10] = DESCRIPTION[23];
   }
}
