package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Vfx.henshin_toAgito;
import dcd_eng.Vfx.henshin_toBlade;
import dcd_eng.Vfx.henshin_toDecade;
import dcd_eng.Vfx.henshin_toDenO;
import dcd_eng.Vfx.henshin_toFaiz;
import dcd_eng.Vfx.henshin_toHibiki;
import dcd_eng.Vfx.henshin_toKabuto;
import dcd_eng.Vfx.henshin_toKiva;
import dcd_eng.Vfx.henshin_toKuuga;
import dcd_eng.Vfx.henshin_toRyuki;

public class HenshinAction extends AbstractGameAction {
   private static String KR = "";

   public HenshinAction(String KR) {
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
      HenshinAction.KR = KR;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         switch (KR) {
            case "kuuga":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toKuuga(), 2.03F));
               break;
            case "agito":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toAgito(), 1.74F));
               break;
            case "ryuki":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toRyuki(), 1.3F));
               break;
            case "faiz":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toFaiz(), 3.33F));
               break;
            case "blade":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toBlade(), 2.63F));
               break;
            case "hibiki":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toHibiki(), 0.7F));
               break;
            case "kabuto":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toKabuto(), 4.0F));
               break;
            case "den-o":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toDenO(), 4.33F));
               break;
            case "kiva":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toKiva(), 3.0F));
               break;
            case "decade":
               AbstractDungeon.actionManager.addToTop(new VFXAction(new henshin_toDecade(), 2.3F));
         }
      }

      this.tickDuration();
   }
}
