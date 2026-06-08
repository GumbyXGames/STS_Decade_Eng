package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.RemoveKamenRideAction;
import dcd_eng.Card.Special.PegasusAttack;
import dcd_eng.Vfx.Allformbacktodcd;

public class RisingPegasusPower extends AbstractPower {
   public static final String POWER_ID = "RisingPegasusPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private boolean trueremove = false;

   public RisingPegasusPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "RisingPegasusPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/RisingPegasusPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.trueremove) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Allformbacktodcd(), 2.0F));
         AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
         AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(this.owner, this.owner));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new KamenRideDecadePower(this.owner), 1));
         CardCrawlGame.sound.playA("driversounds", 0.0F);
         CardCrawlGame.sound.playA("test1", 0.0F);
      }

   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.hasTag(DCDmod.KamenRide) && !card.cardID.equals("Kuuga_Rising")) {
         this.trueremove = false;
      }

   }

   public void atStartOfTurn() {
      this.updateDescription();
      this.trueremove = true;
      AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "RisingPegasusPower", 1));
      if (this.amount != 1) {
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PegasusAttack(), 1));
      }

   }

   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
      return 0.0F;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("RisingPegasusPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
