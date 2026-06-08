package dcd_eng.Patches;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RelieveAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TaikoAttackAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialHibikiTaikoKey;
import dcd_eng.Helper.SpecialHibikiTaikoScore;
import dcd_eng.Helper.SpecialTaikoEffects;
import dcd_eng.Power.FeverPower;
import dcd_eng.Power.HibikiKurenaiPower;
import dcd_eng.Power.HibikiKurenaiSpecialPower;
import dcd_eng.Vfx.Hibiki_Kurenai_henshin_SoundsAndAnimation;
import dcd_eng.Vfx.Hibiki_taikoL1;
import dcd_eng.Vfx.Hibiki_taikoL2;
import dcd_eng.Vfx.Hibiki_taikoR1;
import dcd_eng.Vfx.Hibiki_taikoR2;
import dcd_eng.Vfx.Hibiki_taikoaction;
import dcd_eng.Vfx.Hibiki_taikoprepare;

public class HibikiTaikoKeyEvent {
   private static InputAction[] TaikoButtonActions = new InputAction[5];
   public static String[] TaikoArray = new String[]{"", "", "", "", ""};
   public static int TaikoNumber = -1;
   public static int ComboPoint = 0;
   public static int ActionPoint = 1;
   public static boolean Fever = false;
   public static boolean TaikoTrigger = false;

   public HibikiTaikoKeyEvent() {
      loadKeySettings();
   }

   public static void loadKeySettings() {
      for(int i = 0; i < TaikoButtonActions.length; ++i) {
         TaikoButtonActions[i] = new InputAction(DCDmod.TaikoKeys[i]);
      }

   }

   public static void update() {
      for(int i = 0; i < TaikoButtonActions.length; ++i) {
         if (TaikoButtonActions[i].isJustPressed()) {
            if (ActionPoint > 0) {
               switch (i) {
                  case 0:
                     CardCrawlGame.sound.playA("CHAKA", 0.0F);
                     AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoL1(), 0.0F));
                     KeyCount();
                     TaikoArray[TaikoNumber] = "CHAKA";
                     break;
                  case 1:
                     CardCrawlGame.sound.playA("PON", 0.0F);
                     AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoL2(), 0.0F));
                     KeyCount();
                     TaikoArray[TaikoNumber] = "PON";
                     break;
                  case 2:
                     CardCrawlGame.sound.playA("DON", 0.0F);
                     AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoR2(), 0.0F));
                     KeyCount();
                     TaikoArray[TaikoNumber] = "DON";
                     break;
                  case 3:
                     CardCrawlGame.sound.playA("PATA", 0.0F);
                     AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoR1(), 0.0F));
                     KeyCount();
                     TaikoArray[TaikoNumber] = "PATA";
                     break;
                  case 4:
                     TaikoSpace();
               }
            } else {
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "行动点数不足，无法敲击音色", true));
            }
         }
      }

      if (ComboPoint >= 10 && !Fever) {
         CardCrawlGame.sound.playA("Fever", 0.0F);
         Fever = true;
         SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/FeverScore.png");
         SpecialHibikiTaikoScore.draw_height = 284.0F;
         SpecialHibikiTaikoScore.draw_width = 235.0F;
         SpecialHibikiTaikoScore.ishide = false;
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoprepare(), 1.0F));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FeverPower(AbstractDungeon.player), 1));
      }

   }

   public static void FeverOut(boolean iskey) {
      if (iskey) {
         if (Fever) {
            ComboPoint -= ComboPoint / 2;
         } else {
            --ComboPoint;
            if (ComboPoint < 0) {
               ComboPoint = 0;
            }
         }
      } else {
         --ComboPoint;
         if (ComboPoint < 0) {
            ComboPoint = 0;
         }
      }

      if (ComboPoint < 10 && Fever) {
         Fever = false;
         SpecialHibikiTaikoScore.img = ImageMaster.loadImage("img/char/DCD_Animation/hibiki/taikoscore/UnfeverScore.png");
         SpecialHibikiTaikoScore.draw_height = 284.0F;
         SpecialHibikiTaikoScore.draw_width = 235.0F;
         SpecialHibikiTaikoScore.ishide = false;
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "FeverPower"));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(49);
         if (!DCDmod.AnimationTrigger) {
            SpecialTaikoEffects.a = 4;
            SpecialTaikoEffects.update();
         }
      }

   }

   private static void KeyCount() {
      ++TaikoNumber;
      if (TaikoNumber > 4) {
         TaikoNumber = 0;
      }

      SpecialHibikiTaikoKey.TimerTime = 1.5F;
      if (Fever) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_taikoaction(), 0.0F));
      }

   }

   private static void TaikoCount() {
      if (Fever) {
         ++ComboPoint;
      } else {
         ComboPoint += 2;
      }

      if (ActionPoint > 0) {
         --ActionPoint;
      }

   }

   private static void TaikoSpace() {
      if (TaikoNumber >= 3) {
         switch (TaikoArray[0] + TaikoArray[1] + TaikoArray[2] + TaikoArray[3] + TaikoArray[4]) {
            case "PATAPATAPATAPON":
               if (Fever && ActionPoint > 0) {
                  CardCrawlGame.sound.playA("Forward", 0.0F);
                  TaikoAction("Forward");
                  TaikoCount();
               } else {
                  FeverOut(true);
               }
               break;
            case "PONPONPATAPON":
               CardCrawlGame.sound.playA("Attack", 0.0F);
               TaikoAction("Attack");
               TaikoCount();
               break;
            case "CHAKACHAKAPATAPON":
               CardCrawlGame.sound.playA("Defend", 0.0F);
               TaikoAction("Defend");
               TaikoCount();
               break;
            case "PONPATAPONPATA":
               if (Fever && ActionPoint > 0) {
                  CardCrawlGame.sound.playA("Back", 0.0F);
                  TaikoAction("Back");
                  TaikoCount();
               } else {
                  FeverOut(true);
               }
               break;
            case "DONDONCHAKACHAKA":
               if (Fever && ActionPoint > 0) {
                  CardCrawlGame.sound.playA("Jump", 0.0F);
                  TaikoAction("Jump");
                  TaikoCount();
               } else {
                  FeverOut(true);
               }
               break;
            case "PATAPONDONCHAKA":
               CardCrawlGame.sound.playA("Relieve", 0.0F);
               TaikoAction("Relieve");
               TaikoCount();
               break;
            case "PONPONCHAKACHAKA":
               if (EnergyPanel.totalCount > 0) {
                  CardCrawlGame.sound.playA("Accumulate", 0.0F);
                  TaikoAction("Accumulate");
                  TaikoCount();
               } else {
                  AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "能量不足，无法触发效果", true));
                  FeverOut(true);
               }
               break;
            case "PATAPONPATAPON":
               if (ActionPoint >= 6) {
                  TaikoAction("Suspend");
                  TaikoCount();
               } else {
                  FeverOut(true);
               }
               break;
            case "DONDONDONDONDON":
               if (Fever) {
                  CardCrawlGame.sound.playA("Buff", 0.0F);
                  TaikoAction("Buff");
                  TaikoCount();
               } else {
                  FeverOut(true);
               }
               break;
            default:
               FeverOut(true);
         }
      } else {
         FeverOut(true);
      }

      TaikoArray[0] = "";
      TaikoArray[1] = "";
      TaikoArray[2] = "";
      TaikoArray[3] = "";
      TaikoArray[4] = "";
      SpecialHibikiTaikoKey.img0 = 0;
      SpecialHibikiTaikoKey.img1 = 0;
      SpecialHibikiTaikoKey.img2 = 0;
      SpecialHibikiTaikoKey.img3 = 0;
      SpecialHibikiTaikoKey.img4 = 0;
      SpecialHibikiTaikoKey.img00 = SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img0];
      SpecialHibikiTaikoKey.img11 = SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img1];
      SpecialHibikiTaikoKey.img22 = SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img2];
      SpecialHibikiTaikoKey.img33 = SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img3];
      SpecialHibikiTaikoKey.img44 = SpecialHibikiTaikoKey.img[SpecialHibikiTaikoKey.img4];
      TaikoNumber = -1;
      SpecialHibikiTaikoKey.TimerTime = 0.0F;
   }

   public static void TaikoAction(String name) {
      AbstractPlayer p = AbstractDungeon.player;
      switch (name) {
         case "Attack":
            int d = 1;
            if (Fever) {
               d += ComboPoint / 8;
            }

            for(int i = 0; i < 4; ++i) {
               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying) {
                     AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, d, DamageType.NORMAL), AttackEffect.FIRE));
                  }
               }
            }

            if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
               AbstractDungeon.actionManager.addToBottom(new TaikoAttackAction());
            }
            break;
         case "Defend":
            int b = 4;
            if (Fever) {
               b += ComboPoint / 8;
            }

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, b));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameBarrierPower(p, b / 2), b / 2));
            break;
         case "Forward":
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, ActionPoint), ActionPoint));
            ActionPoint = 0;
            break;
         case "Back":
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, ActionPoint), ActionPoint));
            ActionPoint = 0;
            break;
         case "Jump":
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePower(p, ActionPoint), ActionPoint));
            ActionPoint = 0;
            break;
         case "Relieve":
            String powerID = null;

            for(AbstractPower power : p.powers) {
               if (power.type == PowerType.DEBUFF) {
                  powerID = power.ID;
                  break;
               }
            }

            if (powerID != null) {
               AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, powerID));
            }

            if (Fever) {
               AbstractDungeon.actionManager.addToBottom(new RelieveAction());
            } else if (powerID == null) {
               AbstractDungeon.actionManager.addToBottom(new RelieveAction());
            }
            break;
         case "Accumulate":
            ActionPoint += EnergyPanel.totalCount;
            EnergyPanel.totalCount = 0;
            if (Fever) {
               ++ActionPoint;
            }
            break;
         case "Suspend":
            ComboPoint += ActionPoint * 2;
            ActionPoint = 0;
            break;
         case "Buff":
            TurnTimer.StopBGM(false);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiSpecialPower(p, ComboPoint), ComboPoint));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiPower(p), 1));
            ComboPoint = 0;
            Fever = false;
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "FeverPower"));
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_Kurenai_henshin_SoundsAndAnimation(AbstractDungeon.player.drawX, AbstractDungeon.player.drawY), 2.8F));
            if (!DCDmod.AnimationTrigger) {
               SpecialTaikoEffects.a = 4;
               SpecialTaikoEffects.update();
            }
      }

   }
}
