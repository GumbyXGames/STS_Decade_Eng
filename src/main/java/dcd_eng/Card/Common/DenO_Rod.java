package dcd_eng.Card.Common;

import basemod.BaseMod;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.CardMaxSizeUpPower;
import dcd_eng.Power.KamenRideDenOPower;
import java.util.ArrayList;
import java.util.List;

public class DenO_Rod extends AbstractCustomCardWithType {
   public static final String ID = "DenO_Rod";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DenO_Rod.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;

   public DenO_Rod() {
      super("DenO_Rod", NAME, "img/cards/DenO_Rod.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.DenO);
      this.tags.add(DCDmod.RiderCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      int rn = ReturnRandomNumberAction.ReturnRandomNumber();
      if (p.hasPower("DecadeViolentEmotionPower")) {
         BaseMod.MAX_HAND_SIZE += 2;
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CardMaxSizeUpPower(p, 2)));
         AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
         c.freeToPlayOnce = true;
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
         if ((double)rn < (double)5.0F) {
            KamenRideDenOPower.DenOAxAction();
         }
      } else if ((double)rn < (double)5.0F && !p.hasPower("KamenRideDenOPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new DenO_Rod();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[3];
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
      this.rawDescription = EXTENDED_DESCRIPTION[2];
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
         this.upgradeBaseCost(0);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("DenO_Rod");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
