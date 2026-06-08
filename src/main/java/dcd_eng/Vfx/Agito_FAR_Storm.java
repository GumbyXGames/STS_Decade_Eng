package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Agito_FAR_Storm extends AbstractGameEffect {
   private final int damage;
   int stage;
   int m;
   private final AbstractCreature source;
   private final AbstractCreature target;
   private final Vector2 start;
   private final Vector2 current;
   private final AbstractPlayer p;
   private final Vector2 Tstart;

   public Agito_FAR_Storm(AbstractCreature source, AbstractCreature target, int damage, int mn) {
      this.p = AbstractDungeon.player;
      this.duration = 2.5F;
      this.startingDuration = 2.5F;
      this.damage = damage;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.start = new Vector2(source.drawX, source.drawY);
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.m = mn;
      this.Tstart = new Vector2(target.drawX, target.drawY);
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }

         Decade Decade = (Decade)this.p;
         Decade.Trickster(225);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 1.2F && this.stage == 1) {
         if (this.m == 0) {
            ++this.stage;
         } else if (this.m % 2 == 0) {
            Decade Decade = (Decade)this.p;
            Decade.Trickster(228);
            this.duration = this.startingDuration - 0.84000003F;
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WhirlwindEffect(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(this.p, new CleaveEffect(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
            --this.m;
         } else {
            Decade Decade = (Decade)this.p;
            Decade.Trickster(227);
            this.duration = this.startingDuration - 0.70000005F;
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_WHIRLWIND"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WhirlwindEffect(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(this.p, new CleaveEffect(), 0.0F));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
            --this.m;
         }
      } else if (this.duration < this.startingDuration - 1.2F && this.stage == 2) {
         Decade Decade = (Decade)this.p;
         Decade.Trickster(229);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.5F && this.stage == 3) {
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/agito_far/smoke_effect.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/agito_far/smoke_effect.json";
         new AbstractAnimation("SMOKE_EFFECT", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect3_A = AbstractAnimation.getAnimation("SMOKE_EFFECT");
         VfxController.far_effect3_A.setMovable(false);
         VfxController.far_effect3_A.state.setAnimation(0, "smoke", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.5F && this.stage == 4) {
         if (this.source.drawX < this.target.drawX - this.target.hb_w / 2.0F) {
            AbstractCreature var14 = this.source;
            var14.drawX = (float)((double)var14.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
            Decade Decade = (Decade)this.p;
            Decade.Trickster(231);
            String AGITO_FAR_ATLAS = "img/char/DCD_Animation/agito/far_s/Agito_FAR1_s.atlas";
            String AGITO_FAR_JSON = "img/char/DCD_Animation/agito/far_s/Agito_FAR1_s_6.json";
            new AbstractAnimation("AGITO_FAR_S", AGITO_FAR_ATLAS, AGITO_FAR_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
            VfxController.Rider_far_kick_A = AbstractAnimation.getAnimation("AGITO_FAR_S");
            VfxController.Rider_far_kick_A.setMovable(false);
            VfxController.Rider_far_kick_A.state.setAnimation(0, "6", false);
            this.duration = this.startingDuration - 1.5F;
         }
      } else if (this.duration < this.startingDuration - 1.5F && this.stage == 5) {
         if (this.source.drawX < this.target.drawX + this.target.hb_w / 2.0F) {
            AbstractCreature var12 = this.source;
            var12.drawX = (float)((double)var12.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var13 = VfxController.Rider_far_kick_A;
            var13.drawX = (float)((double)var13.drawX + (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
            this.duration = this.startingDuration - 1.5F;
         }
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("SMOKE_EFFECT");
         AbstractAnimation.clear("AGITO_FAR_S");
         this.source.drawX = this.start.x;
         AbstractDungeon.player.showHealthBar();
         if (this.p.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(182);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(181);
         }

         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.source, this.source, "SpecialStormPower"));
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect3_A = null;
      VfxController.Rider_far_kick_A = null;
   }
}
