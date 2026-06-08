package dcd_eng.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Actions.ApotheosisAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.AgitoLevelPower;
import dcd_eng.Power.FlameLevelPower;
import dcd_eng.Power.StormLevelPower;

public class PrometheusPower extends AbstractCustomCardWithType {
   public static final String ID = "PrometheusPower";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PrometheusPower.png";
   private static final int COST = 1;

   public PrometheusPower() {
      super("PrometheusPower", NAME, "img/cards/PrometheusPower.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Agito);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 1;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApotheosisAction());
      if (p.hasPower("AgitoStormPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StormLevelPower(p, 1), 1));
      }

      if (p.hasPower("AgitoFlamePower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameLevelPower(p, 1), 1));
      }

      if (p.hasPower("KamenRideAgitoPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AgitoLevelPower(p, 1), 1));
      }

      for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
         if (c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
            if (c.timesUpgraded <= 8) {
               c.upgrade();
            } else {
               c.timesUpgraded = 9;
            }
         }
      }

      for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
         if (c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
            if (c.timesUpgraded <= 8) {
               c.upgrade();
            } else {
               c.timesUpgraded = 9;
            }
         }
      }

      for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
         if (c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
            if (c.timesUpgraded <= 8) {
               c.upgrade();
            } else {
               c.timesUpgraded = 9;
            }
         }
      }

      for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
         if (c.cardID.equals("FormRideStorm") || c.cardID.equals("FormRideFlame")) {
            if (c.timesUpgraded <= 8) {
               c.upgrade();
            } else {
               c.timesUpgraded = 9;
            }
         }
      }

   }

   public AbstractCard makeCopy() {
      return new PrometheusPower();
   }

   public void optionDecade() {
   }

   public void optionKuuga() {
   }

   public void optionAgito() {
   }

   public void optionRyuki() {
   }

   public void optionFaiz() {
   }

   public void optionBlade() {
   }

   public void optionHibiki() {
   }

   public void optionKabuto() {
   }

   public void optionDenO() {
   }

   public void optionKiva() {
   }

   public void optionNeutral() {
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(0);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("PrometheusPower");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
