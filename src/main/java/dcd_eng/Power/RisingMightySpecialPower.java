package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcd_eng.Actions.ReturnRandomNumberAction2;
import dcd_eng.Actions.RisingMightyAction;

public class RisingMightySpecialPower extends AbstractPower {
   public static final String POWER_ID = "RisingMightySpecialPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   RisingMightySpecialPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "RisingMightySpecialPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/invigorate.png");
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      this.updateDescription();
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "RisingMightySpecialPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
      if (this.amount >= 3) {
         this.amount = 3;
      }

   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.type == CardType.ATTACK && ReturnRandomNumberAction2.ReturnRandomNumber() < this.amount * 30) {
         AbstractDungeon.actionManager.addToBottom(new RisingMightyAction());
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "RisingMightySpecialPower"));
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("RisingMightySpecialPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
