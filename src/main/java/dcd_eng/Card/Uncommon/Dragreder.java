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
import dcd_eng.Power.DragrederPower;
import dcd_eng.Vfx.Dragreder_appear;
import dcd_eng.Vfx.Dragreder_sounds;
import java.util.ArrayList;
import java.util.List;

public class Dragreder extends AbstractCustomCardWithType {
   public static final String ID = "Dragreder";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Dragreder.png";
   private static final int COST = 3;
   private static final int MAGIC_NUM = 3;
   private List<TooltipInfo> tips;

   public Dragreder() {
      super("Dragreder", NAME, "img/cards/Dragreder.png", 3, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.UNCOMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Ryuki);
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
      this.baseMagicNumber = this.magicNumber = 3;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      AbstractDungeon.actionManager.addToTop(new VFXAction(new Dragreder_sounds(), 0.0F));
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new Dragreder_appear(), 2.5F));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DragrederPower(p, this.magicNumber), this.magicNumber));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("DragrederPower")) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            canUse = false;
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new Dragreder();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void optionDecade() {
      this.baseMagicNumber = this.magicNumber = 3;
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.baseMagicNumber = this.magicNumber = 4;
      }

   }

   public void optionKuuga() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionAgito() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionRyuki() {
      this.baseMagicNumber = this.magicNumber = 4;
   }

   public void optionFaiz() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionBlade() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionHibiki() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionKabuto() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionDenO() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionKiva() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void optionNeutral() {
      this.baseMagicNumber = this.magicNumber = 3;
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(2);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Dragreder");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
