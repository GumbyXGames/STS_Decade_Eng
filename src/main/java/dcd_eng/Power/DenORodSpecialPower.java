package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dcd_eng.DCDmod;
import dcd_eng.Vfx.DenO_RodSpecialAttack;

public class DenORodSpecialPower extends AbstractPower {
   public static final String POWER_ID = "DenORodSpecialPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DenORodSpecialPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "DenORodSpecialPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/Mark.png");
      this.updateDescription();
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   public void atStartOfTurn() {
      super.atStartOfTurn();
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner && info.owner != null && this.owner.currentBlock > 0) {
         int x = this.owner.currentBlock * 2;
         if (!DCDmod.AnimationTrigger && this.owner.hasPower("KamenRideDenOPower")) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_RodSpecialAttack()));
         }

         AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner, new DamageInfo(this.owner, x, DamageType.THORNS), AttackEffect.BLUNT_HEAVY));
      }

      return damageAmount;
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DenORodSpecialPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
