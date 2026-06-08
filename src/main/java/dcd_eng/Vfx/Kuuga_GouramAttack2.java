package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_GouramAttack2 extends AbstractGameEffect {
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;

   Kuuga_GouramAttack2(AbstractCreature source, AbstractCreature target, float PlayerStartDrawX, float PlayerStartDrawY, float MonsterStartDrawX, float MonsterStartDrawY) {
      this.duration = 2.0F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.start = new Vector2(PlayerStartDrawX, PlayerStartDrawY);
      this.Tstart = new Vector2(MonsterStartDrawX, MonsterStartDrawY);
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(138);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.2F && this.duration > this.startingDuration - 0.5F) {
         AbstractCreature var10000 = this.source;
         var10000.drawY += 3000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var5 = this.current;
         var5.y += 3000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         AbstractCreature var6 = this.source;
         var6.drawX -= 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var7 = this.current;
         var7.x -= 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 0.5F && this.stage == 0) {
         if (this.source.drawY > this.start.y) {
            AbstractCreature var8 = this.source;
            var8.drawY = (float)((double)var8.drawY + (double)(this.target.hb.cY - this.current.y) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            var8 = this.source;
            var8.drawX = (float)((double)var8.drawX - (double)(this.target.hb.cX - this.current.x) / 0.3 * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
         }
      } else if (this.stage == 1) {
         if (this.source.hasPower("KamenRideKuugaPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(142);
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(139);
         }

         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(true, false)));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         this.source.drawX = this.start.x;
         this.source.drawY = this.start.y;
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         AbstractDungeon.player.showHealthBar();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
