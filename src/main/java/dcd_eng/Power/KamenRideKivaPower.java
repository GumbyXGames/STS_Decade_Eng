package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialKivaPowerMeter;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Kiva_backtodcd;

public class KamenRideKivaPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideKivaPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int heal = 0;
   private int loseHp = 0;

   public KamenRideKivaPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideKivaPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideKivaPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kiva_backtodcd(), 2.0F));
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
      if (this.owner.hasPower("KivaHungryPower")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KivaHungryPower"));
      }

      if (this.owner.hasPower("KivaHungry2Power")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KivaHungry2Power"));
      }

      if (this.owner.hasPower("KivaHungry3Power")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KivaHungry3Power"));
      }

      if (SpecialKivaPowerMeter.powerMeter < 30) {
         int d = (30 - SpecialKivaPowerMeter.powerMeter) * 2;
         this.addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, d, DamageType.HP_LOSS)));
      }

      SpecialKivaPowerMeter.chainBreak1 = false;
      SpecialKivaPowerMeter.chainBreak2 = false;
      SpecialKivaPowerMeter.F = 0;
      SpecialKivaPowerMeter.B = 8;
      SpecialKivaPowerMeter.KivaTrigger = false;
   }

   public void wasHPLost(DamageInfo info, int damageAmount) {
      if (SpecialKivaPowerMeter.powerMeter <= 0 && !this.owner.hasPower("KivaHungryPower") && !this.owner.hasPower("KivaHungry2Power") && !this.owner.hasPower("KivaHungry3Power")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new KivaHungryPower(this.owner), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new VulnerablePower(this.owner, 1, false), 1));
      } else {
         if (this.owner.hasPower("KivaHungryPower")) {
            this.loseHp += damageAmount;
            this.amount = this.loseHp;
            this.updateDescription();
         }

         if (this.loseHp >= 20 && !this.owner.hasPower("KivaHungry2Power") && !this.owner.hasPower("KivaHungry3Power")) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KivaHungryPower"));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new KivaHungry2Power(this.owner), 1));
            this.loseHp = 0;
            this.amount = this.loseHp;
            this.updateDescription();
         }

      }
   }

   public int onHeal(int healAmount) {
      if (this.owner.hasPower("KivaHungry2Power")) {
         this.heal += healAmount;
         this.amount = this.heal;
         this.updateDescription();
      }

      if (this.heal >= 30 && this.owner.hasPower("KivaHungry2Power") && !this.owner.hasPower("KivaHungry3Power")) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "KivaHungry2Power"));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new KivaHungry3Power(this.owner), 1));
      }

      if (SpecialKivaPowerMeter.chainBreak2) {
         this.amount = 0;
         this.updateDescription();
      }

      return healAmount;
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if ((EnergyPanel.totalCount == 0 || card.costForTurn > EnergyPanel.totalCount) && !card.freeToPlayOnce && !card.purgeOnUse) {
         int KP = SpecialKivaPowerMeter.powerMeter;
         KP -= (card.costForTurn - EnergyPanel.totalCount) * 4;
         if (KP >= 0) {
            SpecialKivaPowerMeter.powerMeter = KP;
         } else {
            KP = card.costForTurn * 4 - SpecialKivaPowerMeter.powerMeter;
            SpecialKivaPowerMeter.powerMeter = 0;
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.owner, KP, DamageType.HP_LOSS)));
         }
      }

   }

   public void atStartOfTurn() {
      SpecialRideBooker.isPlayerTurn = true;
   }

   public void atEndOfTurn(boolean isPlayer) {
      SpecialRideBooker.isPlayerTurn = false;
   }

   public void atEndOfRound() {
      TurnTimer.atEndOfRound();
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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideKivaPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
