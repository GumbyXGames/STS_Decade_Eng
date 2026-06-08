package dcd_eng.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialHibikiTaiko;
import dcd_eng.Helper.SpecialHibikiTaikoKey;
import dcd_eng.Helper.SpecialHibikiTaikoScore;
import dcd_eng.Helper.SpecialKivaPowerMeter;
import dcd_eng.Helper.SpecialRideBooker;

public class RideSpecialTriggerPatch {
   @SpirePatch(
      cls = "basemod.abstracts.CustomPlayer",
      method = "renderOrb"
   )
   public static class RenderPatch {
      public static SpireReturn<Object> Prefix(AbstractPlayer the_AbstractPlayer, SpriteBatch sb) {
         if (!the_AbstractPlayer.isDead && the_AbstractPlayer instanceof Decade) {
            if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) {
               if (AbstractDungeon.player.hasPower("KamenRideHibikiPower") && !AbstractDungeon.player.hasPower("HibikiKurenaiPower") && SpecialRideBooker.nodecade) {
                  SpecialHibikiTaikoKey.update();
                  SpecialHibikiTaikoKey.render(sb);
                  SpecialHibikiTaikoScore.update();
                  SpecialHibikiTaikoScore.render(sb);
               }

               if (AbstractDungeon.player.hasPower("KamenRideKivaPower")) {
                  SpecialKivaPowerMeter.update();
                  SpecialKivaPowerMeter.render(sb);
               }
            }

            return SpireReturn.Continue();
         } else {
            return SpireReturn.Continue();
         }
      }
   }

   @SpirePatch(
      cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
      method = "render"
   )
   public static class RenderPatch2 {
      public static SpireReturn<Object> Prefix(AbstractPlayer the_AbstractPlayer, SpriteBatch sb) {
         if (!the_AbstractPlayer.isDead && the_AbstractPlayer instanceof Decade) {
            if ((AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && AbstractDungeon.player.hasPower("KamenRideHibikiPower") && !AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
               SpecialHibikiTaiko.update();
               SpecialHibikiTaiko.render(sb);
            }

            return SpireReturn.Continue();
         } else {
            return SpireReturn.Continue();
         }
      }
   }

   @SpirePatch(
      clz = AbstractPlayer.class,
      method = "combatUpdate"
   )
   public static class PotionSaceUpdatePatch {
      @SpirePostfixPatch
      public static void Postfix(AbstractPlayer the_AbstractPlayer) {
         if (!the_AbstractPlayer.isDead && the_AbstractPlayer instanceof Decade && (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && !AbstractDungeon.player.isDead && AbstractDungeon.player.hasPower("KamenRideHibikiPower") && !AbstractDungeon.player.hasPower("HibikiKurenaiPower") && HibikiTaikoKeyEvent.TaikoTrigger) {
            HibikiTaikoKeyEvent.update();
         }

      }
   }

   @SpirePatch(
      clz = AbstractPlayer.class,
      method = "renderHand"
   )
   public static class RenderPatch3 {
      @SpirePostfixPatch
      public static SpireReturn<Object> Postfix(AbstractPlayer the_AbstractPlayer, SpriteBatch sb) {
         if (!the_AbstractPlayer.isDead && the_AbstractPlayer instanceof Decade) {
            if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT || AbstractDungeon.getCurrRoom() instanceof MonsterRoom) {
               SpecialRideBooker.update();
               SpecialRideBooker.render(sb);
               if (AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
                  SpecialFaizBox.update();
                  SpecialFaizBox.render(sb);
               }
            }

            return SpireReturn.Continue();
         } else {
            return SpireReturn.Continue();
         }
      }
   }
}
