package dcd_eng.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import com.megacrit.cardcrawl.powers.WeakPower;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Vfx.RiderBooker_shoot;

public class RideBooker_Shoot extends AbstractCustomCardWithType {
   public static final String ID = "RideBooker_Shoot";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RideBooker_Shoot.png";
   private static final int COST = 0;
   private static final int ATTACK_DMG = 1;

   public RideBooker_Shoot() {
      super("RideBooker_Shoot", NAME, "img/cards/RideBooker_Shoot.png", 0, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 1;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("KamenRideDecadePower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new RiderBooker_shoot(), 0.0F));
      }

      for(AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
         if (!mo.isDeadOrEscaped() && !mo.isDead && !mo.isDying && !mo.halfDead) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 1));
         }
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeSlashPower")) {
         int x = AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
         this.damage += x;
         this.isDamageModified = true;
      }

   }

   public AbstractCard makeCopy() {
      return new RideBooker_Shoot();
   }

   public void optionDecade() {
      this.damageType = DamageType.NORMAL;
   }

   public void optionKuuga() {
      if (AbstractDungeon.player.hasPower("KuugaPegasusPower") || AbstractDungeon.player.hasPower("RisingPegasusPower")) {
         this.damageType = DamageType.HP_LOSS;
      }

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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker_Shoot");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
