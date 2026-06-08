package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class XuanyunPower extends AbstractPower {
   public static final String POWER_ID = "XuanyunPower";
   public static final String NAME = "眩晕";
   public static final String DESCRIPTIONS = "敌人将无法行动";
   private byte moveByte;
   private AbstractMonster.Intent moveIntent;

   public XuanyunPower(AbstractCreature owner) {
      this.name = "眩晕";
      this.ID = "XuanyunPower";
      this.owner = owner;
      this.amount = -1;
      this.updateDescription();
      this.type = PowerType.DEBUFF;
      this.img = ImageMaster.loadImage("img/powers/stun.png");
      this.moveByte = 1;
      this.moveIntent = Intent.UNKNOWN;
      if (owner instanceof AbstractMonster && (!owner.hasPower("XuanyunResistancePower") || owner.getPower("XuanyunResistancePower").amount != -1)) {
         AbstractMonster m = (AbstractMonster)owner;
         this.moveByte = m.nextMove;
         this.moveIntent = Intent.valueOf(m.intent.name());
         m.setMove((byte)127, Intent.STUN);
         m.createIntent();
         AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, (byte)127, Intent.STUN));
      }

   }

   public void updateDescription() {
      this.description = "敌人将无法行动";
   }

   public void stackPower(int stackAmount) {
      super.stackPower(stackAmount);
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
      }

   }

   public void onRemove() {
      super.onRemove();
      if (!this.owner.hasPower("XuanyunResistancePower") || this.owner.getPower("XuanyunResistancePower").amount != -1) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new XuanyunResistancePower(this.owner, 1)));
      }

   }

   public void atEndOfRound() {
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
      if ((!this.owner.hasPower("XuanyunResistancePower") || this.owner.getPower("XuanyunResistancePower").amount != -1) && this.owner instanceof AbstractMonster) {
         AbstractMonster m = (AbstractMonster)this.owner;
         m.setMove(this.moveByte, this.moveIntent);
         m.createIntent();
         AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, this.moveByte, this.moveIntent));
      }

   }
}
