package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import dcd_eng.Vfx.Ryuki_attack;
import dcd_eng.Vfx.Ryuki_backtodcd;
import dcd_eng.Vfx.Ryuki_guardattack;
import dcd_eng.Vfx.Ryuki_strikeattack;

public class KamenRideRyukiPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideRyukiPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideRyukiPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideRyukiPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideRyukiPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Ryuki_backtodcd(), 2.0F));
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
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

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (!DCDmod.AnimationTrigger) {
         if (this.owner.hasPower("DragClawPower") && !this.owner.hasPower("DragShieldPower") && card.cardID.equals("Ryuki_DragSaber")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_strikeattack(), 1.0F));
         } else if (this.owner.hasPower("DragShieldPower") && card.cardID.equals("Ryuki_DragSaber")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guardattack(), 0.8F));
         } else if (card.cardID.equals("Ryuki_DragSaber")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_attack(), 0.0F));
         }
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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideRyukiPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
