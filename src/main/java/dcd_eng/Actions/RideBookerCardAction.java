package dcd_eng.Actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import dcd_eng.Card.Basic.FinalAttackRide;
import dcd_eng.Card.Basic.KamenRide;
import dcd_eng.Card.Common.DenO_Ax;
import dcd_eng.Card.Common.DenO_Gun;
import dcd_eng.Card.Common.DenO_Rod;
import dcd_eng.Card.Common.DenO_Sword;
import dcd_eng.Card.Special.AgitoPower;
import dcd_eng.Card.Special.Blade_Beat_s;
import dcd_eng.Card.Special.Blade_Kick_s;
import dcd_eng.Card.Special.Blade_Mach_s;
import dcd_eng.Card.Special.Blade_Metal_s;
import dcd_eng.Card.Special.Blade_Slash_s;
import dcd_eng.Card.Special.Blade_Thunder;
import dcd_eng.Card.Special.CrimsonSmash;
import dcd_eng.Card.Special.Decade_Blast;
import dcd_eng.Card.Special.Decade_Slash;
import dcd_eng.Card.Special.DragClaw_s;
import dcd_eng.Card.Special.DragShield_s;
import dcd_eng.Card.Special.FlameSpecialCard;
import dcd_eng.Card.Special.PegasusAttack;
import dcd_eng.Card.Special.PegasusDefend;
import dcd_eng.Card.Special.PunchingUnit;
import dcd_eng.Card.Special.RideBooker_Attack;
import dcd_eng.Card.Special.RideBooker_Shoot;
import dcd_eng.Card.Special.SparkleCut;
import dcd_eng.Card.Special.StormSpecialCard;
import dcd_eng.Card.Uncommon.AttackRide;
import dcd_eng.Card.Uncommon.Dragreder;
import dcd_eng.Card.Uncommon.FormRide;
import dcd_eng.Helper.SpecialRideBooker;

public class RideBookerCardAction {
   private static CardGroup group;
   private static boolean RideBookerCard;
   private static boolean SelectCard;
   private static AbstractCard c;
   private static final CardStrings cardStrings;
   public static final String[] EXTENDED_DESCRIPTION;
   private static boolean[] ride;
   private static String[] RideCard;

   public static void RideBookerCard(String Rider) {
      if (RideBookerCard && Rider.equals("Decade") && AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         SelectCard = true;
         int Ride = 0;

         for(int i = 0; i < 9; ++i) {
            for(AbstractCard c : NewRideBookerCardAction.AllRideGroup.group) {
               if (c.cardID.equals(RideCard[i]) && !ride[i]) {
                  ride[i] = true;
                  ++Ride;
                  break;
               }
            }
         }

         System.out.println(Ride);
         if (Ride >= 3) {
            group.addToBottom(new KamenRide());
         }

         if (Ride >= 6) {
            group.addToBottom(new FormRide());
            group.addToBottom(new AttackRide());
         }

         if (Ride >= 9) {
            group.addToBottom(new FinalAttackRide());
         }

         group.addToBottom(new Decade_Blast());
         group.addToBottom(new Decade_Slash());
         group.addToBottom(new RideBooker_Shoot());
         group.addToBottom(new RideBooker_Attack());
         if (ride[7]) {
            RideBookerCardAction.c = new DenO_Gun();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[11] + EXTENDED_DESCRIPTION[12];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
            RideBookerCardAction.c = new DenO_Ax();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[10] + EXTENDED_DESCRIPTION[12];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
            RideBookerCardAction.c = new DenO_Rod();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[9] + EXTENDED_DESCRIPTION[12];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
            RideBookerCardAction.c = new DenO_Sword();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[7];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
         }

         if (ride[4]) {
            group.addToBottom(new Blade_Mach_s());
            group.addToBottom(new Blade_Metal_s());
            group.addToBottom(new Blade_Thunder());
            group.addToBottom(new Blade_Kick_s());
            group.addToBottom(new Blade_Beat_s());
            group.addToBottom(new Blade_Slash_s());
         }

         if (ride[3]) {
            RideBookerCardAction.c = new SparkleCut();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[2];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
            RideBookerCardAction.c = new PunchingUnit();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[1];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
            RideBookerCardAction.c = new CrimsonSmash();
            RideBookerCardAction.c.rawDescription = EXTENDED_DESCRIPTION[0];
            RideBookerCardAction.c.initializeDescription();
            group.addToBottom(RideBookerCardAction.c);
         }

         if (ride[2]) {
            group.addToBottom(new DragClaw_s());
            group.addToBottom(new DragShield_s());
            group.addToBottom(new Dragreder());
         }

         if (ride[1]) {
            group.addToBottom(new FlameSpecialCard());
            group.addToBottom(new StormSpecialCard());
            group.addToBottom(new AgitoPower());
         }

         if (ride[0]) {
            group.addToBottom(new PegasusAttack());
            group.addToBottom(new PegasusDefend());
         }

         AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("取消");
         RideBookerCard = false;
      }

   }

   public static void update() {
      if (SelectCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
         c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
         c.freeToPlayOnce = true;
         if (c.cardID.equals("FinalAttackRide")) {
            for(int i = 0; i < SpecialRideBooker.kamenpowerpoint - 1; ++i) {
               SpecialRideBooker.updatecurrentpoint();
            }
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
         c = null;
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         group.clear();
         SpecialRideBooker.updatecurrentpoint();
         SpecialRideBooker.decadepoint = SpecialRideBooker.currentpoint;
         RideBookerCard = true;
         SelectCard = false;

         for(int i = 0; i < 9; ++i) {
            ride[i] = false;
         }
      }

      if (SelectCard && AbstractDungeon.overlayMenu.cancelButton.isHidden) {
         group.clear();
         RideBookerCard = true;

         for(int i = 0; i < 9; ++i) {
            ride[i] = false;
         }

         SelectCard = false;
      }

   }

   static {
      group = new CardGroup(CardGroupType.CARD_POOL);
      RideBookerCard = true;
      SelectCard = false;
      c = null;
      ride = new boolean[]{false, false, false, false, false, false, false, false, false};
      RideCard = new String[]{"KamenRideKuuga", "KamenRideAgito", "KamenRideRyuki", "KamenRideFaiz", "KamenRideBlade", "KamenRideHibiki", "KamenRideKabuto", "KamenRideDenO", "KamenRideKiva"};
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBookerCardAction");
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
