package dcd_eng.Card.Common;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
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
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.RyukiAttackAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.DragClawPower;
import dcd_eng.Power.DragrederFlightPower;
import dcd_eng.Vfx.Dragreder_attack;
import java.util.ArrayList;
import java.util.List;

public class Ryuki_DragSaber extends AbstractCustomCardWithType {
   public static final String ID = "Ryuki_DragSaber";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ryuki_DragSaber.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 8;
   private static final int BLOCK_AMT = 9;
   private static final int MAGIC_NUM = 3;
   private List<TooltipInfo> tips;
   private List<TooltipInfo> tips2;

   public Ryuki_DragSaber() {
      super("Ryuki_DragSaber", NAME, "img/cards/Ryuki_DragSaber.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Ryuki);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseMagicNumber = this.magicNumber = 3;
      this.baseDamage = 8;
      this.baseBlock = 9;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[3]));
      this.tips2 = new ArrayList();
      this.tips2.add(new TooltipInfo(EXTENDED_DESCRIPTION[4], EXTENDED_DESCRIPTION[5]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (!p.hasPower("KamenRideRyukiPower") && !p.hasPower("DecadeViolentEmotion")) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
      } else {
         if (p.hasPower("DragClawPower") && !p.hasPower("DragShieldPower")) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "DragClawPower"));
            AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 5, AttackEffect.BLUNT_HEAVY));
         } else if (p.hasPower("DragShieldPower")) {
            AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 1, AttackEffect.BLUNT_HEAVY));
            AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "DragShieldPower", 1));
         } else {
            AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 1, AttackEffect.BLUNT_HEAVY));
         }

         int x1 = DCDmod.RyukiCardLv[0];
         if (x1 >= 10 && p.hasPower("KamenRideRyukiPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragrederFlightPower(p), 1));
         }

         if (x1 >= 20 && p.hasPower("KamenRideRyukiPower") && p.hasPower("DragrederPower")) {
            int rn = ReturnRandomNumberAction.ReturnRandomNumber();
            if ((double)rn < (double)5.0F) {
               int x = 0;
               int d = 10;
               if (p.hasPower("MirrorWorldPower")) {
                  d += 10;
               }

               if (DCDmod.RyukiCardLv[2] >= 10 && p.hasPower("KamenRideRyukiPower")) {
                  d *= 2;
               }

               AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_attack(), 0.0F));
               CardCrawlGame.sound.playA("dragreder_attack", 0.0F);

               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying) {
                     AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, d, DamageType.THORNS), AttackEffect.FIRE));
                     if (p.hasPower("MirrorWorldPower")) {
                        x += 2;
                     } else {
                        ++x;
                     }

                     if (DCDmod.RyukiCardLv[2] >= 10 && p.hasPower("KamenRideRyukiPower")) {
                        x *= 2;
                     }
                  }
               }

               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragClawPower(p, x), x));
            } else {
               int b = 10;
               if (DCDmod.RyukiCardLv[1] >= 20 && p.hasPower("DragShieldPower")) {
                  b = 20;
               }

               AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, b));
            }
         }
      }

      AbstractDungeon.actionManager.addToTop(new RegenAction(p, this.magicNumber));
   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeSlashPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
         this.isDamageModified = true;
      }

      if (AbstractDungeon.player.hasPower("MirrorWorldPower")) {
         this.damage += 5;
         this.isDamageModified = true;
      }

      if ((AbstractDungeon.player.hasPower("KamenRideRyukiPower") || AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) && AbstractDungeon.player.hasPower("DragClawPower") && !AbstractDungeon.player.hasPower("DragShieldPower")) {
         this.damage += AbstractDungeon.player.getPower("DragClawPower").amount * 3;
         this.isDamageModified = true;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return DCDmod.RyukiCardLv[0] > 0 ? this.tips2 : this.tips;
   }

   public AbstractCard makeCopy() {
      return new Ryuki_DragSaber();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         if (AbstractDungeon.player.hasPower("DragClawPower") && !AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[0];
         } else if (AbstractDungeon.player.hasPower("DragShieldPower")) {
            this.rawDescription = EXTENDED_DESCRIPTION[1];
         }
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
      if (AbstractDungeon.player.hasPower("DragClawPower") && !AbstractDungeon.player.hasPower("DragShieldPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
      } else if (AbstractDungeon.player.hasPower("DragShieldPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[1];
      } else {
         this.rawDescription = DESCRIPTION;
      }

      if (DCDmod.RyukiCardLv[0] > 0) {
         int i = DCDmod.RyukiCardLv[0];
         if (i > 20) {
            i = 20;
         }

         this.upgraded = true;
         this.name = NAME + "Lv" + i;
         this.baseDamage = 9 + i;
         if (i >= 10) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[6];
         }

         if (i >= 20) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[7];
         }
      }

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
   }

   public boolean canUpgrade() {
      return false;
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Ryuki_DragSaber");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
