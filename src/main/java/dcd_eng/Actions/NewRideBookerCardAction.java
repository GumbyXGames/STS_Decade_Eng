package dcd_eng.Actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.localization.CardStrings;
import dcd_eng.DCDmod;
import dcd_eng.Card.Basic.FinalAttackRide;
import dcd_eng.Card.Common.DenO_Ax;
import dcd_eng.Card.Common.DenO_Gun;
import dcd_eng.Card.Common.DenO_Rod;
import dcd_eng.Card.Common.DenO_Sword;
import dcd_eng.Card.Common.KamenRideDecade;
import dcd_eng.Card.KamenRide.Agito_s;
import dcd_eng.Card.KamenRide.Blade_s;
import dcd_eng.Card.KamenRide.Decade_s;
import dcd_eng.Card.KamenRide.DenO_s;
import dcd_eng.Card.KamenRide.Faiz_s;
import dcd_eng.Card.KamenRide.Hibiki_s;
import dcd_eng.Card.KamenRide.Kabuto_s;
import dcd_eng.Card.KamenRide.Kiva_s;
import dcd_eng.Card.KamenRide.Kuuga_s;
import dcd_eng.Card.KamenRide.Ryuki_s;
import dcd_eng.Card.SelectCard.BladeBeat_s;
import dcd_eng.Card.SelectCard.BladeKick_s;
import dcd_eng.Card.SelectCard.BladeMach_s;
import dcd_eng.Card.SelectCard.BladeMetal_s;
import dcd_eng.Card.SelectCard.BladeSlash_s;
import dcd_eng.Card.SelectCard.BladeThunder_s;
import dcd_eng.Card.SelectCard.DenO_Ax_s;
import dcd_eng.Card.SelectCard.DenO_Gun_s;
import dcd_eng.Card.SelectCard.DenO_Rod_s;
import dcd_eng.Card.SelectCard.DenO_Sword_s;
import dcd_eng.Card.Special.Blade_Beat_s;
import dcd_eng.Card.Special.Blade_Kick_s;
import dcd_eng.Card.Special.Blade_Mach_s;
import dcd_eng.Card.Special.Blade_Metal_s;
import dcd_eng.Card.Special.Blade_Slash_s;
import dcd_eng.Card.Special.Blade_Thunder;
import dcd_eng.Card.Special.DragClaw_s;
import dcd_eng.Card.Special.DragShield_s;
import dcd_eng.Card.Special.RideBooker_Attack;
import dcd_eng.Card.Special.RideBooker_Shoot;
import dcd_eng.Card.Uncommon.AttackRide;
import dcd_eng.Card.Uncommon.Dragreder;
import dcd_eng.Card.Uncommon.KamenRideAgito;
import dcd_eng.Card.Uncommon.KamenRideBlade;
import dcd_eng.Card.Uncommon.KamenRideDenO;
import dcd_eng.Card.Uncommon.KamenRideFaiz;
import dcd_eng.Card.Uncommon.KamenRideHibiki;
import dcd_eng.Card.Uncommon.KamenRideKabuto;
import dcd_eng.Card.Uncommon.KamenRideKiva;
import dcd_eng.Card.Uncommon.KamenRideKuuga;
import dcd_eng.Card.Uncommon.KamenRideRyuki;
import dcd_eng.Helper.SpecialRideBooker;

public class NewRideBookerCardAction {
   private static CardGroup group;
   private static boolean RideBookerCard;
   private static boolean SelectCard;
   private static final CardStrings cardStrings;
   public static final String[] EXTENDED_DESCRIPTION;
   public static CardGroup DecadeGroup;
   public static CardGroup HumanGroup;
   public static CardGroup RyukiGroup;
   public static CardGroup BladeGroup;
   public static CardGroup DenOGroup;
   public static CardGroup AllRideGroup;
   public static CardGroup BasicGroup;
   public static String[] RouzeCard;

   public static void BladeJackAction() {
      int UseCardNumber = 0;
      boolean[] R = new boolean[]{false, false, false, false, false, false};

      for(int i = 0; i < 6; ++i) {
         for(AbstractCard c : BladeGroup.group) {
            if (c.cardID.equals(RouzeCard[UseCardNumber])) {
               R[UseCardNumber] = true;
               break;
            }
         }

         if (!R[UseCardNumber]) {
            AbstractCard c = null;
            switch (RouzeCard[UseCardNumber]) {
               case "BladeSlash_s":
                  c = new BladeSlash_s();
                  break;
               case "BladeBeat_s":
                  c = new BladeBeat_s();
                  break;
               case "BladeKick_s":
                  c = new BladeKick_s();
                  break;
               case "BladeThunder_s":
                  c = new BladeThunder_s();
                  break;
               case "BladeMetal_s":
                  c = new BladeMetal_s();
                  break;
               case "BladeMach_s":
                  c = new BladeMach_s();
            }

            BladeGroup.addToBottom(c);
            break;
         }

         ++UseCardNumber;
      }

   }

   static void RideGroup(String Rider) {
      switch (Rider) {
         case "human":
            HumanGroup.clear();
            HumanGroup.addToBottom(new Decade_s());

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideKuuga")) {
                  HumanGroup.addToBottom(new Kuuga_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideAgito")) {
                  HumanGroup.addToBottom(new Agito_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideRyuki")) {
                  HumanGroup.addToBottom(new Ryuki_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideFaiz")) {
                  HumanGroup.addToBottom(new Faiz_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideBlade")) {
                  HumanGroup.addToBottom(new Blade_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideHibiki")) {
                  HumanGroup.addToBottom(new Hibiki_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideKabuto")) {
                  HumanGroup.addToBottom(new Kabuto_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideDenO")) {
                  HumanGroup.addToBottom(new DenO_s());
                  break;
               }
            }

            for(AbstractCard c : AllRideGroup.group) {
               if (c.cardID.equals("KamenRideKiva")) {
                  HumanGroup.addToBottom(new Kiva_s());
                  return;
               }
            }
            break;
         case "Decade":
            DecadeGroup.clear();
            DecadeGroup.addToBottom(new RideBooker_Attack());
            DecadeGroup.addToBottom(new RideBooker_Shoot());
            break;
         case "Ryuki":
            RyukiGroup.clear();
            RyukiGroup.addToBottom(new DragClaw_s());
            RyukiGroup.addToBottom(new DragShield_s());
            RyukiGroup.addToBottom(new Dragreder());
            break;
         case "Blade":
            BladeGroup.clear();
            BladeGroup.addToBottom(new BladeMach_s());
            BladeGroup.addToBottom(new BladeMetal_s());
            BladeGroup.addToBottom(new BladeThunder_s());
            BladeGroup.addToBottom(new BladeKick_s());
            BladeGroup.addToBottom(new BladeBeat_s());
            BladeGroup.addToBottom(new BladeSlash_s());
            break;
         case "DenO":
            DenOGroup.clear();
            DenOGroup.addToBottom(new DenO_Gun_s());
            DenOGroup.addToBottom(new DenO_Ax_s());
            DenOGroup.addToBottom(new DenO_Rod_s());
            DenOGroup.addToBottom(new DenO_Sword_s());
            break;
         case "Basic":
            BasicGroup.clear();
            BasicGroup.addToBottom(new FinalAttackRide());
            BasicGroup.addToBottom(new AttackRide());
      }

   }

   public static void RideBookerCard(String Rider, int i) {
      if (RideBookerCard) {
         switch (i) {
            case 0:
               group.group.addAll(AllRideGroup.group);
               break;
            case 1:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.KuugaCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 2:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.AgitoCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 3:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.RyukiCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 4:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.FaizCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 5:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.BladeCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 6:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.HibikiCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 7:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.KabutoCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 8:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.DenOCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 9:
               for(AbstractCard c : AllRideGroup.group) {
                  if (c.hasTag(DCDmod.KivaCard)) {
                     group.group.add(c);
                  }
               }
               break;
            case 10:
               switch (Rider) {
                  case "human":
                     group.group.addAll(HumanGroup.group);
                     break;
                  case "Decade":
                     group.group.addAll(BasicGroup.group);
                     group.group.addAll(DecadeGroup.group);
                     break;
                  case "Ryuki":
                     group.group.addAll(BasicGroup.group);
                     group.group.addAll(RyukiGroup.group);
                     break;
                  case "Blade":
                     group.group.addAll(BasicGroup.group);
                     group.group.addAll(BladeGroup.group);
                     break;
                  case "DenO":
                     group.group.addAll(BasicGroup.group);
                     group.group.addAll(DenOGroup.group);
                     break;
                  default:
                     group.group.addAll(BasicGroup.group);
               }
         }

         AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("返回");
         RideBookerCard = false;
         SelectCard = true;
      }

   }

   public static void update() {
      if (SelectCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
         AbstractCard c;
         switch (((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).cardID) {
            case "Decade_s":
               c = new KamenRideDecade();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Kuuga_s":
               c = new KamenRideKuuga();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Agito_s":
               c = new KamenRideAgito();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Ryuki_s":
               c = new KamenRideRyuki();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Faiz_s":
               c = new KamenRideFaiz();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Blade_s":
               c = new KamenRideBlade();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Hibiki_s":
               c = new KamenRideHibiki();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Kabuto_s":
               c = new KamenRideKabuto();
               DecadeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "DenO_s":
               c = new KamenRideDenO();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "Kiva_s":
               c = new KamenRideKiva();
               HumanGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "RideBooker_Attack":
            case "RideBooker_Shoot":
               c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
               DecadeGroup.removeCard(c);
               break;
            case "DragClaw_s":
            case "DragShield_s":
               c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
               RyukiGroup.removeCard(c);
               break;
            case "Dragreder":
               c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
               boolean isAllRideGroup = true;

               for(AbstractCard rc : RyukiGroup.group) {
                  if (rc.equals(c)) {
                     RyukiGroup.removeCard(c);
                     isAllRideGroup = false;
                     break;
                  }
               }

               if (isAllRideGroup) {
                  AllRideGroup.removeCard(c);
               }
               break;
            case "BladeBeat_s":
               c = new Blade_Beat_s();
               BladeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "BladeKick_s":
               c = new Blade_Kick_s();
               BladeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "BladeMach_s":
               c = new Blade_Mach_s();
               BladeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "BladeMetal_s":
               c = new Blade_Metal_s();
               BladeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "BladeSlash_s":
               c = new Blade_Slash_s();
               BladeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "BladeThunder_s":
               c = new Blade_Thunder();
               BladeGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "DenO_Sword_s":
               c = new DenO_Sword();
               DenOGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "DenO_Rod_s":
               c = new DenO_Rod();
               DenOGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "DenO_Ax_s":
               c = new DenO_Ax();
               DenOGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "DenO_Gun_s":
               c = new DenO_Gun();
               DenOGroup.removeCard((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0));
               break;
            case "FinalAttackRide":
            case "KamenRide":
               c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
               BasicGroup.removeCard(c);
               break;
            case "AttackRide":
               c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
               boolean isAllRideGroup2 = true;

               for(AbstractCard rc : BasicGroup.group) {
                  if (rc.equals(c)) {
                     BasicGroup.removeCard(c);
                     isAllRideGroup2 = false;
                     break;
                  }
               }

               if (isAllRideGroup2) {
                  AllRideGroup.removeCard(c);
               }
               break;
            default:
               c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
               AllRideGroup.removeCard(c);
         }

         assert c != null;

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         group.clear();
         RideBookerCard = true;
         SelectCard = false;
         SpecialRideBooker.RideBookerOpen = false;
         AbstractDungeon.screen = CurrentScreen.valueOf("NONE");
         AbstractDungeon.isScreenUp = false;
      }

      if (SelectCard && AbstractDungeon.overlayMenu.cancelButton.isHidden) {
         group.clear();
         RideBookerCard = true;
         SelectCard = false;
         AbstractDungeon.screen = CurrentScreen.valueOf("Another2");
      }

   }

   static {
      group = new CardGroup(CardGroupType.CARD_POOL);
      RideBookerCard = true;
      SelectCard = false;
      DecadeGroup = new CardGroup(CardGroupType.CARD_POOL);
      HumanGroup = new CardGroup(CardGroupType.CARD_POOL);
      RyukiGroup = new CardGroup(CardGroupType.CARD_POOL);
      BladeGroup = new CardGroup(CardGroupType.CARD_POOL);
      DenOGroup = new CardGroup(CardGroupType.CARD_POOL);
      AllRideGroup = new CardGroup(CardGroupType.CARD_POOL);
      BasicGroup = new CardGroup(CardGroupType.CARD_POOL);
      RouzeCard = new String[]{"BladeSlash_s", "BladeBeat_s", "BladeKick_s", "BladeThunder_s", "BladeMetal_s", "BladeMach_s"};
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBookerCardAction");
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
