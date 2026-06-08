package dcd_eng.Card.Uncommon;

import basemod.helpers.TooltipInfo;
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
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractCustomCardWithType;
import dcd_eng.Power.Decade_Dash2Power;
import java.util.ArrayList;
import java.util.List;

public class Decade_Dash2 extends AbstractCustomCardWithType {
   public static final String ID = "Decade_Dash2";
   private static final CardStrings cardStrings;
   public static final String NAME;
   public static final String DESCRIPTION;
   public static final String[] EXTENDED_DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Decade_Dash2.png";
   private static final int COST = 1;
   private List<TooltipInfo> tips;

   public Decade_Dash2() {
      super("Decade_Dash2", NAME, "img/cards/Decade_Dash2.png", 1, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DCD, CardRarity.COMMON, CardTarget.ENEMY, AbstractCustomCardWithType.CardColorType.Decade);
      this.tags.add(DCDmod.RiderCard);
      this.baseMagicNumber = this.magicNumber = 1;
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[1]));
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Decade_Dash2Power(p, m, this.magicNumber), this.magicNumber));
      AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
      AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, -3), -3));
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
      boolean canUse = super.canUse(p, m);
      if (!canUse) {
         return false;
      } else {
         if (m != null && m.intent != Intent.ATTACK && m.intent != Intent.ATTACK_BUFF && m.intent != Intent.ATTACK_DEBUFF && m.intent != Intent.ATTACK_DEFEND) {
            canUse = false;
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
         }

         return canUse;
      }
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new Decade_Dash2();
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
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Dash2");
      NAME = cardStrings.NAME;
      DESCRIPTION = cardStrings.DESCRIPTION;
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
