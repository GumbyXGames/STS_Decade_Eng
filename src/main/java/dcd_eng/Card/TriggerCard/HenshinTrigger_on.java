package dcd_eng.Card.TriggerCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;
import dcd_eng.Patches.AbstractGIFCard;
import java.util.ArrayList;
import java.util.List;

public class HenshinTrigger_on extends AbstractGIFCard {
   public static final String ID = "HenshinTrigger_on";
   public static final String IMG_PATH = "img/cards/HenshinTrigger_on.gif";
   private static final int COST = -2;
   private static final CardStrings cardStrings;
   public static final String[] EXTENDED_DESCRIPTION;
   private List<TooltipInfo> tips;

   public HenshinTrigger_on() {
      super("HenshinTrigger_on", "", "img/cards/HenshinTrigger_on.gif", -2, "", CardType.STATUS, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.ENEMY);
      this.tags.add(DCDmod.TriggerCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.setBackgroundTexture("img/1024/orb-dark.png", "img/1024/orb-dark.png");
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new HenshinTrigger_on();
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("HenshinTrigger_on");
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
