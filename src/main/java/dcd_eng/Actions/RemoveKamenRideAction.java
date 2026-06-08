package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveKamenRideAction extends AbstractGameAction {
   public RemoveKamenRideAction(AbstractCreature target, AbstractCreature source) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.hasPower("KamenRideKuugaPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKuugaPower"));
         }

         if (this.target.hasPower("KamenRideAgitoPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideAgitoPower"));
         }

         if (this.target.hasPower("KamenRideRyukiPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideRyukiPower"));
         }

         if (this.target.hasPower("KamenRideFaizPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideFaizPower"));
         }

         if (this.target.hasPower("KamenRideBladePower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideBladePower"));
         }

         if (this.target.hasPower("KamenRideHibikiPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideHibikiPower"));
         }

         if (this.target.hasPower("KamenRideKabutoPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKabutoPower"));
         }

         if (this.target.hasPower("KamenRideDenOPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideDenOPower"));
         }

         if (this.target.hasPower("KamenRideKivaPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KamenRideKivaPower"));
         } else {
            this.isDone = true;
         }
      }

      this.tickDuration();
   }
}
