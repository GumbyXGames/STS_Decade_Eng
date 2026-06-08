package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Jack_henshin extends AbstractGameEffect {
   private boolean jackStart = true;
   private boolean jackEnd = true;
   private boolean tojack = true;

   public Jack_henshin(float x, float y) {
      String HENSHIN2_ATLAS = "img/char/DCD_Animation/blade/jack_henshin2.atlas";
      String JSON1 = "img/char/DCD_Animation/blade/jack_henshin2.json";
      new AbstractAnimation("JACK_HENSHIN2", HENSHIN2_ATLAS, JSON1, 0.8F, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 0.8F);
      this.duration = 3.0F;
      this.startingDuration = 3.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 2.8F && this.jackEnd) {
         AbstractAnimation JACK_HENSHIN = AbstractAnimation.getAnimation("JACK_HENSHIN2");
         JACK_HENSHIN.setMovable(false);
         JACK_HENSHIN.state.setAnimation(0, "henshin", false);
         this.jackEnd = false;
      }

      if (this.duration < 0.25F && this.tojack) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(46);
         this.tojack = false;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("JACK_HENSHIN2");
         this.isDone = true;
         if (!DCDmod.BGMTrigger) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.music.playTempBGM("blade_OP2.ogg");
         }
      }

   }

   public void render(SpriteBatch sb) {
      if (this.jackStart) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(48);
         CardCrawlGame.sound.playA("formride", 0.0F);
         CardCrawlGame.sound.playA("blade_jack", 0.0F);
         this.jackStart = false;
      }

   }

   public void dispose() {
   }
}
