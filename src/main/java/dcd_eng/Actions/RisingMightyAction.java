package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RisingMightyAction extends AbstractGameAction {
   private AbstractPlayer p;
   private CardGroup group;

   public RisingMightyAction() {
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.SPECIAL;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         for(AbstractCard c1 : this.p.hand.group) {
            if (c1.type == CardType.ATTACK) {
               this.group.group.add(c1);
            }
         }

         for(AbstractCard c1 : this.p.drawPile.group) {
            if (c1.type == CardType.ATTACK) {
               this.group.group.add(c1);
            }
         }

         for(AbstractCard c1 : this.p.discardPile.group) {
            if (c1.type == CardType.ATTACK) {
               this.group.group.add(c1);
            }
         }

         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张牌使其耗能降为0并加入手牌", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("取消");
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for(AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
               if (c1.cost > 0) {
                  c1.freeToPlayOnce = true;
               }
            }

            boolean isDone = false;

            for(AbstractCard c2 : this.p.drawPile.group) {
               if (c2 == AbstractDungeon.gridSelectScreen.selectedCards.get(0)) {
                  this.p.drawPile.group.remove(c2);
                  this.p.hand.group.add(c2);
                  isDone = true;
                  break;
               }
            }

            if (!isDone) {
               for(AbstractCard c2 : this.p.discardPile.group) {
                  if (c2 == AbstractDungeon.gridSelectScreen.selectedCards.get(0)) {
                     this.p.discardPile.group.remove(c2);
                     this.p.hand.group.add(c2);
                     break;
                  }
               }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.group.clear();
         }

         this.tickDuration();
      }
   }
}
