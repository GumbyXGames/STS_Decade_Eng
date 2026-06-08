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
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.HenshinAndRemoveKamenRideAction;
import dcd_eng.Actions.RemoveKamenRideAction2;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KamenRideKuugaPower;
import dcd_eng.Power.SuperRegenPower;
import dcd_eng.Vfx.FAR_SoundTimer;
import java.util.ArrayList;
import java.util.List;

public class KamenRideKuuga extends AbstractCustomCardWithType {
   public static final String ID = "KamenRideKuuga";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/KamenRideKuuga.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;
   private int KRNumber;

   public KamenRideKuuga() {
      super("KamenRideKuuga", NAME, "img/cards/KamenRideKuuga.png", 1, DESCRIPTION, CardType.POWER, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kuuga);
      this.baseMagicNumber = this.magicNumber = 2;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.KamenRide);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SuperRegenPower(p, 3), 3));
      } else {
         if (DCDmod.HenshinTrigger) {
            AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction2(p, p));
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(4);
            CardCrawlGame.sound.playA("kamenride", 0.0F);
            CardCrawlGame.sound.playA("kuuga_henshin", 0.0F);
            if (!DCDmod.BGMTrigger) {
               TurnTimer.StopBGM(false);
               CardCrawlGame.music.playTempBGM("kuuga_OP1.ogg");
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
            if (p.hasPower("SuperRegenPower")) {
               if (p.getPower("SuperRegenPower").amount <= 4) {
                  int n = 5 - p.getPower("SuperRegenPower").amount;
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SuperRegenPower(p, n), n));
               }
            } else {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SuperRegenPower(p, 5), 5));
            }
         } else {
            AbstractDungeon.actionManager.addToTop(new HenshinAndRemoveKamenRideAction(p, p, this.KRNumber, "kuuga"));
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideKuugaPower(p), 1));
      }

      this.addToTop(new VFXAction(new FAR_SoundTimer("KamenRide", true)));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("KamenRideKuugaPower")) {
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
      return new KamenRideKuuga();
   }

   public void optionDecade() {
      this.KRNumber = 10;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         if (this.upgraded) {
            this.rawDescription = EXTENDED_DESCRIPTION[5];
         } else {
            this.rawDescription = EXTENDED_DESCRIPTION[4];
         }
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRideKuuga");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
