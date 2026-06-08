package dcd_eng.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.ui.button.BanCardButton;
import java.util.ArrayList;

public class CardRewardScreenPatch {
   private static final ArrayList<BanCardButton> buttons = new ArrayList();
   private static RewardItem rewardItem;
   private static boolean canBan;
   private static boolean isDone;
   public static boolean isReward;

   public static void Open(CardRewardScreen screen, ArrayList<AbstractCard> cards, RewardItem rItem, String header) {
      canBan = false;
      buttons.clear();
      rewardItem = rItem;
      isDone = false;
      isReward = true;

      for(AbstractCard card : cards) {
         BanCardButton banButton = new BanCardButton(card);
         banButton.show();
         buttons.add(banButton);
         canBan = true;
      }

   }

   public static void customCombatOpen(CardRewardScreen screen, ArrayList<AbstractCard> cards, String header, boolean skippable) {
      canBan = false;
      buttons.clear();
      isDone = false;
      isReward = true;

      for(AbstractCard card : cards) {
         BanCardButton banButton = new BanCardButton(card);
         banButton.show();
         buttons.add(banButton);
         canBan = true;
      }

   }

   public static void OnClose(CardRewardScreen screen) {
      buttons.clear();
      canBan = false;
   }

   public static void Update(CardRewardScreen screen) {
      if (canBan && !isDone && isReward) {
         UpdateBanButtons();
      }

   }

   public static void PreRender(CardRewardScreen screen, SpriteBatch sb) {
      if (canBan) {
         for(BanCardButton banButton : buttons) {
            banButton.render(sb);
         }
      }

   }

   private static void UpdateBanButtons() {
      for(BanCardButton banButton : buttons) {
         banButton.update();
         if (banButton.banned) {
            NewRideBookerCardAction.AllRideGroup.addToBottom(banButton.card);
            AbstractDungeon.closeCurrentScreen();
            AbstractDungeon.combatRewardScreen.rewards.remove(rewardItem);
            isDone = true;
            break;
         }
      }

   }
}
