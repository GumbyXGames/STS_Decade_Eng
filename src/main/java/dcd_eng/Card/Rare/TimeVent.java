package dcd_eng.Card.Rare;

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
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Patches.ModBaseClassForSLExample;

public class TimeVent extends AbstractCustomCardWithType {
   public static final String ID = "TimeVent";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TimeVent.png";
   private static final int COST = 3;
   public static boolean TimeVentUpgraded = true;

   public TimeVent() {
      super("TimeVent", NAME, "img/cards/TimeVent.png", 3, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.setBackgroundTexture("img/512/skill_ODIN.png", "img/1024/skill_ODIN.png");
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      ModBaseClassForSLExample.timevent = true;
      Decade.removeTimeVent = true;
      this.moveToDiscardPile();
      if (!this.upgraded) {
         ModBaseClassForSLExample.TimeVentUpgraded = false;
         TimeVentUpgraded = false;

         for(AbstractCard c : p.masterDeck.group) {
            if (c.cardID.equals(this.cardID) && !c.upgraded) {
               AbstractDungeon.player.masterDeck.removeCard(c);
               break;
            }
         }
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (ModBaseClassForSLExample.timevent) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new TimeVent();
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
         this.exhaust = true;
         this.rawDescription = UPGRADE_DESCRIPTION;
         this.initializeDescription();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("TimeVent");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
