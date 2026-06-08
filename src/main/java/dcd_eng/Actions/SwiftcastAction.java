package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SwiftcastAction extends AbstractGameAction {
   private AbstractPlayer p;

   public SwiftcastAction() {
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.CARD_MANIPULATION;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (this.p.hand.isEmpty()) {
            this.isDone = true;
         } else if (this.p.hand.size() == 1) {
            AbstractCard c = this.p.hand.getTopCard();
            if (c.cost > 0) {
               c.freeToPlayOnce = true;
            }

            this.p.hand.addToTop(c);
            AbstractDungeon.player.hand.refreshHandLayout();
            this.isDone = true;
         } else {
            AbstractDungeon.handCardSelectScreen.open("让其耗能变为0", 1, true, true);
            this.tickDuration();
         }
      } else {
         if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for(AbstractCard c1 : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
               if (c1.cost > 0) {
                  c1.freeToPlayOnce = true;
               }

               this.p.hand.addToTop(c1);
            }

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
         }

         this.tickDuration();
      }
   }
}
