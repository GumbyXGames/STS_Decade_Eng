package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Card.Common.Hibiki_Attack1;
import dcd_eng.Card.Common.Hibiki_Attack2;
import dcd_eng.Card.Uncommon.Hibiki_Attack3;

public class TaikoAttackAction extends AbstractGameAction {
   private AbstractPlayer p;
   private CardGroup group;

   public TaikoAttackAction() {
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.SPECIAL;
   }

   public void update() {
      boolean Done = false;
      if (this.duration == Settings.ACTION_DUR_FAST) {
         this.group.addToBottom(new Hibiki_Attack3());
         this.group.addToBottom(new Hibiki_Attack2());
         this.group.addToBottom(new Hibiki_Attack1());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张牌", false, false, true, false);
         this.tickDuration();
      } else {
         if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for(AbstractCard c1 : AbstractDungeon.gridSelectScreen.selectedCards) {
               for(AbstractCard c2 : this.p.hand.group) {
                  if (c2.cardID.equals(c1.cardID) && !Done && !c2.freeToPlayOnce) {
                     c2.freeToPlayOnce = true;
                     Done = true;
                     break;
                  }
               }

               for(AbstractCard c2 : this.p.drawPile.group) {
                  if (c2.cardID.equals(c1.cardID) && !Done && !c2.freeToPlayOnce) {
                     c2.freeToPlayOnce = true;
                     this.p.drawPile.moveToHand(c2, this.p.drawPile);
                     Done = true;
                     break;
                  }
               }

               for(AbstractCard c2 : this.p.discardPile.group) {
                  if (c2.cardID.equals(c1.cardID) && !Done && !c2.freeToPlayOnce) {
                     c2.freeToPlayOnce = true;
                     this.p.discardPile.moveToHand(c2, this.p.discardPile);
                     Done = true;
                     break;
                  }
               }

               if (!Done) {
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c1, 1));
               }
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.group.clear();
         }

         this.tickDuration();
      }
   }
}
