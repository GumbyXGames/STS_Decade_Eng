package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
import dcd_eng.Power.KivaFrozenPower;
import java.util.ArrayList;
import java.util.List;

public class Kiva_Frozen extends AbstractCustomCardWithType {
   public static final String ID = "Kiva_Frozen";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kiva_Frozen.png";
   private static final int COST = 2;
   private List<TooltipInfo> tips;

   public Kiva_Frozen() {
      super("Kiva_Frozen", NAME, "img/cards/Kiva_Frozen.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, AbstractCustomCardWithType.CardColorType.Kiva);
      this.tags.add(DCDmod.RiderCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      int b = 8;
      if (p.hasPower("KivaBasshaaPower")) {
         b = 4;
      }

      for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
         if (!mo.isDeadOrEscaped() && !mo.isDead && !mo.isDying && !mo.halfDead) {
            this.addToBot(new GainBlockAction(mo, p, b));
            if (!mo.hasPower("KivaFARFrozenPower")) {
               this.addToBot(new ApplyPowerAction(mo, p, new KivaFrozenPower(mo)));
            }
         }
      }

   }

   public AbstractCard makeCopy() {
      return new Kiva_Frozen();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[2];
      }

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
      if (AbstractDungeon.player.hasPower("KivaBasshaaPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kiva_Frozen");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
