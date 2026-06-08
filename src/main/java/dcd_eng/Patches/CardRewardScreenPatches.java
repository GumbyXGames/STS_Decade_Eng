package dcd_eng.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import dcd_eng.Characters.Decade;
import dcd_eng.ui.CardRewardScreenPatch;
import java.util.ArrayList;

public class CardRewardScreenPatches {
   @SpirePatch(
      clz = CardRewardScreen.class,
      method = "onClose"
   )
   public static class CardRewardScreenPatch_OnClose {
      @SpirePostfixPatch
      public static void Postfix(CardRewardScreen __instance) {
         if (AbstractDungeon.player instanceof Decade) {
            CardRewardScreenPatch.OnClose(__instance);
         }

      }
   }

   @SpirePatch(
      clz = CardRewardScreen.class,
      method = "open"
   )
   public static class CardRewardScreenPatch_Open {
      @SpirePostfixPatch
      public static void Postfix(CardRewardScreen __instance, ArrayList<AbstractCard> cards, RewardItem rItem, String header) {
         if (AbstractDungeon.player instanceof Decade) {
            CardRewardScreenPatch.Open(__instance, cards, rItem, header);
         }

      }
   }

   @SpirePatch(
      clz = CardRewardScreen.class,
      method = "customCombatOpen"
   )
   public static class CardRewardScreenPatch_customCombatOpen {
      @SpirePostfixPatch
      public static void Postfix(CardRewardScreen __instance, ArrayList<AbstractCard> cards, String header, boolean skippable) {
         if (AbstractDungeon.player instanceof Decade) {
            CardRewardScreenPatch.customCombatOpen(__instance, cards, header, skippable);
         }

      }
   }

   @SpirePatch(
      clz = CardRewardScreen.class,
      method = "render"
   )
   public static class CardRewardScreenPatch_Render {
      @SpirePrefixPatch
      public static void Prefix(CardRewardScreen __instance, SpriteBatch sb) {
         if (AbstractDungeon.player instanceof Decade) {
            CardRewardScreenPatch.PreRender(__instance, sb);
         }

      }
   }

   @SpirePatch(
      clz = CardRewardScreen.class,
      method = "update"
   )
   public static class CardRewardScreenPatch_Update {
      @SpirePostfixPatch
      public static void Postfix(CardRewardScreen __instance) {
         if (AbstractDungeon.player instanceof Decade) {
            CardRewardScreenPatch.Update(__instance);
         }

      }
   }
}
