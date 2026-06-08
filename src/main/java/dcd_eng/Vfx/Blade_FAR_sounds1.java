package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;

public class Blade_FAR_sounds1 extends AbstractGameEffect {
   private boolean FAR = true;
   int stage = 0;

   public Blade_FAR_sounds1() {
      this.duration = 4.2F;
      this.startingDuration = 4.2F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.0F && this.FAR) {
         CardCrawlGame.sound.playA("FAR_BLADE", 0.0F);
         this.FAR = false;
      }

      if (this.duration < 0.2F && this.stage == 0) {
         CardCrawlGame.sound.playA("blade_LightningSlash", 0.0F);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         if (!DCDmod.BGMTrigger) {
            if (AbstractDungeon.player.hasPower("BladeJackPower")) {
               CardCrawlGame.music.playTempBGM("blade_OP2.ogg");
            } else {
               CardCrawlGame.music.playTempBGM("blade_OP1.ogg");
            }
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
