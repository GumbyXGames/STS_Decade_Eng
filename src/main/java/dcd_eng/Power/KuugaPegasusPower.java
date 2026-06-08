package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.RemoveDefendBuffAction;

public class KuugaPegasusPower extends AbstractPower {
   public static final String POWER_ID = "KuugaPegasusPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KuugaPegasusPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KuugaPegasusPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KuugaPegasusPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(this.owner, this.owner));
      AbstractDungeon.actionManager.addToBottom(new RemoveDefendBuffAction(this.owner, this.owner));
   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      return (int)((double)damageAmount * (double)1.5F);
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KuugaPegasusPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
