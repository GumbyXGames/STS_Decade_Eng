package dcd_eng.Card.Rare;

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
import dcd_eng.Actions.RemoveKamenRideAction;
import dcd_eng.Card.Uncommon.RideBooker_Skill_2;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.DecadeViolentEmotionPower;
import dcd_eng.Power.KamenRideDecadePower;
import dcd_eng.Vfx.Decade_toViolentEmotion;

public class DecadeViolentEmotion extends AbstractCustomCardWithType {
   public static final String ID = "DecadeViolentEmotion";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DecadeViolentEmotion.png";
   private static final int COST = 3;
   private int KRNumber;

   public DecadeViolentEmotion() {
      super("DecadeViolentEmotion", NAME, "img/cards/DecadeViolentEmotion.png", 3, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.RARE, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      RideBooker_Skill_2.RBS2isDone = false;
      if (this.KRNumber == 0) {
         CardCrawlGame.sound.playA("BGM", 0.0F);
         CardCrawlGame.sound.playA("decade_henshin", 0.0F);
         AbstractDungeon.actionManager.addToTop(new HenshinAndRemoveKamenRideAction(p, p, this.KRNumber, "decade"));
         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DecadeViolentEmotionPower(p), 1));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KamenRideDecadePower(p), 1));
      } else if (this.KRNumber == 10) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_toViolentEmotion(), 1.5F));
         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DecadeViolentEmotionPower(p), 1));
      } else {
         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DecadeViolentEmotionPower(p), 1));
         CardCrawlGame.sound.playA("driversounds", 0.0F);
         CardCrawlGame.sound.playA("test1", 0.0F);
         AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new KamenRideDecadePower(p), 1));
         AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(p, p));
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Decade_toViolentEmotion(), 0.0F));
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new DecadeViolentEmotion();
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
         this.upgradeBaseCost(2);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("DecadeViolentEmotion");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
