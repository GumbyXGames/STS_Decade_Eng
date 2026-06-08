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
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.Phone_Mark;
import dcd_eng.Vfx.Faiz_gunattack;
import java.util.ArrayList;
import java.util.List;

public class Faiz_Phone extends AbstractCustomCardWithType {
   public static final String ID = "Faiz_Phone";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Faiz_Phone.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 3;
   private static final int MAGIC_NUM = 3;
   private List<TooltipInfo> tips;

   public Faiz_Phone() {
      super("Faiz_Phone", NAME, "img/cards/Faiz_Phone.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ALL_ENEMY, AbstractCustomCardWithType.CardColorType.Faiz);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 3;
      this.baseMagicNumber = this.magicNumber = 3;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      int x = 3;
      if (this.upgraded) {
         x = 4;
      }

      for(int i = 0; i < this.magicNumber; ++i) {
         AbstractMonster monster = AbstractDungeon.getMonsters().getRandomMonster(true);
         AbstractDungeon.actionManager.addToTop(new VFXAction(p, new CleaveEffect(), 0.0F));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, x, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         if (p.hasPower("KamenRideFaizPower") && !monster.hasPower("Phone_Mark")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Phone_Mark(monster), 1));
         }
      }

      if (p.hasPower("KamenRideFaizPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_gunattack(), 0.0F));
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Faiz_Phone();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.baseMagicNumber = this.magicNumber = 6;
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = EXTENDED_DESCRIPTION[1];
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[2];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.baseMagicNumber = this.magicNumber = 3;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeDamage(1);
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         this.initializeDescription();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Faiz_Phone");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
