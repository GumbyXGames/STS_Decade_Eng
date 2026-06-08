package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_flame_AnimationAndSounds extends AbstractGameEffect {
   int stage = 0;

   public Agito_flame_AnimationAndSounds() {
      this.duration = 0.73F;
      this.startingDuration = 0.73F;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(184);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.23F && this.stage == 1) {
         CardCrawlGame.sound.playA("flame", 0.0F);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         if (AbstractDungeon.player.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(186);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(185);
         }
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
