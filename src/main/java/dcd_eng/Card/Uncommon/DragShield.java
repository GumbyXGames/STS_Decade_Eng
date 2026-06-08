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
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.DragShieldPower;
import dcd_eng.Vfx.DragShield_sounds;
import dcd_eng.Vfx.Ryuki_guard;
import java.util.ArrayList;
import java.util.List;

public class DragShield extends AbstractCustomCardWithType {
   public static final String ID = "DragShield";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DragShield.png";
   private static final int COST = 1;
   private static final int MAGIC_NUM = 2;
   private boolean change = true;
   private List<TooltipInfo> tips;
   private List<TooltipInfo> tips2;

   public DragShield() {
      super("DragShield", NAME, "img/cards/DragShield.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Ryuki);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 2;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
      this.tips2 = new ArrayList();
      this.tips2.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragShieldPower(p, this.magicNumber), this.magicNumber));
      if (!p.hasPower("DragShieldPower") && p.hasPower("KamenRideRyukiPower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Ryuki_guard(), 0.8F));
      }

      AbstractDungeon.actionManager.addToTop(new VFXAction(new DragShield_sounds(), 1.5F));
   }

   public AbstractCard makeCopy() {
      return new DragShield();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return DCDmod.RyukiCardLv[1] > 0 ? this.tips2 : this.tips;
   }

   public void optionDecade() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.baseMagicNumber = this.magicNumber = 3;
         if (this.upgraded) {
            this.baseMagicNumber = this.magicNumber = 4;
         }
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKuuga() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.baseMagicNumber = this.magicNumber = 3;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 4;
      }

      this.rawDescription = DESCRIPTION;
      if (DCDmod.RyukiCardLv[1] > 0) {
         int i = DCDmod.RyukiCardLv[1];
         if (i > 20) {
            i = 20;
         }

         this.upgraded = true;
         this.name = NAME + "Lv" + i;
         if (i >= 10) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[5];
         }

         if (i >= 20) {
            this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[6];
         }
      }

      this.initializeDescription();
   }

   public void optionFaiz() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionKiva() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.baseMagicNumber = this.magicNumber = 2;
      if (this.upgraded) {
         this.baseMagicNumber = this.magicNumber = 3;
      }

      this.rawDescription = DESCRIPTION;
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeMagicNumber(1);
      }

   }

   public boolean canUpgrade() {
      return false;
   }

   public void update() {
      super.update();
      if (this.exhaust && this.change) {
         this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION[0];
         this.initializeDescription();
         this.change = false;
      }

      if (!this.exhaust) {
         this.change = true;
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("DragShield");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
