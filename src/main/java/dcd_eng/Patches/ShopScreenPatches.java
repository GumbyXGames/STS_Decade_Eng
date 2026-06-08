package dcd_eng.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.shop.ShopScreen;
import dcd_eng.Characters.Decade;
import dcd_eng.ui.ShopScreenPatch;
import java.util.ArrayList;

public class ShopScreenPatches {
   @SpirePatch(
      clz = ShopScreen.class,
      method = "init"
   )
   public static class ShopScreenPatch_init {
      @SpirePostfixPatch
      public static void Postfix(ShopScreen __instance, ArrayList<AbstractCard> coloredCards, ArrayList<AbstractCard> colorlessCards) {
         if (AbstractDungeon.player instanceof Decade) {
            ShopScreenPatch.Open(__instance, coloredCards, colorlessCards);
         }

      }
   }

   @SpirePatch(
      clz = ShopScreen.class,
      method = "renderCardsAndPrices"
   )
   public static class ShopScreenPatch_Render {
      @SpirePrefixPatch
      public static void Prefix(ShopScreen __instance, SpriteBatch sb) {
         if (AbstractDungeon.player instanceof Decade) {
            ShopScreenPatch.PreRender(__instance, sb);
         }

      }
   }

   @SpirePatch(
      clz = ShopScreen.class,
      method = "update"
   )
   public static class ShopScreenPatch_Update {
      @SpirePostfixPatch
      public static void Postfix(ShopScreen __instance) {
         if (AbstractDungeon.player instanceof Decade) {
            ShopScreenPatch.Update(__instance);
         }

      }
   }

   @SpirePatch(
      clz = ShopScreen.class,
      method = "purchaseCard"
   )
   public static class ShopScreenPatch_PurchaseCard {
      @SpirePostfixPatch
      public static void Postfix(ShopScreen __instance) {
         if (AbstractDungeon.player instanceof Decade) {
            ShopScreenPatch.purchaseCard(__instance);
         }

      }
   }
}
