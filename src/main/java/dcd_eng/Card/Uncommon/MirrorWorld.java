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
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.MirrorWorldPower;
import dcd_eng.Relic.MirrorWorldRelic;

public class MirrorWorld extends AbstractCustomCardWithType {
   public static final String ID = "MirrorWorld";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MirrorWorld.png";
   private static final int COST = 1;

   public MirrorWorld() {
      super("MirrorWorld", NAME, "img/cards/MirrorWorld.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Ryuki);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 1;
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      boolean hasRelic = false;
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MirrorWorldPower(p), this.magicNumber));

      for(AbstractRelic r1 : p.relics) {
         if (r1.relicId.equals("MirrorWorldRelic")) {
            hasRelic = true;
            break;
         }
      }

      if (!hasRelic) {
         AbstractRelic r = new MirrorWorldRelic();
         AbstractDungeon.player.relics.add(r);
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("MirrorWorldPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new MirrorWorld();
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

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("MirrorWorld");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
