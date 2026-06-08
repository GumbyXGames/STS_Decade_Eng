package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Decade_attacked;
import dcd_eng.Vfx.Decade_defend;

public class KamenRideDecadePower extends AbstractPower {
   public static final String POWER_ID = "KamenRideDecadePower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideDecadePower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideDecadePower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideDecadePower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DecadeViolentEmotionPower"));
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner && info.type == DamageType.NORMAL && damageAmount < 1) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_defend(), 0.0F));
      }

      if (info.owner != this.owner && info.type == DamageType.NORMAL && damageAmount > 0) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_attacked(), 0.0F));
      }

      return super.onAttacked(info, damageAmount);
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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideDecadePower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
