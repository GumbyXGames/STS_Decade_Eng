package dcd_eng.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.BladeThunderPower;

public class Blade_Thunder extends AbstractCustomCardWithType {
   public static final String ID = "Blade_Thunder";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BladeThunder.png";
   private static final int COST = 0;

   public Blade_Thunder() {
      super("Blade_Thunder", NAME, "img/cards/BladeThunder.png", 0, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Blade);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.UndeadCard);
      this.baseDamage = 1;
      this.baseMagicNumber = this.magicNumber = 1;
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      CardCrawlGame.sound.playA("blade_thunder", 0.0F);
      AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
      AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
      AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.NONE));
      if (!p.hasPower("BladeThunderPower") || p.getPower("BladeThunderPower").amount < 6) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeThunderPower(p, this.magicNumber), this.magicNumber));
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeThunderPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeThunderPower").amount * 2;
         this.isDamageModified = true;
      }

   }

   public AbstractCard makeCopy() {
      return new Blade_Thunder();
   }

   public void optionDecade() {
      this.setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
   }

   public void optionKuuga() {
      this.setBackgroundTexture("img/512/skill_kuuga.png", "img/1024/skill_kuuga.png");
   }

   public void optionAgito() {
      this.setBackgroundTexture("img/512/skill_agito.png", "img/1024/skill_agito.png");
   }

   public void optionRyuki() {
      this.setBackgroundTexture("img/512/skill_ryuki.png", "img/1024/skill_ryuki.png");
   }

   public void optionFaiz() {
      this.setBackgroundTexture("img/512/skill_faiz.png", "img/1024/skill_faiz.png");
   }

   public void optionBlade() {
      this.setBackgroundTexture("img/512/skill_blade.png", "img/1024/skill_blade.png");
   }

   public void optionHibiki() {
      this.setBackgroundTexture("img/512/skill_hibiki.png", "img/1024/skill_hibiki.png");
   }

   public void optionKabuto() {
      this.setBackgroundTexture("img/512/skill_kabuto.png", "img/1024/skill_kabuto.png");
   }

   public void optionDenO() {
      this.setBackgroundTexture("img/512/skill_deno.png", "img/1024/skill_deno.png");
   }

   public void optionKiva() {
      this.setBackgroundTexture("img/512/skill_kiva.png", "img/1024/skill_kiva.png");
   }

   public void optionNeutral() {
      this.setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeBaseCost(0);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Thunder");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
