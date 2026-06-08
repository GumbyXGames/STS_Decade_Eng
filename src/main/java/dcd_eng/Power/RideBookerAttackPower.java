package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcd_eng.DCDmod;

public class RideBookerAttackPower extends AbstractPower {
   public static final String POWER_ID = "RideBookerAttackPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private final AbstractCard card;

   public RideBookerAttackPower(AbstractCreature owner, int amt, AbstractCard c) {
      this.name = NAME;
      this.ID = "RideBookerAttackPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/AutoVajinPower.png");
      this.card = c;
      this.updateDescription();
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
      if (card.hasTag(DCDmod.WeaponCard) && card != this.card) {
         this.addToBot(new GainEnergyAction(1));
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("RideBookerAttackPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
