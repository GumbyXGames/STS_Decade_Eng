package dcd_eng.Relic;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;

public class PhotonAccelerationRelic extends CustomRelic {
   public static final String ID = "PhotonAccelerationRelic";

   public PhotonAccelerationRelic() {
      super("PhotonAccelerationRelic", new Texture(Gdx.files.internal("img/1024/orb-dark.png")), new Texture(Gdx.files.internal("img/1024/orb-dark.png")), RelicTier.STARTER, LandingSound.MAGICAL);
   }

   public void onExhaust(AbstractCard card) {
   }

   public void onManualDiscard() {
   }

   public String getUpdatedDescription() {
      return this.DESCRIPTIONS[0];
   }
}
