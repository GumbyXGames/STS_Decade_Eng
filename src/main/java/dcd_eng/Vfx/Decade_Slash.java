package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Decade_Slash extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;

   public Decade_Slash(AbstractCreature source, AbstractCreature target, int x) {
      this.duration = 3.0F;
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

         AbstractDungeon.player.flipHorizontal = false;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(82);
         ++this.stage;
         AbstractDungeon.player.hideHealthBar();
      } else if (this.duration < this.startingDuration - 1.5F && this.stage == 1) {
         CardCrawlGame.sound.playA("decade_slash", 0.0F);
         ++this.stage;
      } else if (this.duration <= this.startingDuration - 1.7F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(83);
         ++this.stage;
      } else if (this.stage == 3) {
         if (this.source.drawX < this.target.drawX - this.target.hb_w / 2.0F) {
            AbstractCreature var3 = this.source;
            var3.drawX = (float)((double)var3.drawX + (double)(this.target.hb.cX - this.current.x) / (double)0.5F * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
         }

         if (this.source.drawY > this.target.drawY) {
            AbstractCreature var4 = this.source;
            var4.drawY = (float)((double)var4.drawY - (double)(this.target.hb.cY - this.current.y) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }
      } else if (this.stage == 4) {
         AbstractDungeon.effectsQueue.add(new Decade_Slash2(this.source, this.target, this.damage, this.start.x, this.start.y, this.Tstart.x, this.Tstart.y));
         ++this.stage;
      }

      if (this.duration <= 0.0F) {
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
