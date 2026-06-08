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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TaikoAttackAction;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Patches.HibikiTaikoKeyEvent;
import dcd_eng.Power.HibikiBurnPower;
import dcd_eng.Power.HibikiKurenaiSpecialPower;
import dcd_eng.Power.HibikiRollPower;
import java.util.ArrayList;
import java.util.List;

public class Hibiki_Ongekibou extends AbstractCustomCardWithType {
   public static final String ID = "Hibiki_Ongekibou";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Hibiki_Ongekibou.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 5;
   private List<TooltipInfo> tips;
   private List<TooltipInfo> tips2;
   private List<TooltipInfo> tips3;

   public Hibiki_Ongekibou() {
      super("Hibiki_Ongekibou", NAME, "img/cards/Hibiki_Ongekibou.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Hibiki);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 5;
      this.baseMagicNumber = this.magicNumber = 1;
      this.damageType = DamageType.NORMAL;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
      this.tips2 = new ArrayList();
      this.tips2.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[3]));
      this.tips3 = new ArrayList();
      this.tips3.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[4]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.FIRE));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new HibikiBurnPower(m, this.magicNumber, p), this.magicNumber));
      if (p.hasPower("KamenRideHibikiPower") && !p.hasPower("HibikiKurenaiPower")) {
         ++HibikiTaikoKeyEvent.ComboPoint;
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiRollPower(p, 1), 1));
      } else if (p.hasPower("HibikiKurenaiPower")) {
         if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
            if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
               AbstractDungeon.actionManager.addToBottom(new TaikoAttackAction());
            } else {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiSpecialPower(p, this.damage), this.damage));
            }
         }
      } else if (p.hasPower("DecadeViolentEmotionPower") && (double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
         if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
         } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
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

   public AbstractCard makeCopy() {
      return new Hibiki_Ongekibou();
   }

   public List<TooltipInfo> getCustomTooltips() {
      if (AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("KamenRideHibikiPower") && AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
         return this.tips2;
      } else {
         return AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("DecadeViolentEmotionPower") ? this.tips3 : this.tips;
      }
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
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionHibiki() {
      if (AbstractDungeon.player.hasPower("KamnRideHibikiPower") && !AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      } else if (AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
         this.rawDescription = DESCRIPTION;
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
         this.upgradeDamage(3);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Ongekibou");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
