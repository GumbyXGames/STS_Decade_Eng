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
import dcd_eng.Actions.KivaVampireAction;

public class KivaLacerationPower extends AbstractPower {
   public static final String POWER_ID = "KivaLacerationPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private AbstractCreature source;

   public KivaLacerationPower(AbstractCreature owner, int amt, AbstractCreature source) {
      this.name = NAME;
      this.ID = "KivaLacerationPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/KivaLacerationPower.png");
      this.source = source;
      this.type = PowerType.DEBUFF;
      this.updateDescription();
   }

   public void updateDescription() {
      if (this.amount >= 5) {
         int m = this.amount / 5;
         if (m > 3) {
            m = 3;
         }

         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + m + DESCRIPTIONS[3];
      } else {
         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (this.amount >= 5) {
         float m = (float)this.amount / 5.0F / 10.0F;
         if (m >= 3.0F) {
            m = 3.0F;
         }

         if (info.owner != this.owner && info.type == DamageType.NORMAL) {
            if (this.source.hasPower("KivaGaruruPower") && this.source.hasPower("KivaHungry3Power")) {
               m *= 2.0F;
            }

            this.addToBot(new KivaVampireAction(info.owner, this.owner, m));
         }
      }

      return damageAmount;
   }

   public void atStartOfTurn() {
      if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
         this.flashWithoutSound();
         int x = this.amount;
         if (x <= 0) {
            x = 1;
         }

         AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(this.owner, this.source, x, AttackEffect.SLASH_HORIZONTAL));
      }

   }

   public void atEndOfRound() {
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaLacerationPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
