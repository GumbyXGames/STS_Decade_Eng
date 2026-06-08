package dcd_eng.Card.Special;

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
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.AgitoPowerPower;
import dcd_eng.Vfx.Agito_Power_Animation;
import dcd_eng.Vfx.Agito_power;
import dcd_eng.Vfx.Agito_power_t;

public class AgitoPower extends AbstractCustomCardWithType {
   public static final String ID = "AgitoPower";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AgitoPower.png";
   private static final int COST = 0;
   private int form = 0;

   public AgitoPower() {
      super("AgitoPower", NAME, "img/cards/AgitoPower.png", 0, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Agito);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 1;
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AgitoPowerPower(p, this.magicNumber), this.magicNumber));
      String f = null;
      switch (this.form) {
         case 0:
            f = null;
            break;
         case 1:
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_power(), 1.5F));
            break;
         case 2:
            f = "f";
            break;
         case 3:
            f = "s";
            break;
         case 4:
            f = "t";
      }

      if (f != null) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_power_t(f), 0.6F));
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_Power_Animation(), 0.0F));
      }

   }

   public AbstractCard makeCopy() {
      return new AgitoPower();
   }

   public void optionDecade() {
      this.form = 0;
   }

   public void optionKuuga() {
      this.form = 0;
   }

   public void optionAgito() {
      if (AbstractDungeon.player.hasPower("AgitoStormPower") && AbstractDungeon.player.hasPower("AgitoFlamePower")) {
         this.form = 4;
      } else if (AbstractDungeon.player.hasPower("AgitoStormPower")) {
         this.form = 3;
      } else if (AbstractDungeon.player.hasPower("AgitoFlamePower")) {
         this.form = 2;
      } else if (AbstractDungeon.player.hasPower("KamenRideAgitoPower")) {
         this.form = 1;
      }

   }

   public void optionRyuki() {
      this.form = 0;
   }

   public void optionFaiz() {
      this.form = 0;
   }

   public void optionBlade() {
      this.form = 0;
   }

   public void optionHibiki() {
      this.form = 0;
   }

   public void optionKabuto() {
      this.form = 0;
   }

   public void optionDenO() {
      this.form = 0;
   }

   public void optionKiva() {
      this.form = 0;
   }

   public void optionNeutral() {
      this.form = 0;
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("AgitoPower");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
