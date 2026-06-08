package dcd_eng.Helper;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.CurScreen;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcd_eng.Actions.FaizBoxScreenAction;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Special.CrimsonSmash;
import dcd_eng.Card.Special.PunchingUnit;
import dcd_eng.Card.Special.SparkleCut;
import dcd_eng.Vfx.Axel_attack;
import dcd_eng.Vfx.Faiz_gunattack2;

public class SpecialFaizBox {
   public static Hitbox hb;
   public static Texture img;
   public static Texture img2;
   public static Texture FE_H;
   public static Texture FE_D;
   public static Texture FE_DH;
   public static Texture FP_H;
   public static Texture FP_D;
   public static Texture FP_DH;
   public static Texture FL_H;
   public static Texture FL_D;
   public static Texture FL_DH;
   public static Texture FS_H;
   public static Texture FS_D;
   public static Texture FS_DH;
   public static float cX;
   public static float cY;
   public static float draw_width;
   public static float draw_width2;
   public static float draw_height;
   public static float draw_height2;
   public static int currentmX;
   public static int currentmY;
   public static boolean BoxOpen = false;
   public static int a;
   public static String name;
   public static String firstline;
   public static String secondline;
   public static String tripleline;
   public static String FEname;
   public static String FEfirstline;
   public static String FEsecondline;
   public static String FEtripleline;
   public static String FPname;
   public static String FPfirstline;
   public static String FPsecondline;
   public static String FPtripleline;
   public static String FLname;
   public static String FLfirstline;
   public static String FLsecondline;
   public static String FLtripleline;
   public static String FSname;
   public static String FSfirstline;
   public static String FSsecondline;
   public static String FStripleline;
   public static int ShotPoint;
   public static int PointerPoint;
   public static int EdgePoint;
   private static float TimerTime;
   private static float TimerTime2 = 0.0F;
   private static final Color blackScreenColor;
   private static final Hitbox Box_FE;
   private static final Hitbox Box_FP;
   private static final Hitbox Box_FL;
   private static final Hitbox Box_FS;
   public static boolean FaizPhone = true;
   public static boolean FaizPointer = true;
   public static boolean FaizShot = true;
   public static boolean FaizEdge = true;
   public static boolean AxelForm = false;
   public static boolean FP = false;
   public static boolean FL = false;
   public static boolean FS = false;
   public static boolean FE = false;
   public static int FaizPoint;
   private static AbstractCard c;
   private static final UIStrings UIStrings;
   public static final String[] DESCRIPTION;

   public SpecialFaizBox() {
      draw_height = 189.0F;
      draw_width = 189.0F;
      draw_height2 = 1024.0F;
      draw_width2 = 1024.0F;
      currentmX = 0;
      currentmY = 0;
      hb.move(201.2F * Settings.scale, 360.24F * Settings.scale);
      Box_FE.move(990.36F * Settings.scale, 762.65F * Settings.scale);
      Box_FP.move(773.49F * Settings.scale, 372.28F * Settings.scale);
      Box_FL.move(1061.44F * Settings.scale, 457.83F * Settings.scale);
      Box_FS.move(1073.49F * Settings.scale, 318.07F * Settings.scale);
   }

   public static void update() {
      if (FaizPoint > 10) {
         FaizPoint = 10;
      }

      if (CardCrawlGame.mainMenuScreen.screen != CurScreen.CARD_LIBRARY) {
         if (TimerTime > 0.0F) {
            TimerTime -= Gdx.graphics.getDeltaTime();
         } else {
            TimerTime = 0.0F;
         }

         if (TimerTime2 > 0.0F) {
            TimerTime2 -= Gdx.graphics.getDeltaTime();
         } else {
            TimerTime2 = 0.0F;
         }

         if (InputHelper.isMouseDown && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && AbstractDungeon.screen.name().equals("NONE")) {
            hb.move((float)InputHelper.mX, (float)InputHelper.mY);
         }

         if (InputHelper.isMouseDown_R && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && AbstractDungeon.screen.name().equals("NONE") && TimerTime == 0.0F && FaizBoxScreenAction.FaizGear && SpecialRideBooker.isPlayerTurn) {
            if (AxelForm) {
               if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                  BoxOpen = !BoxOpen;
                  TimerTime = 0.5F;
                  FaizBoxScreenAction.EnterButton();
               } else {
                  AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, DESCRIPTION[20], true));
               }
            }

            if (!AxelForm) {
               System.out.println(hb.cX + "," + hb.cY);
               BoxOpen = !BoxOpen;
               TimerTime = 0.5F;
               FaizBoxScreenAction.EnterButton();
            }
         }

         if (!InputHelper.isMouseDown && !InputHelper.isMouseDown_R && (float)InputHelper.mX < hb.x + hb.width && (float)InputHelper.mX > hb.x && (float)InputHelper.mY < hb.y + hb.height && (float)InputHelper.mY > hb.y && AbstractDungeon.screen.name().equals("NONE")) {
            TipHelper.renderGenericTip(hb.x + hb.width, hb.y + hb.height, name, firstline + secondline + tripleline + FaizPoint + "/10");
         }

         if (!InputHelper.isMouseDown_R && (float)InputHelper.mX < Box_FE.x + Box_FE.width && (float)InputHelper.mX > Box_FE.x && (float)InputHelper.mY < Box_FE.y + Box_FE.height && (float)InputHelper.mY > Box_FE.y && AbstractDungeon.screen.name().equals("Another")) {
            if (InputHelper.isMouseDown && TimerTime2 == 0.0F) {
               EnterButton("FE");
            } else {
               TimerTime = 0.1F;
               FE = true;
               if (FaizEdge) {
                  TipHelper.renderGenericTip(Box_FE.x + Box_FE.width, Box_FE.y + Box_FE.height, FEname, FEfirstline);
               } else {
                  TipHelper.renderGenericTip(Box_FE.x + Box_FE.width, Box_FE.y + Box_FE.height, FEname, FEsecondline);
               }
            }
         } else {
            FE = false;
         }

         if (!InputHelper.isMouseDown_R && (float)InputHelper.mX < Box_FP.x + Box_FP.width && (float)InputHelper.mX > Box_FP.x && (float)InputHelper.mY < Box_FP.y + Box_FP.height && (float)InputHelper.mY > Box_FP.y && AbstractDungeon.screen.name().equals("Another")) {
            if (InputHelper.isMouseDown && TimerTime2 == 0.0F && !AxelForm) {
               EnterButton("FP");
            } else {
               TimerTime = 0.1F;
               FP = true;
               if (FaizPhone) {
                  TipHelper.renderGenericTip(Box_FP.x + Box_FP.width, Box_FP.y + Box_FP.height, FPname, FPfirstline);
               } else if (AxelForm) {
                  TipHelper.renderGenericTip(Box_FP.x + Box_FP.width, Box_FP.y + Box_FP.height, FPname, FPtripleline);
               } else {
                  TipHelper.renderGenericTip(Box_FP.x + Box_FP.width, Box_FP.y + Box_FP.height, FPname, FPsecondline);
               }
            }
         } else {
            FP = false;
         }

         if (!InputHelper.isMouseDown_R && (float)InputHelper.mX < Box_FL.x + Box_FL.width && (float)InputHelper.mX > Box_FL.x && (float)InputHelper.mY < Box_FL.y + Box_FL.height && (float)InputHelper.mY > Box_FL.y && AbstractDungeon.screen.name().equals("Another")) {
            if (InputHelper.isMouseDown && TimerTime2 == 0.0F) {
               EnterButton("FL");
            } else {
               TimerTime = 0.1F;
               FL = true;
               if (FaizPointer) {
                  TipHelper.renderGenericTip(Box_FL.x + Box_FL.width, Box_FL.y + Box_FL.height, FLname, FLfirstline);
               } else {
                  TipHelper.renderGenericTip(Box_FL.x + Box_FL.width, Box_FL.y + Box_FL.height, FLname, FLsecondline);
               }
            }
         } else {
            FL = false;
         }

         if (!InputHelper.isMouseDown_R && (float)InputHelper.mX < Box_FS.x + Box_FS.width && (float)InputHelper.mX > Box_FS.x && (float)InputHelper.mY < Box_FS.y + Box_FS.height && (float)InputHelper.mY > Box_FS.y && AbstractDungeon.screen.name().equals("Another")) {
            if (InputHelper.isMouseDown && TimerTime2 == 0.0F) {
               EnterButton("FS");
            } else {
               TimerTime = 0.1F;
               FS = true;
               if (FaizShot) {
                  TipHelper.renderGenericTip(Box_FS.x + Box_FS.width, Box_FS.y + Box_FS.height, FSname, FSfirstline);
               } else {
                  TipHelper.renderGenericTip(Box_FS.x + Box_FS.width, Box_FS.y + Box_FS.height, FSname, FSsecondline);
               }
            }
         } else {
            FS = false;
         }
      }

      FaizBoxScreenAction.update();
      updateBlackScreen();
   }

   private static void updateBlackScreen() {
      if (blackScreenColor.a != 0.85F) {
         if (0.85F > blackScreenColor.a) {
            Color var10000 = blackScreenColor;
            var10000.a += Gdx.graphics.getDeltaTime() * 2.0F;
            if (blackScreenColor.a > 0.85F) {
               blackScreenColor.a = 0.85F;
            }
         } else {
            Color var10000 = blackScreenColor;
            var10000.a -= Gdx.graphics.getDeltaTime() * 2.0F;
            if (blackScreenColor.a < 0.85F) {
               blackScreenColor.a = 0.85F;
            }
         }
      }

   }

   public static void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());
      sb.draw(img, hb.cX - draw_width / 2.0F, hb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, draw_width, draw_height, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
      if (BoxOpen) {
         sb.setColor(blackScreenColor);
         sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
         sb.setColor(Color.WHITE.cpy());
         sb.draw(img2, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         BitmapFont healthInfoFont = FontHelper.healthInfoFont;
         StringBuilder append = (new StringBuilder()).append(FaizPoint).append("/");
         FontHelper.renderFontCentered(sb, healthInfoFont, append.append(10).toString(), hb.cX, hb.cY, Color.WHITE);
         if (!FaizEdge) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(FE_D, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         }

         if (!FaizPhone || AxelForm) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(FP_D, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         }

         if (!FaizPointer) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(FL_D, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         }

         if (!FaizShot) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(FS_D, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
         }

         if (TimerTime > 0.0F) {
            if (FaizEdge && FE) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FE_H, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            } else if (!FaizEdge && FE) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FE_DH, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            }

            if (FaizPhone && FP) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FP_H, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            } else if (!FaizPhone && FP) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FP_DH, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            }

            if (FaizPointer && FL) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FL_H, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            } else if (!FaizPointer && FL) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FL_DH, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            }

            if (FaizShot && FS) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FS_H, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            } else if (!FaizShot && FS) {
               sb.setColor(Color.WHITE.cpy());
               sb.draw(FS_DH, (float)Settings.WIDTH / 2.0F - draw_width2 / 2.0F, (float)Settings.HEIGHT / 2.0F - draw_height2 / 2.0F, draw_width2 / 2.0F, draw_height2 / 2.0F, draw_width2, draw_height2, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width2, (int)draw_height2, false, false);
            }
         }
      } else {
         BitmapFont healthInfoFont = FontHelper.healthInfoFont;
         StringBuilder append = (new StringBuilder()).append(FaizPoint).append("/");
         FontHelper.renderFontCentered(sb, healthInfoFont, append.append(10).toString(), hb.cX, hb.cY - 75.0F, Color.WHITE);
      }

   }

   public static void EnterButton(String Gear) {
      boolean Error = false;
      switch (Gear) {
         case "FE":
            if (AxelForm) {
               c = new SparkleCut();
               ++EdgePoint;
               if (EdgePoint > 1) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0.0F));
                  AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
               } else {
                  c.freeToPlayOnce = true;
                  c.purgeOnUse = true;
                  AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true), c.energyOnUse));
               }
            } else if (!FaizEdge) {
               if (FaizPoint >= 3) {
                  FaizPoint -= 3;
                  FaizEdge = true;
                  CardCrawlGame.sound.playA("faiz_button", 0.0F);
                  TimerTime2 = 0.3F;
               } else {
                  Error = true;
               }
            } else if (TimerTime2 == 0.0F) {
               c = new SparkleCut();
               FaizEdge = false;
            }
            break;
         case "FP":
            if (!FaizPhone) {
               if (FaizPoint >= 2) {
                  FaizPoint -= 2;
                  FaizPhone = true;
                  CardCrawlGame.sound.playA("faiz_button", 0.0F);
                  TimerTime2 = 0.3F;
               } else {
                  Error = true;
               }
            } else if (TimerTime2 == 0.0F) {
               BoxOpen = !BoxOpen;
               FaizBoxScreenAction.FaizGear = true;
               FaizBoxScreenAction.selectcard = false;
               AbstractDungeon.isScreenUp = false;
               AbstractDungeon.screen = CurrentScreen.NONE;
               AbstractDungeon.overlayMenu.cancelButton.hide();
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_gunattack2(), 0.0F));
               FaizPhone = false;
            }
            break;
         case "FL":
            if (AxelForm) {
               c = new CrimsonSmash();
               ++PointerPoint;
               if (PointerPoint > 1) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0.0F));
                  AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
               } else {
                  c.freeToPlayOnce = true;
                  c.purgeOnUse = true;
                  AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true), c.energyOnUse));
               }
            } else if (!FaizPointer) {
               if (FaizPoint >= 3) {
                  FaizPoint -= 3;
                  FaizPointer = true;
                  CardCrawlGame.sound.playA("faiz_button", 0.0F);
                  TimerTime2 = 0.3F;
               } else {
                  Error = true;
               }
            } else if (TimerTime2 == 0.0F) {
               c = new CrimsonSmash();
               FaizPointer = false;
            }
            break;
         case "FS":
            if (AxelForm) {
               c = new PunchingUnit();
               ++ShotPoint;
               if (ShotPoint > 1) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new Axel_attack(), 0.0F));
                  AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
               } else {
                  c.freeToPlayOnce = true;
                  c.purgeOnUse = true;
                  AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true), c.energyOnUse));
               }
            } else if (!FaizShot) {
               if (FaizPoint >= 3) {
                  FaizPoint -= 3;
                  FaizShot = true;
                  CardCrawlGame.sound.playA("faiz_button", 0.0F);
                  TimerTime2 = 0.3F;
               } else {
                  Error = true;
               }
            } else if (TimerTime2 == 0.0F) {
               c = new PunchingUnit();
               FaizShot = false;
            }
      }

      if (AxelForm) {
         BoxOpen = !BoxOpen;
         FaizBoxScreenAction.FaizGear = true;
         FaizBoxScreenAction.selectcard = false;
         AbstractDungeon.isScreenUp = false;
         AbstractDungeon.screen = CurrentScreen.NONE;
         AbstractDungeon.overlayMenu.cancelButton.hide();
      } else if (Error || c != null) {
         if (Error) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, " #rError! ", true));
         } else {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
            c = null;
         }

         BoxOpen = !BoxOpen;
         FaizBoxScreenAction.FaizGear = true;
         FaizBoxScreenAction.selectcard = false;
         AbstractDungeon.isScreenUp = false;
         AbstractDungeon.screen = CurrentScreen.NONE;
         AbstractDungeon.overlayMenu.cancelButton.hide();
      }

   }

   static {
      UIStrings = CardCrawlGame.languagePack.getUIString("SpecialFaizBox");
      DESCRIPTION = UIStrings.EXTRA_TEXT;
      name = DESCRIPTION[0];
      firstline = DESCRIPTION[1];
      secondline = DESCRIPTION[2];
      tripleline = DESCRIPTION[3];
      FEname = DESCRIPTION[4];
      FEfirstline = DESCRIPTION[5];
      FEsecondline = DESCRIPTION[6];
      FEtripleline = DESCRIPTION[7];
      FPname = DESCRIPTION[8];
      FPfirstline = DESCRIPTION[9];
      FPsecondline = DESCRIPTION[10];
      FPtripleline = DESCRIPTION[11];
      FLname = DESCRIPTION[12];
      FLfirstline = DESCRIPTION[13];
      FLsecondline = DESCRIPTION[14];
      FLtripleline = DESCRIPTION[15];
      FSname = DESCRIPTION[16];
      FSfirstline = DESCRIPTION[17];
      FSsecondline = DESCRIPTION[18];
      FStripleline = DESCRIPTION[19];
      img = ImageMaster.loadImage("img/char/DCD/FaizBox/FaizBox.png");
      img2 = ImageMaster.loadImage("img/char/DCD/FaizBox/FaizBox_Open.png");
      FE_H = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FE_hold.png");
      FE_D = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FE_d.png");
      FE_DH = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FE_d_hold.png");
      FP_H = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FP_hold.png");
      FP_D = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FP_d.png");
      FP_DH = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FP_d_hold.png");
      FL_H = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FL_hold.png");
      FL_D = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FL_d.png");
      FL_DH = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FL_d_hold.png");
      FS_H = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FS_hold.png");
      FS_D = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FS_d.png");
      FS_DH = ImageMaster.loadImage("img/char/DCD/FaizBox/Box_FS_d_hold.png");
      Box_FE = new Hitbox(518.0F * Settings.scale, 335.0F * Settings.scale);
      Box_FP = new Hitbox(133.0F * Settings.scale, 174.0F * Settings.scale);
      Box_FL = new Hitbox(291.0F * Settings.scale, 57.0F * Settings.scale);
      Box_FS = new Hitbox(249.0F * Settings.scale, 124.0F * Settings.scale);
      blackScreenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
      hb = new Hitbox(189.0F * Settings.scale, 189.0F * Settings.scale);
   }
}
