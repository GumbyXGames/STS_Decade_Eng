package dcd_eng.Card.SelectCard;

import basemod.abstracts.CustomCard;
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
import java.util.ArrayList;
import java.util.List;

public class BasshaaWhistle extends CustomCard {
   public static final String ID = "BasshaaWhistle";
   public static final String IMG_PATH = "img/1024/orb-dark.png";
   private static final int COST = -2;
   private List<TooltipInfo> tips;
   private static final CardStrings cardStrings;
   public static final String[] EXTENDED_DESCRIPTION;

   public BasshaaWhistle() {
      super("BasshaaWhistle", " ", "img/1024/orb-dark.png", -2, " ", CardType.STATUS, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.SelectCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.setBackgroundTexture("img/cards/BasshaaWhistle.png", "img/cards/BasshaaWhistle.png");
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public AbstractCard makeCopy() {
      return new BasshaaWhistle();
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("BasshaaWhistle");
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
