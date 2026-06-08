package dcd_eng.Card.Common;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KuugaSpecialPower;
import dcd_eng.Vfx.Kuuga_TitanAttack;
import java.util.ArrayList;
import java.util.List;

public class Kuuga_TitanSword extends AbstractCustomCardWithType {
   public static final String ID = "Kuuga_TitanSword";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kuuga_TitanSword.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 12;
   private static final int MAGIC_NUM = 2;
   private List<TooltipInfo> tips;

   public Kuuga_TitanSword() {
      super("Kuuga_TitanSword", NAME, "img/cards/Kuuga_TitanSword.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 12;
      this.baseMagicNumber = this.magicNumber = 2;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[8], EXTENDED_DESCRIPTION[9]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (DCDmod.AnimationTrigger || !p.hasPower("RisingTitanPower") && !p.hasPower("KuugaTitanPower")) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         if (p.hasPower("RisingTitanPower")) {
            if (p.hasPower("RisingMightyPower")) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            }

            for(int i = 0; i < 3; ++i) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, this.magicNumber, DamageType.THORNS), AttackEffect.BLUNT_HEAVY));
            }
         } else if (p.hasPower("KuugaTitanPower")) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, this.magicNumber, DamageType.THORNS), AttackEffect.BLUNT_HEAVY));
         } else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, this.magicNumber, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
         }
      } else {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_TitanAttack(m, this.damage, this.magicNumber), 0.0F));
      }

      if (p.hasPower("RisingMightyPower") || p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
      }

      if (p.hasPower("KamenRideKuugaPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeSlashPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
         this.isDamageModified = true;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Kuuga_TitanSword();
   }

   public void optionDecade() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) * 2;
         if (this.upgraded) {
            this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) * 2 + 2;
         }

         this.rawDescription = EXTENDED_DESCRIPTION[10];
      }

      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.name = NAME;
      if (AbstractDungeon.player.hasPower("KuugaTitanPower")) {
         this.baseDamage = (int)((double)12.0F + (double)AbstractDungeon.player.currentBlock * (double)0.5F);
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         if (this.upgraded) {
            this.baseDamage = 20 + AbstractDungeon.player.currentBlock;
            this.baseMagicNumber = this.magicNumber = 5;
            this.rawDescription = EXTENDED_DESCRIPTION[1];
         }

         this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      } else if (AbstractDungeon.player.hasPower("RisingTitanPower")) {
         this.baseDamage = (int)((double)14.0F + (double)AbstractDungeon.player.currentBlock * (double)0.5F);
         this.baseMagicNumber = this.magicNumber = 3;
         this.rawDescription = EXTENDED_DESCRIPTION[2];
         this.name = EXTENDED_DESCRIPTION[6];
         this.loadCardImage("img/cards/Rising_TitanSword.png");
         if (this.upgraded) {
            this.baseDamage = 22 + AbstractDungeon.player.currentBlock;
            this.baseMagicNumber = this.magicNumber = 4;
            this.rawDescription = EXTENDED_DESCRIPTION[3];
         }

         if (AbstractDungeon.player.hasPower("RisingMightyPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[4];
            this.name = EXTENDED_DESCRIPTION[7];
            this.loadCardImage("img/cards/Rising_DoubleTitanSword.png");
            if (this.upgraded) {
               this.rawDescription = EXTENDED_DESCRIPTION[5];
            }
         }
      } else {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
         this.baseMagicNumber = this.magicNumber = 2;
         if (this.upgraded) {
            this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
            this.baseMagicNumber = this.magicNumber = 1;
         }

         this.rawDescription = DESCRIPTION;
         this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionBlade() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionDenO() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionKiva() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.name = NAME;
      this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseDamage = 12 + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
         this.baseMagicNumber = this.magicNumber = 1;
      }

      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_TitanSword.png");
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeDamage(2);
         this.upgradeMagicNumber(-1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_TitanSword");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
