package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
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

public class BladeMachPower extends AbstractPower {
   public static final String POWER_ID = "BladeMachPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public BladeMachPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "BladeMachPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/BladeMachPower.png");
      this.updateDescription();
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.type == CardType.SKILL) {
         AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
      }

   }

   public void atStartOfTurn() {
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "BladeMachPower"));
      } else {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "BladeMachPower", this.amount));
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("BladeMachPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
