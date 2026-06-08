package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.DCDmod;
import dcd_eng.ui.CardRewardScreenPatch;
import java.util.ArrayList;

public class DenOAction extends AbstractGameAction {
   private static ArrayList<AbstractCard> temp = new ArrayList();
   boolean takeCard = false;

   public DenOAction() {
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.SPECIAL;
   }

   public void update() {
      if (this.duration != Settings.ACTION_DUR_FAST) {
         if (!this.takeCard && AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(AbstractDungeon.cardRewardScreen.discoveryCard, 1));
            this.takeCard = true;
         }

         this.tickDuration();
      } else {
         temp.clear();

         for(int i = 0; i < 3; ++i) {
            SelectCardAction();
         }

         while(((AbstractCard)temp.get(0)).cardID.equals("NMDAZYYGL")) {
            temp.remove(0);
            SelectCardAction();
            System.out.println("移除第1张牌");
         }

         while(((AbstractCard)temp.get(1)).cardID.equals("NMDAZYYGL")) {
            temp.remove(1);
            SelectCardAction();
            System.out.println("移除第2张牌");
         }

         while(((AbstractCard)temp.get(2)).cardID.equals("NMDAZYYGL")) {
            temp.remove(2);
            SelectCardAction();
            System.out.println("移除第3张牌");
         }

         AbstractDungeon.cardRewardScreen.customCombatOpen(temp, "选择1张卡获得", true);
         CardRewardScreenPatch.isReward = false;
         this.tickDuration();
      }
   }

   private static void SelectCardAction() {
      if ((double)ReturnRandomNumberAction.ReturnRandomNumber() < (double)3.0F) {
         AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
         c.tags.add(DCDmod.DenOActionCard);
         temp.add(c);
      } else if ((double)ReturnRandomNumberAction.ReturnRandomNumber() < (double)6.0F) {
         AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
         c.tags.add(DCDmod.DenOActionCard);
         temp.add(c);
      } else {
         AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.SKILL).makeCopy();
         c.tags.add(DCDmod.DenOActionCard);
         temp.add(c);
      }

   }
}
