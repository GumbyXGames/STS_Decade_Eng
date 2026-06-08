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
import dcd_eng.Actions.GetHibikiAttackCardAciton;
import dcd_eng.Actions.ReturnRandomNumberAction2;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.HibikiBurnPower;
import dcd_eng.Vfx.Hibiki_Attack2_sounds;
import dcd_eng.Vfx.Hibiki_attack2;
import java.util.ArrayList;
import java.util.List;

public class Hibiki_Attack2 extends AbstractCustomCardWithType {
   public static final String ID = "Hibiki_Attack2";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Hibiki_Attack2.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 5;
   private List<TooltipInfo> tips;

   public Hibiki_Attack2() {
      super("Hibiki_Attack2", NAME, "img/cards/Hibiki_Attack2.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Hibiki);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 5;
      this.baseMagicNumber = this.magicNumber = 1;
      this.damageType = DamageType.NORMAL;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      if (p.hasPower("HibikiKurenaiPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_attack2(m, this.damage, this.damageType, this.magicNumber), 0.0F));
         if (50 > ReturnRandomNumberAction2.ReturnRandomNumber()) {
            AbstractDungeon.actionManager.addToBottom(new GetHibikiAttackCardAciton());
         }
      } else {
         int d = this.damage;
         if (p.hasPower("DecadeViolentEmotionPower")) {
            if (50 > ReturnRandomNumberAction2.ReturnRandomNumber()) {
               AbstractDungeon.actionManager.addToBottom(new GetHibikiAttackCardAciton());
            }
         } else {
            d = this.damage / 2;
         }

         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.FIRE));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new HibikiBurnPower(m, this.magicNumber, p), this.magicNumber));

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying && monster != m) {
               AbstractDungeon.actionManager.addToTop(new DamageAction(monster, new DamageInfo(p, d, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(monster, p, new HibikiBurnPower(monster, this.magicNumber, p), this.magicNumber));
            }
         }
      }

      AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_Attack2_sounds(), 1.5F));
   }

   public AbstractCard makeCopy() {
      return new Hibiki_Attack2();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
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
      if (AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[2];
      }

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
         this.upgradeDamage(2);
         this.upgradeMagicNumber(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Attack2");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
