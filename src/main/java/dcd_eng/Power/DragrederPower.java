package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RyukiAttackAction;
import dcd_eng.Characters.Decade;
import dcd_eng.Vfx.Dragreder_attack;
import dcd_eng.Vfx.Dragreder_defend;
import dcd_eng.Vfx.Dragreder_disappear;
import dcd_eng.Vfx.Ryuki_defend;

public class DragrederPower extends AbstractPower {
   public static final String POWER_ID = "DragrederPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public DragrederPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "DragrederPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/DragrederPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_disappear(), 0.0F));
      CardCrawlGame.sound.playA("autovajindisappea", 0.0F);
   }

   public void atStartOfTurn() {
      this.updateDescription();
      if (this.amount == 0) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DragrederPower"));
      } else {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragrederPower", 1));
      }

   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (!card.hasTag(DCDmod.RyukiCard) && card.type == CardType.ATTACK && DCDmod.RyukiCardLv[2] >= 20 && this.owner.hasPower("KamenRideRyukiPower") && !card.cardID.equals("FinalAttackRide") && this.owner.hasPower("DragClawPower") && this.owner.getPower("DragClawPower").amount >= 2) {
         int x = 0;
         int d = 10;
         if (this.owner.hasPower("MirrorWorldPower")) {
            d += 10;
         }

         d *= 2;
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_attack(), 0.0F));
         CardCrawlGame.sound.playA("dragreder_attack", 0.0F);

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(this.owner, d, DamageType.THORNS), AttackEffect.FIRE));
               if (this.owner.hasPower("MirrorWorldPower")) {
                  x += 2;
               } else {
                  ++x;
               }
            }
         }

         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragClawPower", 2));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DragClawPower(this.owner, x * 2), x * 2));
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (this.owner.hasPower("DragShieldPower") && info.owner != this.owner) {
         AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "DragShieldPower", 1));
         if (Decade.cf != 24 && this.owner.hasPower("KamenRideRyukiPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_defend(), 0.0F));
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_defend(), 0.0F));
         }

         int hp = 10;
         if (this.owner.hasPower("MirrorWorldPower")) {
            hp = 20;
         }

         if (DCDmod.RyukiCardLv[1] > 0 && this.owner.hasPower("KamenRideRyukiPower")) {
            int d2 = DCDmod.RyukiCardLv[1];
            if (DCDmod.RyukiCardLv[1] >= 20) {
               d2 = 20;
            }

            hp += d2 * 3;
         }

         AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(info.owner, new DamageInfo(this.owner, this.owner.maxHealth * hp / 100, DamageType.THORNS), 2, AttackEffect.SMASH));
      }

      return damageAmount;
   }

   public void atEndOfTurn(boolean isPlayer) {
      int x = 0;
      int d = 10;
      if (this.owner.hasPower("MirrorWorldPower")) {
         d += 10;
      }

      if (DCDmod.RyukiCardLv[2] >= 10 && this.owner.hasPower("KamenRideRyukiPower")) {
         d *= 2;
      }

      AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_attack(), 0.0F));
      CardCrawlGame.sound.playA("dragreder_attack", 0.0F);

      for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
         if (!monster.isDead && !monster.isDying) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(this.owner, d, DamageType.THORNS), AttackEffect.FIRE));
            if (this.owner.hasPower("MirrorWorldPower")) {
               x += 2;
            } else {
               ++x;
            }

            if (DCDmod.RyukiCardLv[2] >= 10 && this.owner.hasPower("KamenRideRyukiPower")) {
               x *= 2;
            }
         }
      }

      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DragClawPower(this.owner, x), x));
      int b = 10;
      if (DCDmod.RyukiCardLv[1] >= 20 && this.owner.hasPower("DragShieldPower")) {
         b = 20;
      }

      AbstractDungeon.actionManager.addToTop(new GainBlockAction(this.owner, this.owner, b));
   }

   public void onVictory() {
      AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_disappear(), 0.0F));
      CardCrawlGame.sound.playA("autovajindisappea", 0.0F);
   }

   public void updateDescription() {
      if (this.owner.hasPower("MirrorWorldPower")) {
         this.description = DESCRIPTIONS[1];
      } else {
         this.description = DESCRIPTIONS[0];
      }

      if (DCDmod.RyukiCardLv[2] >= 10) {
         this.description = this.description + DESCRIPTIONS[2];
      }

      if (DCDmod.RyukiCardLv[2] >= 20) {
         this.description = this.description + DESCRIPTIONS[3];
      }

   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DragrederPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
