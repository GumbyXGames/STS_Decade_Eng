package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.Allformbacktodcd;
import dcd_eng.Vfx.Blade_backtodcd;

public class KamenRideBladePower extends AbstractPower {
   public static final String POWER_ID = "KamenRideBladePower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KamenRideBladePower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideBladePower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideBladePower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         if (this.owner.hasPower("BladeJackPower")) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Allformbacktodcd(), 2.0F));
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Blade_backtodcd(), 2.0F));
         }
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
   }

   public void atStartOfTurn() {
      SpecialRideBooker.isPlayerTurn = true;
   }

   public void atEndOfTurn(boolean isPlayer) {
      if (this.owner.currentBlock <= 0 && !this.owner.hasPower("BladeJackPower")) {
         AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, 5));
      }

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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideBladePower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
