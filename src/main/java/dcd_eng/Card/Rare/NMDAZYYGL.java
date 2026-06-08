package dcd_eng.Card.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;

public class NMDAZYYGL extends AbstractCustomCardWithType {
   public static final String ID = "NMDAZYYGL";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NMDAZYYGL.gif";
   private static final int COST = 3;

   public NMDAZYYGL() {
      super("NMDAZYYGL", NAME, "img/cards/NMDAZYYGL.gif", 3, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      m.escape();
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (m != null && m.type == EnemyType.BOSS) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         if (m != null && m.id.equals("Darkling")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new NMDAZYYGL();
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
         this.upgradeBaseCost(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("NMDAZYYGL");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
