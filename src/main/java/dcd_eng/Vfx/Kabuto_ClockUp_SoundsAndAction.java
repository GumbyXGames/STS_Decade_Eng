package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.ClockUpAction;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Basic.FinalAttackRide;

public class Kabuto_ClockUp_SoundsAndAction extends AbstractGameEffect {
   private boolean sounds = true;
   private boolean action = true;
   private AbstractCard c;

   public Kabuto_ClockUp_SoundsAndAction(AbstractCard c) {
      this.c = c;
      this.duration = 4.0F;
      this.startingDuration = 4.0F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 2.0F) {
         if (this.sounds) {
            CardCrawlGame.sound.playA("kabuto_clockup", 0.0F);
            AbstractDungeon.actionManager.addToBottom(new ClockUpAction(this.c));
            this.sounds = false;
         }

         if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower") || AbstractDungeon.player.hasPower("KamenRideKabutoPower")) {
            AbstractCard far = new FinalAttackRide();
            far.freeToPlayOnce = true;
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(far, 1));
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.action) {
         CardCrawlGame.sound.playA("attackride", 0.0F);
         if (AbstractDungeon.player.discardPile.size() > 0) {
            AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
            AbstractDungeon.actionManager.addToBottom(new ShuffleAction(AbstractDungeon.player.drawPile, false));
         }

         this.action = false;
      }

   }

   public void dispose() {
   }
}
