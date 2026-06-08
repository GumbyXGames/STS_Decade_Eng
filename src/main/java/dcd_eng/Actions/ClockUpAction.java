package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ClockUpAction extends AbstractGameAction {
   private AbstractPlayer p;
   private AbstractCard c;
   private int x;
   private CardGroup group;

   public ClockUpAction(AbstractCard c) {
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.c = c;
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.SPECIAL;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         this.group.group.addAll(this.p.drawPile.group);

         for(AbstractCard c1 : this.group.group) {
            if (c1.cardID.equals(this.c.cardID)) {
               ++this.x;
            }
         }

         for(int i = 0; i < this.x; ++i) {
            for(AbstractCard c1 : this.group.group) {
               if (c1.cardID.equals(this.c.cardID)) {
                  this.group.removeCard(c1);
                  break;
               }
            }
         }

         AbstractDungeon.gridSelectScreen.open(this.group, 3, "选择3张牌使其耗能降为0并加入手牌", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("取消");
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for(AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
               if (c1.cost > 0) {
                  c1.freeToPlayOnce = true;
               }

               this.p.hand.addToHand(c1);
               this.p.drawPile.removeCard(c1);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.group.clear();
         }

         this.tickDuration();
      }
   }
}
