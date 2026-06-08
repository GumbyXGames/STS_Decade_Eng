package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class RisingTitanPower extends AbstractPower {
   public static final String POWER_ID = "RisingTitanPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int y = 0;

   public RisingTitanPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "RisingTitanPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/RisingTitanPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.type == CardType.ATTACK) {
         AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(this.owner, this.owner));
      }

      AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.owner, 2, DamageType.THORNS), AttackEffect.BLUNT_HEAVY));
      this.amount += 2;
      this.updateDescription();
      if (card.cardID.equals("FinalAttackRide")) {
         this.amount = 0;
         this.updateDescription();
      }

   }

   public void onRemove() {
      if (this.owner.hasPower("Barricade")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Barricade"));
      }

   }

   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
      this.y = (int)damage;
      return damage;
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner) {
         this.amount += this.y;
         if (damageAmount > 0) {
            int z = damageAmount * 4;
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, z));
         }
      } else if (damageAmount > 0) {
         int z = damageAmount * 4;
         AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, z));
      }

      this.updateDescription();
      return damageAmount;
   }

   public float modifyBlock(float blockAmount) {
      return blockAmount * 2.0F;
   }

   public void updateDescription() {
      if (this.amount <= 0) {
         this.description = DESCRIPTIONS[0] + "0" + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("RisingTitanPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
