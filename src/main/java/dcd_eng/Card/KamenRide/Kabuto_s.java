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
import dcd_eng.Card.Uncommon.KamenRideKabuto;
import dcd_eng.Patches.AbstractCardEnum;
import java.util.ArrayList;
import java.util.List;

public class Kabuto_s extends CustomCard {
   public static final String ID = "Kabuto_s";
   public static final String IMG_PATH = "img/1024/orb-dark.png";
   private static final int COST = -2;
   private static final CardStrings cardStrings;
   public static final String[] EXTENDED_DESCRIPTION;
   private List<TooltipInfo> tips;

   public Kabuto_s() {
      super("Kabuto_s", " ", "img/1024/orb-dark.png", -2, " ", CardType.STATUS, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.SelectCard);
      this.tips = new ArrayList();
      this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
      this.setBackgroundTexture("img/cards/Kabuto_s.png", "img/cards/Kabuto_s_p.png");
      this.cardsToPreview = new KamenRideKabuto();
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
   }

   public AbstractCard makeCopy() {
      return new Kabuto_s();
   }

   public List<TooltipInfo> getCustomTooltips() {
      return this.tips;
   }

   public void upgrade() {
   }

   static {
      cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_s");
      EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
   }
}
