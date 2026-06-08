package dcd_eng.Card.Rare;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Card.Special.PegasusAttack;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.RisingDragonPower;
import dcd_eng.Power.RisingMightyPower;
import dcd_eng.Power.RisingPegasusPower;
import dcd_eng.Power.RisingTitanPower;
import dcd_eng.Power.SuperRegenPower;
import dcd_eng.Vfx.Kuuga_MightySoundsAndAnimation;

public class Kuuga_Rising extends AbstractCustomCardWithType {
   public static final String ID = "Kuuga_Rising";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kuuga_Rising.png";
   private static final int COST = 3;

   public Kuuga_Rising() {
      super("Kuuga_Rising", NAME, "img/cards/Kuuga_Rising.png", 3, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SuperRegenPower(p, 3), 3));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
      } else if (p.hasPower("KuugaDragonPower")) {
         int x = p.getPower("KuugaDragonPower").amount;
         AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
         if (x > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingDragonPower(p, x), x));
         } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingDragonPower(p, 0), 0));
         }
      } else if (p.hasPower("KuugaPegasusPower")) {
         AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingPegasusPower(p, 3), 3));
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PegasusAttack(), 1));
      } else if (p.hasPower("KuugaTitanPower")) {
         int x = p.getPower("KuugaTitanPower").amount;
         AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
         if (x > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingTitanPower(p, x), x));
         } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingTitanPower(p, 0), 0));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p), 1));
      } else {
         if (!p.hasPower("RisingMightyPower")) {
            if (!DCDmod.AnimationTrigger) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_MightySoundsAndAnimation(), 1.79F));
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingMightyPower(p), 1));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SuperRegenPower(p, 3), 3));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
      }

      AbstractDungeon.actionManager.addToTop(new SFXAction("THUNDERCLAP", 0.05F));
      AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(p.drawX, p.drawY), 0.05F));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideKuugaPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new Kuuga_Rising();
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
         this.exhaust = false;
         this.rawDescription = UPGRADE_DESCRIPTION;
         this.initializeDescription();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_Rising");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
