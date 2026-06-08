package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
import dcd_eng.Helper.SpecialTaikoEffects;
import dcd_eng.Patches.HibikiTaikoKeyEvent;
import dcd_eng.Vfx.Allformbacktodcd;
import dcd_eng.Vfx.Hibiki_backtodcd;

public class KamenRideHibikiPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideHibikiPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideHibikiPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideHibikiPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideHibikiPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         if (this.owner.hasPower("HibikiKurenaiPower")) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2.0F));
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Hibiki_backtodcd(), 2.0F));
         }
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
      if (HibikiTaikoKeyEvent.Fever) {
         HibikiTaikoKeyEvent.Fever = false;
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FeverPower"));
         if (!DCDmod.AnimationTrigger) {
            SpecialTaikoEffects.a = 4;
            SpecialTaikoEffects.update();
         }
      }

   }

   public void atStartOfTurn() {
      ++HibikiTaikoKeyEvent.ActionPoint;
      HibikiTaikoKeyEvent.TaikoTrigger = true;
      SpecialRideBooker.isPlayerTurn = true;
      SpecialRideBooker.nodecade = true;
   }

   public void atEndOfTurn(boolean isPlayer) {
      HibikiTaikoKeyEvent.TaikoTrigger = false;
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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideHibikiPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
