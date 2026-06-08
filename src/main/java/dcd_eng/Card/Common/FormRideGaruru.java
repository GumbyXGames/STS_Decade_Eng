package dcd_eng.Card.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KivaGaruruPower;
import dcd_eng.Vfx.Kiva_GaruruSounds;

public class FormRideGaruru extends AbstractCustomCardWithType {
   public static final String ID = "FormRideGaruru";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRideGaruru.png";
   private static final int COST = 2;
   private static int HP = 0;

   public FormRideGaruru() {
      super("FormRideGaruru", NAME, "img/cards/FormRideGaruru.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kiva);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         int d = (int)((double)p.currentHealth * (double)0.5F);
         this.addToBot(new DamageAction(p, new DamageInfo(p, d, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
         this.addToBot(new GainEnergyAction((int)((double)d * (double)0.25F)));
      } else if (p.hasPower("KamenRideKivaPower")) {
         CardCrawlGame.sound.playA("formride", 0.0F);
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kiva_GaruruSounds(), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KivaGaruruPower(p), 1));
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

         if (p.hasPower("KivaGaruruPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[1];
            canUse = false;
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
            if (p.currentHealth <= 7) {
               this.cantUseMessage = EXTENDED_DESCRIPTION[5];
               canUse = false;
            }
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new FormRideGaruru();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = true;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[2] + HP + EXTENDED_DESCRIPTION[3] + (int)((double)HP * (double)0.25F) + EXTENDED_DESCRIPTION[4];
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

   public void update() {
      super.update();
      if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         HP = (int)((double)AbstractDungeon.player.currentHealth * (double)0.5F);
         this.rawDescription = EXTENDED_DESCRIPTION[2] + HP + EXTENDED_DESCRIPTION[3] + (int)((double)HP * (double)0.25F) + EXTENDED_DESCRIPTION[4];
         this.initializeDescription();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRideGaruru");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
