package dcd_eng.Card.Special;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Vfx.Axel_PunchingUnit_Timer;
import dcd_eng.Vfx.Faiz_FAR_SoundsAndAnimation;
import dcd_eng.Vfx.Faiz_PunchingUnit;

public class PunchingUnit extends AbstractCustomCardWithType {
   public static final String ID = "PunchingUnit";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FinalAttackRide_Faiz.png";
   private static final int COST = 2;
   private static final int ATTACK_DMG = 20;

   public PunchingUnit() {
      super("PunchingUnit", NAME, "img/cards/FinalAttackRide_Faiz.png", 2, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Faiz);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 20;
      this.exhaust = true;
      this.setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (SpecialFaizBox.AxelForm) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_PunchingUnit_Timer(this.damage), 0.0F));
      } else {
         CardCrawlGame.sound.playA("FAR", 0.0F);
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_PunchingUnit(m.drawX, m.drawY, m, this.damage), 0.0F));
         if (!DCDmod.AnimationTrigger && p.hasPower("KamenRideFaizPower")) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.music.playTempBGM("faiz_BGM2.ogg");
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 4.425F));
         } else {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Faiz_FAR_SoundsAndAnimation(p.drawX, p.drawY), 2.0F));
         }
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideFaizPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
         }

         return canUse;
      }
   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (arg0.currentBlock > 0) {
         this.damage += arg0.currentBlock * 3;
         this.isDamageModified = true;
      }

   }

   public AbstractCard makeCopy() {
      return new PunchingUnit();
   }

   public void optionDecade() {
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[1];
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.setBackgroundTexture("img/512/FAR.png", "img/1024/FAR.png");
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("PunchingUnit");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
