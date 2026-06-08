package dcd_eng.Card.Basic;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KuugaSpecialPower;
import dcd_eng.Vfx.Decade_attack;
import dcd_eng.Vfx.Kuuga_attack;
import java.util.ArrayList;
import java.util.List;

public class Decade_Attack extends AbstractCustomCardWithType {
   public static final String ID = "Decade_Attack";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Decade_Attack.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 6;
   private static final int UPGRADE_PLUS_DMG = 3;
   private int KRnumber = 0;
   private List<TooltipInfo> tips;

   public Decade_Attack() {
      super("Decade_Attack", NAME, "img/cards/Decade_Attack.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UnarmedCard);
      this.tags.add(CardTags.STARTER_STRIKE);
      this.baseDamage = 6;
      this.baseMagicNumber = this.magicNumber = 1;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (!p.hasPower("KamenRideKuugaPower")) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
      }

      switch (this.KRnumber) {
         case 1:
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_attack(m), 0.0F));
            if (!p.hasPower("KuugaPegasusPower") && !p.hasPower("RisingPegasusPower")) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
            } else {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.HP_LOSS)));
            }

            if (p.hasPower("RisingMightyPower")) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
            break;
         case 10:
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_attack(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
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
      return new Decade_Attack();
   }

   public void optionDecade() {
      this.KRnumber = 10;
      this.rawDescription = EXTENDED_DESCRIPTION[0];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void optionKuuga() {
      this.KRnumber = 1;
      if (!AbstractDungeon.player.hasPower("KuugaPegasusPower") && !AbstractDungeon.player.hasPower("RisingPegasusPower")) {
         this.rawDescription = DESCRIPTION;
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[3];
      }

      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
   }

   public void optionAgito() {
      this.KRnumber = 2;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
   }

   public void optionRyuki() {
      this.KRnumber = 3;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
   }

   public void optionFaiz() {
      this.KRnumber = 4;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
   }

   public void optionBlade() {
      this.KRnumber = 5;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
   }

   public void optionHibiki() {
      this.KRnumber = 6;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
   }

   public void optionKabuto() {
      this.KRnumber = 7;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
   }

   public void optionDenO() {
      this.KRnumber = 8;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
   }

   public void optionKiva() {
      this.KRnumber = 9;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
   }

   public void optionNeutral() {
      this.KRnumber = 0;
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeDamage(3);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Attack");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
