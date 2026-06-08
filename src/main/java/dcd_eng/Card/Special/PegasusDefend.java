package dcd_eng.Card.Special;

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
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.PegasusDefendPower;

public class PegasusDefend extends AbstractCustomCardWithType {
   public static final String ID = "PegasusDefend";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PegasusDefend.png";
   private static final int COST = 0;

   public PegasusDefend() {
      super("PegasusDefend", NAME, "img/cards/PegasusDefend.png", 0, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
      this.baseMagicNumber = this.magicNumber = 1;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PegasusDefendPower(p, this.magicNumber), this.magicNumber));
   }

   public AbstractCard makeCopy() {
      return new PegasusDefend();
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
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("PegasusDefend");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
