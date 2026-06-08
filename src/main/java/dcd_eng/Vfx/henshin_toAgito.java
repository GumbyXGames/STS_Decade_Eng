package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.MakeTempCardInHandAction;
import dcd_eng.Card.Special.AgitoPower;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Power.AgitoLevelPower;

public class henshin_toAgito extends AbstractGameEffect {
   private int stage;

   public henshin_toAgito() {
      this.duration = 1.74F;
      this.startingDuration = 1.74F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         String HENSHIN_ATLAS = "img/char/DCD_Animation/agito/agito_henshin_effect.atlas";
         String HENSHIN_JSON = "img/char/DCD_Animation/agito/agito_henshin_effect.json";
         new AbstractAnimation("agito_henshin", HENSHIN_ATLAS, HENSHIN_JSON, 0.8F, AbstractDungeon.player.drawX, AbstractDungeon.player.drawY, AbstractDungeon.player.hb_w, AbstractDungeon.player.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("agito_henshin");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 1.0F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(149);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AgitoLevelPower(AbstractDungeon.player, 1), 1));
         if (AgitoLevelPower.Lv + 1 >= 2) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
         }

         if (AgitoLevelPower.Lv + 1 >= 3) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new AgitoPower(), 1));
         }

         AbstractAnimation.clear("agito_henshin");
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(14);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("agito_OP1.ogg");
         }

         SpecialRideBooker.isPlayerTurn = true;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Henshin_A = null;
   }
}
