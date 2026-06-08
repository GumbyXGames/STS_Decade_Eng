package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.KivaBasshaaFARAction;
import dcd_eng.Actions.UpdateDescriptionAction;

public class KivaFrozenPower extends AbstractPower {
   public static final String POWER_ID = "KivaFrozenPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean isStartRemove = false;

   public KivaFrozenPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaFrozenPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/ice.png");
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
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaFrozenPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
