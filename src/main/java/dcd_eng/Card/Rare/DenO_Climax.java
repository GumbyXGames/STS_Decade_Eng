package dcd_eng.Card.Rare;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.CardMaxSizeUpPower;
import dcd_eng.Power.KamenRideDenOPower;
import java.util.ArrayList;
import java.util.List;

public class DenO_Climax extends AbstractCustomCardWithType {
   public static final String ID = "DenO_Climax";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DenO_Climax.png";
   private static final int COST = 1;
   public static int use = 1;
   private List<TooltipInfo> tips;

   public DenO_Climax() {
      super("DenO_Climax", NAME, "img/cards/DenO_Climax.png", 1, DESCRIPTION, CardType.POWER, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.DenO);
      this.tags.add(DCDmod.RiderCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[6]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      switch (use) {
         case 1:
            KamenRideDenOPower.retain = true;
            use = 2;
            break;
         case 2:
            BaseMod.MAX_HAND_SIZE += 10;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CardMaxSizeUpPower(p, 10)));
            use = 3;
            break;
         case 3:
            for(AbstractCard c : p.hand.group) {
               if (!c.freeToPlayOnce) {
                  c.freeToPlayOnce = true;
               }
            }

            p.hand.update();
            use = 4;
            break;
         case 4:
            int sd = 0;

            for(AbstractCard c : p.hand.group) {
               if (c.hasTag(DCDmod.DenOActionCard) || c.type == CardType.CURSE || c.type == CardType.STATUS) {
                  AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand));
                  ++sd;
               }
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, sd), sd));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, sd), sd));
            use = 5;
            break;
         case 5:
            int x = AbstractDungeon.player.exhaustPile.size();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, x), x));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, x), x));
            use = 1;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new DenO_Climax();
   }

   public void optionDecade() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = EXTENDED_DESCRIPTION[use];
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(0);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("DenO_Climax");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
