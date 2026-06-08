package dcd_eng.Card.Common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
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
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.RemoveHalfAttributeAction;
import dcd_eng.Card.Special.PegasusDefend;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KuugaPegasusPower;
import dcd_eng.Power.RisingPegasusPower;
import dcd_eng.Power.Rollpower;
import dcd_eng.Vfx.Kuuga_PegasusSoundsAndAnimation;

public class FormRidePegasus extends AbstractCustomCardWithType {
   public static final String ID = "FormRidePegasus";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRidePegasus.png";
   private static final int COST = 2;

   public FormRidePegasus() {
      super("FormRidePegasus", NAME, "img/cards/FormRidePegasus.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.baseMagicNumber = this.magicNumber = 5;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Rollpower(p, 2), 2));
      } else if (p.hasPower("KamenRideKuugaPower")) {
         CardCrawlGame.sound.playA("formride", 0.0F);
         if ((p.hasPower("RisingMightyPower") || p.hasPower("RisingDragonPower") || p.hasPower("RisingTitanPower")) && !p.hasPower("KuugaPegasusPower") && !p.hasPower("RisingPegasusPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
            AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingPegasusPower(p, 3), 3));
            if (DCDmod.AnimationTrigger) {
               Decade Decade = (Decade)p;
               Decade.Trickster(8);
               AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 0.5F));
            } else {
               AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 1.75F));
            }
         } else if (!p.hasPower("KuugaPegasusPower") && !p.hasPower("RisingPegasusPower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
            AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KuugaPegasusPower(p), 1));
            if (DCDmod.AnimationTrigger) {
               Decade Decade = (Decade)p;
               Decade.Trickster(8);
               AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 0.5F));
            } else {
               AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 1.75F));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new PegasusDefend(), 1, true, true));
      }

      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PegasusDefend(), 1));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideKuugaPower")) {
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
      return new FormRidePegasus();
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
      if (AbstractDungeon.player.hasPower("RisingMightyPower") || AbstractDungeon.player.hasPower("RisingDragonPower") || AbstractDungeon.player.hasPower("RisingTitanPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[1];
      }

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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRidePegasus");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
