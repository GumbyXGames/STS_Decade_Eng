package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KivaLacerationPower;
import java.util.ArrayList;
import java.util.List;

public class Kiva_Bats extends AbstractCustomCardWithType {
   public static final String ID = "Kiva_Bats";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kiva_Bats.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 4;
   private List<TooltipInfo> tips;

   public Kiva_Bats() {
      super("Kiva_Bats", NAME, "img/cards/Kiva_Bats.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, AbstractCustomCardWithType.CardColorType.Kiva);
      this.baseDamage = 4;
      this.baseMagicNumber = this.magicNumber = 1;
      this.tags.add(DCDmod.RiderCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
         if (!mo.isDeadOrEscaped() && !mo.isDead && !mo.isDying && !mo.halfDead) {
            this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
            this.addToBot(new DamageAction(mo, new DamageInfo(mo, this.damage, DamageType.NORMAL)));
            this.addToBot(new DamageAction(mo, new DamageInfo(mo, this.damage, DamageType.NORMAL)));
            this.addToBot(new ApplyPowerAction(mo, p, new KivaLacerationPower(mo, this.magicNumber, p), this.magicNumber));
         }
      }

   }

   public AbstractCard makeCopy() {
      return new Kiva_Bats();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.baseDamage = 6;
         this.baseMagicNumber = this.magicNumber = 2;
         if (this.upgraded) {
            this.baseDamage = 8;
            this.baseMagicNumber = 3;
         }
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
      this.baseDamage = 6;
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 8;
         this.baseMagicNumber = 3;
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
         this.upgradeDamage(2);
         this.upgradeMagicNumber(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kiva_Bats");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
