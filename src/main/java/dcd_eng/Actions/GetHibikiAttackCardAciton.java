package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Card.Uncommon.Hibiki_Attack3;

public class GetHibikiAttackCardAciton extends AbstractGameAction {
   private AbstractPlayer p;

   public GetHibikiAttackCardAciton() {
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.CARD_MANIPULATION;
   }

   public void update() {
      boolean Done = false;
      if (this.duration == Settings.ACTION_DUR_FAST) {
         for(AbstractCard c : this.p.hand.group) {
            if (c.cardID.equals("Hibiki_Attack3")) {
               Done = true;
               c.freeToPlayOnce = true;
               break;
            }
         }

         for(AbstractCard c : this.p.drawPile.group) {
            if (c.cardID.equals("Hibiki_Attack3") && !Done) {
               Done = true;
               c.freeToPlayOnce = true;
               this.p.drawPile.moveToHand(c, this.p.drawPile);
               break;
            }
         }

         for(AbstractCard c : this.p.discardPile.group) {
            if (c.cardID.equals("Hibiki_Attack3") && !Done) {
               Done = true;
               c.freeToPlayOnce = true;
               this.p.discardPile.moveToHand(c, this.p.discardPile);
               break;
            }
         }

         if (!Done) {
            AbstractCard c = new Hibiki_Attack3();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
         }

         this.isDone = true;
      }

   }
}
