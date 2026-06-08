package dcd_eng.Actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Card.Rare.DenO_Climax;
import dcd_eng.Power.CardMaxSizeUpPower;
import dcd_eng.Power.KamenRideDenOPower;
import dcd_eng.ui.CardRewardScreenPatch;
import java.util.ArrayList;

public class DenOWingAction extends AbstractGameAction {
   private static ArrayList<AbstractCard> temp = new ArrayList();
   private AbstractPlayer p;
   boolean takeCard = false;

   public DenOWingAction() {
      this.p = AbstractDungeon.player;
      this.duration = Settings.ACTION_DUR_FAST;
      this.actionType = ActionType.SPECIAL;
   }

   public void update() {
      if (this.duration == Settings.ACTION_DUR_FAST) {
         temp.clear();
         AbstractCard c = new DenO_Climax();
         c.tags.add(DCDmod.DenOActionCard);
         c.rawDescription = "手牌保留3回合";
         c.initializeDescription();
         temp.add(c);
         AbstractCard var7 = new DenO_Climax();
         var7.tags.add(DCDmod.DenOActionCard);
         var7.rawDescription = "本场战斗手牌上限+10";
         ((AbstractCard)var7).initializeDescription();
         temp.add(var7);
         var7 = new DenO_Climax();
         var7.tags.add(DCDmod.DenOActionCard);
         var7.rawDescription = "手牌耗能在使用前降为0";
         ((AbstractCard)var7).initializeDescription();
         temp.add(var7);
         var7 = new DenO_Climax();
         var7.tags.add(DCDmod.DenOActionCard);
         var7.rawDescription = "消耗所有非卡组的手牌，每张获得1点力量和敏捷";
         ((AbstractCard)var7).initializeDescription();
         temp.add(var7);
         var7 = new DenO_Climax();
         var7.tags.add(DCDmod.DenOActionCard);
         var7.rawDescription = "获得消耗牌堆数的 力量 和 敏捷 ";
         ((AbstractCard)var7).initializeDescription();
         temp.add(var7);
         AbstractDungeon.cardRewardScreen.customCombatOpen(temp, "选择1种效果即刻生效", false);
         CardRewardScreenPatch.isReward = false;
         this.tickDuration();
      } else {
         if (!this.takeCard) {
            switch (AbstractDungeon.cardRewardScreen.discoveryCard.rawDescription) {
               case "手牌保留3回合":
                  KamenRideDenOPower.retain = true;
                  break;
               case "本场战斗手牌上限+10":
                  BaseMod.MAX_HAND_SIZE += 10;
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new CardMaxSizeUpPower(this.p, 10)));
                  break;
               case "手牌耗能在使用前降为0":
                  for(AbstractCard c1 : this.p.hand.group) {
                     if (!c1.freeToPlayOnce) {
                        c1.freeToPlayOnce = true;
                     }
                  }

                  this.p.hand.update();
                  break;
               case "消耗所有非卡组的手牌，每张获得1点力量和敏捷":
                  int sd = 0;

                  for(AbstractCard c1 : this.p.hand.group) {
                     if (c1.hasTag(DCDmod.DenOActionCard) || c1.type == CardType.CURSE || c1.type == CardType.STATUS) {
                        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c1, this.p.hand));
                        ++sd;
                     }
                  }

                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, sd), sd));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, sd), sd));
                  break;
               case "获得消耗牌堆数的 力量 和 敏捷 ":
                  int x = AbstractDungeon.player.exhaustPile.group.size();
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, x), x));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new DexterityPower(this.p, x), x));
            }

            this.takeCard = true;
         }

         this.tickDuration();
      }
   }
}
