package dcd_eng.Actions;

import basemod.DevConsole;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dcd_eng.DCDmod;
import dcd_eng.Card.Common.DenO_DenGasher;
import dcd_eng.Card.Rare.DenO_Climax;
import dcd_eng.Card.Uncommon.RideBooker_Skill_2;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialHibikiTaikoKey;
import dcd_eng.Helper.SpecialHibikiTaikoScore;
import dcd_eng.Helper.SpecialKivaPowerMeter;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Helper.SpecialTaikoEffects;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractHandAnimation;
import dcd_eng.Patches.AbstractSummonedAnimation;
import dcd_eng.Patches.HibikiTaikoKeyEvent;
import dcd_eng.Patches.ModBaseClassForSLExample;
import dcd_eng.Power.DenOSpecialPower;
import dcd_eng.Power.KamenRideDenOPower;
import dcd_eng.Power.TimeVentPower;

public class TurnTimer {
   public static boolean BattleEnd = false;

   public static void atEndOfRound() {
      NewRideBookerCardAction.RideGroup("DenO");
      NewRideBookerCardAction.RideGroup("Decade");
      SpecialRideBooker.isPlayerTurn = false;
      SpecialRideBooker.decadenextturn = true;
      SpecialRideBooker.axelformpoint = 0;
      SpecialRideBooker.currentpoint = 0;
      SpecialRideBooker.decadepoint = 0;
      SpecialRideBooker.jackformpoint = 0;
      SpecialRideBooker.nodecade = false;
      SpecialRideBooker.RideBookerOpen = false;
   }

   public static void atNextBattle() {
      BattleEnd = true;
      RideBooker_Skill_2.RBS2isDone = true;
      ModBaseClassForSLExample.timevent = false;
      ModBaseClassForSLExample.testOutput = 0;
      if (SpecialFaizBox.AxelForm) {
         SpecialFaizBox.AxelForm = false;
         SpecialFaizBox.FaizPhone = false;
         SpecialFaizBox.FaizPointer = false;
         SpecialFaizBox.FaizShot = false;
         SpecialFaizBox.FaizEdge = false;
         SpecialFaizBox.FaizPoint = 0;
      }

      DCDmod.clearConfig2();
      DCDmod.saveConfig2();
      Decade.cf = 0;
      SpecialKivaPowerMeter.chainBreak1 = false;
      SpecialKivaPowerMeter.chainBreak2 = false;
      SpecialKivaPowerMeter.F = 0;
      SpecialKivaPowerMeter.B = 8;
      SpecialKivaPowerMeter.powerMeter = 30;
      SpecialKivaPowerMeter.KivaTrigger = false;
      DenOSpecialPower.Form = 0;
      DenO_DenGasher.Form = 0;
      DenO_Climax.use = 1;
      KamenRideDenOPower.retain = false;
      HibikiTaikoKeyEvent.TaikoNumber = -1;
      HibikiTaikoKeyEvent.ComboPoint = 0;
      HibikiTaikoKeyEvent.ActionPoint = 1;
      SpecialHibikiTaikoKey.TimerTime = 0.0F;
      if (HibikiTaikoKeyEvent.Fever) {
         HibikiTaikoKeyEvent.Fever = false;
      }

      if (!DCDmod.AnimationTrigger) {
         SpecialTaikoEffects.a = 4;
         SpecialTaikoEffects.update();
      }

      SpecialHibikiTaikoScore.img = SpecialHibikiTaikoScore.img1;
      SpecialHibikiTaikoScore.draw_height = 19.0F;
      SpecialHibikiTaikoScore.draw_width = 235.0F;
      SpecialHibikiTaikoScore.ishide = true;
      DevConsole.logger.info("===========================太鼓动画代码：" + SpecialTaikoEffects.a);
      StopBGM(false);
      SpecialRideBooker.isPlayerTurn = false;
      SpecialRideBooker.decadenextturn = true;
      SpecialRideBooker.kamenpower_decadeVE_point = 1;
      SpecialRideBooker.axelformpoint = 0;
      SpecialRideBooker.currentpoint = 0;
      SpecialRideBooker.decadepoint = 0;
      SpecialRideBooker.jackformpoint = 0;
      SpecialRideBooker.nodecade = false;
      AbstractRelic r1 = null;
      if (AbstractDungeon.player.hasRelic("MirrorWorldRelic")) {
         for(AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.relicId.equals("MirrorWorldRelic")) {
               r1 = r;
            }
         }

         if (r1 != null) {
            AbstractDungeon.player.relics.remove(r1);
         }
      }

      AbstractSummonedAnimation.clearAll();
      AbstractAnimation.clearAll();
      AbstractHandAnimation.clearAll();
      AbstractDungeon.uncommonRelicPool.remove("MummifiedHand");
      AbstractDungeon.uncommonRelicPool.remove("Mummified Hand");
      AbstractDungeon.uncommonRelicPool.remove("SingingBowl");
      AbstractDungeon.uncommonRelicPool.remove("Singing Bowl");
   }

   public static void atBattleStart() {
      BattleEnd = false;
      SpecialRideBooker.isPlayerTurn = true;
      SpecialKivaPowerMeter.chainBreak1 = false;
      SpecialKivaPowerMeter.chainBreak2 = false;
      SpecialKivaPowerMeter.F = 0;
      SpecialKivaPowerMeter.B = 8;
      SpecialKivaPowerMeter.powerMeter = 30;
      SpecialKivaPowerMeter.KivaTrigger = false;
      DCDmod.clearConfig2();
      DCDmod.saveConfig2();
      Decade.KamenRide = "human";
      SpecialRideBooker.nodecade = false;
      if (AbstractDungeon.player instanceof Decade) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(179);
      }

      NewRideBookerCardAction.RideGroup("human");
      NewRideBookerCardAction.RideGroup("Decade");
      NewRideBookerCardAction.RideGroup("Ryuki");
      NewRideBookerCardAction.RideGroup("Blade");
      NewRideBookerCardAction.RideGroup("DenO");
      NewRideBookerCardAction.RideGroup("Basic");
      SpecialRideBooker.kamenpower_decadeVE_point = 1;
      DenOSpecialPower.Form = 0;
      DenO_DenGasher.Form = 0;
      DenO_Climax.use = 1;
      KamenRideDenOPower.retain = false;
      HibikiTaikoKeyEvent.TaikoNumber = -1;
      HibikiTaikoKeyEvent.ComboPoint = 0;
      HibikiTaikoKeyEvent.ActionPoint = 1;
      SpecialHibikiTaikoKey.TimerTime = 0.0F;
      if (HibikiTaikoKeyEvent.Fever) {
         HibikiTaikoKeyEvent.Fever = false;
      }

      if (!DCDmod.AnimationTrigger) {
         SpecialTaikoEffects.a = 4;
         SpecialTaikoEffects.update();
      }

      HibikiTaikoKeyEvent.loadKeySettings();
      if (ModBaseClassForSLExample.timevent && ModBaseClassForSLExample.testOutput > 0) {
         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TimeVentPower(AbstractDungeon.player, ModBaseClassForSLExample.testOutput), ModBaseClassForSLExample.testOutput));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, ModBaseClassForSLExample.testOutput * 2), ModBaseClassForSLExample.testOutput * 2));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, ModBaseClassForSLExample.testOutput * 2), ModBaseClassForSLExample.testOutput * 2));
      }

      if (!ModBaseClassForSLExample.TimeVentUpgraded && ModBaseClassForSLExample.testOutput > 0) {
         boolean removetimevent = false;

         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals("TimeVent") && !c.upgraded) {
               AbstractDungeon.player.hand.group.remove(c);
               removetimevent = true;
               break;
            }
         }

         if (!removetimevent) {
            for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
               if (c.cardID.equals("TimeVent") && !c.upgraded) {
                  AbstractDungeon.player.drawPile.group.remove(c);
                  break;
               }
            }
         }

         for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals("TimeVent") && !c.upgraded) {
               AbstractDungeon.player.masterDeck.group.remove(c);
               break;
            }
         }
      }

      AbstractRelic r1 = null;
      if (AbstractDungeon.player.hasRelic("MirrorWorldRelic")) {
         for(AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.relicId.equals("MirrorWorldRelic")) {
               r1 = r;
            }
         }

         if (r1 != null) {
            AbstractDungeon.player.relics.remove(r1);
         }
      }

      AbstractSummonedAnimation.clearAll();
      AbstractAnimation.clearAll();
      AbstractHandAnimation.clearAll();
      AbstractDungeon.uncommonRelicPool.remove("MummifiedHand");
      AbstractDungeon.uncommonRelicPool.remove("Mummified Hand");
      AbstractDungeon.uncommonRelicPool.remove("SingingBowl");
      AbstractDungeon.uncommonRelicPool.remove("Singing Bowl");
   }

   public static void StopBGM(boolean isBattleStartOrEnd) {
      if (!isBattleStartOrEnd && !DCDmod.BGMTrigger) {
         CardCrawlGame.music.silenceBGM();
         CardCrawlGame.music.silenceBGMInstantly();
         CardCrawlGame.music.silenceTempBgmInstantly();
      } else {
         CardCrawlGame.music.playPrecachedTempBgm();
      }

   }
}
