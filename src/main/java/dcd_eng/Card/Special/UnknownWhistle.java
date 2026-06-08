package dcd_eng.Card.Special;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Card.SelectCard.BasshaaWhistle;
import dcd_eng.Card.SelectCard.DoggaWhistle;
import dcd_eng.Card.SelectCard.GaruruWhistle;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KivaBasshaaPower;
import dcd_eng.Power.KivaDoggaPower;
import dcd_eng.Power.KivaGaruruPower;
import dcd_eng.Power.RemoveFormRidePower;
import dcd_eng.Vfx.Kiva_BasshaaSounds;
import dcd_eng.Vfx.Kiva_DoggaSounds;
import dcd_eng.Vfx.Kiva_GaruruSounds;
import dcd_eng.ui.CardRewardScreenPatch;
import java.util.ArrayList;

public class UnknownWhistle extends AbstractCustomCardWithType {
   public static final String ID = "UnknownWhistle";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/UnknownWhistle.png";
   private static final int COST = 1;
   private boolean SelectCard = false;
   private AbstractPlayer p;

   public UnknownWhistle() {
      super("UnknownWhistle", NAME, "img/cards/UnknownWhistle.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.p = AbstractDungeon.player;
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
      this.selfRetain = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      ArrayList<AbstractCard> temp = new ArrayList();
      AbstractCard c = new GaruruWhistle();
      temp.add(c);
      AbstractCard c1 = new BasshaaWhistle();
      temp.add(c1);
      AbstractCard c2 = new DoggaWhistle();
      temp.add(c2);
      AbstractDungeon.cardRewardScreen.customCombatOpen(temp, "选择1种哨笛", false);
      this.SelectCard = true;
   }

   public AbstractCard makeCopy() {
      return new UnknownWhistle();
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideKivaPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         }

         return canUse;
      }
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
      if (AbstractDungeon.player != null) {
         if (this.SelectCard) {
            CardRewardScreenPatch.isReward = false;
         }

         if (this.SelectCard && AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            switch (AbstractDungeon.cardRewardScreen.discoveryCard.cardID) {
               case "GaruruWhistle":
                  CardCrawlGame.sound.playA("formride", 0.0F);
                  this.addToBot(new VFXAction(new Kiva_GaruruSounds(), 0.0F));
                  this.addToBot(new RemoveFormRideAction(this.p, this.p));
                  this.addToBot(new ApplyPowerAction(this.p, this.p, new KivaGaruruPower(this.p), 1));
                  this.addToBot(new ApplyPowerAction(this.p, this.p, new RemoveFormRidePower(this.p, 3), 3));
                  break;
               case "BasshaaWhistle":
                  CardCrawlGame.sound.playA("formride", 0.0F);
                  this.addToBot(new VFXAction(new Kiva_BasshaaSounds(), 0.0F));
                  this.addToBot(new RemoveFormRideAction(this.p, this.p));
                  this.addToBot(new ApplyPowerAction(this.p, this.p, new KivaBasshaaPower(this.p), 1));
                  this.addToBot(new ApplyPowerAction(this.p, this.p, new RemoveFormRidePower(this.p, 3), 3));
                  break;
               case "DoggaWhistle":
                  CardCrawlGame.sound.playA("formride", 0.0F);
                  this.addToBot(new VFXAction(new Kiva_DoggaSounds(), 0.0F));
                  this.addToBot(new RemoveFormRideAction(this.p, this.p));
                  this.addToBot(new ApplyPowerAction(this.p, this.p, new KivaDoggaPower(this.p), 1));
                  this.addToBot(new ApplyPowerAction(this.p, this.p, new RemoveFormRidePower(this.p, 3), 3));
            }

            this.SelectCard = false;
         }
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("UnknownWhistle");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
