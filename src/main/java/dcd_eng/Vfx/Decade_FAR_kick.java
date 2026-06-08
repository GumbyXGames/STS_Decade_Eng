package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Decade_FAR_kick extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;
   private boolean attack = true;

   public Decade_FAR_kick(AbstractCreature source, AbstractCreature target, int x) {
      this.duration = 3.2F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.start = new Vector2(source.drawX, source.drawY);
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.damage = x;
      this.Tstart = new Vector2(target.drawX, target.drawY);
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         AbstractDungeon.effectsQueue.add(new Decade_FAR_Card(this.source, this.target, this.damage));
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration <= 1.3F && this.attack) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         this.attack = false;
      }

      if (this.duration < this.startingDuration - 1.2F && this.stage == 0) {
         if (!this.target.isDead && !this.target.isDying) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(71);
            ++this.stage;
         } else {
            this.isDone = true;
         }
      }

      if (this.duration < this.startingDuration - 1.2F && this.duration > this.startingDuration - 1.5F) {
         if (!this.target.isDead && !this.target.isDying) {
            AbstractCreature var14 = this.source;
            var14.drawY += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
            Vector2 var15 = this.current;
            var15.y += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         } else {
            this.isDone = true;
         }
      } else if (this.duration < this.startingDuration - 1.5F && this.duration > this.startingDuration - 1.87F) {
         if (this.stage == 1) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(72);
            ++this.stage;
         }
      } else if (this.duration < this.startingDuration - 1.87F && this.duration > 0.55F) {
         if (this.stage == 2) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(74);
            String DECADE_KICK_ATLAS = "img/char/DCD_Animation/decade/FAR/Decade_FAR_kick.atlas";
            String DECADE_KICK_JSON = "img/char/DCD_Animation/decade/FAR/Decade_FAR_kick.json";
            new AbstractAnimation("decade_far_kick", DECADE_KICK_ATLAS, DECADE_KICK_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
            VfxController.Rider_far_kick_A = AbstractAnimation.getAnimation("decade_far_kick");
            VfxController.Rider_far_kick_A.setMovable(false);
            VfxController.Rider_far_kick_A.state.setAnimation(0, "kick", true);
            ++this.stage;
         }

         if (this.source.drawX < this.target.drawX) {
            AbstractAnimation var10 = VfxController.Rider_far_kick_A;
            var10.drawX = (float)((double)var10.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
            AbstractCreature var11 = this.source;
            var11.drawX = (float)((double)var11.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.source.drawY > this.target.drawY) {
            AbstractAnimation var12 = VfxController.Rider_far_kick_A;
            var12.drawY = (float)((double)var12.drawY + (double)(this.target.hb.cY - this.current.y) / 0.2 * (double)Gdx.graphics.getDeltaTime());
            AbstractCreature var13 = this.source;
            var13.drawY = (float)((double)var13.drawY + (double)(this.target.hb.cY - this.current.y) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }
      } else if (this.duration <= 0.55F && this.stage == 3) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(73);
         AbstractAnimation.clear("decade_far_kick");
         String DECADE_KICK_ATLAS = "img/char/DCD_Animation/decade/FAR/Decade_FAR3.atlas";
         String DECADE_KICK_JSON = "img/char/DCD_Animation/decade/FAR/Decade_FAR3.json";
         new AbstractAnimation("decade_far3", DECADE_KICK_ATLAS, DECADE_KICK_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.Rider_far_kick_A = AbstractAnimation.getAnimation("decade_far3");
         VfxController.Rider_far_kick_A.setMovable(false);
         VfxController.Rider_far_kick_A.state.setAnimation(0, "FAR3", false);
         ++this.stage;
      }

      if (this.duration <= 0.55F && this.duration > 0.45F) {
         AbstractCreature var16 = this.source;
         var16.drawX += (this.target.hb.cX - this.current.x) * Gdx.graphics.getDeltaTime();
         AbstractAnimation var17 = VfxController.Rider_far_kick_A;
         var17.drawX += (this.target.hb.cX - this.current.x) * Gdx.graphics.getDeltaTime();
         AbstractCreature var18 = this.source;
         var18.drawY = (float)((double)var18.drawY + (double)(this.target.hb.cY - this.current.y) / (double)1.5F * (double)Gdx.graphics.getDeltaTime());
         AbstractAnimation var19 = VfxController.Rider_far_kick_A;
         var19.drawY = (float)((double)var19.drawY + (double)(this.target.hb.cY - this.current.y) / (double)1.5F * (double)Gdx.graphics.getDeltaTime());
      }

      if (this.duration <= 0.0F) {
         AbstractAnimation.clear("decade_far3");
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         this.source.drawX = this.start.x;
         this.source.drawY = this.start.y;
         AbstractDungeon.player.showHealthBar();
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Rider_far_kick_A = null;
   }
}
