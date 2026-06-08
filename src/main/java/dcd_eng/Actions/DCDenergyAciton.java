package dcd_eng.Actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.ArrayList;

public class DCDenergyAciton {
   static ArrayList<AbstractCard> temp = new ArrayList();

   public static void change() {
      for(AbstractCard c : AbstractDungeon.player.hand.group) {
         temp.add(c);
      }

      AbstractDungeon.player.hand.group.clear();
      AbstractDungeon.player.hand.group = temp;
   }
}
