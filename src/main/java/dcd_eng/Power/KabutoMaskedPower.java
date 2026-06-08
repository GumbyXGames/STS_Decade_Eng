package dcd_eng.Power;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Special.Kabuto_PutOn;

public class KabutoMaskedPower extends AbstractPower {
   public static final String POWER_ID = "KabutoMaskedPower";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;
   public static int PhotonPoint = 0;

   public KabutoMaskedPower(AbstractCreature owner, int amt) {
      this.name = NAME;
      this.ID = "KabutoMaskedPower";
      this.owner = owner;
      this.amount = amt;
      this.img = ImageMaster.loadImage("img/powers/KabutoMaskedPower.png");
      this.type = PowerType.valueOf("KamenRide");
      this.updateDescription();
   }

   public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
      if (damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage <= 5.0F) {
         return 0.0F;
      } else {
         return damageType != DamageType.HP_LOSS && damageType != DamageType.THORNS && damage > 5.0F ? damage - 3.0F : damage;
      }
   }

   public void onDrawOrDiscard() {
      if (PhotonPoint >= 3) {
         this.amount = PhotonPoint - 3;
         PhotonPoint -= 3;
         AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Kabuto_PutOn(), 1));
         this.flash();
      } else {
         this.amount = PhotonPoint;
      }

   }

   public void updateDescription() {
      this.description = DESCRIPTIONS[0];
   }

   static {
      powerStrings = CardCrawlGame.languagePack.getPowerStrings("KabutoMaskedPower");
      NAME = powerStrings.NAME;
      DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   }
}
