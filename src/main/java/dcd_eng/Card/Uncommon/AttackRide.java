package dcd_eng.Card.Uncommon;

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
import dcd_eng.Card.Common.Agito_FlameSaber;
import dcd_eng.Card.Common.Agito_StormHalberd;
import dcd_eng.Card.Common.DenO_DenGasher;
import dcd_eng.Card.Common.Faiz_Edge;
import dcd_eng.Card.Common.Faiz_Phone;
import dcd_eng.Card.Common.Faiz_Shot;
import dcd_eng.Card.Common.Hibiki_Ongekibou;
import dcd_eng.Card.Common.Kabuto_KunaiGun;
import dcd_eng.Card.Common.Kiva_BasshaaMagnum;
import dcd_eng.Card.Common.Kiva_DoggaHammer;
import dcd_eng.Card.Common.Kiva_GaruruSaber;
import dcd_eng.Card.Common.Kuuga_DragonRod;
import dcd_eng.Card.Common.Kuuga_PegasusBowgun;
import dcd_eng.Card.Common.Kuuga_TitanSword;
import dcd_eng.Card.Common.Ryuki_DragSaber;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;

public class AttackRide extends AbstractCustomCardWithType {
   public static final String ID = "AttackRide";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AttackRide.png";
   private static final int COST = 1;
   private AbstractCard c = null;
   private CardGroup group;
   private boolean AttackRideCard;

   public AttackRide() {
      super("AttackRide", NAME, "img/cards/AttackRide.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.group = new CardGroup(CardGroupType.CARD_POOL);
      this.AttackRideCard = false;
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      this.AttackRideCard = true;
      if (p.hasPower("KamenRideDecadePower")) {
         if (p.hasPower("DecadeViolentEmotionPower")) {
            this.c = new Kiva_DoggaHammer();
            this.c.rawDescription = EXTENDED_DESCRIPTION[25];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Kiva_BasshaaMagnum();
            this.c.rawDescription = EXTENDED_DESCRIPTION[24];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Kiva_GaruruSaber();
            this.c.rawDescription = EXTENDED_DESCRIPTION[23];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new DenO_DenGasher();
            this.c.rawDescription = EXTENDED_DESCRIPTION[21];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Kabuto_KunaiGun();
            this.c.rawDescription = EXTENDED_DESCRIPTION[17];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Hibiki_Ongekibou();
            this.c.rawDescription = EXTENDED_DESCRIPTION[18];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Blade_BlayRouzer();
            this.c.rawDescription = EXTENDED_DESCRIPTION[19];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Faiz_Edge();
            this.c.rawDescription = EXTENDED_DESCRIPTION[14];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Faiz_Shot();
            this.c.rawDescription = EXTENDED_DESCRIPTION[16];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Faiz_Phone();
            this.c.rawDescription = EXTENDED_DESCRIPTION[15];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.group.addToBottom(new Ryuki_DragSaber());
            this.c = new Agito_StormHalberd();
            this.c.rawDescription = EXTENDED_DESCRIPTION[13];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Agito_FlameSaber();
            this.c.rawDescription = EXTENDED_DESCRIPTION[12];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Kuuga_TitanSword();
            this.c.rawDescription = EXTENDED_DESCRIPTION[9];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Kuuga_PegasusBowgun();
            this.c.rawDescription = EXTENDED_DESCRIPTION[11];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
            this.c = new Kuuga_DragonRod();
            this.c.rawDescription = EXTENDED_DESCRIPTION[10];
            this.c.initializeDescription();
            this.group.addToBottom(this.c);
         } else {
            this.group.addToBottom(new Kiva_DoggaHammer());
            this.group.addToBottom(new Kiva_BasshaaMagnum());
            this.group.addToBottom(new Kiva_GaruruSaber());
            this.group.addToBottom(new DenO_DenGasher());
            this.group.addToBottom(new Kabuto_KunaiGun());
            this.group.addToBottom(new Hibiki_Ongekibou());
            this.group.addToBottom(new Blade_BlayRouzer());
            this.group.addToBottom(new Faiz_Edge());
            this.group.addToBottom(new Faiz_Shot());
            this.group.addToBottom(new Faiz_Phone());
            this.group.addToBottom(new Ryuki_DragSaber());
            this.group.addToBottom(new Agito_StormHalberd());
            this.group.addToBottom(new Agito_FlameSaber());
            this.group.addToBottom(new Kuuga_TitanSword());
            this.group.addToBottom(new Kuuga_PegasusBowgun());
            this.group.addToBottom(new Kuuga_DragonRod());
         }

         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张卡牌获得", false, false, true, false);
      } else if (p.hasPower("KamenRideKuugaPower")) {
         this.group.addToBottom(new Kuuga_TitanSword());
         this.group.addToBottom(new Kuuga_DragonRod());
         this.group.addToBottom(new Kuuga_PegasusBowgun());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张卡牌获得", false, false, true, false);
      } else if (p.hasPower("KamenRideAgitoPower")) {
         this.group.addToBottom(new Agito_FlameSaber());
         this.group.addToBottom(new Agito_StormHalberd());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张卡牌获得", false, false, true, false);
      } else if (p.hasPower("KamenRideRyukiPower")) {
         this.c = new Ryuki_DragSaber();
         --this.c.costForTurn;
         if (this.upgraded) {
            this.c.upgrade();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.AttackRideCard = false;
      } else if (p.hasPower("KamenRideFaizPower")) {
         this.group.addToBottom(new Faiz_Edge());
         this.group.addToBottom(new Faiz_Phone());
         this.group.addToBottom(new Faiz_Shot());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张卡牌获得", false, false, true, false);
      } else if (p.hasPower("KamenRideBladePower")) {
         this.c = new Blade_BlayRouzer();
         --this.c.costForTurn;
         if (this.upgraded) {
            this.c.upgrade();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.AttackRideCard = false;
      } else if (p.hasPower("KamenRideHibikiPower")) {
         this.c = new Hibiki_Ongekibou();
         --this.c.costForTurn;
         if (this.upgraded) {
            this.c.upgrade();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.AttackRideCard = false;
      } else if (p.hasPower("KamenRideKabutoPower")) {
         this.c = new Kabuto_KunaiGun();
         --this.c.costForTurn;
         if (this.upgraded) {
            this.c.upgrade();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.AttackRideCard = false;
      } else if (p.hasPower("KamenRideDenOPower")) {
         this.c = new DenO_DenGasher();
         --this.c.costForTurn;
         if (this.upgraded) {
            this.c.upgrade();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.AttackRideCard = false;
      } else if (p.hasPower("KamenRideKivaPower")) {
         this.group.addToBottom(new Kiva_DoggaHammer());
         this.group.addToBottom(new Kiva_BasshaaMagnum());
         this.group.addToBottom(new Kiva_GaruruSaber());
         AbstractDungeon.gridSelectScreen.open(this.group, 1, "选择1张卡牌获得", false, false, true, false);
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideDecadePower") && !p.hasPower("KamenRideKuugaPower") && !p.hasPower("KamenRideAgitoPower") && !p.hasPower("KamenRideRyukiPower") && !p.hasPower("KamenRideFaizPower") && !p.hasPower("KamenRideBladePower") && !p.hasPower("KamenRideHibikiPower") && !p.hasPower("KamenRideKabutoPower") && !p.hasPower("KamenRideDenOPower") && !p.hasPower("KamenRideKivaPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new AttackRide();
   }

   public void optionDecade() {
      this.rawDescription = EXTENDED_DESCRIPTION[1];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
      this.cardsToPreview = null;
   }

   public void optionKuuga() {
      this.rawDescription = EXTENDED_DESCRIPTION[2];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_kuuga.png", "img/1024/skill_kuuga.png");
      this.cardsToPreview = null;
   }

   public void optionAgito() {
      this.rawDescription = EXTENDED_DESCRIPTION[3];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_agito.png", "img/1024/skill_agito.png");
      this.cardsToPreview = null;
   }

   public void optionRyuki() {
      this.rawDescription = EXTENDED_DESCRIPTION[4];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_ryuki.png", "img/1024/skill_ryuki.png");
      this.cardsToPreview = new Ryuki_DragSaber();
   }

   public void optionFaiz() {
      this.rawDescription = EXTENDED_DESCRIPTION[5];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_faiz.png", "img/1024/skill_faiz.png");
      this.cardsToPreview = null;
   }

   public void optionBlade() {
      this.rawDescription = EXTENDED_DESCRIPTION[6];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_blade.png", "img/1024/skill_blade.png");
      this.cardsToPreview = new Blade_BlayRouzer();
   }

   public void optionHibiki() {
      this.rawDescription = EXTENDED_DESCRIPTION[7];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_hibiki.png", "img/1024/skill_hibiki.png");
      this.cardsToPreview = new Hibiki_Ongekibou();
   }

   public void optionKabuto() {
      this.rawDescription = EXTENDED_DESCRIPTION[8];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_kabuto.png", "img/1024/skill_kabuto.png");
      this.cardsToPreview = new Kabuto_KunaiGun();
   }

   public void optionDenO() {
      this.rawDescription = EXTENDED_DESCRIPTION[20];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_deno.png", "img/1024/skill_deno.png");
      this.cardsToPreview = new DenO_DenGasher();
   }

   public void optionKiva() {
      this.rawDescription = EXTENDED_DESCRIPTION[22];
      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_kiva.png", "img/1024/skill_kiva.png");
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      }

      this.initializeDescription();
      this.setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.rawDescription = UPGRADE_DESCRIPTION;
         this.initializeDescription();
      }

   }

   public void update() {
      super.update();
      if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && this.AttackRideCard) {
         this.c = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);
         --this.c.costForTurn;
         if (this.upgraded) {
            this.c.upgrade();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.c, 1));
         this.c = null;
         this.AttackRideCard = false;
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         this.group.clear();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("AttackRide");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   }
}
