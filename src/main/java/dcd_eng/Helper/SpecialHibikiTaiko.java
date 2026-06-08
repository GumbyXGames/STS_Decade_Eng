package dcd_eng.Helper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;
import dcd_eng.Patches.HibikiTaikoKeyEvent;

public class SpecialHibikiTaiko {
   public static Hitbox hb;
   public static Texture img;
   public static float cX;
   public static float cY;
   public static float draw_width;
   public static float draw_height;
   public static int currentmX;
   public static int currentmY;
   public static int a;
   public static String name;
   public static String firstline;
   public static String secenoline;
   public static String tripleline;
   public static String Fourthline;
   public static String Fifthline;
   private static final UIStrings UIStrings;
   public static final String[] DESCRIPTION;

   public SpecialHibikiTaiko() {
      draw_height = 126.0F;
      draw_width = 709.0F;
      currentmX = 0;
      currentmY = 0;
      hb.move(150.0F * Settings.scale + draw_width / 2.0F, 900.0F * Settings.scale - draw_height / 2.0F);
   }

   public static void update() {
      if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY && !InputHelper.isMouseDown && !InputHelper.isMouseDown_R && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && AbstractDungeon.screen.name().equals("NONE")) {
         TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline + secenoline + tripleline + HibikiTaikoKeyEvent.ComboPoint + Fourthline + HibikiTaikoKeyEvent.ActionPoint + Fifthline);
      }

   }

   public static void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());
      sb.draw(img, hb.cX - draw_width / 2.0F, hb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, draw_width, draw_height, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
      BitmapFont healthInfoFont = FontHelper.healthInfoFont;
      FontHelper.renderFontCentered(sb, healthInfoFont, "AP:" + HibikiTaikoKeyEvent.ActionPoint, hb.cX + draw_width / 4.0F, hb.cY - draw_height / 2.0F - 4.0F, Color.WHITE);
      BitmapFont healthInfoFont2 = FontHelper.healthInfoFont;
      FontHelper.renderFontCentered(sb, healthInfoFont2, "CP:" + HibikiTaikoKeyEvent.ComboPoint, hb.cX + draw_width / 4.0F, hb.cY - draw_height / 2.0F - 24.0F, Color.WHITE);
   }

   static {
      UIStrings = CardCrawlGame.languagePack.getUIString("SpecialHibikiTaiko");
      DESCRIPTION = UIStrings.EXTRA_TEXT;
      img = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taiko/taiko1.png");
      name = DESCRIPTION[0];
      firstline = DESCRIPTION[1];
      secenoline = DESCRIPTION[2];
      tripleline = DESCRIPTION[3];
      Fourthline = DESCRIPTION[4];
      Fifthline = DESCRIPTION[5];
      hb = new Hitbox(709.0F * Settings.scale, 126.0F * Settings.scale);
   }
}
