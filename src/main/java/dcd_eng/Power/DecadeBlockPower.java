package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class DecadeBlockPower extends AbstractPower {
   public static final String POWER_ID = "DecadeBlockPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean Block = false;

   public DecadeBlockPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "DecadeBlockPower";
      this.owner = owner;
      this.amount = amt;
      this.updateDescription();
      this.img = ImageMaster.loadImage("img/powers/DecadeBlockPower.png");
      this.type = PowerType.BUFF;
   }

   public void stackPower(int stackAmount) {
      this.updateDescription();
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DecadeBlockPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
   }

   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
      if (damage >= (float)AbstractDungeon.player.currentBlock && AbstractDungeon.player.currentBlock > 0) {
         this.Block = true;
         return (float)((int)((double)damage * (double)0.5F));
      } else {
         return damage;
      }
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (this.Block) {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DecadeBlockPower", 1));
         this.Block = false;
      }

      return damageAmount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DecadeBlockPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
