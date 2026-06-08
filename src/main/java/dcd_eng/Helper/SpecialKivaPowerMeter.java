package dcd_eng.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbPassiveEffect;
import dcd_eng.Vfx.HealVerticalLineButHorizontalEffect;

public class SpecialKivaPowerMeter {
   private static Texture chainFront;
   private static Texture chainBack;
   private static final Texture pixel;
   private static final Texture pm;
   private static float draw_width;
   private static float draw_height;
   private static float timer;
   private static float timer2;
   private static float particletimer;
   private static int displayPowerMeter;
   public static int powerMeter;
   public static boolean chainBreak1 = false;
   public static boolean chainBreak2 = false;
   public static boolean KivaTrigger = false;
   private static final Hitbox pmhb;
   private static String name;
   private static String description;
   private static final String[] chain = new String[]{"img/char/DCD_Animation/kiva/chain/front1.png", "img/char/DCD_Animation/kiva/chain/front2.png", "img/char/DCD_Animation/kiva/chain/front3.png", "img/char/DCD_Animation/kiva/chain/front4.png", "img/char/DCD_Animation/kiva/chain/front5.png", "img/char/DCD_Animation/kiva/chain/front6.png", "img/char/DCD_Animation/kiva/chain/front7.png", "img/char/DCD_Animation/kiva/chain/front8.png", "img/char/DCD_Animation/kiva/chain/back1.png", "img/char/DCD_Animation/kiva/chain/back2.png", "img/char/DCD_Animation/kiva/chain/back3.png", "img/char/DCD_Animation/kiva/chain/back4.png", "img/char/DCD_Animation/kiva/chain/back5.png", "img/char/DCD_Animation/kiva/chain/back6.png", "img/char/DCD_Animation/kiva/chain/back7.png", "img/char/DCD_Animation/kiva/chain/back8.png", "img/1024/orb-dark.png"};
   public static int F;
   public static int B;
   private static int HpUp;
   private static final UIStrings UIStrings;
   public static final String[] DESCRIPTION;

   public SpecialKivaPowerMeter() {
      powerMeter = 30;
      displayPowerMeter = 30;
      particletimer = 0.0F;
      timer = 0.0F;
      timer2 = 0.0F;
      F = 0;
      B = 8;
      if (AbstractDungeon.player != null) {
         HpUp = AbstractDungeon.player.maxHealth;
      }

      draw_height = 32.0F;
      draw_width = 250.0F;
      pmhb.move(350.4F * Settings.scale, 231.6F * Settings.scale);
      name = DESCRIPTION[0];
      description = DESCRIPTION[1];
   }

   public static void update() {
      if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY && AbstractDungeon.screen.name().equals("NONE")) {
         if (AbstractDungeon.player.maxHealth > HpUp) {
            int up = AbstractDungeon.player.maxHealth - HpUp;
            if (AbstractDungeon.player.hasPower("KivaHungry3Power")) {
               powerMeter += up;
            }

            HpUp += up;
         }

         if (powerMeter > 30) {
            powerMeter = 30;
         }

         if (!InputHelper.isMouseDown && !InputHelper.isMouseDown_R && (float)InputHelper.mX < pmhb.x + pmhb.width && (float)InputHelper.mX > pmhb.x && (float)InputHelper.mY < pmhb.y + pmhb.height && (float)InputHelper.mY > pmhb.y) {
            TipHelper.renderGenericTip(pmhb.x, pmhb.y + 212.5F, name, description);
         }

         if (InputHelper.isMouseDown_R && (float)InputHelper.mX < pmhb.x + pmhb.width && (float)InputHelper.mX > pmhb.x && (float)InputHelper.mY < pmhb.y + pmhb.height && (float)InputHelper.mY > pmhb.y) {
            pmhb.move((float)InputHelper.mX, (float)InputHelper.mY);
         }

         displayPowerMeter = powerMeter;
         if (powerMeter > 0) {
            particletimer -= Gdx.graphics.getDeltaTime();
            if (particletimer < 0.0F) {
               particletimer = 0.05F + (float)(30 - powerMeter) / 5000.0F;
               if (powerMeter < 15) {
                  AbstractDungeon.effectList.add(new HealVerticalLineButHorizontalEffect(pmhb.x, pmhb.y + MathUtils.random(pmhb.height), pmhb.width * 0.1F));
               } else {
                  AbstractGameEffect age = new LightningOrbPassiveEffect(pmhb.x + MathUtils.random(pmhb.width), pmhb.cY);
                  AbstractDungeon.effectList.add(age);
               }
            }
         }

         if (AbstractDungeon.player.hasPower("KivaHungry2Power") || KivaTrigger) {
            int s = AbstractDungeon.player.getPower("KamenRideKivaPower").amount;
            if (s >= 15 && s <= 29 && !chainBreak1) {
               chainBreak1 = true;
               timer2 = 0.05F;
               CardCrawlGame.sound.playA("kiva_chainbreak", 0.0F);
            } else if (s >= 30 && chainBreak1 && !chainBreak2) {
               chainBreak2 = true;
               timer2 = 0.05F;
               CardCrawlGame.sound.playA("kiva_chainbreak", 0.0F);
            } else if (s >= 30 && !chainBreak2) {
               chainBreak1 = true;
               chainBreak2 = true;
               timer2 = 0.05F;
               timer = 0.25F;
               CardCrawlGame.sound.playA("kiva_chainbreak", 0.0F);
            }

            if (KivaTrigger) {
               KivaTrigger = false;
            }
         }

         if (timer2 > 0.0F) {
            timer2 -= Gdx.graphics.getDeltaTime();
         }

         if (timer2 < 0.0F) {
            Timer();
         }

         if (timer > 0.0F) {
            timer -= Gdx.graphics.getDeltaTime();
         }

         if (timer < 0.0F) {
            CardCrawlGame.sound.playA("kiva_chainbreak", 0.0F);
            timer = 0.0F;
         }
      }

   }

   private static void Timer() {
      if (chainBreak1 && F <= 3) {
         ++F;
         ++B;
         chainFront = ImageMaster.loadImage(chain[F]);
         chainBack = ImageMaster.loadImage(chain[B]);
         timer2 += 0.05F;
      } else if (chainBreak2 && F <= 6) {
         ++F;
         ++B;
         chainFront = ImageMaster.loadImage(chain[F]);
         chainBack = ImageMaster.loadImage(chain[B]);
         timer2 += 0.05F;
      } else {
         if (chainBreak1 && chainBreak2 && F == 7) {
            F = 16;
            B = 16;
            chainFront = ImageMaster.loadImage(chain[F]);
            chainBack = ImageMaster.loadImage(chain[B]);
            timer2 = 0.0F;
         }

      }
   }

   public static void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());
      sb.draw(chainBack, pmhb.cX - 212.5F, pmhb.cY - 212.5F, 212.5F, 212.5F, (float)chainBack.getWidth(), (float)chainBack.getHeight(), Settings.scale, Settings.scale, 0.0F, 0, 0, chainBack.getWidth(), chainBack.getHeight(), false, false);
      if (powerMeter > 0) {
         float width2 = pmhb.width;
         float n = width2 * (float)Math.min(30, displayPowerMeter);
         float width = n / 30.0F;
         sb.setColor(CardHelper.getColor(185, 60, 45));
         sb.draw(pixel, pmhb.cX - draw_width / 2.0F, pmhb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, width, draw_height / 1.0F * 0.95F, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
         sb.setColor(CardHelper.getColor(145, 50, 35));
         sb.draw(pixel, pmhb.cX - draw_width / 2.0F, pmhb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, width, draw_height / 1.0F * 0.4F, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
      }

      sb.setColor(Color.WHITE.cpy());
      sb.draw(pm, pmhb.cX - draw_width / 2.0F, pmhb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, draw_width / 1.0F, draw_height / 1.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
      sb.setColor(Color.WHITE.cpy());
      sb.draw(chainFront, pmhb.cX - 212.5F, pmhb.cY - 212.5F, 212.5F, 212.5F, (float)chainFront.getWidth(), (float)chainFront.getHeight(), Settings.scale, Settings.scale, 0.0F, 0, 0, chainFront.getWidth(), chainFront.getHeight(), false, false);
      BitmapFont healthInfoFont = FontHelper.healthInfoFont;
      StringBuilder append = (new StringBuilder()).append(powerMeter).append("/");
      FontHelper.renderFontCentered(sb, healthInfoFont, append.append(30).toString(), pmhb.cX, pmhb.cY - pmhb.height - 4.0F, Color.WHITE);
   }

   static {
      UIStrings = CardCrawlGame.languagePack.getUIString("SpecialKivaPowerMeter");
      DESCRIPTION = UIStrings.EXTRA_TEXT;
      pixel = ImageMaster.loadImage("img/char/DCD_Animation/kiva/PowerMeter/filledpixel.png");
      pm = ImageMaster.loadImage("img/char/DCD_Animation/kiva/PowerMeter/PowerMeter.png");
      chainFront = ImageMaster.loadImage(chain[F]);
      chainBack = ImageMaster.loadImage(chain[B]);
      pmhb = new Hitbox((float)pm.getWidth(), (float)pm.getHeight());
   }
}
