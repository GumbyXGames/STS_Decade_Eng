package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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
import dcd_eng.Vfx.Hibiki_attack3;
import java.util.ArrayList;
import java.util.List;

public class Hibiki_Attack3 extends AbstractCustomCardWithType {
   public static final String ID = "Hibiki_Attack3";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Hibiki_Attack3.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 12;
   private List<TooltipInfo> tips;

   public Hibiki_Attack3() {
      super("Hibiki_Attack3", NAME, "img/cards/Hibiki_Attack3.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Hibiki);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 12;
      this.baseMagicNumber = this.magicNumber = 3;
      this.damageType = DamageType.NORMAL;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      if (p.hasPower("HibikiKurenaiPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Hibiki_attack3(m, this.damage, this.damageType, this.magicNumber), 0.0F));
      } else {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, AbstractDungeon.player, "HibikiBurnPower"));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.FIRE));
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (arg0.hasPower("HibikiBurnPower")) {
         if (!AbstractDungeon.player.hasPower("HibikiKurenaiPower") && !AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
            this.damage += this.damage;
            this.isDamageModified = true;
         } else {
            int x = arg0.getPower("HibikiBurnPower").amount / 10;
            if (x <= 0) {
               x = 1;
            }

            this.damage += this.damage * x;
            this.isDamageModified = true;
         }
      }

   }

   public AbstractCard makeCopy() {
      return new Hibiki_Attack3();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void optionDecade() {
   }

   public void optionKuuga() {
   }

   public void optionAgito() {
   }

   public void optionRyuki() {
   }

   public void optionFaiz() {
   }

   public void optionBlade() {
   }

   public void optionHibiki() {
   }

   public void optionKabuto() {
   }

   public void optionDenO() {
   }

   public void optionKiva() {
   }

   public void optionNeutral() {
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeDamage(3);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Attack3");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
