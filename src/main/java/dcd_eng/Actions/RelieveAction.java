package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RelieveAction extends AbstractGameAction {
   private AbstractPlayer p;
   CardGroup group;

   public RelieveAction() {
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.SPECIAL;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         this.group.group.addAll(this.p.hand.group);
         this.group.group.addAll(this.p.drawPile.group);
         this.group.group.addAll(this.p.discardPile.group);
         AbstractDungeon.gridSelectScreen.open(this.group, 3, "选择3张牌使其消耗", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("确认");
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for(AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
               for(AbstractCard c2 : this.p.hand.group) {
                  if (c2 == c1) {
                     this.p.hand.moveToExhaustPile(c2);
                     break;
                  }
               }

               for(AbstractCard c2 : this.p.drawPile.group) {
                  if (c2 == c1) {
                     this.p.drawPile.moveToExhaustPile(c2);
                     break;
                  }
               }

               for(AbstractCard c2 : this.p.discardPile.group) {
                  if (c2 == c1) {
                     this.p.discardPile.moveToExhaustPile(c2);
                     break;
                  }
               }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.group.clear();
            AbstractDungeon.overlayMenu.cancelButton.hide();
         }

         this.tickDuration();
      }
   }
}
