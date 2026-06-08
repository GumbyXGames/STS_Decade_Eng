package dcd_eng.Card.Special;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RyukiAttackAction;
import dcd_eng.Card.Uncommon.DragClaw;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.DragClawPower;
import dcd_eng.Vfx.DragClaw_sounds;
import dcd_eng.Vfx.Ryuki_guardstrike;
import dcd_eng.Vfx.Ryuki_strike;
import java.util.ArrayList;
import java.util.List;

public class DragClaw_s extends AbstractCustomCardWithType {
   public static final String ID = "DragClaw_s";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DragClaw.png";
   private static final int COST = 2;
   private static final int ATTACK_DMG = 10;
   private List<TooltipInfo> tips;
   private List<TooltipInfo> tips2;

   public DragClaw_s() {
      super("DragClaw_s", NAME, "img/cards/DragClaw.png", 2, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Ryuki);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 10;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[6], EXTENDED_DESCRIPTION[7]));
      this.exhaust = true;
      this.tips2 = new ArrayList();
      this.tips2.add(new TooltipInfo(EXTENDED_DESCRIPTION[8], EXTENDED_DESCRIPTION[9]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      int x = 1;
      int d = this.damage;
      CardCrawlGame.sound.playA("attackride", 0.0F);
      AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 3, AttackEffect.FIRE));

      for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
         if (!monster.isDead && !monster.isDying && monster != m) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage / 2, this.damageTypeForTurn), AttackEffect.FIRE));
            d += this.damage - 5;
            ++x;
         }
      }

      if (p.hasPower("MirrorWorldPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragClawPower(p, x * 2), x * 2));
      } else {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragClawPower(p, x), x));
      }

      if (p.hasPower("DragShieldPower") && p.getPower("DragShieldPower").amount >= 2) {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "DragShieldPower", 2));
         AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, d));
         if (p.hasPower("KamenRideRyukiPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guardstrike(), 1.25F));
         }
      } else if (p.hasPower("KamenRideRyukiPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_strike(), 1.0F));
      }

      AbstractDungeon.actionManager.addToTop(new VFXAction(new DragClaw_sounds(), 1.5F));
   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         this.damage += 5;
         this.isDamageModified = true;
      }

      if (AbstractDungeon.player.hasPower("KamenRideRyukiPower") || AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.damage += 3;
         this.isDamageModified = true;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return DCDmod.RyukiCardLv[2] > 0 ? this.tips2 : this.tips;
   }

   public AbstractCard makeCopy() {
      return new DragClaw();
   }

   public void optionDecade() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionRyuki() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      if (DCDmod.RyukiCardLv[2] > 0) {
         int i = DCDmod.RyukiCardLv[2];
         if (i > 20) {
            i = 20;
         }

         this.upgraded = true;
         this.name = NAME + "Lv" + i;
         this.baseDamage = 9 + i;
         if (i >= 10) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[10];
         }

         if (i >= 20) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[11];
         }
      }

      this.initializeDescription();
   }

   public void optionFaiz() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionBlade() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionHibiki() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionKabuto() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionDenO() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionKiva() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void optionNeutral() {
      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[4];
         }
      } else if (AbstractDungeon.player.hasPower("DragShieldPower") && AbstractDungeon.player.getPower("DragShieldPower").amount >= 2) {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[3];
      } else {
         this.rawDescription = EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2];
      }

      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeDamage(3);
      }

   }

   public boolean canUpgrade() {
      return false;
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("DragClaw_s");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
