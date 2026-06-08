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
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KuugaSpecialPower;
import dcd_eng.Vfx.Kuuga_PegasusAttack;
import java.util.ArrayList;
import java.util.List;

public class Kuuga_PegasusBowgun extends AbstractCustomCardWithType {
   public static final String ID = "Kuuga_PegasusBowgun";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kuuga_PegasusBowgun.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 8;
   private List<TooltipInfo> tips;

   public Kuuga_PegasusBowgun() {
      super("Kuuga_PegasusBowgun", NAME, "img/cards/Kuuga_PegasusBowgun.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 8;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (DCDmod.AnimationTrigger || !p.hasPower("KuugaPegasusPower") && !p.hasPower("RisingPegasusPower")) {
         int x = 0;
         if (p.hasPower("KuugaPegasusPower")) {
            ++x;
         } else if (p.hasPower("RisingPegasusPower")) {
            x += 2;
         } else if (p.hasPower("DecadeViolentEmotionPower")) {
            ++x;
         }

         if (x > 0) {
            for(int i = 0; i < x; ++i) {
               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying) {
                     AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, DamageType.HP_LOSS), AttackEffect.SLASH_DIAGONAL));
                     if (p.hasPower("RisingMightyPower")) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new KuugaSpecialPower(monster, 1), 1));
                     }

                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new KuugaSpecialPower(monster, 1), 1));
                  }
               }
            }
         } else {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.HP_LOSS), AttackEffect.SLASH_DIAGONAL));
            if (p.hasPower("RisingMightyPower")) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
            }

            if (p.hasPower("KamenRideKuugaPower")) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new KuugaSpecialPower(m, 1), 1));
            }
         }
      } else {
         if (p.hasPower("KuugaPegasusPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_PegasusAttack(this.damage)));
         }

         if (p.hasPower("RisingPegasusPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_PegasusAttack(this.damage)));
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

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Kuuga_PegasusBowgun();
   }

   public void optionDecade() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[5];
      }

      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("KuugaPegasusPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      } else if (AbstractDungeon.player.hasPower("RisingPegasusPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[1];
         this.name = EXTENDED_DESCRIPTION[2];
         this.loadCardImage("img/cards/Rising_PegasusBowgun.png");
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionBlade() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionDenO() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionKiva() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.name = NAME;
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kuuga_PegasusBowgun.png");
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeDamage(3);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_PegasusBowgun");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
