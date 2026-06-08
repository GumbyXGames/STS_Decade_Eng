package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import dcd_eng.Actions.KivaVampire2Action;
import dcd_eng.Actions.KivaVampireAction;

public class KivaHungry3Power extends AbstractPower {
   public static final String POWER_ID = "KivaHungry3Power";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public KivaHungry3Power(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaHungry3Power";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/VampireTeeth.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (info.owner == this.owner && target != this.owner && info.type == DamageType.NORMAL) {
         this.addToBot(new KivaVampireAction(this.owner, target, 0.4F));
      }

   }

   public void atStartOfTurn() {
      for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
         if (!mo.isDeadOrEscaped() && !mo.isDead && !mo.isDying && !mo.halfDead) {
            this.addToBot(new VFXAction(new BiteEffect(mo.hb.cX, mo.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(mo, 5, DamageType.HP_LOSS)));
            this.addToBot(new KivaVampire2Action(mo));
         }
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaHungry3Power");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
