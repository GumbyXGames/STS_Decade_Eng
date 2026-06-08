package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KabutoStrengthPower extends AbstractPower {
   public static final String POWER_ID = "KabutoStrengthPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KabutoStrengthPower(AbstractCreature owner, int newAmount) {
      this.name = NAME;
      this.ID = "KabutoStrengthPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/Exercise1.png");
      this.updateDescription();
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (!this.owner.hasPower("KamenRideKabutoPower")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "KabutoStrengthPower"));
      }

   }

   public float atDamageGive(float damage, DamageInfo.DamageType type) {
      return damage * 4.0F;
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KabutoStrengthPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
