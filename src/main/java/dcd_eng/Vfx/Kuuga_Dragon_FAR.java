package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Characters.Decade;

public class Kuuga_Dragon_FAR extends AbstractGameEffect {
   private final int damage;
   private final int combo;
   private int stage;
   private final AbstractCreature source;
   private final AbstractCreature target;
   private final Vector2 start;
   private final Vector2 current;
   private final Vector2 Tstart;

   public Kuuga_Dragon_FAR(AbstractCreature source, AbstractCreature target, int damage, int combo) {
      this.duration = 3.2F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.start = new Vector2(source.drawX, source.drawY);
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.damage = damage;
      this.combo = combo;
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
         Decade.Trickster(113);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.33F && this.duration > this.startingDuration - 0.46F) {
         AbstractCreature var2 = this.source;
         var2.drawY += 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var3 = this.current;
         var3.y += 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         AbstractCreature var4 = this.source;
         var4.drawX += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var5 = this.current;
         var5.x += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 0.46F && this.stage == 0) {
         if (this.source.drawY > this.target.drawY) {
            AbstractCreature var6 = this.source;
            var6.drawY = (float)((double)var6.drawY + (double)(this.target.hb.cY - this.current.y) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
         }

         if (this.source.drawX < this.target.drawX - this.target.hb_w / 2.0F) {
            AbstractCreature var7 = this.source;
            var7.drawX = (float)((double)var7.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }
      }

      if (this.stage == 1) {
         if (this.combo >= 4) {
            AbstractDungeon.effectsQueue.add(new Kuuga_Dragon_FAR2(this.target, this.damage, this.combo, this.start.x, this.start.y, this.Tstart.x, this.Tstart.y));
         } else {
            AbstractDungeon.effectsQueue.add(new Kuuga_DragonAttack(this.target, this.damage, this.combo, true, this.start.x, this.start.y, this.Tstart.x, this.Tstart.y));
            if (!DCDmod.BGMTrigger) {
               CardCrawlGame.music.playTempBGM("kuuga_OP1.ogg");
            }
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
