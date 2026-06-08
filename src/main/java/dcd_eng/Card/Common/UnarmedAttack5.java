package dcd_eng.Card.Common;

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
import dcd_eng.Vfx.Kuuga_UnarmedAttack5;

public class UnarmedAttack5 extends AbstractCustomCardWithType {
   public static final String ID = "UnarmedAttack5";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/UnarmedAttack5.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 9;

   public UnarmedAttack5() {
      super("UnarmedAttack5", NAME, "img/cards/UnarmedAttack5.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UnarmedCard);
      this.baseDamage = 9;
      this.baseMagicNumber = 3;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("KamenRideKuugaPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_UnarmedAttack5(m), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType)));
         if (p.hasPower("RisingMightyPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
      } else {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.BLUNT_HEAVY));
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("Dexterity") && AbstractDungeon.player.getPower("Dexterity").amount > 0) {
         if (this.upgraded) {
            this.damage += AbstractDungeon.player.getPower("Dexterity").amount * 4;
         } else {
            this.damage += AbstractDungeon.player.getPower("Dexterity").amount * 3;
         }

         this.isDamageModified = true;
      }

      if (AbstractDungeon.player.hasPower("BladeBeatPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeBeatPower").amount * 2;
         this.isDamageModified = true;
      }

   }

   public AbstractCard makeCopy() {
      return new UnarmedAttack5();
   }

   public void optionDecade() {
      this.damageType = DamageType.NORMAL;
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void optionKuuga() {
      if (AbstractDungeon.player.hasPower("KuugaPegasusPower") || AbstractDungeon.player.hasPower("RisingPegasusPower")) {
         this.damageType = DamageType.HP_LOSS;
      }

      this.setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
   }

   public void optionAgito() {
      this.setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
   }

   public void optionRyuki() {
      this.setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
   }

   public void optionFaiz() {
      this.setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
   }

   public void optionBlade() {
      this.setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
   }

   public void optionHibiki() {
      this.setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
   }

   public void optionKabuto() {
      this.setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
   }

   public void optionDenO() {
      this.setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
   }

   public void optionKiva() {
      this.setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
   }

   public void optionNeutral() {
      this.damageType = DamageType.NORMAL;
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeMagicNumber(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("UnarmedAttack5");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
