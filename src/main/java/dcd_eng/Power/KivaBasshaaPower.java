package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.UpdateDescriptionAction;

public class KivaBasshaaPower extends AbstractPower {
   public static final String POWER_ID = "KivaBasshaaPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KivaBasshaaPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaBasshaaPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KivaBasshaaPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      if (power.ID.equals("KivaHungry3Power")) {
         this.addToBot(new UpdateDescriptionAction(this));
      }

   }

   public void atStartOfTurn() {
      this.updateDescription();
      this.flash();

      for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
         if (!mo.isDeadOrEscaped() && !mo.isDead && !mo.isDying && !mo.halfDead) {
            this.addToTop(new ApplyPowerAction(mo, this.owner, new KivaLacerationPower(mo, 2, this.owner), 2));
         }
      }

      if (this.owner.hasPower("KivaHungry3Power")) {
         AbstractMonster m = AbstractDungeon.getRandomMonster();
         this.addToBot(new GainBlockAction(m, this.owner, 5));
         if (!m.hasPower("KivaFARFrozenPower")) {
            this.addToBot(new ApplyPowerAction(m, this.owner, new KivaFrozenPower(m)));
         }
      }

   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.type == CardType.SKILL) {
         for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped() && !mo.isDead && !mo.isDying && !mo.halfDead) {
               this.addToTop(new ApplyPowerAction(mo, this.owner, new KivaLacerationPower(mo, 1, this.owner), 1));
            }
         }
      }

   }

   public void updateDescription() {
      if (this.owner.hasPower("KivaHungry3Power")) {
         this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaBasshaaPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
