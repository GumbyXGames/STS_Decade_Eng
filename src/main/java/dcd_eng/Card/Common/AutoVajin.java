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
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.DexterityPower;
import dcd_eng.DCDmod;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.AutoVajinPower;
import dcd_eng.Vfx.AutoVajin_appear;
import dcd_eng.Vfx.AutoVajin_sounds;

public class AutoVajin extends AbstractCustomCardWithType {
   public static final String ID = "AutoVajin";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AutoVajin.png";
   private static final int COST = 2;

   public AutoVajin() {
      super("AutoVajin", NAME, "img/cards/AutoVajin.png", 2, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Faiz);
      this.tags.add(DCDmod.RiderCard);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      AbstractDungeon.actionManager.addToTop(new VFXAction(new AutoVajin_sounds(), 0.0F));
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_appear(), 2.5F));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AutoVajinPower(p, 3), 3));
      if (SpecialFaizBox.FaizEdge && p.hasOrb()) {
         for(AbstractOrb o : p.orbs) {
            if (o.ID.equals("FaizPointer")) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
               break;
            }
         }
      }

   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (p.hasPower("AutoVajinPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public AbstractCard makeCopy() {
      return new AutoVajin();
   }

   public void optionDecade() {
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
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("AutoVajin");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
