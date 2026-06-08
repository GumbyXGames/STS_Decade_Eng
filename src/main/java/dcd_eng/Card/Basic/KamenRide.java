package dcd_eng.Card.Basic;

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
import dcd_eng.Card.Common.KamenRideDecade;
import dcd_eng.Card.KamenRide.Agito_s;
import dcd_eng.Card.KamenRide.Blade_s;
import dcd_eng.Card.KamenRide.Decade_s;
import dcd_eng.Card.KamenRide.DenO_s;
import dcd_eng.Card.KamenRide.Faiz_s;
import dcd_eng.Card.KamenRide.Hibiki_s;
import dcd_eng.Card.KamenRide.Kabuto_s;
import dcd_eng.Card.KamenRide.Kiva_s;
import dcd_eng.Card.KamenRide.Kuuga_s;
import dcd_eng.Card.KamenRide.Ryuki_s;
import dcd_eng.Card.Uncommon.KamenRideAgito;
import dcd_eng.Card.Uncommon.KamenRideBlade;
import dcd_eng.Card.Uncommon.KamenRideDenO;
import dcd_eng.Card.Uncommon.KamenRideFaiz;
import dcd_eng.Card.Uncommon.KamenRideHibiki;
import dcd_eng.Card.Uncommon.KamenRideKabuto;
import dcd_eng.Card.Uncommon.KamenRideKiva;
import dcd_eng.Card.Uncommon.KamenRideKuuga;
import dcd_eng.Card.Uncommon.KamenRideRyuki;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;

public class KamenRide extends AbstractCustomCardWithType {
   public static final String ID = "KamenRide";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/KamenRide.png";
   private static final int COST = 1;
   private AbstractCard c = null;
   private CardGroup group;
   private boolean KamenRideCard;

   public KamenRide() {
      super("KamenRide", NAME, "img/cards/KamenRide.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.KamenRideCard = false;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.KamenRide);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      this.KamenRideCard = true;
      this.group.addToBottom(new Kiva_s());
      this.group.addToBottom(new DenO_s());
      this.group.addToBottom(new Kabuto_s());
      this.group.addToBottom(new Hibiki_s());
      this.group.addToBottom(new Blade_s());
      this.group.addToBottom(new Faiz_s());
      this.group.addToBottom(new Ryuki_s());
      this.group.addToBottom(new Agito_s());
      this.group.addToBottom(new Kuuga_s());
      this.group.addToBottom(new Decade_s());
      AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张获得", false, false, true, false);
   }

   public AbstractCard makeCopy() {
      return new KamenRide();
   }

   public void optionDecade() {
   }

   public void optionKuuga() {
   }

   public void optionAgito() {
   }

   public void optionRyuki() {
   }

   public void optionFaiz() {
   }

   public void optionBlade() {
   }

   public void optionHibiki() {
   }

   public void optionKabuto() {
   }

   public void optionDenO() {
   }

   public void optionKiva() {
   }

   public void optionNeutral() {
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(0);
      }

   }

   public void update() {
      super.update();
      if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && this.KamenRideCard) {
         switch (((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).cardID) {
            case "Decade_s":
               this.c = new KamenRideDecade();
               break;
            case "Kuuga_s":
               this.c = new KamenRideKuuga();
               break;
            case "Agito_s":
               this.c = new KamenRideAgito();
               break;
            case "Ryuki_s":
               this.c = new KamenRideRyuki();
               break;
            case "Faiz_s":
               this.c = new KamenRideFaiz();
               break;
            case "Blade_s":
               this.c = new KamenRideBlade();
               break;
            case "Hibiki_s":
               this.c = new KamenRideHibiki();
               break;
            case "Kabuto_s":
               this.c = new KamenRideKabuto();
               break;
            case "DenO_s":
               this.c = new KamenRideDenO();
               break;
            case "Kiva_s":
               this.c = new KamenRideKiva();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.c = null;
         this.KamenRideCard = false;
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         this.group.clear();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRide");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
