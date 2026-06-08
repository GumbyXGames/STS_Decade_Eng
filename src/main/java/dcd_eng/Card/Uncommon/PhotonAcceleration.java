package dcd_eng.Card.Uncommon;

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
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.PhotonAccelerationPower;

public class PhotonAcceleration extends AbstractCustomCardWithType {
   public static final String ID = "PhotonAcceleration";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PhotonAcceleration.png";
   private static final int COST = 2;

   public PhotonAcceleration() {
      super("PhotonAcceleration", NAME, "img/cards/PhotonAcceleration.png", 2, DESCRIPTION, CardType.POWER, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kabuto);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 1;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PhotonAccelerationPower(p), this.magicNumber));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("PhotonAccelerationPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new PhotonAcceleration();
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
         this.rawDescription = UPGRADE_DESCRIPTION;
         this.initializeDescription();
         this.isInnate = true;
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("PhotonAcceleration");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   }
}
