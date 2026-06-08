package dcd_eng.Card.Common;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
import dcd_eng.Actions.KunaiGunAttackAction;
import dcd_eng.Actions.KunaiGunDiscardAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import java.util.ArrayList;
import java.util.List;

public class Kabuto_KunaiGun extends AbstractCustomCardWithType {
   public static final String ID = "Kabuto_KunaiGun";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kabuto_KunaiGun.png";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 8;
   private static final int MAGIC_NUM = 1;
   private static final int BLOCK_AMT = 8;
   private List<TooltipInfo> tips;

   public Kabuto_KunaiGun() {
      super("Kabuto_KunaiGun", NAME, "img/cards/Kabuto_KunaiGun.png", 1, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Kabuto);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.WeaponCard);
      this.baseDamage = 8;
      this.baseBlock = 8;
      this.baseMagicNumber = this.magicNumber = 1;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      if (!p.hasPower("KabutoMaskedPower") && !p.hasPower("DecadeViolentEmotionPower")) {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
         AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
      } else {
         AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
         AbstractDungeon.actionManager.addToBottom(new KunaiGunDiscardAction(p, p, this.magicNumber, false));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         AbstractDungeon.actionManager.addToBottom(new KunaiGunAttackAction(m, this.damage, this.block));
      }

   }

   public void calculateCardDamage(AbstractMonster arg0) {
      super.calculateCardDamage(arg0);
      if (AbstractDungeon.player.hasPower("BladeSlashPower")) {
         this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
         this.isDamageModified = true;
      }

   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Kabuto_KunaiGun();
   }

   public void optionDecade() {
      if (AbstractDungeon.player.hasPower("DecadeViolentEmotionPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      } else {
         this.rawDescription = DESCRIPTION;
         this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      }

      this.initializeDescription();
   }

   public void optionKuuga() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionAgito() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionRyuki() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionFaiz() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionBlade() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionHibiki() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionKabuto() {
      if (AbstractDungeon.player.hasPower("KabutoMaskedPower")) {
         this.rawDescription = EXTENDED_DESCRIPTION[0];
         this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      } else {
         this.rawDescription = DESCRIPTION;
         this.loadCardImage("img/cards/Kabuto_KunaiGun_k.png");
      }

      this.initializeDescription();
   }

   public void optionDenO() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionKiva() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void optionNeutral() {
      this.rawDescription = DESCRIPTION;
      this.loadCardImage("img/cards/Kabuto_KunaiGun.png");
      this.initializeDescription();
   }

   public void upgrade() {
      if (!this.upgraded) {
         this.upgradeName();
         this.upgradeMagicNumber(1);
      }

   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_KunaiGun");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
