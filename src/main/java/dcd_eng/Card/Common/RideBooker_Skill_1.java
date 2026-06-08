package dcd_eng.Card.Common;

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
import dcd_eng.Actions.RideBookerSkillAction;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;

public class RideBooker_Skill_1 extends AbstractCustomCardWithType {
   public static final String ID = "RideBooker_Skill_1";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RideBooker_Skill_1.gif";
   private static final int COST = 1;

   public RideBooker_Skill_1() {
      super("RideBooker_Skill_1", NAME, "img/cards/RideBooker_Skill_1.gif", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.SELF, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 1;
      this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new RideBookerSkillAction());
   }

   public AbstractCard makeCopy() {
      return new RideBooker_Skill_1();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker_Skill_1");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
   }
}
