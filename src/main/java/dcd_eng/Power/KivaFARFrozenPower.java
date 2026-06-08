package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.KivaBasshaaFARAction;
import dcd_eng.Actions.UpdateDescriptionAction;

public class KivaFARFrozenPower extends AbstractPower {
   public static final String POWER_ID = "KivaFARFrozenPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean isStartRemove = false;

   public KivaFARFrozenPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaFARFrozenPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/FARice.png");
      this.type = PowerType.DEBUFF;
      this.updateDescription();
   }

   public void onRemove() {
      if (!this.isStartRemove) {
         int d = 0;
         if (this.owner.hasPower("KivaLacerationPower")) {
            d = this.owner.getPower("KivaLacerationPower").amount;
         }

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying && !monster.halfDead) {
               if (d > 0) {
                  this.addToBot(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, d * 2, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
               }

               this.addToBot(new ApplyPowerAction(monster, AbstractDungeon.player, new KivaLacerationPower(monster, 3, AbstractDungeon.player)));
               System.out.println("冰结运行");
            }
         }
      }

   }

   public void atStartOfTurn() {
      this.isStartRemove = true;
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
      AbstractMonster m = (AbstractMonster)this.owner;
      if (m.type == EnemyType.BOSS) {
         int d = 0;
         if (m.hasPower("KivaLacerationPower")) {
            d = m.getPower("KivaLacerationPower").amount;
         }

         if (d > 0) {
            this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, d, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new KivaLacerationPower(m, d / 2, AbstractDungeon.player), d / 2));
         }
      } else if (m.type == EnemyType.ELITE || m.type == EnemyType.NORMAL) {
         boolean isMinion = false;

         for(AbstractPower p : m.powers) {
            if (p.ID.equals("Minion")) {
               isMinion = true;
               break;
            }
         }

         if (isMinion) {
            this.addToBot(new DamageAction(m, new DamageInfo(AbstractDungeon.player, m.currentHealth, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
            this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, m.currentHealth));
         } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new XuanyunPower(m), 1));
         }
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      this.addToBot(new KivaBasshaaFARAction(this.owner));
      return damageAmount;
   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      this.addToBot(new UpdateDescriptionAction(this));
   }

   public void updateDescription() {
      if (this.owner.hasPower("KivaLacerationPower")) {
         int d = this.owner.getPower("KivaLacerationPower").amount * 2;
         this.description = DESCRIPTIONS[0] + d + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0] + "0" + DESCRIPTIONS[1];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaFARFrozenPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
