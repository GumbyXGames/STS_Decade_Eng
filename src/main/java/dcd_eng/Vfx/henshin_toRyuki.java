package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialRideBooker;

public class henshin_toRyuki extends AbstractGameEffect {
   private int stage;

   public henshin_toRyuki() {
      this.duration = 1.8F;
      this.startingDuration = 1.8F;
      this.stage = 0;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(28);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.6F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(29);
         ++this.stage;
      }

      if (this.duration < this.startingDuration - 1.3F && this.stage == 2) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(30);
         SpecialRideBooker.isPlayerTurn = true;
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("ryuki_OP1.ogg");
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
