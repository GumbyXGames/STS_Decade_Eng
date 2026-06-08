package dcd_eng.Card.Rare;

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
import dcd_eng.Patches.HibikiTaikoKeyEvent;
import dcd_eng.Power.HibikiKurenaiSpecialPower;

public class Hibiki_FEVER extends AbstractCustomCardWithType {
   public static final String ID = "Hibiki_FEVER";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FEVER.png";
   private static final int COST = 3;

   public Hibiki_FEVER() {
      super("Hibiki_FEVER", NAME, "img/cards/FEVER.png", 3, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Hibiki);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if ((!p.hasPower("HibikiKurenaiPower") || !p.hasPower("HibikiKurenaiSpecialPower")) && !p.hasPower("DecadeViolentEmotionPower")) {
         HibikiTaikoKeyEvent.ComboPoint += 10;
      } else {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiSpecialPower(p, 10), 10));
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideHibikiPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new Hibiki_FEVER();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         if (this.upgraded) {
            this.rawDescription = EXTENDED_DESCRIPTION[2];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1];
         }
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionRyuki() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionFaiz() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionBlade() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionHibiki() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionKabuto() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionDenO() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionKiva() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionNeutral() {
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(2);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_FEVER");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
