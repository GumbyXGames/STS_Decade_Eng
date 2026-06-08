package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import dcd_eng.Vfx.AutoVajin_attack;
import dcd_eng.Vfx.AutoVajin_defend;
import dcd_eng.Vfx.AutoVajin_disappear;

public class AutoVajinPower extends AbstractPower {
   public static final String POWER_ID = "AutoVajinPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   public static boolean AutoVajinAttack = false;
   private boolean noformme;

   public AutoVajinPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "AutoVajinPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/AutoVajinPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_disappear(), 0.0F));
   }

   public void atStartOfTurn() {
      this.updateDescription();
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "AutoVajinPower"));
      } else {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "AutoVajinPower", 1));
      }

   }

   public void atEndOfTurn(boolean isPlayer) {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_defend(), 0.0F));
      AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.owner, this.owner, 8));
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.type == CardType.ATTACK && action.target != this.owner && AutoVajinAttack) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_attack(), 0.0F));

         for(int i = 0; i < 3; ++i) {
            CardCrawlGame.sound.playA("autovajinattack", 0.0F);
            AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new CleaveEffect(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(this.owner, 3, DamageType.THORNS)));
         }

         AutoVajinAttack = false;
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.owner != this.owner) {
         this.noformme = true;
      }

      return damageAmount;
   }

   public int onLoseHp(int damageAmount) {
      if (this.noformme) {
         this.noformme = false;
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "AutoVajinPower"));
         return 0;
      } else {
         return damageAmount;
      }
   }

   public void onVictory() {
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_disappear(), 0.0F));
      CardCrawlGame.sound.playA("autovajindisappear", 0.0F);
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("AutoVajinPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
