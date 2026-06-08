package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Vfx.DenO_Imagin;

public class DenOSpecialPower extends AbstractPower {
   public static final String POWER_ID = "DenOSpecialPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private static int BUFF = 0;
   private static int DEBUFF = 0;
   public static int Form = 0;
   static boolean GunAttack = false;
   private static int NowForm = 0;
   static boolean wing = false;

   public DenOSpecialPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "DenOSpecialPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/DenOSpecialPower.png");
      this.type = PowerType.valueOf("KamenRide");
      NowForm = Form;
      this.updateDescription();
   }

   public void updateDescription() {
      if (Form == 0 && NowForm == 0) {
         this.description = DESCRIPTIONS[0];
         this.img = ImageMaster.loadImage("img/powers/DenOSpecialPower.png");
      } else {
         int x = NowForm;
         int y = Form;
         if (x == 0) {
            x = 8;
         }

         if (y == 0) {
            y = 8;
         }

         this.description = DESCRIPTIONS[6] + DESCRIPTIONS[x] + DESCRIPTIONS[7] + DESCRIPTIONS[y];
      }

   }

   public void atStartOfTurn() {
      super.atStartOfTurn();
      GunAttack = false;
      NowForm = Form;
      BUFF = 0;
      DEBUFF = 0;

      for(AbstractPower power : AbstractDungeon.player.powers) {
         if (!power.ID.equals("KamenRideDenOPower") && !power.ID.equals("CardMaxSizeUpPower") && power != this) {
            if (power.type == PowerType.BUFF) {
               ++BUFF;
            } else if (power.type == PowerType.DEBUFF) {
               ++DEBUFF;
            }
         }
      }

      if (wing) {
         for(AbstractPower power : AbstractDungeon.player.powers) {
            if (!power.ID.equals("KamenRideDenOPower") && power != this && (power.type != PowerType.BUFF || power.type != PowerType.valueOf("KamenRide"))) {
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, power.ID));
            }
         }

         wing = false;
      } else {
         for(AbstractPower power : AbstractDungeon.player.powers) {
            if (!power.ID.equals("KamenRideDenOPower") && !power.ID.equals("CardMaxSizeUpPower") && power != this && power.type != PowerType.valueOf("KamenRide")) {
               AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, power.ID));
            }
         }
      }

      this.DenOSpecialAction();
   }

   public void onAfterCardPlayed(AbstractCard usedCard) {
      super.onAfterCardPlayed(usedCard);
      if (Form == 5) {
         this.img = ImageMaster.loadImage("img/powers/wing.png");
      } else {
         switch (NowForm) {
            case 1:
               this.img = ImageMaster.loadImage("img/powers/awe.png");
               break;
            case 2:
               this.img = ImageMaster.loadImage("img/powers/rod.png");
               break;
            case 3:
               this.img = ImageMaster.loadImage("img/powers/ax.png");
               break;
            case 4:
               this.img = ImageMaster.loadImage("img/powers/gun.png");
         }
      }

      this.updateDescription();
   }

   private void DenOSpecialAction() {
      if (NowForm != 0) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_Imagin(NowForm)));
      }

      switch (NowForm) {
         case 1:
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, BUFF * 3), BUFF * 3));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, BUFF * 3), BUFF * 3));
            this.img = ImageMaster.loadImage("img/powers/awe.png");
            break;
         case 2:
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, (BUFF + DEBUFF) * 5));
            this.img = ImageMaster.loadImage("img/powers/rod.png");
            break;
         case 3:
            AbstractDungeon.actionManager.addToBottom(new RegenAction(this.owner, DEBUFF * 15));
            this.img = ImageMaster.loadImage("img/powers/ax.png");
            break;
         case 4:
            if (BUFF > DEBUFF) {
               GunAttack = true;
            } else if (DEBUFF > BUFF) {
               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying) {
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, monster, new XuanyunPower(monster)));
                  }
               }
            } else {
               GunAttack = true;

               for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                  if (!monster.isDead && !monster.isDying) {
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, monster, new XuanyunPower(monster)));
                  }
               }
            }

            this.img = ImageMaster.loadImage("img/powers/gun.png");
            break;
         case 5:
            if (this.owner.hasPower("Strength")) {
               int p = this.owner.getPower("Strength").amount;
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, p), p));
            }

            if (this.owner.hasPower("Dexterity")) {
               int p = this.owner.getPower("Dexterity").amount;
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, p), p));
            }

            this.img = ImageMaster.loadImage("img/powers/wing.png");
      }

      Form = 0;
      this.updateDescription();
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("DenOSpecialPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
