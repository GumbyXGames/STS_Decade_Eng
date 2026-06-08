package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ApotheosisAction extends AbstractGameAction {
   public ApotheosisAction() {
      this.duration = Settings.ACTION_DUR_MED;
      this.actionType = ActionType.WAIT;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_MED) {
         AbstractPlayer p = AbstractDungeon.player;
         this.upgradeAllCardsInGroup(p.hand);
         this.isDone = true;
      }

   }

   private void upgradeAllCardsInGroup(CardGroup cardGroup) {
      for(AbstractCard c : cardGroup.group) {
         if (c.canUpgrade()) {
            if (cardGroup.type == CardGroupType.HAND) {
               c.superFlash();
            }

            c.upgrade();
            c.applyPowers();
         }
      }

   }
}
