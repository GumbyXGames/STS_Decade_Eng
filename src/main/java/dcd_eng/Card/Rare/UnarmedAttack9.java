package dcd_eng.Card.Rare;

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
import dcd_eng.Vfx.Kuuga_UnarmedAttack9;

public class UnarmedAttack9 extends AbstractCustomCardWithType {
   public static final String ID = "UnarmedAttack9";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/UnarmedAttack9.png";
   private static final int COST = 1;
   private int s = 0;
   private int d = 0;

   public UnarmedAttack9() {
      super("UnarmedAttack9", NAME, "img/cards/UnarmedAttack9.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UnarmedCard);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (this.upgraded) {
         if (p.hasPower("Strength")) {
            this.s = p.getPower("Strength").amount;
            this.s *= 2;
         }

         if (p.hasPower("Dexterity")) {
            this.d = p.getPower("Dexterity").amount;
            this.d *= 2;
         }

         int x;
         if (this.s + this.d >= 100) {
            x = m.maxHealth;
         } else {
            x = m.maxHealth * (this.s + this.d) / 100;
         }

         if (x > m.currentHealth) {
            x = m.currentHealth;
         }

         m.currentHealth -= x;
      } else {
         if (p.hasPower("Strength")) {
            this.s = p.getPower("Strength").amount;
            this.s *= 2;
         }

         int x;
         if (this.s >= 100) {
            x = m.maxHealth;
         } else {
            x = m.maxHealth * this.s / 100;
         }

         if (x > m.currentHealth) {
            x = m.currentHealth;
         }

         m.currentHealth -= x;
      }

      if (p.hasPower("KamenRideKuugaPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_UnarmedAttack9(m), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 0, DamageType.HP_LOSS)));
         if (p.hasPower("RisingMightyPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
      } else {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, 0, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
      }

   }

   public AbstractCard makeCopy() {
      return new UnarmedAttack9();
   }

   public void optionDecade() {
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void optionKuuga() {
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
      this.setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.rawDescription = UPGRADE_DESCRIPTION;
         this.initializeDescription();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("UnarmedAttack9");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   }
}
