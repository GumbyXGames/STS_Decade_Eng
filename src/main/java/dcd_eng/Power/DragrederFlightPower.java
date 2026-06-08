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

public class DragrederFlightPower extends AbstractPower {
   public static final String POWER_ID = "DragrederFlightPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DragrederFlightPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "DragrederFlightPower";
      this.owner = owner;
      this.amount = -1;
      this.updateDescription();
      this.img = ImageMaster.loadImage("img/powers/flight.png");
      this.type = PowerType.valueOf("KamenRide");
   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      if (power.ID.equals("DragrederPower")) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(this.owner, 20, DamageType.THORNS), AttackEffect.FIRE));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DragClawPower(this.owner, 3), 3));
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DragrederFlightPower"));
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DragrederFlightPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
