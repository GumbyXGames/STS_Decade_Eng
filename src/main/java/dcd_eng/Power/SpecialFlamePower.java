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
import dcd_eng.Vfx.Agito_flame;
import dcd_eng.Vfx.Agito_flame_t;

public class SpecialFlamePower extends AbstractPower {
   public static final String POWER_ID = "SpecialFlamePower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   SpecialFlamePower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "SpecialFlamePower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/Mark.png");
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "SpecialFlamePower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (this.owner.hasPower("Strength") && info.owner != this.owner && info.owner != null) {
         int x = this.owner.getPower("Strength").amount;
         if (x > 0) {
            if (!DCDmod.AnimationTrigger && this.owner.hasPower("KamenRideAgitoPower")) {
               if (this.owner.hasPower("AgitoFlamePower") && !this.owner.hasPower("AgitoStormPower")) {
                  AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(), 0.0F));
               } else if (this.owner.hasPower("AgitoFlamePower") && this.owner.hasPower("AgitoStormPower")) {
                  AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame_t(), 0.0F));
               }
            }

            AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner, new DamageInfo(this.owner, x * this.amount, DamageType.THORNS), AttackEffect.BLUNT_HEAVY));
         }
      }

      return damageAmount;
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("SpecialFlamePower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
