package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.DCDmod;
import dcd_eng.Vfx.Agito_storm;

public class AgitoStormPower extends AbstractPower {
   public static final String POWER_ID = "AgitoStormPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int ax = 0;

   public AgitoStormPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "AgitoStormPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/AgitoStormPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("StormLevelPower")) {
         AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "StormLevelPower"));
      }

   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (!this.owner.hasPower("AgitoFlamePower") && card.cardID.equals("Agito_StormHalberd") && !DCDmod.AnimationTrigger) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_storm(), 0.0F));
      }

   }

   public void onAfterCardPlayed(AbstractCard usedCard) {
      if (usedCard.type == CardType.ATTACK) {
         ++this.ax;
         this.updateDescription();
      }

      if (this.ax >= 5) {
         this.ax -= 5;
         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.owner, this.owner, new StormLevelPower(this.owner, 1), 1));
         this.updateDescription();
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0] + this.ax + DESCRIPTIONS[1];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("AgitoStormPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
