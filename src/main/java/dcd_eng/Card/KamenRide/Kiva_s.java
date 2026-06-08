package dcd_eng.Card.KamenRide;

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
import dcd_eng.Card.Uncommon.KamenRideKiva;
import dcd_eng.Patches.AbstractCardEnum;
import java.util.ArrayList;
import java.util.List;

public class Kiva_s extends CustomCard {
   public static final String ID = "Kiva_s";
   public static final String IMG_PATH = "img/1024/orb-dark.png";
   private static final int COST = -2;
   private static final CardStrings cardStrings;
   public static final String[] EXTENDED_DESCRIPTION;
   private List<TooltipInfo> tips;

   public Kiva_s() {
      super("Kiva_s", " ", "img/1024/orb-dark.png", -2, " ", CardType.STATUS, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.SelectCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.setBackgroundTexture("img/cards/Kiva_s.png", "img/cards/Kiva_s_p.png");
      this.cardsToPreview = new KamenRideKiva();
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
   }

   public AbstractCard makeCopy() {
      return new Kiva_s();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kiva_s");
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
