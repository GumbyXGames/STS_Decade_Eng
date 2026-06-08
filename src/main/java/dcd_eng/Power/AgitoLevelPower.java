package dcd_eng.Power;

import basemod.DevConsole;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Special.AgitoPower;

public class AgitoLevelPower extends AbstractPower {
   public static final String POWER_ID = "AgitoLevelPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean AgitoPower = true;
   public static int Lv = 0;

   public AgitoLevelPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "AgitoLevelPower";
      this.owner = owner;
      if (Lv > 0) {
         this.amount = Lv;
      } else {
         this.amount = amt;
      }

      this.img = ImageMaster.loadImage("img/powers/AgitoLevelPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      this.fontScale = 8.0F;
      this.amount += stackAmount;
      Lv = this.amount;
      DevConsole.logger.info(Lv);
   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      if (power.ID.equals("AgitoLevelPower")) {
         int lv = this.amount + 1;
         if (lv == 2) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1), 1));
         }

         if (lv >= 3 && this.AgitoPower) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new AgitoPower(), 1));
            this.AgitoPower = false;
         }
      }

   }

   public void updateDescription() {
      int x = this.amount + 1;
      if (x > 4) {
         x = 4;
      }

      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[x];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("AgitoLevelPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
