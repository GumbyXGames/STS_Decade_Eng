package dcd_eng.Card.Basic;

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
import dcd_eng.Power.DecadeSpecialSkillPower;

public class Decade_SpecialSkill extends AbstractCustomCardWithType {
   public static final String ID = "Decade_SpecialSkill";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Decade_SpecialSkill.png";
   private static final int COST = 1;

   public Decade_SpecialSkill() {
      super("Decade_SpecialSkill", NAME, "img/cards/Decade_SpecialSkill.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DecadeSpecialSkillPower(p, 1), 1));
   }

   public AbstractCard makeCopy() {
      return new Decade_SpecialSkill();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_SpecialSkill");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
