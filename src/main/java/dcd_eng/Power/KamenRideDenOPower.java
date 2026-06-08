package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.DenOAction;
import dcd_eng.Actions.DenOWingAction;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Card.Common.DenO_DenGasher;
import dcd_eng.Card.Rare.DenO_Climax;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Vfx.DenO_backtodcd;
import dcd_eng.Vfx.DenO_bokuni_tsurarete_miru;
import dcd_eng.Vfx.DenO_kotaewa_kiite_nai;
import dcd_eng.Vfx.DenO_nakerude;
import dcd_eng.Vfx.DenO_ore_sanjo;
import java.util.ArrayList;
import java.util.List;

public class KamenRideDenOPower extends AbstractPower {
   public static final String POWER_ID = "KamenRideDenOPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private static int x;
   public static boolean retain = false;
   private static int rt = 3;

   public KamenRideDenOPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KamenRideDenOPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/KamenRideDenOPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onRemove() {
      if (this.owner.hasPower("KamenRideDecadePower")) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_backtodcd(), 2.0F));
      }

      AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(this.owner, this.owner));
      AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "DenOSpecialPower"));
      retain = false;
   }

   public void atStartOfTurn() {
      SpecialRideBooker.isPlayerTurn = true;

      for(AbstractCard c : AbstractDungeon.player.hand.group) {
         if (c.cardID.equals("DenO_DenGasher")) {
            c.baseMagicNumber = 3;
            c.initializeDescription();
         }
      }

      for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
         if (c.cardID.equals("DenO_DenGasher")) {
            c.baseMagicNumber = 3;
            c.initializeDescription();
         }
      }

      for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
         if (c.cardID.equals("DenO_DenGasher")) {
            c.baseMagicNumber = 3;
            c.initializeDescription();
         }
      }

   }

   public void atEndOfTurn(boolean isPlayer) {
      SpecialRideBooker.isPlayerTurn = false;
      if (retain) {
         --rt;
      }

      if (rt <= 0) {
         retain = false;
         rt = 3;

         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.retain) {
               c.retain = false;
            }
         }
      }

      this.updateDescription();
   }

   public void atEndOfRound() {
      TurnTimer.atEndOfRound();
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (DenOSpecialPower.GunAttack) {
         this.DenOPowerUpAction();
      }

   }

   public void onAfterCardPlayed(AbstractCard usedCard) {
      int rn = ReturnRandomNumberAction.ReturnRandomNumber();
      if (usedCard.cardID.equals("DenO_Ax") || usedCard.cardID.equals("DenO_Sword") || usedCard.cardID.equals("DenO_Rod") || usedCard.cardID.equals("DenO_Gun")) {
         if ((double)rn <= (double)1.0F) {
            DenOSpecialPower.Form = 5;
            DenO_DenGasher.Form = 5;
            DenOSpecialPower.wing = true;
            AbstractDungeon.actionManager.addToBottom(new DenOWingAction());

            for(AbstractPower power : AbstractDungeon.player.powers) {
               if (!power.ID.equals("KamenRideDenOPower") && power != this && power.type != PowerType.BUFF && power.type != PowerType.valueOf("KamenRide")) {
                  AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, power.ID));
               }
            }
         } else {
            DenOSpecialPower.wing = false;
            switch (usedCard.cardID) {
               case "DenO_Sword":
                  DenOSpecialPower.Form = 1;
                  DenO_DenGasher.Form = 1;
                  break;
               case "DenO_Rod":
                  DenOSpecialPower.Form = 2;
                  DenO_DenGasher.Form = 2;
                  break;
               case "DenO_Ax":
                  DenOSpecialPower.Form = 3;
                  DenO_DenGasher.Form = 3;
                  break;
               case "DenO_Gun":
                  DenOSpecialPower.Form = 4;
                  DenO_DenGasher.Form = 4;
            }
         }

         if (x + 2 >= 7) {
            this.DenOSpecialAction(usedCard.cardID);
            x = 0;
         } else if ((double)rn < (double)5.0F) {
            this.DenOSpecialAction(usedCard.cardID);
            x += 2;
         } else {
            x += 2;
         }

         if (x >= 5) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new DenO_Climax(), 1));
            this.flash();
         }

         this.amount = x;
      }

   }

   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
      super.onAfterUseCard(card, action);
      if (card.cardID.equals("DenO_DenGasher") && DenO_DenGasher.Form == 1 && !card.exhaust && !card.exhaustOnUseOnce) {
         if (card.magicNumber >= 1) {
            action.reboundCard = true;
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
         }

         --card.baseMagicNumber;
         card.isMagicNumberModified = true;
         card.initializeDescription();
      }

      this.updateDescription();
   }

   public void onVictory() {
      CardCrawlGame.sound.playA("victory_normal", 0.0F);
      if ((double)ReturnRandomNumberAction.ReturnRandomNumber() > (double)5.0F) {
         CardCrawlGame.sound.playA("victory1", 0.0F);
      } else {
         CardCrawlGame.sound.playA("victory2", 0.0F);
      }

      TurnTimer.atNextBattle();
      Decade Decade = (Decade)AbstractDungeon.player;
      Decade.Trickster(3);
   }

   public void updateDescription() {
      if (retain) {
         this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + rt + DESCRIPTIONS[2];
      } else {
         this.description = DESCRIPTIONS[0];
      }

   }

   private void DenOSpecialAction(String name) {
      switch (name) {
         case "DenO_Ax":
            AbstractDungeon.actionManager.addToBottom(new DenOAction());
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_nakerude()));
            break;
         case "DenO_Sword":
            AbstractDungeon.actionManager.addToBottom(new DenOAction());
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_ore_sanjo()));
            break;
         case "DenO_Gun":
            AbstractDungeon.actionManager.addToBottom(new DenOAction());
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_kotaewa_kiite_nai()));
            break;
         case "DenO_Rod":
            AbstractDungeon.actionManager.addToBottom(new DenOAction());
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DenO_bokuni_tsurarete_miru()));
      }

   }

   public static void DenOAxAction() {
      AbstractMonster monster = AbstractDungeon.getMonsters().getRandomMonster(true);
      List<AbstractPower> PowerGroup = new ArrayList();
      if (!monster.powers.isEmpty()) {
         for(AbstractPower mp : monster.powers) {
            if (mp.type == PowerType.BUFF) {
               PowerGroup.add(mp);
            }
         }

         int x = PowerGroup.size();
         if (x != 0) {
            if (x == 1) {
               x = 0;
            } else {
               x = (int)Math.floor(Math.random() * (double)x);
            }

            AbstractPower xp = (AbstractPower)PowerGroup.get(x);
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(monster, monster, xp.ID));
         }
      }

   }

   private void DenOPowerUpAction() {
      x += 2;
      if (x >= 5) {
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new DenO_Climax(), 1));
      }

      if (x >= 7) {
         x = 0;
      }

      this.amount = x;
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KamenRideDenOPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
