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

public class Decade_Blast extends AbstractCustomCardWithType {
   public static final String ID = "Decade_Blast";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Decade_Blast.png";
   private static final int COST = 2;
   private static final int ATTACK_DMG = 3;

   public Decade_Blast() {
      super("Decade_Blast", NAME, "img/cards/Decade_Blast.png", 2, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.baseDamage = 3;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("attackride", 0.0F);
      if (p.hasPower("KamenRideDecadePower") && !DCDmod.AnimationTrigger) {
         this.addToTop(new VFXAction(new dcd_eng.Vfx.Decade_Blast(this.damage, this.damageType), 0.0F));
      } else {
         for(int i = 0; i < 5; ++i) {
            AbstractMonster m1 = AbstractDungeon.getMonsters().getRandomMonster(true);
            this.addToBot(new DamageAction(m1, new DamageInfo(p, this.damage, this.damageType), AttackEffect.SLASH_HORIZONTAL));
            this.addToBot(new ApplyPowerAction(m1, p, new WeakPower(m1, 2, false), 2));
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
      return new Decade_Blast();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Blast");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
