package dcd_eng.Card.Common;

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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.HenshinAndRemoveKamenRideAction;
import dcd_eng.Actions.NewRideBookerCardAction;
import dcd_eng.Actions.RemoveKamenRideAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Card.Basic.KamenRide;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KamenRideDecadePower;
import dcd_eng.Vfx.FAR_SoundTimer;

public class KamenRideDecade extends AbstractCustomCardWithType {
   public static final String ID = "KamenRideDecade";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String UPGRADE_DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/KamenRideDecade.png";
   private static final int COST = 1;
   private int KRNumber;

   public KamenRideDecade() {
      super("KamenRideDecade", NAME, "img/cards/KamenRideDecade.png", 1, DESCRIPTION, CardType.POWER, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.baseMagicNumber = this.magicNumber = 1;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.KamenRide);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideDecadePower(p), 1));
      if (SpecialRideBooker.nodecade) {
         CardCrawlGame.sound.playA("driversounds", 0.0F);
         CardCrawlGame.sound.playA("test1", 0.0F);
         AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(p, p));
         if (!DCDmod.BGMTrigger) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.music.playTempBGM("decade_OP1.ogg");
         }
      } else {
         CardCrawlGame.sound.playA("decade_henshin", 0.0F);
         if (DCDmod.HenshinTrigger) {
            AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(p, p));
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(1);
            if (!DCDmod.BGMTrigger) {
               TurnTimer.StopBGM(false);
               CardCrawlGame.music.playTempBGM("decade_OP1.ogg");
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
         } else {
            TurnTimer.StopBGM(false);
            CardCrawlGame.sound.playA("BGM", 0.0F);
            AbstractDungeon.actionManager.addToTop(new HenshinAndRemoveKamenRideAction(p, p, this.KRNumber, "decade"));
         }
      }

      NewRideBookerCardAction.BasicGroup.group.add(new KamenRide());
      this.addToTop(new VFXAction(new FAR_SoundTimer("KamenRide", true)));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("KamenRideDecadePower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new KamenRideDecade();
   }

   public void optionDecade() {
      this.KRNumber = 10;
   }

   public void optionKuuga() {
      this.KRNumber = 1;
   }

   public void optionAgito() {
      this.KRNumber = 2;
   }

   public void optionRyuki() {
      this.KRNumber = 3;
   }

   public void optionFaiz() {
      this.KRNumber = 4;
   }

   public void optionBlade() {
      this.KRNumber = 5;
   }

   public void optionHibiki() {
      this.KRNumber = 6;
   }

   public void optionKabuto() {
      this.KRNumber = 7;
   }

   public void optionDenO() {
      this.KRNumber = 8;
   }

   public void optionKiva() {
      this.KRNumber = 9;
   }

   public void optionNeutral() {
      this.KRNumber = 0;
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("KamenRideDecade");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
