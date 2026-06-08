package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class Agito_t_FAR_kick extends AbstractGameEffect {
   int stage = 1;
   private AbstractPlayer p;
   private AbstractCreature source;
   private AbstractCreature target;
   private final int damage;
   private Vector2 Tstart;

   public Agito_t_FAR_kick(AbstractCreature source, AbstractCreature target, int x) {
      this.p = AbstractDungeon.player;
      this.duration = 4.5F;
      this.startingDuration = 4.5F;
      this.source = source;
      this.target = target;
      this.damage = x;
      this.Tstart = new Vector2(target.drawX, target.drawY);
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 1) {
         if (!this.p.hasPower("AgitoPowerPower")) {
            AbstractDungeon.effectsQueue.add(new Agito_Power_Animation());
            Decade Decade = (Decade)this.p;
            Decade.Trickster(206);
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.2F && this.stage == 2) {
         Decade Decade = (Decade)this.p;
         Decade.Trickster(207);
         ++this.stage;
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/agito_far_effect1.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/agito_far_effect1.json";
         new AbstractSummonedAnimation("FAR_EFFECT1", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("FAR_EFFECT1");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, "effect1", false);
         CardCrawlGame.sound.playA("agito_kick_charge", 0.0F);
      } else if (this.duration < this.startingDuration - 1.0F && this.stage == 3) {
         AbstractSummonedAnimation.clear("FAR_EFFECT1");
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/agito_far_effect2.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/agito_far_effect2.json";
         new AbstractSummonedAnimation("FAR_EFFECT2", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("FAR_EFFECT2");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, "effect2", true);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 4.0F && this.stage == 4) {
         Decade Decade = (Decade)this.p;
         Decade.Trickster(208);
         AbstractSummonedAnimation.clear("FAR_EFFECT2");
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/agito_far_effect3.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/agito_far_effect3.json";
         new AbstractSummonedAnimation("FAR_EFFECT3", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("FAR_EFFECT3");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, "effect3", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 4.11F && this.stage == 5) {
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/agito_far_effect5.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/agito_far_effect5.json";
         new AbstractAnimation("FAR_EFFECT5", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("FAR_EFFECT5");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "effect5", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 4.5F && this.stage == 6) {
         AbstractSummonedAnimation.clear("FAR_EFFECT3");
         AbstractAnimation.clear("FAR_EFFECT5");
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/smoke_effect.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/smoke_effect.json";
         new AbstractAnimation("SMOKE_EFFECT", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect3_A = AbstractAnimation.getAnimation("SMOKE_EFFECT");
         VfxController.far_effect3_A.setMovable(false);
         VfxController.far_effect3_A.state.setAnimation(0, "smoke", false);
         AbstractDungeon.effectsQueue.add(new Agito_t_FAR_kick2(this.source, this.target, this.damage, this.Tstart.x, this.Tstart.y));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("SMOKE_EFFECT");
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect_S = null;
      VfxController.far_effect2_A = null;
      VfxController.far_effect3_A = null;
   }
}
