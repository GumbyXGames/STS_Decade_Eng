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
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.RideBookerAttackPower;
import dcd_eng.Vfx.RiderBooker_attack;

public class RideBooker_Attack extends AbstractCustomCardWithType {
   public static final String ID = "RideBooker_Attack";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RideBooker_Attack.png";
   private static final int COST = 0;
   private static final int ATTACK_DMG = 2;

   public RideBooker_Attack() {
      super("RideBooker_Attack", NAME, "img/cards/RideBooker_Attack.png", 0, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 2;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (p.hasPower("KamenRideDecadePower")) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new RiderBooker_attack(m, this.damage, this.damageType, this), 0.0F));
      } else {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.BLUNT_HEAVY));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RideBookerAttackPower(p, 1, this), 1));
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
      return new RideBooker_Attack();
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
      this.damageType = DamageType.NORMAL;
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker_Attack");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
