package dcd_eng.Card.Special;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Status.Kabuto_Photon;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Vfx.Kabuto_MaskedToRider;
import java.util.ArrayList;
import java.util.List;

public class Kabuto_CastOff extends AbstractCustomCardWithType {
   public static final String ID = "Kabuto_CastOff";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kabuto_CastOff.png";
   private static final int COST = 0;
   private List<TooltipInfo> tips;

   public Kabuto_CastOff() {
      super("Kabuto_CastOff", NAME, "img/cards/Kabuto_CastOff.png", 0, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Kabuto);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.KamenRide);
      this.exhaust = true;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
      this.selfRetain = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToTop(new VFXAction(new Kabuto_MaskedToRider(), 1.4F));
      AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "KabutoMaskedPower"));
      int[] N = new int[]{0, 0, 0, 0, 0};

      for(AbstractCard c : p.hand.group) {
         switch (c.type) {
            case ATTACK:
               int var14 = N[0]++;
               break;
            case CURSE:
               int var13 = N[1]++;
               AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand, true));
               break;
            case POWER:
               int var12 = N[2]++;
               break;
            case SKILL:
               int var11 = N[3]++;
               break;
            case STATUS:
               int var10002 = N[4]++;
               AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, p.hand, true));
         }
      }

      int theSize = p.hand.size();
      AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, theSize, false));
      if (N[4] != 0) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, N[4]), N[4]));
      }

      if (N[3] != 0) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, N[3] * 2, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
            }
         }
      }

      if (N[2] != 0) {
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_PutOn(), 1));
      }

      if (N[1] != 0) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(p, new DamageInfo(p, N[1] * 3, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));

         for(int i = 0; i < 3; ++i) {
            AbstractCard c = new Kabuto_Photon();
            AbstractDungeon.player.hand.moveToDeck(c, true);
         }
      }

      if (N[0] != 0) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, N[0]));
      }

   }

   public AbstractCard makeCopy() {
      return new Kabuto_CastOff();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (!p.hasPower("KabutoMaskedPower")) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_CastOff");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
