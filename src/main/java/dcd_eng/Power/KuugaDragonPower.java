package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.ReturnRandomNumberAction2;
import dcd_eng.Vfx.Kuuga_dash;

public class KuugaDragonPower extends AbstractPower {
   public static final String POWER_ID = "KuugaDragonPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private int x = 0;

   public KuugaDragonPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KuugaDragonPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KuugaDragonPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (!this.owner.hasPower("RisingDragonPower") || !this.owner.hasPower("KuugaDragonPower")) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying && monster.hasPower("KuugaSpecialPower") && monster.getPower("KuugaSpecialPower").amount > 3) {
               int x = monster.getPower("KuugaSpecialPower").amount - 3;
               AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(monster, monster, "KuugaSpecialPower", x));
            }
         }
      }

   }

   public int onAttacked(DamageInfo info, int damageAmount) {
      if (info.type != DamageType.HP_LOSS && info.owner != this.owner && info.owner != null && this.owner.hasPower("Dexterity")) {
         int power = this.owner.getPower("Dexterity").amount;
         power *= 2;
         if (ReturnRandomNumberAction2.ReturnRandomNumber() < power) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_dash(), 0.0F));
            return 0;
         }
      }

      return damageAmount;
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (this.x < 8) {
         ++this.x;
      }

      if (this.x >= 8) {
         this.x = -1;
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DragonDashPower(this.owner, 1), 1));
      }

      this.amount = this.x;
      this.updateDescription();
   }

   public float modifyBlock(float blockAmount) {
      int x = 0;
      if (this.owner.hasPower("Dexterity") && this.owner.getPower("Dexterity").amount > 0) {
         x -= this.owner.getPower("Dexterity").amount;
      }

      return (float)x;
   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KuugaDragonPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
