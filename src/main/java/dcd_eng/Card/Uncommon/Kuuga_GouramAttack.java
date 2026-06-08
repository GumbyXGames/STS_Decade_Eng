package dcd_eng.Card.Uncommon;

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
import dcd_eng.Power.KuugaSpecialPower;
import dcd_eng.Vfx.Another_GouramAttack1;
import dcd_eng.Vfx.Kuuga_FAR_Background;
import dcd_eng.Vfx.Kuuga_GouramAttack1;
import java.util.ArrayList;
import java.util.List;

public class Kuuga_GouramAttack extends AbstractCustomCardWithType {
   public static final String ID = "Kuuga_GouramAttack";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kuuga_GouramAttack.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 16;
   private List<TooltipInfo> tips;

   public Kuuga_GouramAttack() {
      super("Kuuga_GouramAttack", NAME, "img/cards/Kuuga_GouramAttack.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 16;
      this.damageType = DamageType.NORMAL;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      if (!DCDmod.AnimationTrigger) {
         if (p.hasPower("KamenRideKuugaPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_GouramAttack1(p, m, this.damage)));
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(false, true)));
         } else {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Another_GouramAttack1(p, m, this.damage)));
         }
      } else {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.FIRE));
         if (p.hasPower("RisingMightyPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying && monster != m) {
               AbstractDungeon.actionManager.addToTop(new DamageAction(monster, new DamageInfo(p, this.damage / 2, DamageType.NORMAL), AttackEffect.FIRE));
               if (p.hasPower("RisingMightyPower")) {
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new KuugaSpecialPower(monster, 1), 1));
               }

               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new KuugaSpecialPower(monster, 1), 1));
            }
         }
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if ((AbstractDungeon.player.hasPower("KamenRideKuugaPower") || AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) && arg0.hasPower("KuugaSpecialPower") && arg0.getPower("KuugaSpecialPower").amount >= 3) {
         this.damage += this.damage;
         this.isDamageModified = true;
      }

   }

   public AbstractCard makeCopy() {
      return new Kuuga_GouramAttack();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = EXTENDED_DESCRIPTION[0];
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
         this.upgradeDamage(3);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_GouramAttack");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
