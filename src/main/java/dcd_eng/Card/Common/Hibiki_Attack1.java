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
import dcd_eng.Actions.ReturnRandomNumberAction2;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.HibikiBurnPower;
import dcd_eng.Vfx.Hibiki_Attack1_sounds;
import dcd_eng.Vfx.Hibiki_attack1;
import java.util.ArrayList;
import java.util.List;

public class Hibiki_Attack1 extends AbstractCustomCardWithType {
   public static final String ID = "Hibiki_Attack1";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Hibiki_Attack1.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 5;
   private List<TooltipInfo> tips;

   public Hibiki_Attack1() {
      super("Hibiki_Attack1", NAME, "img/cards/Hibiki_Attack1.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ALL_ENEMY, AbstractCustomCardWithType.CardColorType.Hibiki);
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
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_attack1(this.damage, this.damageType, this.magicNumber), 0.0F));
         int x = p.getPower("HibikiKurenaiSpecialPower").amount;
         if (x > ReturnRandomNumberAction2.ReturnRandomNumber()) {
            for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
               if (!monster.isDead && !monster.isDying && monster.hasPower("HibikiBurnPower")) {
                  int c = monster.getPower("HibikiBurnPower").amount;
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new HibikiBurnPower(monster, c, p), c));
               }
            }
         }
      } else {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new HibikiBurnPower(monster, this.magicNumber, p), this.magicNumber));
            }
         }

         if (p.hasPower("DecadeViolentEmotionPower") && p.hasPower("Strength") && p.getPower("Strength").amount > 0) {
            int x = p.getPower("Strength").amount * 5;
            if (x > ReturnRandomNumberAction2.ReturnRandomNumber()) {
               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying && monster.hasPower("HibikiBurnPower")) {
                     int c = monster.getPower("HibikiBurnPower").amount;
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new HibikiBurnPower(monster, c, p), c));
                  }
               }
            }
         }
      }

      AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_Attack1_sounds(), 1.5F));
   }

   public AbstractCard makeCopy() {
      return new Hibiki_Attack1();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Attack1");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
