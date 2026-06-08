package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UnarmedAttack3Power extends AbstractPower {
   public static final String POWER_ID = "UnarmedAttack3Power";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public UnarmedAttack3Power(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "UnarmedAttack3Power";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/BrokenRibs.png");
      this.updateDescription();
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   public void atEndOfRound() {
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "UnarmedAttack3Power"));
   }

   public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
      if (type == DamageType.NORMAL) {
         damage *= 0.4F;
      }

      return super.atDamageFinalGive(damage, type);
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("UnarmedAttack3Power");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
