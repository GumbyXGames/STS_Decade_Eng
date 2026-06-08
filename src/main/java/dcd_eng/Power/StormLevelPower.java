package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
import dcd_eng.Card.Special.StormSpecialCard;
import dcd_eng.Characters.Decade;

public class StormLevelPower extends AbstractPower {
   public static final String POWER_ID = "StormLevelPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean AgitoPower = true;

   public StormLevelPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "StormLevelPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/AgitoLevelPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void stackPower(int stackAmount) {
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "StormLevelPower"));
      }

      this.fontScale = 8.0F;
      this.amount += stackAmount;
      if (this.amount >= 10) {
         this.amount = 10;
         this.AgitoPower = false;
      }

   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (target != this.owner && this.amount >= 6) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new SpecialStormPower(this.owner, 1), 1));
      }

   }

   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
      if (power.ID.equals("StormLevelPower")) {
         int lv = this.amount + 1;
         switch (lv) {
            case 2:
            case 7:
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1), 1));
               break;
            case 3:
               AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new StormSpecialCard(), 1));
               break;
            case 4:
            case 8:
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1), 1));
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 2), 2));
            case 5:
            case 6:
            default:
               break;
            case 9:
               AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new AgitoPower(), 1));
         }

         if (lv >= 10 && this.AgitoPower) {
            this.AgitoPower = false;
         }
      }

      if (this.amount >= 5 && power.ID.equals("AgitoFlamePower")) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(17);
      }

   }

   public void updateDescription() {
      int x = this.amount + 1;
      if (x > 11) {
         x = 11;
      }

      this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + DESCRIPTIONS[x];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("StormLevelPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
