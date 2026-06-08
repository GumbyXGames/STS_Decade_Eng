package dcd_eng.Card.Rare;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dcd_eng.DCDmod;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Vfx.Axel_faiztoaxel;
import dcd_eng.Vfx.Faiz_axelsounds;

public class FormRideAxel extends AbstractCustomCardWithType {
   public static final String ID = "FormRideAxel";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRideAxel.png";
   private static final int COST = 2;

   public FormRideAxel() {
      super("FormRideAxel", NAME, "img/cards/FormRideAxel.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Faiz);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower") && SpecialRideBooker.axelformpoint < 1) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 5));
         AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(5));
         ++SpecialRideBooker.axelformpoint;
      } else if (p.hasPower("KamenRideFaizPower")) {
         CardCrawlGame.sound.playA("formride", 0.0F);
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_faiztoaxel(), 5.7F));
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_axelsounds(), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 3, false), 3));
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideFaizPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         } else if (SpecialFaizBox.AxelForm) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
            canUse = false;
         } else if (SpecialFaizBox.FaizPoint < 10) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[4];
            canUse = false;
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            if (SpecialRideBooker.axelformpoint >= 1) {
               this.cantUseMessage = EXTENDED_DESCRIPTION[3];
               canUse = false;
            } else {
               canUse = true;
            }
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new FormRideAxel();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRideAxel");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
