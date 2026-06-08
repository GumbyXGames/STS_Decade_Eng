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
import dcd_eng.Power.KuugaRollpower;
import dcd_eng.Power.KuugaSpecialPower;
import dcd_eng.Vfx.Kuuga_DragonAttack;
import java.util.ArrayList;
import java.util.List;

public class Kuuga_DragonRod extends AbstractCustomCardWithType {
   public static final String ID = "Kuuga_DragonRod";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kuuga_DragonRod.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 2;
   private static final int MAGIC_NUM = 3;
   private List<TooltipInfo> tips;

   public Kuuga_DragonRod() {
      super("Kuuga_DragonRod", NAME, "img/cards/Kuuga_DragonRod.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 2;
      this.baseMagicNumber = this.magicNumber = 3;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (!p.hasPower("KuugaDragonPower") && !p.hasPower("RisingDragonPower")) {
         int x = this.magicNumber;
         if (p.hasPower("DecadeViolentEmotionPower") && m.hasPower("KuugaSpecialPower") && m.getPower("KuugaSpecialPower").amount > 5) {
            x = this.magicNumber + m.getPower("KuugaSpecialPower").amount / 5;
         }

         for(int i = 0; i < x; ++i) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         }
      } else {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KuugaRollpower(p), 1));
         if (p.hasPower("RisingDragonPower") && m.hasPower("KuugaSpecialPower") && m.getPower("KuugaSpecialPower").amount > 5) {
            this.magicNumber += m.getPower("KuugaSpecialPower").amount / 5;
         }

         if (!DCDmod.AnimationTrigger) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_DragonAttack(m, this.damage, this.magicNumber, false, p.drawX, p.drawY, m.drawX, m.drawY), 0.0F));
         } else {
            for(int i = 0; i < this.magicNumber; ++i) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            }
         }
      }

      if (p.hasPower("KamenRideKuugaPower") || p.hasPower("DecadeViolentEmotionPower")) {
         int x = this.magicNumber;
         if (p.hasPower("RisingMightyPower")) {
            x = this.magicNumber * 2;
         }

         for(int i = 0; i < x; ++i) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
         }
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
      return new Kuuga_DragonRod();
   }

   public void optionDecade() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[5];
      }

      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("KuugaDragonPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      }

      if (AbstractDungeon.player.hasPower("RisingDragonPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[1];
         this.name = EXTENDED_DESCRIPTION[2];
         this.loadCardImage("img/cards/Rising_DragonRod.png");
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionBlade() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionDenO() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionKiva() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_DragonRod.png");
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeMagicNumber(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_DragonRod");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
