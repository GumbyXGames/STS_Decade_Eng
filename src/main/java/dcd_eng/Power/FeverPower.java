package dcd_eng.Power;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Patches.HibikiTaikoKeyEvent;

public class FeverPower extends AbstractPower {
   public static final String POWER_ID = "FeverPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;

   public FeverPower(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "FeverPower";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/Fever.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public void onUseCard(AbstractCard card, UseCardAction action) {
      if (!card.cardID.equals("Hibiki_Ongekibou") && !card.cardID.equals("Hibiki_Attack1") && !card.cardID.equals("Hibiki_Attack2") && !card.cardID.equals("Hibiki_Attack3")) {
         HibikiTaikoKeyEvent.FeverOut(false);
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("FeverPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
