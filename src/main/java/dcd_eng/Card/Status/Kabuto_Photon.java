package dcd_eng.Card.Status;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
import dcd_eng.Actions.PhotonAction;
import dcd_eng.Card.Rare.Kabuto_ClockUp;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Power.KabutoMaskedPower;
import dcd_eng.Power.PhotonAccelerationPower;
import java.util.ArrayList;
import java.util.List;

public class Kabuto_Photon extends CustomCard {
   public static final String ID = "Kabuto_Photon";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kabuto_Photon.png";
   private static final int COST = 0;
   private List<TooltipInfo> tips = new ArrayList();

   public Kabuto_Photon() {
      super("Kabuto_Photon", NAME, "img/cards/Kabuto_Photon.png", 0, DESCRIPTION, CardType.STATUS, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF);
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new PhotonAction(this));
   }

   public void triggerOnExhaust() {
      if (AbstractDungeon.player.hasPower("PhotonAccelerationPower")) {
         AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
         ++PhotonAccelerationPower.x;
         if (PhotonAccelerationPower.x >= 5) {
            AbstractCard c = new Kabuto_ClockUp();
            AbstractDungeon.player.hand.moveToDeck(c, false);
         }
      }

   }

   public void triggerOnManualDiscard() {
      AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, 1, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
      if (AbstractDungeon.player.hasPower("KabutoMaskedPower")) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
         AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
         ++KabutoMaskedPower.PhotonPoint;
      }

      if (AbstractDungeon.player.hasPower("PhotonAccelerationPower")) {
         ++PhotonAccelerationPower.x;
         if (PhotonAccelerationPower.x >= 5) {
            AbstractCard c = new Kabuto_ClockUp();
            AbstractDungeon.player.hand.moveToDeck(c, false);
         }
      }

   }

   public void triggerWhenDrawn() {
      if (AbstractDungeon.player.hasPower("PhotonAccelerationPower")) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
      }

   }

   public AbstractCard makeCopy() {
      return new Kabuto_Photon();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      return true;
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_Photon");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
