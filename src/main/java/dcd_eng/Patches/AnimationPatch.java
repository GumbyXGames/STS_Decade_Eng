package dcd_eng.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Characters.Decade;
import dcd_eng.Vfx.Decade_BlackScreen;

public class AnimationPatch {
   @SpirePatch(
      cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
      method = "update"
   )
   public static class UpdatePatch {
      @SpirePostfixPatch
      public static void Postfix(AbstractPlayer player) {
         for(AbstractAnimation animation : AbstractAnimation.animations) {
            if (animation != null) {
               animation.update();
            }
         }

      }

      @SpirePostfixPatch
      public static void Postfix2(AbstractPlayer player) {
         for(AbstractSummonedAnimation animation : AbstractSummonedAnimation.S_animations) {
            if (animation != null) {
               animation.update();
            }
         }

      }

      @SpirePostfixPatch
      public static void Postfix3(AbstractPlayer player) {
         for(AbstractHandAnimation animation : AbstractHandAnimation.H_animations) {
            if (animation != null) {
               animation.update();
            }
         }

      }
   }

   @SpirePatch(
      cls = "basemod.abstracts.CustomPlayer",
      method = "renderOrb"
   )
   public static class RenderPatch {
      public static void Prefix(AbstractPlayer player, SpriteBatch sb) {
         for(AbstractAnimation animation : AbstractAnimation.animations) {
            if (animation != null) {
               animation.render(sb);
            }
         }

      }
   }

   @SpirePatch(
      cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
      method = "render"
   )
   public static class RenderPatch2 {
      public static void Prefix(AbstractPlayer player, SpriteBatch sb) {
         for(AbstractSummonedAnimation animation : AbstractSummonedAnimation.S_animations) {
            if (animation != null) {
               animation.render(sb);
            }
         }

      }
   }

   @SpirePatch(
      cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
      method = "renderRelics"
   )
   public static class RenderPatch3 {
      public static void Prefix(AbstractPlayer player, SpriteBatch sb) {
         if (player instanceof Decade && AbstractDungeon.player != null && Decade_BlackScreen.isBlackScreen) {
            BlackScreen.render(sb);
         }

         for(AbstractHandAnimation animation : AbstractHandAnimation.H_animations) {
            if (animation != null) {
               animation.render(sb);
            }
         }

      }
   }
}
