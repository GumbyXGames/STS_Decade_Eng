package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import java.util.ArrayList;
import java.util.List;

public class RideBooker_Skill_2 extends AbstractCustomCardWithType {
   public static final String ID = "RideBooker_Skill_2";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RideBooker_Skill_2.png";
   private static final int COST = 1;
   private CardGroup group;
   private boolean RideBooker_Skill_2Card;
   private List<TooltipInfo> tips;
   public static boolean RBS2isDone = true;

   public RideBooker_Skill_2() {
      super("RideBooker_Skill_2", NAME, "img/cards/RideBooker_Skill_2.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.RideBooker_Skill_2Card = false;
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      this.RideBooker_Skill_2Card = true;
      if (RBS2isDone) {
         for(AbstractCard c : NewRideBookerCardAction.AllRideGroup.group) {
            if (c.rarity != CardRarity.RARE) {
               this.group.group.add(c);
            }
         }
      } else {
         this.group.group.addAll(NewRideBookerCardAction.AllRideGroup.group);
      }

      AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张卡牌获得", false, false, true, false);
      AbstractDungeon.overlayMenu.cancelButton.show("取消");
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (NewRideBookerCardAction.AllRideGroup.isEmpty()) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new RideBooker_Skill_2();
   }

   public void optionDecade() {
      this.setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
   }

   public void optionKuuga() {
      this.setBackgroundTexture("img/512/skill_kuuga.png", "img/1024/skill_kuuga.png");
   }

   public void optionAgito() {
      this.setBackgroundTexture("img/512/skill_agito.png", "img/1024/skill_agito.png");
   }

   public void optionRyuki() {
      this.setBackgroundTexture("img/512/skill_ryuki.png", "img/1024/skill_ryuki.png");
   }

   public void optionFaiz() {
      this.setBackgroundTexture("img/512/skill_faiz.png", "img/1024/skill_faiz.png");
   }

   public void optionBlade() {
      this.setBackgroundTexture("img/512/skill_blade.png", "img/1024/skill_blade.png");
   }

   public void optionHibiki() {
      this.setBackgroundTexture("img/512/skill_hibiki.png", "img/1024/skill_hibiki.png");
   }

   public void optionKabuto() {
      this.setBackgroundTexture("img/512/skill_kabuto.png", "img/1024/skill_kabuto.png");
   }

   public void optionDenO() {
      this.setBackgroundTexture("img/512/skill_deno.png", "img/1024/skill_deno.png");
   }

   public void optionKiva() {
      this.setBackgroundTexture("img/512/skill_kiva.png", "img/1024/skill_kiva.png");
   }

   public void optionNeutral() {
      this.setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(0);
      }

   }

   public void update() {
      super.update();
      if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && this.RideBooker_Skill_2Card) {
         AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeCopy();
         if (((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).upgraded) {
            c.upgrade();
         }

         c.freeToPlayOnce = true;
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
         this.RideBooker_Skill_2Card = false;
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         this.group.clear();
         if (c.rarity == CardRarity.RARE) {
            RBS2isDone = true;
         }
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker_Skill_2");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
