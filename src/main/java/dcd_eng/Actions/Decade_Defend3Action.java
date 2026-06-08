package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.DCDmod;

public class Decade_Defend3Action extends AbstractGameAction {
   private AbstractPlayer p;
   private static final float DURATION;

   public Decade_Defend3Action(AbstractCreature target, AbstractCreature source) {
      this.p = (AbstractPlayer)target;
      this.setValues(target, source);
      this.actionType = ActionType.DISCARD;
      this.duration = DURATION;
   }

   public void update() {
      if (this.duration == DURATION) {
         if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
         } else if (this.p.hand.group.size() != 0) {
            int n = this.p.hand.group.size();
            if (n == 1) {
               AbstractCard c1 = (AbstractCard)AbstractDungeon.player.hand.group.get(0);
               AbstractDungeon.player.hand.moveToDiscardPile(c1);
               c1.triggerOnManualDiscard();
               if (c1.hasTag(DCDmod.WeaponCard)) {
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c1.makeCopy(), 1, true, true));
               }

               this.tickDuration();
            } else {
               AbstractDungeon.handCardSelectScreen.open("选择1张牌弃置", 1, false);
               AbstractDungeon.player.hand.applyPowers();
               this.tickDuration();
            }
         } else {
            this.tickDuration();
         }
      } else {
         if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for(AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
               this.p.hand.moveToDiscardPile(card);
               card.triggerOnManualDiscard();
               if (card.hasTag(DCDmod.WeaponCard)) {
                  AbstractCard c = card.makeCopy();
                  if (c.upgraded) {
                     c.upgrade();
                  }

                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(c, 1, true, true));
               }
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
         }

         this.tickDuration();
      }
   }

   static {
      DURATION = Settings.ACTION_DUR_XFAST;
   }
}
