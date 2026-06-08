package dcd_eng.Card.Rare;

import basemod.helpers.TooltipInfo;
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
import dcd_eng.Actions.KivaEatAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KivaLacerationPower;
import java.util.ArrayList;
import java.util.List;

public class Kiva_Eat extends AbstractCustomCardWithType {
   public static final String ID = "Kiva_Eat";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kiva_Eat.png";
   private static final int COST = 1;
   private AbstractPlayer p;
   private boolean isLast;
   private List<TooltipInfo> tips;

   public Kiva_Eat() {
      super("Kiva_Eat", NAME, "img/cards/Kiva_Eat.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kiva);
      this.p = AbstractDungeon.player;
      this.isLast = false;
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 2;
      this.exhaust = true;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (m.hasPower("KivaLacerationPower")) {
         int mn = m.getPower("KivaLacerationPower").amount * (this.magicNumber - 1);
         this.addToBot(new ApplyPowerAction(m, p, new KivaLacerationPower(m, mn, p)));
         if (this.isLast) {
            this.addToBot(new KivaEatAction(m, p));
         }
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if ((this.p.hasPower("KamenRideKivaPower") || this.p.hasPower("DecadeViolentEmotionPower")) && this.p.hand.group.size() <= 1) {
         this.isLast = true;
      } else if (this.p.hand.group.size() > 1) {
         this.isLast = false;
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (m != null && !m.hasPower("KivaLacerationPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         }

         return canUse;
      }
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Kiva_Eat();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeMagicNumber(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kiva_Eat");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
