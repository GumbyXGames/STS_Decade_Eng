package dcd_eng.Actions;

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import dcd_eng.Helper.SpecialFaizBox;

public class FaizBoxScreenAction {
   private static CardGroup group;
   public static boolean FaizGear;
   public static boolean selectcard;

   public static void EnterButton() {
      if (FaizGear) {
         AbstractDungeon.screen = CurrentScreen.valueOf("Another");
         AbstractDungeon.overlayMenu.cancelButton.show("取消");
         AbstractDungeon.isScreenUp = true;
         FaizGear = false;
         selectcard = true;
      }

   }

   public static void update() {
      if (selectcard && AbstractDungeon.overlayMenu.cancelButton.isHidden) {
         AbstractDungeon.screen = CurrentScreen.NONE;
         AbstractDungeon.isScreenUp = false;
         group.clear();
         FaizGear = true;
         selectcard = false;
         SpecialFaizBox.BoxOpen = false;
      }

   }

   static {
      group = new CardGroup(CardGroupType.CARD_POOL);
      FaizGear = true;
      selectcard = false;
   }
}
