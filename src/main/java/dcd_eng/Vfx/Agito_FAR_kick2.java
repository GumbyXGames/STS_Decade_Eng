package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractHandAnimation;

public class Agito_FAR_kick2 extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private final AbstractCreature source;
   private final AbstractCreature target;
   private final Vector2 start;
   private final Vector2 current;
   private final Vector2 Tstart;

   public Agito_FAR_kick2(AbstractCreature source, AbstractCreature target, int x, float tsX, float tsY) {
      this.duration = 5.3F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.start = new Vector2(source.drawX, source.drawY);
      this.Tstart = new Vector2(tsX, tsY);
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.damage = x;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(199);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.3F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(200);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.6F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(202);
         ++this.stage;
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/agito_far_effect4.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/agito_far_effect4.json";
         new AbstractHandAnimation("FAR_EFFECT4", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.far_effect_H = AbstractHandAnimation.getAnimation("FAR_EFFECT4");
         VfxController.far_effect_H.setMovable(false);
         VfxController.far_effect_H.state.setAnimation(0, "effect4", false);
      } else if (this.duration < this.startingDuration - 0.90000004F && this.stage == 3) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(201);
         String KICK_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/agito_FAR2.atlas";
         String KICK_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/agito_FAR2_2.json";
         new AbstractAnimation("FAR_KICK", KICK_EFFECT_ATLAS, KICK_EFFECT_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.Rider_far_kick_A = AbstractAnimation.getAnimation("FAR_KICK");
         VfxController.Rider_far_kick_A.setMovable(false);
         VfxController.Rider_far_kick_A.state.setAnimation(0, "2", false);
         ++this.stage;
      }

      if (this.duration < this.startingDuration && this.duration > this.startingDuration - 0.3F) {
         AbstractCreature var10000 = this.source;
         var10000.drawY += 700.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var14 = this.current;
         var14.y += 700.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration && this.duration > this.startingDuration - 0.90000004F) {
         AbstractCreature var22 = this.source;
         var22.drawX += 50.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var23 = this.current;
         var23.x += 50.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      } else if (this.duration < this.startingDuration - 0.90000004F && this.stage == 4) {
         if (this.source.drawX < this.target.drawX - this.target.hb_w / 2.0F) {
            AbstractHandAnimation var16 = VfxController.far_effect_H;
            var16.drawX = (float)((double)var16.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var17 = VfxController.Rider_far_kick_A;
            var17.drawX = (float)((double)var17.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
            AbstractCreature var18 = this.source;
            var18.drawX = (float)((double)var18.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.source.drawY > this.target.drawY + this.target.hb_h / 2.0F) {
            AbstractHandAnimation var19 = VfxController.far_effect_H;
            var19.drawY = (float)((double)var19.drawY + (double)(this.target.hb.cY - this.current.y) / 0.2 * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var20 = VfxController.Rider_far_kick_A;
            var20.drawY = (float)((double)var20.drawY + (double)(this.target.hb.cY - this.current.y) / 0.2 * (double)Gdx.graphics.getDeltaTime());
            AbstractCreature var21 = this.source;
            var21.drawY = (float)((double)var21.drawY + (double)(this.target.hb.cY - this.current.y) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.source.drawX > this.target.drawX - this.target.hb_w / 2.0F && this.source.drawY < this.target.drawY + this.target.hb_h / 2.0F) {
            ++this.stage;
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            this.duration = this.startingDuration - 0.90000004F;
         }
      } else if (this.duration < this.startingDuration - 1.2F && this.stage == 5) {
         if (this.target.drawX < (float)Settings.M_W - this.target.hb_w) {
            AbstractCreature var15 = this.target;
            var15.drawX = (float)((double)var15.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
            this.duration = this.startingDuration - 1.2F;
         }
      } else if (this.duration < this.startingDuration - 1.2F && this.stage == 6) {
         AbstractHandAnimation.clear("FAR_EFFECT4");
         AbstractAnimation.clear("FAR_KICK");
         this.source.drawY = this.start.y;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(203);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.7F && this.stage == 7) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target, true)));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(204);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 3.66F && this.stage == 8) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(205);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 4.36F && this.stage == 9) {
         AbstractDungeon.effectsQueue.add(new Agito_ReturnPower_Animation());
         ++this.stage;
      }

      if (this.duration <= 0.0F) {
         AbstractHandAnimation.clear("FAR_EFFECT4");
         AbstractAnimation.clear("FAR_KICK");
         if (AbstractDungeon.player.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(16);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(14);
         }

         this.source.drawX = this.start.x;
         this.source.drawY = this.start.y;
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         AbstractDungeon.player.showHealthBar();
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect_H = null;
      VfxController.Rider_far_kick_A = null;
   }
}
