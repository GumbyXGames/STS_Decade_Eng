package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
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
import dcd_eng.Actions.HenshinAndRemoveKamenRideAction;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RemoveKamenRideAction2;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Actions.UpdatePowerAmountAction;
import dcd_eng.Card.Special.UnknownWhistle;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.DecadeHungryPower;
import dcd_eng.Power.KamenRideKivaPower;
import dcd_eng.Power.KivaHungry2Power;
import dcd_eng.Power.KivaHungry3Power;
import dcd_eng.Vfx.FAR_SoundTimer;
import java.util.ArrayList;
import java.util.List;

public class KamenRideKiva extends AbstractCustomCardWithType {
   public static final String ID = "KamenRideKiva";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/KamenRideKiva.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;
   private int KRNumber;

   public KamenRideKiva() {
      super("KamenRideKiva", NAME, "img/cards/KamenRideKiva.png", 1, DESCRIPTION, CardType.POWER, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kiva);
      this.baseMagicNumber = this.magicNumber = 1;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.KamenRide);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.cardsToPreview = new UnknownWhistle();
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         this.addToBot(new ApplyPowerAction(p, p, new DecadeHungryPower(p)));
      } else {
         if (DCDmod.HenshinTrigger) {
            AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction2(p, p));
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(176);
            CardCrawlGame.sound.playA("kamenride", 0.0F);
            CardCrawlGame.sound.playA("kiva_henshin", 0.0F);
            if (!DCDmod.BGMTrigger) {
               TurnTimer.StopBGM(false);
               CardCrawlGame.music.playTempBGM("kiva_OP1.ogg");
            }
         } else {
            AbstractDungeon.actionManager.addToTop(new HenshinAndRemoveKamenRideAction(p, p, this.KRNumber, "kiva"));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideKivaPower(p), 1));
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new UnknownWhistle(), 1));
         if (p.maxHealth - p.currentHealth >= 50) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KivaHungry3Power(p), 1));
            this.addToBot(new UpdatePowerAmountAction(p));
         } else if (p.maxHealth - p.currentHealth >= 20) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KivaHungry2Power(p), 1));
         }
      }

      this.addToTop(new VFXAction(new FAR_SoundTimer("KamenRide", true)));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("KamenRideKivaPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[3];
         }

         return canUse;
      }
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new KamenRideKiva();
   }

   public void optionDecade() {
      this.KRNumber = 10;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[4];
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.KRNumber = 1;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionAgito() {
      this.KRNumber = 2;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionRyuki() {
      this.KRNumber = 3;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionFaiz() {
      this.KRNumber = 4;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionBlade() {
      this.KRNumber = 5;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionHibiki() {
      this.KRNumber = 6;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionKabuto() {
      this.KRNumber = 7;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionDenO() {
      this.KRNumber = 8;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionKiva() {
      this.KRNumber = 9;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
   }

   public void optionNeutral() {
      this.KRNumber = 0;
      if (this.upgraded) {
         this.rawDescription = UPGRADE_DESCRIPTION;
      } else {
         this.rawDescription = DESCRIPTION;
      }

      this.initializeDescription();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRideKiva");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
