package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class SleepPower extends AbstractPower {
   public static final String POWER_ID = "SleepPower";
   public static final String NAME = "沉睡";
   public static final String DESCRIPTIONS = "敌人正陷入沉睡，被攻击将会醒来";
   private byte moveByte;
   private AbstractMonster.Intent moveIntent;

   public SleepPower(AbstractCreature owner, int amt) {
      this.name = "沉睡";
      this.ID = "SleepPower";
      this.owner = owner;
      this.amount = amt;
      this.updateDescription();
      this.type = PowerType.DEBUFF;
      this.img = ImageMaster.loadImage("img/powers/sleep.png");
      this.moveByte = 1;
      this.moveIntent = Intent.UNKNOWN;
      if (owner instanceof AbstractMonster) {
         AbstractMonster m = (AbstractMonster)owner;
         this.moveByte = m.nextMove;
         this.moveIntent = Intent.valueOf(m.intent.name());
         m.setMove((byte)127, Intent.SLEEP);
         m.createIntent();
         AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, (byte)127, Intent.SLEEP));
      }

   }

   public void updateDescription() {
      this.description = "敌人正陷入沉睡，被攻击将会醒来";
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
      if (this.owner instanceof AbstractMonster) {
         AbstractMonster m = (AbstractMonster)this.owner;
         m.setMove(this.moveByte, this.moveIntent);
         m.createIntent();
         AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
      }

      return super.onAttacked(info, damageAmount);
   }

   public void atStartOfTurn() {
      super.atStartOfTurn();
      if (this.owner instanceof AbstractMonster) {
         AbstractMonster m = (AbstractMonster)this.owner;
         this.moveByte = m.nextMove;
         this.moveIntent = Intent.valueOf(m.intent.name());
         m.setMove((byte)127, Intent.SLEEP);
         m.createIntent();
         AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, (byte)127, Intent.SLEEP));
      }

   }

   public void atEndOfTurn(boolean isPlayer) {
      super.atEndOfTurn(isPlayer);
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
   }

   public void onRemove() {
      super.onRemove();
      if (this.owner instanceof AbstractMonster) {
         AbstractMonster m = (AbstractMonster)this.owner;
         m.setMove(this.moveByte, this.moveIntent);
         m.createIntent();
         AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
      }

   }

   public void atEndOfRound() {
   }
}
