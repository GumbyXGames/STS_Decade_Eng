package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_FAR_R_kick extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;
   private AbstractAnimation kuuga_far_kick = null;

   public Kuuga_FAR_R_kick(AbstractCreature source, AbstractCreature target, int x) {
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
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(125);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 1.0F && this.stage == 0) {
         CardCrawlGame.sound.playA("kuuga_currentsound", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.62F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(126);
         ++this.stage;
      }

      if (this.duration < this.startingDuration - 1.62F && this.duration > this.startingDuration - 2.12F) {
         AbstractCreature var8 = this.source;
         var8.drawY += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var9 = this.current;
         var9.y += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         AbstractCreature var10 = this.source;
         var10.drawX += 500.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var11 = this.current;
         var11.x += 500.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 1.72F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(127);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.82F && this.stage == 3) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(128);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.12F) {
         if (this.stage == 4) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(104);
            String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R5.atlas";
            String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R5.json";
            new AbstractAnimation("kuuga_far_kick", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
            this.kuuga_far_kick = AbstractAnimation.getAnimation("kuuga_far_kick");
            this.kuuga_far_kick.setMovable(false);
            this.kuuga_far_kick.state.setAnimation(0, "FAR5", false);
            ++this.stage;
         }

         if (this.source.drawY > this.target.drawY) {
            AbstractCreature var12 = this.source;
            var12.drawY = (float)((double)var12.drawY + (double)(this.target.hb.cY - this.current.y) / (double)0.5F * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var13 = this.kuuga_far_kick;
            var13.drawY = (float)((double)var13.drawY + (double)(this.target.hb.cY - this.current.y) / (double)0.5F * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.source.drawX < this.target.drawX) {
            AbstractCreature var14 = this.source;
            var14.drawX = (float)((double)var14.drawX + (double)(this.target.hb.cX - this.current.x) / (double)0.5F * (double)Gdx.graphics.getDeltaTime());
            AbstractAnimation var15 = this.kuuga_far_kick;
            var15.drawX = (float)((double)var15.drawX + (double)(this.target.hb.cX - this.current.x) / (double)0.5F * (double)Gdx.graphics.getDeltaTime());
         } else {
            AbstractAnimation.clear("kuuga_far_kick");
            AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
            AbstractDungeon.effectsQueue.add(new Kuuga_FAR_R_kick2(this.source, this.target, this.damage, this.start.x, this.start.y, this.Tstart.x, this.Tstart.y));
            this.dispose();
            this.isDone = true;
         }
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      this.kuuga_far_kick = null;
   }
}
