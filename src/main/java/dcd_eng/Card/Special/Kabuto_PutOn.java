package dcd_eng.Card.Special;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.KabutoMaskedPower;
import dcd_eng.Vfx.Kabuto_RiderToMasked;
import java.util.ArrayList;
import java.util.List;

public class Kabuto_PutOn extends AbstractCustomCardWithType {
   public static final String ID = "Kabuto_PutOn";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kabuto_PutOn.png";
   private static final int COST = 0;
   private List<TooltipInfo> tips;

   public Kabuto_PutOn() {
      super("Kabuto_PutOn", NAME, "img/cards/Kabuto_PutOn.png", 0, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kabuto);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.KamenRide);
      this.exhaust = true;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
      this.selfRetain = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_RiderToMasked(), 1.2F));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KabutoMaskedPower(p, 1), 1));
      AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_CastOff(), 1));
      KabutoMaskedPower.PhotonPoint = 0;
   }

   public AbstractCard makeCopy() {
      return new Kabuto_PutOn();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("KabutoMaskedPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         if (!p.hasPower("KamenRideKabutoPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[3];
         }

         return canUse;
      }
   }

   public void optionDecade() {
      this.damageType = DamageType.NORMAL;
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
      this.damageType = DamageType.NORMAL;
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_PutOn");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
