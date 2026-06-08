package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class KunaiGunDiscardAction extends AbstractGameAction {
   private AbstractPlayer p;
   private boolean isRandom;
   private boolean endTurn;
   public static int numDiscarded;
   private static final float DURATION;
   boolean calculate;
   public static int A = 0;
   public static int D = 0;

   public KunaiGunDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
      this(target, source, amount, isRandom, false);
   }

   public KunaiGunDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn) {
      this.calculate = false;
      this.p = (AbstractPlayer)target;
      this.isRandom = isRandom;
      this.amount = amount;
      this.setValues(target, source, amount);
      this.actionType = ActionType.DISCARD;
      this.endTurn = endTurn;
      this.duration = DURATION;
   }

   public void update() {
      if (this.duration == DURATION) {
         if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
            return;
         }

         if (this.p.hand.size() <= this.amount) {
            this.amount = this.p.hand.size();
            int i = this.p.hand.size();

            for(int i1 = 0; i1 < i; ++i1) {
               AbstractCard c1 = this.p.hand.getTopCard();
               this.p.hand.moveToDiscardPile(c1);
               if (!this.endTurn) {
                  c1.triggerOnManualDiscard();
               }

               GameActionManager.incrementDiscard(this.endTurn);
            }

            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
         }

         if (!this.isRandom) {
            if (this.amount < 0) {
               AbstractDungeon.handCardSelectScreen.open("选择卡牌弃置", 99, true, true);
               AbstractDungeon.player.hand.applyPowers();
               this.tickDuration();
               return;
            }

            numDiscarded = this.amount;
            if (this.p.hand.size() > this.amount) {
               AbstractDungeon.handCardSelectScreen.open("选择卡牌弃置", this.amount, false);
            }

            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
         }

         for(int i = 0; i < this.amount; ++i) {
            AbstractCard c = this.p.hand.getRandomCard(true);
            this.p.hand.moveToDiscardPile(c);
            c.triggerOnManualDiscard();
            GameActionManager.incrementDiscard(this.endTurn);
         }
      }

      if (!this.calculate) {
         for(AbstractCard c1 : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
            if (c1.type == CardType.ATTACK) {
               ++A;
            }

            if (c1.type == CardType.SKILL) {
               ++D;
            }
         }

         this.calculate = true;
      }

      if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
         for(AbstractCard abstractCard : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
            this.p.hand.moveToDiscardPile(abstractCard);
            abstractCard.triggerOnManualDiscard();
            GameActionManager.incrementDiscard(this.endTurn);
         }

         AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
      }

      this.tickDuration();
   }

   static {
      DURATION = Settings.ACTION_DUR_XFAST;
   }
}
