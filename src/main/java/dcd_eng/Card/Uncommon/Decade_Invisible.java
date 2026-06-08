package dcd_eng.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;

public class Decade_Invisible extends AbstractCustomCardWithType {
   public static final String ID = "Decade_Invisible";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Decade_Invisible.png";
   private static final int COST = 2;
   private static final int BLOCK_AMT = 15;

   public Decade_Invisible() {
      super("Decade_Invisible", NAME, "img/cards/Decade_Invisible.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.baseBlock = 15;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      int x = 0;
      if (p.hasPower("Dexterity") && p.getPower("Dexterity").amount > 0) {
         x = p.getPower("Dexterity").amount;
      }

      if (this.upgraded) {
         x *= 2;
      }

      AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block + x));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EntanglePower(p), 1));
   }

   public AbstractCard makeCopy() {
      return new Decade_Invisible();
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
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         this.initializeDescription();
      }

   }

   public void update() {
      super.update();
      if (AbstractDungeon.player != null) {
         int x = 0;
         if (AbstractDungeon.player.hasPower("Dexterity") && AbstractDungeon.player.getPower("Dexterity").amount > 0) {
            x = AbstractDungeon.player.getPower("Dexterity").amount;
         }

         if (this.upgraded) {
            x *= 2;
            this.rawDescription = EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[1] + x + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[1] + x + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[4];
         }

         this.initializeDescription();
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Invisible");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
