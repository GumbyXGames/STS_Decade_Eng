package dcd_eng.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UseCardAction extends AbstractGameAction {
   private final AbstractCard c;
   private final AbstractPlayer p;

   public UseCardAction(AbstractCard c, AbstractPlayer p) {
      this.actionType = ActionType.SPECIAL;
      this.duration = Settings.ACTION_DUR_FAST;
      this.c = c;
      this.p = p;
   }

   public void update() {
      AbstractCard tmp = this.c;
      this.p.limbo.addToBottom(tmp);
      tmp.exhaust = true;
      tmp.current_x = (float)Settings.WIDTH / 2.0F;
      tmp.current_y = (float)Settings.HEIGHT / 2.0F;
      tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
      tmp.target_y = (float)Settings.HEIGHT / 2.0F;
      AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, (AbstractMonster)null, tmp.energyOnUse, true, true), true);
      this.isDone = true;
   }
}
