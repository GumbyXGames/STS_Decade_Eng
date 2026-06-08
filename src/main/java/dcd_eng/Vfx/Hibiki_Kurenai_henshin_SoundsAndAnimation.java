package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Hibiki_Kurenai_henshin_SoundsAndAnimation extends AbstractGameEffect {
   private boolean HENSHIN1Start = true;
   private boolean HENSHIN2Start = true;
   private boolean HENSHIN3Start = true;
   private boolean HENSHIN4Start = true;

   public Hibiki_Kurenai_henshin_SoundsAndAnimation(float x, float y) {
      String HENSHIN1_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_kurenai_r.atlas";
      String JSON1 = "img/char/DCD_Animation/hibiki/hibiki_kurenai_r.json";
      new AbstractAnimation("HIBIKI_KURENAI_HENSHIN1", HENSHIN1_ATLAS, JSON1, 1.0F, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      String HENSHIN2_ATLAS = "img/char/DCD_Animation/hibiki/hibiki_kurenai_henshin.atlas";
      String JSON2 = "img/char/DCD_Animation/hibiki/hibiki_kurenai_henshin.json";
      new AbstractAnimation("HIBIKI_KURENAI_HENSHIN2", HENSHIN2_ATLAS, JSON2, 1.0F, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      this.duration = 2.8F;
      this.startingDuration = 2.8F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 2.5F && this.HENSHIN2Start) {
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("HIBIKI_KURENAI_HENSHIN1");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "henshin", false);
         CardCrawlGame.sound.playA("hibiki_kurenai_sound", 0.0F);
         this.HENSHIN2Start = false;
      }

      if (this.duration < 1.2F && this.HENSHIN3Start) {
         VfxController.Henshin_A = AbstractAnimation.getAnimation("HIBIKI_KURENAI_HENSHIN2");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "henshin", false);
         CardCrawlGame.sound.playA("hibiki_kurenai", 0.0F);
         this.HENSHIN3Start = false;
      }

      if (this.duration < 0.5F && this.HENSHIN4Start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(55);
         this.HENSHIN4Start = false;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("HIBIKI_KURENAI_HENSHIN1");
         AbstractAnimation.clear("HIBIKI_KURENAI_HENSHIN2");
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("hibiki_BGM.ogg");
         }

         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.HENSHIN1Start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(54);
         this.HENSHIN1Start = false;
      }

   }

   public void dispose() {
      VfxController.far_effect2_A = null;
      VfxController.Henshin_A = null;
   }
}
