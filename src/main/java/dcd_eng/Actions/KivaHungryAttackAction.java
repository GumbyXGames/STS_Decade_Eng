package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dcd_eng.Helper.SpecialKivaPowerMeter;

public class KivaHungryAttackAction extends AbstractGameAction {
   private boolean exhaustCards;

   public KivaHungryAttackAction(AbstractCreature target, boolean exhausts) {
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.WAIT;
      this.source = AbstractDungeon.player;
      this.target = target;
      this.exhaustCards = exhausts;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
            this.isDone = true;
            return;
         }

         if (AbstractDungeon.player.drawPile.isEmpty()) {
            this.addToTop(new KivaHungryAttackAction(this.target, this.exhaustCards));
            this.addToTop(new EmptyDeckShuffleAction());
            this.isDone = true;
            return;
         }

         if (!AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
            if (!card.type.equals(CardType.ATTACK)) {
               AbstractDungeon.player.drawPile.group.remove(card);
               AbstractDungeon.player.hand.addToBottom(card);
               this.isDone = true;
               return;
            }

            if (card.costForTurn > EnergyPanel.totalCount) {
               int c = card.costForTurn - EnergyPanel.totalCount;
               int cs = c;
               EnergyPanel.useEnergy(EnergyPanel.totalCount);

               for(int i = 0; i < cs && SpecialKivaPowerMeter.powerMeter >= 4 && c >= 0; ++i) {
                  SpecialKivaPowerMeter.powerMeter -= 4;
                  --c;
               }

               if (c >= 0) {
                  AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, c * 4, DamageType.HP_LOSS)));
               }
            } else {
               EnergyPanel.useEnergy(card.costForTurn);
            }

            AbstractDungeon.player.drawPile.group.remove(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            card.exhaustOnUseOnce = this.exhaustCards;
            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.scale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();
            this.addToTop(new NewQueueCardAction(card, this.target, false, true));
            this.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
               this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
               this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
         }

         this.isDone = true;
      }

      this.tickDuration();
   }
}
