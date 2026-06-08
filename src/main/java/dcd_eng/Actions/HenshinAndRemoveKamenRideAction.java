package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.DCDmod;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Human_henshin;
import dcd_eng.Vfx.Rider_henshin;

public class HenshinAndRemoveKamenRideAction extends AbstractGameAction {
   private static String KR = "";
   private final int KRNumber;

   public HenshinAndRemoveKamenRideAction(AbstractCreature target, AbstractCreature source, int KRNumber, String KR) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
      this.KRNumber = KRNumber;
      HenshinAndRemoveKamenRideAction.KR = KR;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (!DCDmod.BGMTrigger) {
            TurnTimer.StopBGM(false);
         }

         SpecialRideBooker.isPlayerTurn = false;
         switch (this.KRNumber) {
            case 0:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Human_henshin(KR), 2.3F));
               break;
            case 1:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 105), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKuugaPower"));
               break;
            case 2:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 148), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideAgitoPower"));
               break;
            case 3:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 162), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideRyukiPower"));
               break;
            case 4:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 154), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideFaizPower"));
               break;
            case 5:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 150), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideBladePower"));
               break;
            case 6:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 156), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideHibikiPower"));
               break;
            case 7:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 158), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKabutoPower"));
               break;
            case 8:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 152), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideDenOPower"));
               break;
            case 9:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 160), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKivaPower"));
               break;
            case 10:
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Rider_henshin(KR, 43), 1.9F));
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideDecadePower"));
         }
      }

      this.tickDuration();
   }
}
