package dcd_eng.Card.Common;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.Faiz_ShotAction;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.Flex1;
import dcd_eng.Power.KuugaSpecialPower;
import java.util.ArrayList;
import java.util.List;

public class Faiz_Shot extends AbstractCustomCardWithType {
   public static final String ID = "Faiz_Shot";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Faiz_Shot.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 8;
   private static final int MAGIC_NUM = 2;
   private List<TooltipInfo> tips;

   public Faiz_Shot() {
      super("Faiz_Shot", NAME, "img/cards/Faiz_Shot.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Faiz);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UnarmedCard);
      this.baseDamage = 8;
      this.baseMagicNumber = this.magicNumber = 2;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
      if (m.currentBlock > 0) {
         AbstractDungeon.actionManager.addToBottom(new Faiz_ShotAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL)));
      }

      if (p.hasPower("KamenRideFaizPower")) {
         SpecialFaizBox.FaizPoint += this.magicNumber;
      } else if (p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flex1(p, 2), 2));
      } else if (p.hasPower("KamenRideKuugaPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
         if (p.hasPower("RisingMightyPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
         }
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeBeatPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeBeatPower").amount * 2;
         this.isDamageModified = true;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Faiz_Shot();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
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
      this.rawDescription = EXTENDED_DESCRIPTION[0];
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
         this.upgradeDamage(3);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Faiz_Shot");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
