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
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.FlameSpecialPower;
import java.util.ArrayList;
import java.util.List;

public class Agito_FlameSaber extends AbstractCustomCardWithType {
   public static final String ID = "Agito_FlameSaber";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Agito_FlameSaber.png";
   private static final int COST = 1;
   private static final int MAGIC_NUM = 1;
   private List<TooltipInfo> tips;

   public Agito_FlameSaber() {
      super("Agito_FlameSaber", NAME, "img/cards/Agito_FlameSaber.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Agito);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 0;
      this.baseMagicNumber = this.magicNumber = 1;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameSpecialPower(p, this.magicNumber), this.magicNumber));
      if (p.hasPower("AgitoFlamePower")) {
         boolean Flame = true;

         for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals("FormRideFlame")) {
               AbstractDungeon.player.discardPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               Flame = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals("FormRideFlame") && Flame) {
               AbstractDungeon.player.drawPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c);
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               Flame = false;
               break;
            }
         }

         for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.cardID.equals("FormRideFlame") && Flame) {
               AbstractDungeon.player.exhaustPile.removeCard(c);
               AbstractDungeon.player.hand.addToTop(c.makeCopy());
               AbstractDungeon.player.hand.refreshHandLayout();
               AbstractDungeon.player.hand.applyPowers();
               break;
            }
         }
      }

      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeSlashPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
         this.isDamageModified = true;
      }

      if (AbstractDungeon.player.hasPower("Strength")) {
         this.damage -= AbstractDungeon.player.getPower("Strength").amount;
         this.isDamageModified = true;
      }

      if (AbstractDungeon.player.hasPower("Dexterity") && AbstractDungeon.player.getPower("Dexterity").amount > 0) {
         this.damage += AbstractDungeon.player.getPower("Dexterity").amount * 2;
         this.isDamageModified = true;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Agito_FlameSaber();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[3];
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("AgitoFlamePower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

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
         this.upgradeBaseCost(0);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Agito_FlameSaber");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
