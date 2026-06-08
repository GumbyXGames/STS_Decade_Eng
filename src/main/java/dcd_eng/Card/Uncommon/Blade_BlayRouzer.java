package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Common.Blade_Beat;
import dcd_eng.Card.Common.Blade_Slash;
import dcd_eng.Card.SelectCard.BladeBeat_s;
import dcd_eng.Card.SelectCard.BladeKick_s;
import dcd_eng.Card.SelectCard.BladeMach_s;
import dcd_eng.Card.SelectCard.BladeMetal_s;
import dcd_eng.Card.SelectCard.BladeSlash_s;
import dcd_eng.Card.SelectCard.BladeThunder_s;
import dcd_eng.Card.Special.Blade_Thunder;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import java.util.ArrayList;
import java.util.List;

public class Blade_BlayRouzer extends AbstractCustomCardWithType {
   public static final String ID = "Blade_BlayRouzer";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Blade_BlayRouzer.png";
   private static final int COST = 2;
   private boolean RouzeCard = false;
   private List<TooltipInfo> tips;
   private static CardGroup group;
   private static AbstractCard c;

   public Blade_BlayRouzer() {
      super("Blade_BlayRouzer", NAME, "img/cards/Blade_BlayRouzer.png", 2, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Blade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[3]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      int x = 0;
      int d = 0;
      if (p.hasPower("BladeSlashPower")) {
         d += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
      }

      for(AbstractPower power : p.powers) {
         if (power.type == PowerType.BUFF && power.type != PowerType.valueOf("KamenRide")) {
            ++x;
         }
      }

      if (d > 0 || x > 0) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, x * 5 + d, DamageType.NORMAL), AttackEffect.BLUNT_LIGHT));
         if (x >= 6 && p.hasPower("KamenRideBladePower") && !p.hasPower("BladeJackPower")) {
            group.addToBottom(new BladeMach_s());
            group.addToBottom(new BladeMetal_s());
            group.addToBottom(new BladeThunder_s());
            group.addToBottom(new BladeKick_s());
            group.addToBottom(new BladeBeat_s());
            group.addToBottom(new BladeSlash_s());
            AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
            this.RouzeCard = true;
         } else if (x >= 5 && p.hasPower("BladeJackPower")) {
            group.addToBottom(new BladeMach_s());
            group.addToBottom(new BladeMetal_s());
            group.addToBottom(new BladeThunder_s());
            group.addToBottom(new BladeKick_s());
            group.addToBottom(new BladeBeat_s());
            group.addToBottom(new BladeSlash_s());
            AbstractDungeon.gridSelectScreen.open(group, 1, "选择1张获得", false, false, true, false);
            this.RouzeCard = true;
         } else if (x >= 5 && p.hasPower("DecadeViolentEmotionPower") && SpecialRideBooker.decadepoint > 0) {
            --SpecialRideBooker.decadepoint;
         }
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Blade_BlayRouzer();
   }

   public void optionDecade() {
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.name = NAME;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.name = EXTENDED_DESCRIPTION[5];
         this.loadCardImage("img/cards/Blade_BlayRouzer_plus.png");
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionAgito() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionBlade() {
      if (AbstractDungeon.player.hasPower("BladeJackPower")) {
         this.name = EXTENDED_DESCRIPTION[5];
         this.loadCardImage("img/cards/Blade_BlayRouzer_plus.png");
      } else {
         this.name = NAME;
         this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      }

      this.initializeDescription();
   }

   public void optionHibiki() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionDenO() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionKiva() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.name = NAME;
      this.loadCardImage("img/cards/Blade_BlayRouzer.png");
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   public void update() {
      super.update();
      if (AbstractDungeon.player != null) {
         AbstractPlayer p = AbstractDungeon.player;
         int x = 0;
         int d = 0;
         if (p.hasPower("BladeSlashPower")) {
            d += p.getPower("BladeSlashPower").amount * 2;
         }

         for(AbstractPower power : p.powers) {
            if (power.type == PowerType.BUFF) {
               ++x;
            }
         }

         d += x * 5;
         this.rawDescription = EXTENDED_DESCRIPTION[0] + d + EXTENDED_DESCRIPTION[1];
         if (p.hasPower("BladeJackPower")) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[7];
         } else if (p.hasPower("KamenRideBladePower")) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[6];
         } else if (p.hasPower("DecadeViolentEmotionPower")) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[4];
         }

         this.initializeDescription();
      }

      if (this.RouzeCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
         switch (((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).cardID) {
            case "BladeBeat_s":
               c = new Blade_Beat();
               break;
            case "BladeKick_s":
               c = new Blade_Kick();
               break;
            case "BladeMach_s":
               c = new Blade_Mach();
               break;
            case "BladeMetal_s":
               c = new Blade_Metal();
               break;
            case "BladeSlash_s":
               c = new Blade_Slash();
               break;
            case "BladeThunder_s":
               c = new Blade_Thunder();
         }

         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
         c = null;
         group.clear();
         AbstractDungeon.gridSelectScreen.selectedCards.clear();
         this.RouzeCard = false;
      }

   }

   static {
      group = new CardGroup(CardGroupType.CARD_POOL);
      c = null;
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_BlayRouzer");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
