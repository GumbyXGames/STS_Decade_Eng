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

public class KuugaTitanPower extends AbstractPower {
   public static final String POWER_ID = "KuugaTitanPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int x = 0;
   private int y = 0;

   public KuugaTitanPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KuugaTitanPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KuugaTitanPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.type == CardType.ATTACK) {
         AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(this.owner, this.owner));
      }

      AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(this.owner, 1, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
      if (card.cardID.equals("FinalAttackRide")) {
         this.amount = -1;
         this.x = 0;
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
      if (!info.owner.isPlayer) {
         this.x += this.y;
      }

      if (damageAmount > 0) {
         AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, damageAmount * 2));
         this.x += damageAmount;
      }

      this.amount = this.x;
      this.updateDescription();
      return damageAmount;
   }

   public float modifyBlock(float blockAmount) {
      return blockAmount * 2.0F;
   }

   public void updateDescription() {
      if (this.amount == -1) {
         this.description = DESCRIPTIONS[0] + "0" + DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KuugaTitanPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
