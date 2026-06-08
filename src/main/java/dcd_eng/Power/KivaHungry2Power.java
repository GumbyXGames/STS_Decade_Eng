package dcd_eng.Power;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.KivaHungry2Action;
import dcd_eng.Actions.KivaVampireAction;

public class KivaHungry2Power extends AbstractPower {
   public static final String POWER_ID = "KivaHungry2Power";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   private static int DrawCard;

   public KivaHungry2Power(AbstractCreature owner) {
      this.name = NAME;
      this.ID = "KivaHungry2Power";
      this.owner = owner;
      this.amount = -1;
      this.img = ImageMaster.loadImage("img/powers/VampireTeeth.png");
      this.type = PowerType.valueOf("KamenRide");
      DrawCard = AbstractDungeon.player.gameHandSize;
      this.updateDescription();
   }

   public void onRemove() {
      AbstractDungeon.player.gameHandSize = DrawCard;
   }

   public void onVictory() {
      AbstractDungeon.player.gameHandSize = DrawCard;
   }

   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
      if (info.owner == this.owner && target != this.owner && info.type == DamageType.NORMAL) {
         this.addToBot(new KivaVampireAction(this.owner, target, 0.3F));
      }

   }

   public void atStartOfTurn() {
      this.flash();

      for(int i = 0; i < DrawCard; ++i) {
         this.addToBot(new KivaHungry2Action());
      }

   }

   public void atStartOfTurnPostDraw() {
      AbstractDungeon.player.gameHandSize = DrawCard;
   }

   public void atEndOfTurn(boolean isPlayer) {
      if (isPlayer) {
         DrawCard = AbstractDungeon.player.gameHandSize;
         AbstractPlayer var10000 = AbstractDungeon.player;
         var10000.gameHandSize -= AbstractDungeon.player.gameHandSize;
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KivaHungry2Power");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
