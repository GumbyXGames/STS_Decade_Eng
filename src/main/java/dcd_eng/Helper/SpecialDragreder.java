package dcd_eng.Helper;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Patches.AbstractSummonedAnimation;
import dcd_eng.Vfx.VfxController;

public class SpecialDragreder {
   public static int a = 0;
   public static boolean isDefend;
   public static boolean isDefend2;

   public static void DragrederAnimationControl(int ControlNumber) {
      if (a != ControlNumber) {
         if (VfxController.Dragreder != null) {
            AbstractSummonedAnimation.clear("DRAGREDER" + a);
         }

         a = ControlNumber;
         switch (a) {
            case 1:
               String DRAGREDER_ATLAS1 = "img/char/DCD_Animation/dragreder/dragreder_appear.atlas";
               String DRAGREDER_JSON1 = "img/char/DCD_Animation/dragreder/dragreder_appear.json";
               new AbstractSummonedAnimation("DRAGREDER1", DRAGREDER_ATLAS1, DRAGREDER_JSON1, 0.8F, AbstractDungeon.player.drawX + 300.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
               VfxController.Dragreder = AbstractSummonedAnimation.getAnimation("DRAGREDER" + a);
               VfxController.Dragreder.setMovable(false);
               VfxController.Dragreder.state.setAnimation(0, "appear", false);
               break;
            case 2:
               String DRAGREDER_ATLAS2 = "img/char/DCD_Animation/dragreder/dragreder_attack.atlas";
               String DRAGREDER_JSON2 = "img/char/DCD_Animation/dragreder/dragreder_attack.json";
               new AbstractSummonedAnimation("DRAGREDER2", DRAGREDER_ATLAS2, DRAGREDER_JSON2, 0.8F, AbstractDungeon.player.drawX + 300.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
               VfxController.Dragreder = AbstractSummonedAnimation.getAnimation("DRAGREDER" + a);
               VfxController.Dragreder.setMovable(false);
               VfxController.Dragreder.state.setAnimation(0, "attack", false);
               break;
            case 3:
               String DRAGREDER_ATLAS3 = "img/char/DCD_Animation/dragreder/dragreder_defend.atlas";
               String DRAGREDER_JSON3 = "img/char/DCD_Animation/dragreder/dragreder_defend_defendtonormal.json";
               new AbstractSummonedAnimation("DRAGREDER3", DRAGREDER_ATLAS3, DRAGREDER_JSON3, 0.8F, AbstractDungeon.player.drawX + 300.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
               VfxController.Dragreder = AbstractSummonedAnimation.getAnimation("DRAGREDER" + a);
               VfxController.Dragreder.setMovable(false);
               VfxController.Dragreder.state.setAnimation(0, "defendtonormal", false);
               break;
            case 4:
               String DRAGREDER_ATLAS4 = "img/char/DCD_Animation/dragreder/dragreder_disappear.atlas";
               String DRAGREDER_JSON4 = "img/char/DCD_Animation/dragreder/dragreder_disappear.json";
               new AbstractSummonedAnimation("DRAGREDER4", DRAGREDER_ATLAS4, DRAGREDER_JSON4, 0.8F, AbstractDungeon.player.drawX + 300.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
               VfxController.Dragreder = AbstractSummonedAnimation.getAnimation("DRAGREDER" + a);
               VfxController.Dragreder.setMovable(false);
               VfxController.Dragreder.state.setAnimation(0, "disappear", false);
               break;
            case 5:
               String DRAGREDER_ATLAS5 = "img/char/DCD_Animation/dragreder/dragreder_normal.atlas";
               String DRAGREDER_JSON5 = "img/char/DCD_Animation/dragreder/dragreder_normal.json";
               new AbstractSummonedAnimation("DRAGREDER5", DRAGREDER_ATLAS5, DRAGREDER_JSON5, 0.8F, AbstractDungeon.player.drawX + 300.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
               VfxController.Dragreder = AbstractSummonedAnimation.getAnimation("DRAGREDER" + a);
               VfxController.Dragreder.setMovable(false);
               VfxController.Dragreder.state.setAnimation(0, "normal", true);
               break;
            case 6:
               String DRAGREDER_ATLAS6 = "img/char/DCD_Animation/dragreder/dragreder_defend.atlas";
               String DRAGREDER_JSON6 = "img/char/DCD_Animation/dragreder/dragreder_defend_normaltodefend.json";
               new AbstractSummonedAnimation("DRAGREDER6", DRAGREDER_ATLAS6, DRAGREDER_JSON6, 0.8F, AbstractDungeon.player.drawX + 300.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
               VfxController.Dragreder = AbstractSummonedAnimation.getAnimation("DRAGREDER" + a);
               VfxController.Dragreder.setMovable(false);
               VfxController.Dragreder.state.setAnimation(0, "normaltodefend", false);
         }

      }
   }

   public static void dispose() {
      VfxController.Dragreder = null;
   }
}
