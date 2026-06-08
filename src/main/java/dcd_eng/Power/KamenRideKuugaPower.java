package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Allformbacktodcd;
import dcd_eng.Vfx.Kuuga_TitanDefend;
import dcd_eng.Vfx.Kuuga_attacked;
import dcd_eng.Vfx.Kuuga_backtodcd;
import dcd_eng.Vfx.Kuuga_defend;

public class KamenRideKuugaPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideKuugaPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideKuugaPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideKuugaPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideKuugaPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         if (!DCDmod.AnimationTrigger || !this.owner.hasPower("KuugaDragonPower") && !this.owner.hasPower("RisingDragonPower") && !this.owner.hasPower("KuugaPegasusPower") && !this.owner.hasPower("RisingPegasusPower") && !this.owner.hasPower("KuugaTitanPower") && !this.owner.hasPower("RisingTitanPower")) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_backtodcd(), 2.0F));
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2.0F));
         }
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
   }

   public void atStartOfTurn() {
      SpecialRideBooker.isPlayerTurn = true;
   }

   public void atEndOfRound() {
      TurnTimer.atEndOfRound();
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner && info.type == DamageType.NORMAL && damageAmount < 1) {
         if (!this.owner.hasPower("KuugaTitanPower") && !this.owner.hasPower("RisingTitanPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_defend(), 0.0F));
         } else {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_TitanDefend(), 0.0F));
         }
      }

      if (info.owner != this.owner && info.type == DamageType.NORMAL && damageAmount > 0) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_attacked(), 0.0F));
      }

      return super.onAttacked(info, damageAmount);
   }

   public void onVictory() {
      CardCrawlGame.sound.playA("victory_normal", 0.0F);
      if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
         CardCrawlGame.sound.playA("victory1", 0.0F);
      } else {
         CardCrawlGame.sound.playA("victory2", 0.0F);
      }

      TurnTimer.atNextBattle();
      Decade Decade = (Decade)AbstractDungeon.player;
      Decade.Trickster(3);
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideKuugaPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
