package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RemoveFormRideAction extends AbstractGameAction {
   public RemoveFormRideAction(AbstractCreature target, AbstractCreature source) {
      this.target = target;
      this.source = source;
      this.actionType = ActionType.WAIT;
      this.duration = Settings.ACTION_DUR_FAST;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.target.hasPower("KuugaDragonPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KuugaDragonPower"));
         }

         if (this.target.hasPower("KuugaTitanPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KuugaTitanPower"));
         }

         if (this.target.hasPower("KuugaPegasusPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KuugaPegasusPower"));
         }

         if (this.target.hasPower("RisingDragonPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingDragonPower"));
         }

         if (this.target.hasPower("RisingTitanPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingTitanPower"));
         }

         if (this.target.hasPower("RisingPegasusPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingPegasusPower"));
         }

         if (this.target.hasPower("RisingMightyPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RisingMightyPower"));
         }

         if (this.target.hasPower("AgitoStormPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "AgitoStormPower"));
         }

         if (this.target.hasPower("AgitoFlamePower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "AgitoFlamePower"));
         }

         if (this.target.hasPower("FaizAxelPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "FaizAxelPower"));
         }

         if (this.target.hasPower("BladeJackPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "BladeJackPower"));
         }

         if (this.target.hasPower("HibikiKurenaiPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "HibikiKurenaiPower"));
         }

         if (this.target.hasPower("KabutoMaskedPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KabutoMaskedPower"));
         }

         if (this.target.hasPower("KivaGaruruPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KivaGaruruPower"));
         }

         if (this.target.hasPower("KivaBasshaaPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KivaBasshaaPower"));
         }

         if (this.target.hasPower("KivaDoggaPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "KivaDoggaPower"));
         }

         if (this.target.hasPower("RemoveFormRidePower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.target, this.source, "RemoveFormRidePower"));
         }

         this.isDone = true;
      }

      this.tickDuration();
   }
}
