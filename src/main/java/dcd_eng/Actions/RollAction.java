package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RollAction extends AbstractGameAction {
   private AbstractPlayer p;
   private CardGroup group;

   public RollAction() {
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
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张牌使其耗能降为0", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("取消");
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for(AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
               if (c1.cost > 0) {
                  c1.freeToPlayOnce = true;
               }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.group.clear();
         }

         this.tickDuration();
      }
   }
}
