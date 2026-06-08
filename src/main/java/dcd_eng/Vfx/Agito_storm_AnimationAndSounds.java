package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Agito_storm_AnimationAndSounds extends AbstractGameEffect {
   int stage = 0;

   public Agito_storm_AnimationAndSounds() {
      this.duration = 0.73F;
      this.startingDuration = 0.73F;
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(180);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.23F && this.stage == 1) {
         CardCrawlGame.sound.playA("storm", 0.0F);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         if (AbstractDungeon.player.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(182);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(181);
         }
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
