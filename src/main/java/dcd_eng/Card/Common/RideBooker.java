package dcd_eng.Card.Common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Card.SelectCard.GunForm;
import dcd_eng.Card.SelectCard.SwordForm;
import dcd_eng.Card.Special.Decade_Blast;
import dcd_eng.Card.Special.RideBooker_Attack;
import dcd_eng.Card.Special.RideBooker_Shoot;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.GunFormPower;
import dcd_eng.Power.SwordFormPower;
import dcd_eng.ui.CardRewardScreenPatch;
import java.util.ArrayList;

public class RideBooker extends AbstractCustomCardWithType {
   public static final String ID = "RideBooker";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RideBooker.png";
   private static final int COST = 1;
   private boolean SelectCard = false;

   public RideBooker() {
      super("RideBooker", NAME, "img/cards/RideBooker.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      ArrayList<AbstractCard> temp = new ArrayList();
      AbstractCard c = new GunForm();
      temp.add(c);
      AbstractCard c1 = new SwordForm();
      temp.add(c1);
      AbstractDungeon.cardRewardScreen.customCombatOpen(temp, "选择1种模式", false);
      this.SelectCard = true;
   }

   public AbstractCard makeCopy() {
      return new RideBooker();
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
            if (AbstractDungeon.cardRewardScreen.discoveryCard.cardID.equals("SwordForm")) {
               if (!AbstractDungeon.player.hasPower("SwordFormPower")) {
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new RideBooker_Attack(), 3, true, true));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SwordFormPower(AbstractDungeon.player, 1), 1));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
               }

               if (AbstractDungeon.player.hasPower("GunFormPower")) {
                  AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "GunFormPower"));
               }
            } else {
               if (!AbstractDungeon.player.hasPower("GunFormPower")) {
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new RideBooker_Shoot(), 3, true, true));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Decade_Blast(), 1, true, true));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GunFormPower(AbstractDungeon.player), 1));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
               }

               if (AbstractDungeon.player.hasPower("SwordFormPower")) {
                  AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "SwordFormPower"));
               }
            }

            this.SelectCard = false;
         }
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
