package dcd_eng.Card.Uncommon;

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
import dcd_eng.Power.BladeKickPower;
import java.util.ArrayList;
import java.util.List;

public class Blade_Kick extends AbstractCustomCardWithType {
   public static final String ID = "Blade_Kick";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BladeKick.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;

   public Blade_Kick() {
      super("Blade_Kick", NAME, "img/cards/BladeKick.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Blade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UndeadCard);
      this.baseMagicNumber = this.magicNumber = 1;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("blade_kick", 0.0F);
      if (!p.hasPower("BladeKickPower")) {
         AbstractDungeon.actionManager.addToBottom(new RemoveRouzePowerAction(p, p));
      }

      if (p.hasPower("KamenRideBladePower") || p.hasPower("DecadeViolentEmotionPower")) {
         boolean inhand = false;

         for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals("FinalAttackRide")) {
               inhand = true;
               break;
            }
         }

         if (!inhand) {
            boolean done = false;

            for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
               if (c.cardID.equals("FinalAttackRide")) {
                  AbstractDungeon.player.discardPile.removeCard(c);
                  AbstractDungeon.player.hand.addToHand(c);
                  done = true;
                  break;
               }
            }

            for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
               if (c.cardID.equals("FinalAttackRide") && !done) {
                  AbstractDungeon.player.drawPile.removeCard(c);
                  AbstractDungeon.player.hand.addToHand(c);
                  done = true;
                  break;
               }
            }

            for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
               if (c.cardID.equals("FinalAttackRide") && !done) {
                  AbstractDungeon.player.exhaustPile.removeCard(c);
                  AbstractDungeon.player.hand.addToHand(c);
                  break;
               }
            }
         }
      }

      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeKickPower(p), this.magicNumber));
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Blade_Kick();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Kick");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
