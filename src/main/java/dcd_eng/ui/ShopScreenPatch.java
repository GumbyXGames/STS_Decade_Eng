package dcd_eng.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.shop.ShopScreen;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.ui.button.BanCardButton;
import java.util.ArrayList;

public class ShopScreenPatch {
   private static final ArrayList<BanCardButton> buttons = new ArrayList();
   private static AbstractCard nc = null;

   public static void Open(ShopScreen screen, ArrayList<AbstractCard> cards1, ArrayList<AbstractCard> cards2) {
      buttons.clear();

      for(AbstractCard card : cards1) {
         BanCardButton banButton = new BanCardButton(card);
         banButton.show();
         buttons.add(banButton);
      }

      for(AbstractCard card : cards2) {
         BanCardButton banButton = new BanCardButton(card);
         banButton.show();
         buttons.add(banButton);
      }

   }

   public static void Update(ShopScreen screen) {
      UpdateBanButtons();
   }

   public static void PreRender(ShopScreen screen, SpriteBatch sb) {
      for(BanCardButton banButton : buttons) {
         banButton.render(sb);
      }

   }

   private static void UpdateBanButtons() {
      for(BanCardButton banButton : buttons) {
         banButton.update();
         if (banButton.banned) {
            if (AbstractDungeon.player.gold < banButton.card.price) {
               AbstractDungeon.shopScreen.playCantBuySfx();
            } else {
               CardCrawlGame.metricData.addShopPurchaseData(banButton.card.getMetricID());
               AbstractDungeon.player.loseGold(banButton.card.price);
               CardCrawlGame.sound.play("SHOP_PURCHASE", 0.1F);
               if (!AbstractDungeon.player.hasRelic("The Courier")) {
                  AbstractDungeon.shopScreen.coloredCards.remove(banButton.card);
                  AbstractDungeon.shopScreen.colorlessCards.remove(banButton.card);
               } else if (banButton.card.color == CardColor.COLORLESS) {
                  AbstractCard.CardRarity tempRarity = CardRarity.UNCOMMON;
                  if (AbstractDungeon.merchantRng.random() < AbstractDungeon.colorlessRareChance) {
                     tempRarity = CardRarity.RARE;
                  }

                  nc = AbstractDungeon.getColorlessCardFromPool(tempRarity).makeCopy();

                  for(AbstractRelic r : AbstractDungeon.player.relics) {
                     r.onPreviewObtainCard(nc);
                  }

                  nc.current_x = banButton.card.current_x;
                  nc.current_y = banButton.card.current_y;
                  nc.target_x = nc.current_x;
                  nc.target_y = nc.current_y;
                  setPrice(nc);
                  AbstractDungeon.shopScreen.colorlessCards.set(AbstractDungeon.shopScreen.colorlessCards.indexOf(banButton.card), nc);
               } else {
                  for(nc = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), banButton.card.type, false).makeCopy(); nc.color == CardColor.COLORLESS; nc = AbstractDungeon.getCardFromPool(AbstractDungeon.rollRarity(), banButton.card.type, false).makeCopy()) {
                  }

                  for(AbstractRelic r : AbstractDungeon.player.relics) {
                     r.onPreviewObtainCard(nc);
                  }

                  nc.current_x = banButton.card.current_x;
                  nc.current_y = banButton.card.current_y;
                  nc.target_x = nc.current_x;
                  nc.target_y = nc.current_y;
                  setPrice(nc);
                  AbstractDungeon.shopScreen.coloredCards.set(AbstractDungeon.shopScreen.coloredCards.indexOf(banButton.card), nc);
               }

               NewRideBookerCardAction.AllRideGroup.addToBottom(banButton.card);
               banButton.card = null;
               AbstractDungeon.shopScreen.playBuySfx();
            }

            banButton.banned = false;
         }
      }

      if (nc != null) {
         BanCardButton banButton = new BanCardButton(nc);
         banButton.show();
         buttons.add(banButton);
         nc = null;
      }

   }

   private static void setPrice(AbstractCard card) {
      float tmpPrice = (float)AbstractCard.getPrice(card.rarity) * AbstractDungeon.merchantRng.random(0.9F, 1.1F);
      if (card.color == CardColor.COLORLESS) {
         tmpPrice *= 1.2F;
      }

      if (AbstractDungeon.player.hasRelic("The Courier")) {
         tmpPrice *= 0.8F;
      }

      if (AbstractDungeon.player.hasRelic("Membership Card")) {
         tmpPrice *= 0.5F;
      }

      card.price = (int)tmpPrice;
   }

   public static void purchaseCard(ShopScreen screen) {
      buttons.clear();

      for(AbstractCard card : AbstractDungeon.shopScreen.coloredCards) {
         BanCardButton banButton = new BanCardButton(card);
         banButton.show();
         buttons.add(banButton);
      }

      for(AbstractCard card : AbstractDungeon.shopScreen.colorlessCards) {
         BanCardButton banButton = new BanCardButton(card);
         banButton.show();
         buttons.add(banButton);
      }

   }
}
