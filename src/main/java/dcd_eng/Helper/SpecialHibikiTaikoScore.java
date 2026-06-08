package dcd_eng.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;
import dcd_eng.Patches.HibikiTaikoKeyEvent;

public class SpecialHibikiTaikoScore {
   private static float TimerTime = 0.0F;
   public static Hitbox hb;
   public static Texture img;
   public static Texture img1;
   public static Texture img2;
   public static Texture img3;
   public static Texture img4;
   public static Texture img5;
   public static float cX;
   public static float cY;
   public static float draw_width;
   public static float draw_height;
   public static boolean ishide = true;
   public static int a;
   public static String name;
   private static final String hide;
   private static final String Attack;
   private static final String Attack2;
   private static final String Attack3;
   private static final String Defend;
   private static final String Defend2;
   private static final String Defend3;
   private static final String Defend4;
   private static final String Relieve;
   private static final String Relieve2;
   private static final String Accumulate;
   private static final String Accumulate2;
   private static final String Forward;
   private static final String Back;
   private static final String Fever;
   private static final UIStrings UIStrings;
   public static final String[] DESCRIPTION;

   public SpecialHibikiTaikoScore() {
      draw_height = 19.0F;
      draw_width = 235.0F;
      hb.move(220.0F * Settings.scale, 600.0F * Settings.scale);
   }

   public static void update() {
      if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY && AbstractDungeon.screen.name().equals("NONE")) {
         if (TimerTime > 0.0F) {
            TimerTime -= Gdx.graphics.getDeltaTime();
         } else {
            TimerTime = 0.0F;
         }

         if (InputHelper.isMouseDown_R && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y) {
            hb.move((float)InputHelper.mX, (float)InputHelper.mY);
         }

         if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY) {
            if (InputHelper.isMouseDown && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && AbstractDungeon.player.hasPower("KamenRideHibikiPower") && TimerTime == 0.0F) {
               hidescore();
            }

            if (!InputHelper.isMouseDown && !InputHelper.isMouseDown_R && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y) {
               if (ishide) {
                  TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, hide);
               } else if (HibikiTaikoKeyEvent.Fever) {
                  TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, hide + " NL " + DESCRIPTION[16] + " NL " + Attack2 + (HibikiTaikoKeyEvent.ComboPoint / 8 + 1) + Attack3 + " NL " + DESCRIPTION[17] + " NL " + Defend2 + (HibikiTaikoKeyEvent.ComboPoint / 8 + 4) + Defend3 + (HibikiTaikoKeyEvent.ComboPoint / 8 + 4) / 2 + Defend4 + " NL " + DESCRIPTION[18] + " NL " + Relieve2 + " NL " + DESCRIPTION[19] + " NL " + Accumulate2 + " NL " + DESCRIPTION[20] + " NL " + Forward + " NL " + DESCRIPTION[21] + " NL " + Back + " NL " + DESCRIPTION[22] + " NL " + Fever);
               } else {
                  TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, hide + " NL " + DESCRIPTION[16] + " NL " + Attack + " NL " + DESCRIPTION[17] + " NL " + Defend + " NL " + DESCRIPTION[18] + " NL " + Relieve + " NL " + DESCRIPTION[19] + " NL " + Accumulate);
               }
            }
         }
      }

   }

   private static void hidescore() {
      if (ishide) {
         if (HibikiTaikoKeyEvent.Fever) {
            if (Settings.language != GameLanguage.ZHS && Settings.language != GameLanguage.ZHT) {
               img = img5;
            } else {
               img = img4;
            }
         } else if (Settings.language != GameLanguage.ZHS && Settings.language != GameLanguage.ZHT) {
            img = img3;
         } else {
            img = img2;
         }

         draw_height = 284.0F;
         draw_width = 235.0F;
         ishide = false;
      } else {
         img = img1;
         draw_height = 19.0F;
         draw_width = 235.0F;
         ishide = true;
      }

      TimerTime = 0.5F;
   }

   public static void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());
      sb.draw(img, hb.cX - draw_width / 2.0F, hb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, draw_width / 1.0F, draw_height / 1.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
   }

   static {
      UIStrings = CardCrawlGame.languagePack.getUIString("SpecialHibikiTaikoScore");
      DESCRIPTION = UIStrings.EXTRA_TEXT;
      img1 = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/Score.png");
      img2 = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/UnfeverScore.png");
      img3 = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/UnfeverScore_2.png");
      img4 = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/FeverScore.png");
      img5 = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/FeverScore_2.png");
      img = img1;
      name = DESCRIPTION[0];
      hide = DESCRIPTION[1];
      Attack = DESCRIPTION[2];
      Attack2 = DESCRIPTION[3];
      Attack3 = DESCRIPTION[4];
      Defend = DESCRIPTION[5];
      Defend2 = DESCRIPTION[6];
      Defend3 = DESCRIPTION[7];
      Defend4 = DESCRIPTION[8];
      Relieve = DESCRIPTION[9];
      Relieve2 = DESCRIPTION[10];
      Accumulate = DESCRIPTION[11];
      Accumulate2 = DESCRIPTION[12];
      Forward = DESCRIPTION[13];
      Back = DESCRIPTION[14];
      Fever = DESCRIPTION[15];
      hb = new Hitbox(235.0F * Settings.scale, 284.0F * Settings.scale);
   }
}
