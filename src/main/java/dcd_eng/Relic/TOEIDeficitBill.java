package dcd_eng.Relic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Card.TriggerCard.AnimationTrigger_off;
import dcd_eng.Card.TriggerCard.AnimationTrigger_on;
import dcd_eng.Card.TriggerCard.BGMTrigger_off;
import dcd_eng.Card.TriggerCard.BGMTrigger_on;
import dcd_eng.Card.TriggerCard.HenshinTrigger_off;
import dcd_eng.Card.TriggerCard.HenshinTrigger_on;
import dcd_eng.Patches.AbstractClickRelic;

public class TOEIDeficitBill extends AbstractClickRelic {
   public static final String ID = "TOEIDeficitBill";
   private static CardGroup group;
   private boolean SelectCard;

   public TOEIDeficitBill() {
      super("TOEIDeficitBill", new Texture(Gdx.files.internal("img/relics/TOEIDeficitBill.png")), RelicTier.STARTER, LandingSound.MAGICAL);
   }

   protected void onRightClick() {
      if (AbstractDungeon.screen.name().equals("NONE")) {
         group.group.clear();
         if (DCDmod.AnimationTrigger) {
            group.group.add(new AnimationTrigger_off());
         } else {
            group.group.add(new AnimationTrigger_on());
         }

         if (DCDmod.HenshinTrigger) {
            group.group.add(new HenshinTrigger_off());
         } else {
            group.group.add(new HenshinTrigger_on());
         }

         if (DCDmod.BGMTrigger) {
            group.group.add(new BGMTrigger_off());
         } else {
            group.group.add(new BGMTrigger_on());
         }

         AbstractDungeon.gridSelectScreen.open(group, 1, "选择需要开关的特效", false, false, true, false);
         AbstractDungeon.overlayMenu.cancelButton.show("取消");
         this.SelectCard = true;
      }

   }

   public String getUpdatedDescription() {
      return this.DESCRIPTIONS[0];
   }

   public void update() {
      super.update();
      if (this.SelectCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
         switch (((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).cardID) {
            case "HenshinTrigger_on":
               DCDmod.HenshinTrigger = true;
               CardCrawlGame.sound.playA("648", 0.0F);
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "支付宝到账648元，特效已关闭", true));
               break;
            case "HenshinTrigger_off":
               DCDmod.HenshinTrigger = false;
               CardCrawlGame.sound.playA("648", 0.0F);
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "支付宝到账648元，特效已开启", true));
               break;
            case "AnimationTrigger_on":
               DCDmod.AnimationTrigger = true;
               CardCrawlGame.sound.playA("648", 0.0F);
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "支付宝到账648元，特效已关闭", true));
               break;
            case "AnimationTrigger_off":
               DCDmod.AnimationTrigger = false;
               CardCrawlGame.sound.playA("648", 0.0F);
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "支付宝到账648元，特效已开启", true));
               break;
            case "BGMTrigger_on":
               DCDmod.BGMTrigger = true;
               CardCrawlGame.sound.playA("648", 0.0F);
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "支付宝到账648元，音效已关闭", true));
               TurnTimer.StopBGM(true);
               break;
            case "BGMTrigger_off":
               DCDmod.BGMTrigger = false;
               CardCrawlGame.sound.playA("648", 0.0F);
               AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "支付宝到账648元，音效已开启", true));
         }

         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         this.SelectCard = false;
      }

   }

   static {
      group = new CardGroup(CardGroupType.CARD_POOL);
   }
}
