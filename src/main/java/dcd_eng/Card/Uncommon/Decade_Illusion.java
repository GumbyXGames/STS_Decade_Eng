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
import com.megacrit.cardcrawl.powers.BufferPower;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;

public class Decade_Illusion extends AbstractCustomCardWithType {
   public static final String ID = "Decade_Illusion";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Decade_Illusion.png";
   private static final int COST = 1;

   public Decade_Illusion() {
      super("Decade_Illusion", NAME, "img/cards/Decade_Illusion.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
      this.baseMagicNumber = this.magicNumber = 1;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BufferPower(p, this.magicNumber), this.magicNumber));
   }

   public AbstractCard makeCopy() {
      return new Decade_Illusion();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Illusion");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
