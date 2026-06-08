package dcd_eng.Card.Common;

import basemod.abstracts.CustomSavable;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Actions.RemoveFormRideAction;
import dcd_eng.Actions.RemoveHalfAttributeAction;
import dcd_eng.Card.Special.AgitoPower;
import dcd_eng.Card.Special.FlameSpecialCard;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.AgitoFlamePower;
import dcd_eng.Power.FlameLevelPower;
import dcd_eng.Vfx.Agito_flame_AnimationAndSounds;
import java.util.ArrayList;
import java.util.List;

public class FormRideFlame extends AbstractCustomCardWithType implements CustomSavable<int[]> {
   public static final String ID = "FormRideFlame";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FormRideFlame.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;

   public FormRideFlame() {
      this(0);
   }

   private FormRideFlame(int upgrades) {
      super("FormRideFlame", NAME, "img/cards/FormRideFlame.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Agito);
      if (upgrades > 9) {
         upgrades = 9;
      }

      this.timesUpgraded = upgrades;
      this.baseMagicNumber = 1;
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.FormRide);
      this.tips = new ArrayList();
      switch (this.timesUpgraded) {
         case 0:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
            break;
         case 1:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2]));
            break;
         case 2:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3]));
            break;
         case 3:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4]));
            break;
         case 4:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5]));
            break;
         case 5:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6]));
            break;
         case 6:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7]));
            break;
         case 7:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7] + EXTENDED_DESCRIPTION[8]));
            break;
         case 8:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7] + EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[9]));
            break;
         case 9:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7] + EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[9] + EXTENDED_DESCRIPTION[10]));
      }

   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
      } else if (p.hasPower("KamenRideAgitoPower")) {
         if (!p.hasPower("AgitoFlamePower")) {
            AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
         }

         if (p.hasPower("AgitoFlamePower") && p.hasPower("AgitoStormPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
         }

         if (!p.hasPower("AgitoFlamePower") || p.hasPower("AgitoFlamePower") && p.getPower("FlameLevelPower").amount < this.timesUpgraded + 1) {
            if (p.hasPower("AgitoStormPower")) {
               if (p.getPower("StormLevelPower").amount <= 4) {
                  AbstractDungeon.actionManager.addToTop(new RemoveFormRideAction(p, p));
                  AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
               } else if (p.hasPower("AgitoFlamePower")) {
                  AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
               }
            }

            if (this.timesUpgraded >= 9) {
               this.timesUpgraded = 9;
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AgitoFlamePower(p), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameLevelPower(p, this.timesUpgraded + 1), this.timesUpgraded + 1));
            switch (this.timesUpgraded) {
               case 1:
               case 2:
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameSpecialCard(), 1));
                  break;
               case 3:
               case 4:
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameSpecialCard(), 1));
                  break;
               case 5:
               case 6:
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 4), 4));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameSpecialCard(), 1));
                  break;
               case 7:
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 6), 6));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameSpecialCard(), 1));
                  break;
               case 8:
               case 9:
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 6), 6));
                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 2));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new AgitoPower(), 1));
                  AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new FlameSpecialCard(), 1));
            }

            CardCrawlGame.sound.playA("formride", 0.0F);
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame_AnimationAndSounds(), 0.73F));
         }
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KamenRideAgitoPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[11];
            canUse = false;
         }

         if (p.hasPower("DecadeViolentEmotionPower")) {
            canUse = true;
         }

         return canUse;
      }
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new FormRideFlame(this.timesUpgraded);
   }

   public void optionDecade() {
      this.rawDescription = DESCRIPTION;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[12];
         int x = (this.timesUpgraded + 1) / 2;
         if (x == 0) {
            x = 1;
         }

         this.magicNumber = x;
         this.exhaust = true;
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionKabuto() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.exhaust = false;
      this.initializeDescription();
   }

   public void upgrade() {
      this.upgradeMagicNumber(1);
      ++this.timesUpgraded;
      if (this.timesUpgraded >= 9) {
         this.timesUpgraded = 9;
      }

      this.upgraded = true;
      this.name = NAME + "Lv" + (this.timesUpgraded + 1);
      this.tips = new ArrayList();
      switch (this.timesUpgraded) {
         case 0:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
            break;
         case 1:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2]));
            break;
         case 2:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3]));
            break;
         case 3:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4]));
            break;
         case 4:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5]));
            break;
         case 5:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6]));
            break;
         case 6:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7]));
            break;
         case 7:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7] + EXTENDED_DESCRIPTION[8]));
            break;
         case 8:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7] + EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[9]));
            break;
         case 9:
            this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1] + EXTENDED_DESCRIPTION[2] + EXTENDED_DESCRIPTION[3] + EXTENDED_DESCRIPTION[4] + EXTENDED_DESCRIPTION[5] + EXTENDED_DESCRIPTION[6] + EXTENDED_DESCRIPTION[7] + EXTENDED_DESCRIPTION[8] + EXTENDED_DESCRIPTION[9] + EXTENDED_DESCRIPTION[10]));
      }

      this.initializeTitle();
   }

   public boolean canUpgrade() {
      if (AbstractDungeon.getCurrRoom() == null) {
         return false;
      } else {
         return this.timesUpgraded <= 8 && !(AbstractDungeon.getCurrRoom() instanceof RestRoom) && !(AbstractDungeon.getCurrRoom() instanceof EventRoom);
      }
   }

   public void update() {
      super.update();
      if (this.timesUpgraded >= 9) {
         this.timesUpgraded = 9;
         this.initializeTitle();
      }

   }

   public void onLoad(int[] arg0) {
   }

   public int[] onSave() {
      return null;
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRideFlame");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
