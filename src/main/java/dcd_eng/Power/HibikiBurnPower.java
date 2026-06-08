package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;

public class HibikiBurnPower extends AbstractPower {
   public static final String POWER_ID = "HibikiBurnPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private AbstractCreature source;

   public HibikiBurnPower(AbstractCreature owner, int amt, AbstractCreature source) {
      this.name = NAME;
      this.ID = "HibikiBurnPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/fire.png");
      this.source = source;
      this.type = PowerType.DEBUFF;
      this.updateDescription();
   }

   public void updateDescription() {
      int x = this.owner.maxHealth * 100 / 16 / 100;
      if (x <= 0) {
         x = 1;
      }

      if (this.owner.isPlayer) {
         this.description = DESCRIPTIONS[0] + x + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[2] + x + DESCRIPTIONS[3];
      }

   }

   public void atStartOfTurn() {
      if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
         this.flashWithoutSound();
         int x = this.owner.maxHealth * 100 / 16 / 100;
         if (x <= 0) {
            x = 1;
         }

         AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.source, x, AttackEffect.FIRE));
      }

   }

   public void atEndOfRound() {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
   }

   public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
      if (type == DamageType.NORMAL) {
         damage *= 0.75F;
      }

      return super.atDamageFinalGive(damage, type);
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("HibikiBurnPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
