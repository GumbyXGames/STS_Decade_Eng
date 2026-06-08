package dcd_eng.Card.Common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KivaBasshaaPower;
import dcd_eng.Vfx.Kiva_BasshaaSounds;

public class FormRideBasshaa extends AbstractCustomCardWithType {
   public static final String ID = "FormRideBasshaa";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRideBasshaa.png";
   private static final int COST = 2;

   public FormRideBasshaa() {
      super("FormRideBasshaa", NAME, "img/cards/FormRideBasshaa.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kiva);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.baseBlock = 6;
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         int b = (int)((double)EnergyPanel.totalCount * (double)0.5F);
         EnergyPanel.totalCount -= b;
         this.addToBot(new GainBlockAction(p, p, b * this.block));
      } else if (p.hasPower("KamenRideKivaPower")) {
         CardCrawlGame.sound.playA("formride", 0.0F);
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kiva_BasshaaSounds(), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KivaBasshaaPower(p), 1));
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideKivaPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         }

         if (p.hasPower("KivaBasshaaPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
            canUse = false;
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
            if (EnergyPanel.totalCount == 0) {
               this.cantUseMessage = EXTENDED_DESCRIPTION[3];
               canUse = false;
            }
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new FormRideBasshaa();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = true;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[2];
         this.upgradeBaseCost(0);
         this.exhaust = false;
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.upgradeBaseCost(2);
      if (this.upgraded) {
         this.upgradeBaseCost(1);
      }

      this.exhaust = true;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRideBasshaa");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
