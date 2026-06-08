package dcd_eng.Relic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveKamenRideAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractClickRelic;
import dcd_eng.Power.KamenRideDecadePower;
import dcd_eng.Vfx.Decaderiver_henshin;
import java.util.ArrayList;

public class Decaderiver extends AbstractClickRelic {
   public static final String ID = "Decadriver";
   private boolean ishensin = false;
   private boolean ismonsterroom = false;

   public Decaderiver() {
      super("Decadriver", new Texture(Gdx.files.internal("img/relics/Decadriver.png")), RelicTier.STARTER, LandingSound.MAGICAL);
   }

   public String getUpdatedDescription() {
      return this.DESCRIPTIONS[0];
   }

   public void atBattleStart() {
      this.ismonsterroom = true;
      this.ishensin = false;
   }

   public void atTurnStart() {
      SpecialRideBooker.decadenextturn = true;
      SpecialRideBooker.isPlayerTurn = true;
   }

   public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
      return super.onAttackToChangeDamage(info, damageAmount);
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (card.hasTag(DCDmod.FormRide) || card.hasTag(DCDmod.KamenRide)) {
         this.flash();
         AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
         ArrayList<AbstractCard> groupCopy = new ArrayList();

         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
               groupCopy.add(c);
            } else {
               logger.info("COST IS 0: " + c.name);
            }
         }

         for(CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
            if (i.card != null) {
               logger.info("INVALID: " + i.card.name);
               groupCopy.remove(i.card);
            }
         }

         AbstractCard c = null;
         if (groupCopy.isEmpty()) {
            logger.info("NO VALID CARDS");
         } else {
            logger.info("VALID CARDS: ");

            for(AbstractCard cc : groupCopy) {
               logger.info(cc.name);
            }

            c = (AbstractCard)groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
         }

         if (c != null) {
            logger.info("Decaderiver: " + c.name);
            c.setCostForTurn(0);
         } else {
            logger.info("ERROR: Decaderiver NOT WORKING");
         }
      }

   }

   protected void onRightClick() {
      if (AbstractDungeon.screen.name().equals("NONE") && SpecialRideBooker.isPlayerTurn) {
         if (AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "已进入Decade状态", true));
         } else if (this.ishensin) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "本场战斗已使用过驱动器", true));
         } else if (!this.ismonsterroom) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "没必要在这里变身", true));
         } else if (Decade.KamenRide.equals("human")) {
            this.flash();
            TurnTimer.StopBGM(false);
            CardCrawlGame.sound.playA("BGM", 0.0F);
            CardCrawlGame.sound.playA("people_henshin", 0.0F);
            CardCrawlGame.sound.playA("decade_henshin", 0.0F);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamenRideDecadePower(AbstractDungeon.player), 1));
            AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(AbstractDungeon.player, AbstractDungeon.player));
            if (DCDmod.HenshinTrigger) {
               Decade Decade = (Decade)AbstractDungeon.player;
               Decade.Trickster(1);
            } else {
               AbstractDungeon.actionManager.addToBottom(new VFXAction(new Decaderiver_henshin(), 5.0F));
            }

            if (!DCDmod.BGMTrigger) {
               CardCrawlGame.music.playTempBGM("decade_OP1.ogg");
            }

            this.ishensin = true;
         } else {
            this.flash();
            TurnTimer.StopBGM(false);
            CardCrawlGame.sound.playA("driversounds", 0.0F);
            CardCrawlGame.sound.playA("test1", 0.0F);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamenRideDecadePower(AbstractDungeon.player), 1));
            AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(AbstractDungeon.player, AbstractDungeon.player));
            this.ishensin = true;
            if (!DCDmod.BGMTrigger) {
               CardCrawlGame.music.playTempBGM("decade_OP1.ogg");
            }
         }
      }

   }

   public void onVictory() {
      SpecialRideBooker.decadenextturn = true;
      this.ishensin = false;
      this.ismonsterroom = false;
   }
}
