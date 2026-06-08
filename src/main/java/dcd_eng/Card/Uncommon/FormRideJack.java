package dcd_eng.Card.Uncommon;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.BladeJackPower;
import dcd_eng.Power.JackFlightPower;
import dcd_eng.Vfx.Jack_henshin;

public class FormRideJack extends AbstractCustomCardWithType {
   public static final String ID = "FormRideJack";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRideJack.png";
   private static final int COST = 2;

   public FormRideJack() {
      super("FormRideJack", NAME, "img/cards/FormRideJack.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Blade);
      this.baseMagicNumber = this.magicNumber = 1;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower") && SpecialRideBooker.decadepoint > 0 && SpecialRideBooker.jackformpoint < 1) {
         SpecialRideBooker.decadepoint = 0;
         ++SpecialRideBooker.jackformpoint;
      } else if (p.hasPower("KamenRideBladePower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeJackPower(p), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new JackFlightPower(p), 1));
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Jack_henshin(p.drawX, p.drawY), 0.0F));
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideBladePower") && !p.hasPower("DecadeViolentEmotionPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         } else {
            if (p.hasPower("DecadeViolentEmotionPower") && SpecialRideBooker.decadepoint <= 0) {
               this.cantUseMessage = EXTENDED_DESCRIPTION[2];
               canUse = false;
            }

            if (p.hasPower("DecadeViolentEmotionPower") && SpecialRideBooker.jackformpoint >= 1) {
               this.cantUseMessage = EXTENDED_DESCRIPTION[3];
               canUse = false;
            }
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new FormRideJack();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[1];
         this.exhaust = true;
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.exhaust = false;
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRideJack");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
