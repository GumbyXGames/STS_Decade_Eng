package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.KabutoDiscardAction;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Allformbacktodcd;
import dcd_eng.Vfx.Kabuto_backtodcd;

public class KamenRideKabutoPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideKabutoPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideKabutoPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideKabutoPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideKabutoPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         if (this.owner.hasPower("KabutoMaskedPower")) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2.0F));
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kabuto_backtodcd(), 2.0F));
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

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (!AbstractDungeon.player.hasPower("KabutoMaskedPower")) {
         AbstractDungeon.actionManager.addToTop(new KabutoDiscardAction(AbstractDungeon.player, AbstractDungeon.player, 2, false));
         AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 2));
      }

      if (this.owner.hasPower("KabutoStrengthPower")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KabutoStrengthPower"));
      }

      if (this.owner.hasPower("KabutoDexterityPower")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KabutoDexterityPower"));
      }

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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideKabutoPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
