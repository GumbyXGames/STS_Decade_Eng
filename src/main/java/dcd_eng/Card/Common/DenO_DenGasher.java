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
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.DenGasherAxAction;
import dcd_eng.Actions.DenGasherDecadeAction;
import dcd_eng.Actions.ForceIntentAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType2;
import dcd_eng.Power.DenORodSpecialPower;
import dcd_eng.Power.SleepPower;
import dcd_eng.Vfx.DenGasher_Ax;
import dcd_eng.Vfx.DenGasher_Gun1;
import dcd_eng.Vfx.DenGasher_Gun2;
import dcd_eng.Vfx.DenGasher_Rod;
import dcd_eng.Vfx.DenGasher_Sword;
import dcd_eng.Vfx.DenGasher_Wing;
import java.util.ArrayList;
import java.util.List;

public class DenO_DenGasher extends AbstractCustomCardWithType2 {
   public static final String ID = "DenO_DenGasher";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DenGasher_Sword.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 6;
   private static final int MAGIC_NUM = 3;
   private List<TooltipInfo> tips;
   public static int Form = 0;

   public DenO_DenGasher() {
      super("DenO_DenGasher", NAME, "img/cards/DenGasher_Sword.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType2.CardColorType.DenO, AbstractCustomCardWithType2.IntentTypes.ATTACK);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 6;
      this.baseMagicNumber = this.magicNumber = 3;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.magicNumber = this.baseMagicNumber = 3;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying && !monster.halfDead) {
               AbstractDungeon.actionManager.addToBottom(new ForceIntentAction(monster, AbstractCustomCardWithType2.IntentTypes.ATTACK));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new DenGasherDecadeAction(this.damage));
      } else if (p.hasPower("KamenRideDenOPower")) {
         switch (Form) {
            case 0:
               AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
               break;
            case 1:
               if (p.hasPower("KamenRideDenOPower")) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenGasher_Sword()));
               }

               AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
               AbstractDungeon.actionManager.addToBottom(new ForceIntentAction(m, AbstractCustomCardWithType2.IntentTypes.ATTACK));
               break;
            case 2:
               int d = p.currentBlock * 2 + this.damage;
               if (p.hasPower("KamenRideDenOPower")) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenGasher_Rod(m, d)));
               } else if (m.intent == Intent.ATTACK || m.intent == Intent.ATTACK_BUFF || m.intent == Intent.ATTACK_DEBUFF || m.intent == Intent.ATTACK_DEFEND) {
                  AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, d, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DenORodSpecialPower(p)));
               }
               break;
            case 3:
               if (p.hasPower("KamenRideDenOPower")) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenGasher_Ax()));
               }

               AbstractDungeon.actionManager.addToBottom(new DenGasherAxAction(m, this.damage));
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
               break;
            case 4:
               int n = 3;
               if (m.intent == Intent.SLEEP || m.intent == Intent.STUN) {
                  n = 8;
               }

               if (!DCDmod.AnimationTrigger && p.hasPower("KamenRideDenOPower")) {
                  if (n == 3) {
                     AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenGasher_Gun1()));

                     for(int i = 0; i < n; ++i) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
                     }
                  } else {
                     AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenGasher_Gun2(m, this.damage)));
                  }
               } else {
                  for(int i = 0; i < n; ++i) {
                     AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
                  }
               }
               break;
            case 5:
               if (p.hasPower("KamenRideDenOPower")) {
                  AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenGasher_Wing()));
               }

               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying) {
                     AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
                     AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
                  }

                  if (!monster.isDead && !monster.isDying && m.intent != Intent.SLEEP) {
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new SleepPower(monster, 3), 3));
                  }
               }
         }
      } else {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
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
      return new DenO_DenGasher();
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.tags.remove(DCDmod.IntentCard);
      this.baseDamage = 6;
      this.name = NAME;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[7];
         this.target = CardTarget.ALL_ENEMY;
         this.baseDamage = 0;
         this.loadCardImage("img/cards/DenGasher_Sword.png");
         this.tags.add(DCDmod.IntentCard);
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      switch (Form) {
         case 1:
            this.rawDescription = EXTENDED_DESCRIPTION[2] + this.magicNumber + EXTENDED_DESCRIPTION[13];
            this.tags.add(DCDmod.IntentCard);
            this.baseDamage = 6;
            this.loadCardImage("img/cards/DenGasher_Sword.png");
            this.name = this.name + EXTENDED_DESCRIPTION[8];
            break;
         case 2:
            this.rawDescription = EXTENDED_DESCRIPTION[3];
            this.baseDamage = 0;
            this.loadCardImage("img/cards/DenGasher_Rod.png");
            this.name = this.name + EXTENDED_DESCRIPTION[9];
            break;
         case 3:
            this.rawDescription = EXTENDED_DESCRIPTION[4];
            this.baseDamage = 0;
            this.loadCardImage("img/cards/DenGasher_Ax.png");
            this.name = this.name + EXTENDED_DESCRIPTION[10];
            break;
         case 4:
            this.rawDescription = EXTENDED_DESCRIPTION[5];
            this.baseDamage = 1;
            this.loadCardImage("img/cards/DenGasher_Gun.png");
            this.name = this.name + EXTENDED_DESCRIPTION[11];
            break;
         case 5:
            this.rawDescription = EXTENDED_DESCRIPTION[6];
            this.baseDamage = 6;
            this.loadCardImage("img/cards/DenGasher_Wing.png");
            this.name = this.name + EXTENDED_DESCRIPTION[12];
      }

      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.target = CardTarget.ENEMY;
      this.baseDamage = 6;
      this.tags.remove(DCDmod.IntentCard);
      this.name = NAME;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(0);
      }

   }

   public void energychange() {
      if (this.freeToPlayOnce) {
         this.setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
      } else if (this.costForTurn != -1 && this.costForTurn <= 5) {
         int cost = this.costForTurn;
         this.setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
      } else {
         this.setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("DenO_DenGasher");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
