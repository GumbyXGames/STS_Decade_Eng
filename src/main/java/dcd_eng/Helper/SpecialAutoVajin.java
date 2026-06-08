package dcd_eng.Helper;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Patches.AbstractSummonedAnimation;
import dcd_eng.Patches.AnimationLoader;

public class SpecialAutoVajin {
   public static boolean AutoVajinTrigger1 = true;
   public static int a;
   public static int cf;
   private static final String AUTOVAJIN_ATLAS = "img/char/DCD_Animation/autovajin/autovajin.atlas";
   private static final String AUTOVAJIN_JSON1 = "img/char/DCD_Animation/autovajin/autovajin_appear.json";
   private static final String AUTOVAJIN_JSON2 = "img/char/DCD_Animation/autovajin/autovajin_attack.json";
   private static final String AUTOVAJIN_JSON3 = "img/char/DCD_Animation/autovajin/autovajin_disappear.json";
   private static final String AUTOVAJIN_JSON4 = "img/char/DCD_Animation/autovajin/autovajin_defend.json";
   private static final String AUTOVAJIN_JSON5 = "img/char/DCD_Animation/autovajin/autovajin_normal.json";
   private static final AnimationLoader AutoVajin1 = new AnimationLoader("img/char/DCD_Animation/autovajin/autovajin.atlas", "img/char/DCD_Animation/autovajin/autovajin_appear.json", 0.8F);
   private static final AnimationLoader AutoVajin2 = new AnimationLoader("img/char/DCD_Animation/autovajin/autovajin.atlas", "img/char/DCD_Animation/autovajin/autovajin_attack.json", 0.8F);
   private static final AnimationLoader AutoVajin3 = new AnimationLoader("img/char/DCD_Animation/autovajin/autovajin.atlas", "img/char/DCD_Animation/autovajin/autovajin_disappear.json", 0.8F);
   private static final AnimationLoader AutoVajin4 = new AnimationLoader("img/char/DCD_Animation/autovajin/autovajin.atlas", "img/char/DCD_Animation/autovajin/autovajin_defend.json", 0.8F);
   private static final AnimationLoader AutoVajin5 = new AnimationLoader("img/char/DCD_Animation/autovajin/autovajin.atlas", "img/char/DCD_Animation/autovajin/autovajin_normal.json", 0.8F);
   private static AbstractSummonedAnimation AutoVajin;

   public static void update() {
      if (!TurnTimer.BattleEnd) {
         if (AutoVajinTrigger1) {
            new AbstractSummonedAnimation("AutoVajin", "img/char/DCD_Animation/autovajin/autovajin.atlas", "img/char/DCD_Animation/autovajin/autovajin_appear.json", 0.8F, AbstractDungeon.player.drawX + 150.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
            AutoVajin = AbstractSummonedAnimation.getAnimation("AutoVajin");
            AutoVajin.setMovable(false);
            AutoVajinTrigger1 = false;
         }

         if (a == cf) {
            System.out.println("==a");
            return;
         }

         cf = a;
         if (a == 1) {
            AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin1);
            AutoVajin.state.setAnimation(0, "appear", false);
         } else if (a == 2) {
            AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin2);
            AutoVajin.state.setAnimation(0, "attack", false);
         } else if (a == 3) {
            AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin3);
            AutoVajin.state.setAnimation(0, "disappear", false);
            AutoVajinTrigger1 = true;
         } else if (a == 4) {
            AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin4);
            AutoVajin.state.setAnimation(0, "defend", false);
         } else if (a == 5) {
            AbstractSummonedAnimation.changeAnimation(AutoVajin, AutoVajin5);
            AutoVajin.state.setAnimation(0, "normal", true);
         }
      } else {
         AbstractSummonedAnimation.clear("AutoVajin");
         dispose();
      }

   }

   public static void render() {
   }

   public static void dispose() {
      AutoVajin = null;
   }
}
