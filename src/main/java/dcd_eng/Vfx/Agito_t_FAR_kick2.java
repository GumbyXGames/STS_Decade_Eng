package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveKamenRideAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Patches.AbstractHandAnimation;
import dcd_eng.Power.KamenRideDecadePower;

public class Agito_t_FAR_kick2 extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;

   public Agito_t_FAR_kick2(AbstractCreature source, AbstractCreature target, int x, float tsX, float tsY) {
      this.duration = 2.9199998F;
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
         Decade.Trickster(210);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.3F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(211);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.72F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(213);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.92F && this.stage == 3) {
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/far_t/Agito_FAR_Effect1_t.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/far_t/Agito_FAR_Effect1_t.json";
         new AbstractHandAnimation("FAR_EFFECT", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.far_effect_H = AbstractHandAnimation.getAnimation("FAR_EFFECT");
         VfxController.far_effect_H.setMovable(false);
         VfxController.far_effect_H.state.setAnimation(0, "effect", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.02F && this.stage == 4) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(212);
         String KICK_EFFECT_ATLAS = "img/char/DCD_Animation/agito/far_t/agito_FAR2_t.atlas";
         String KICK_EFFECT_JSON = "img/char/DCD_Animation/agito/far_t/agito_FAR2_t_2.json";
         new AbstractAnimation("FAR_KICK", KICK_EFFECT_ATLAS, KICK_EFFECT_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.Rider_far_kick_A = AbstractAnimation.getAnimation("FAR_KICK");
         VfxController.Rider_far_kick_A.setMovable(false);
         VfxController.Rider_far_kick_A.state.setAnimation(0, "2", false);
         ++this.stage;
      }

      if (this.duration < this.startingDuration && this.duration > this.startingDuration - 0.3F) {
         AbstractCreature var10000 = this.source;
         var10000.drawY += 700.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var10 = this.current;
         var10.y += 700.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration && this.duration > this.startingDuration - 1.02F) {
         AbstractCreature var18 = this.source;
         var18.drawX += 50.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var19 = this.current;
         var19.x += 50.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      } else if (this.duration < this.startingDuration - 1.02F && this.stage == 5) {
         if (this.source.drawX < this.target.drawX - this.target.hb_w / 2.0F) {
            AbstractHandAnimation var12 = VfxController.far_effect_H;
            var12.drawX = (float)((double)var12.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var13 = VfxController.Rider_far_kick_A;
            var13.drawX = (float)((double)var13.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            AbstractCreature var14 = this.source;
            var14.drawX = (float)((double)var14.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.source.drawY > this.target.drawY + this.target.hb_h / 2.0F) {
            AbstractHandAnimation var15 = VfxController.far_effect_H;
            var15.drawY = (float)((double)var15.drawY + (double)(this.target.hb.cY - this.current.y) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var16 = VfxController.Rider_far_kick_A;
            var16.drawY = (float)((double)var16.drawY + (double)(this.target.hb.cY - this.current.y) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            AbstractCreature var17 = this.source;
            var17.drawY = (float)((double)var17.drawY + (double)(this.target.hb.cY - this.current.y) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.source.drawX > this.target.drawX - this.target.hb_w / 2.0F && this.source.drawY < this.target.drawY + this.target.hb_h / 2.0F) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            this.duration = this.startingDuration - 1.02F;
            ++this.stage;
         }
      } else if (this.duration < this.startingDuration - 1.12F && this.stage == 6) {
         AbstractHandAnimation.clear("FAR_EFFECT");
         AbstractAnimation.clear("FAR_KICK");
         this.source.drawY = this.start.y;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(214);
         AbstractDungeon.effectsQueue.add(new Agito_ReturnPower_Animation());
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.3199999F && this.stage == 7) {
         if (this.target.drawX < (float)Settings.M_W - this.target.hb_w) {
            AbstractCreature var11 = this.target;
            var11.drawX = (float)((double)var11.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target, true)));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            this.duration = this.startingDuration - 1.02F;
            ++this.stage;
         }
      }

      if (this.duration <= 0.0F) {
         this.source.drawX = this.start.x;
         this.source.drawY = this.start.y;
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         AbstractDungeon.player.showHealthBar();
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.source, this.source, new KamenRideDecadePower(this.source), 1));
         CardCrawlGame.sound.playA("test1", 0.0F);
         AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(this.source, this.source));
         if (!DCDmod.BGMTrigger) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.sound.playAndLoop("decade_OP1");
         }

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
