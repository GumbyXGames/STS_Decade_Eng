package dcd_eng.Card.SelectCard;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcd_eng.DCDmod;
import dcd_eng.Patches.AbstractCardEnum;

public class SwordForm extends CustomCard {
   public static final String ID = "SwordForm";
   public static final String IMG_PATH = "img/1024/orb-dark.png";
   private static final int COST = -2;

   public SwordForm() {
      super("SwordForm", " ", "img/1024/orb-dark.png", -2, " ", CardType.STATUS, AbstractCardEnum.DCD, CardRarity.BASIC, CardTarget.SELF);
      this.tags.add(DCDmod.RiderCard);
      this.tags.add(DCDmod.SelectCard);
      this.setBackgroundTexture("img/cards/RideBooker_s.png", "img/cards/RideBooker_s_p.png");
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
   }

   public AbstractCard makeCopy() {
      return new SwordForm();
   }

   public void upgrade() {
   }
}
