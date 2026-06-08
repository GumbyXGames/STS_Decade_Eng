package dcd_eng.Card.Special;

import basemod.helpers.TooltipInfo;
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
import dcd_eng.Actions.RemoveRouzePowerAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.BladeSlashPower;
import java.util.ArrayList;
import java.util.List;

public class Blade_Slash_s extends AbstractCustomCardWithType {
   public static final String ID = "Blade_Slash_s";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BladeSlash.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;
   private boolean done = false;

   public Blade_Slash_s() {
      super("Blade_Slash_s", NAME, "img/cards/BladeSlash.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Blade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UndeadCard);
      this.baseMagicNumber = this.magicNumber = 1;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("blade_slash", 0.0F);
      if (p.hasPower("BladeSlashPower") && p.getPower("BladeSlashPower").amount >= 2) {
         for(AbstractCard c : p.drawPile.group) {
            if (c.cardID.equals("Blade_Slash") && !this.done) {
               p.drawPile.moveToHand(c, p.drawPile);
               this.done = true;
               break;
            }
         }

         for(AbstractCard c : p.discardPile.group) {
            if (c.cardID.equals("Blade_Slash") && !this.done) {
               p.drawPile.moveToHand(c, p.discardPile);
               this.done = true;
               break;
            }
         }
      } else {
         if (!p.hasPower("BladeSlashPower")) {
            AbstractDungeon.actionManager.addToBottom(new RemoveRouzePowerAction(p, p));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeSlashPower(p, this.magicNumber), this.magicNumber));
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Blade_Slash_s();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Slash_s");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
