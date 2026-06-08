package dcd_eng.Effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LightningEffect extends AbstractGameEffect {
   private Texture img;
   private int index = 0;
   private float x;
   private float y;
   private float vY;
   private boolean flipX;
   private boolean flipY;
   private float intervalDuration;
   private float dur_div2;

   public LightningEffect() {
      this.renderBehind = MathUtils.randomBoolean();
      this.duration = MathUtils.random(1.3F, 1.8F);
      this.dur_div2 = this.duration / 2.0F;
      this.img = (Texture)ImageMaster.LIGHTNING_PASSIVE_VFX.get(this.index);
      this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 30.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 30.0F * Settings.scale);
      this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F - -10.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 10.0F * Settings.scale);
      this.x -= (float)this.img.getWidth() / 2.0F;
      this.y -= (float)this.img.getHeight() / 2.0F;
      this.color = CardHelper.getColor(230, 140, 70);
      this.scale = MathUtils.random(0.6F, 1.0F) * Settings.scale;
      this.rotation = MathUtils.random(360.0F);
      if (this.rotation < 120.0F) {
         this.renderBehind = true;
      }

      this.flipX = MathUtils.randomBoolean();
      this.flipY = MathUtils.randomBoolean();
      this.intervalDuration = MathUtils.random(0.03F, 0.06F);
      this.duration = this.intervalDuration;
   }

   public void update() {
      this.vY += Gdx.graphics.getDeltaTime() * 40.0F * Settings.scale;
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.0F) {
         ++this.index;
         if (this.index > ImageMaster.LIGHTNING_PASSIVE_VFX.size() - 1) {
            this.isDone = true;
            return;
         }

         this.img = (Texture)ImageMaster.LIGHTNING_PASSIVE_VFX.get(this.index);
         this.duration = this.intervalDuration;
      }

   }

   public void render(SpriteBatch sb) {
      sb.setColor(this.color);
      sb.setBlendFunction(770, 1);
      sb.draw(this.img, this.x, this.y + this.vY, (float)this.img.getWidth() / 2.0F, (float)this.img.getHeight() / 2.0F, (float)this.img.getWidth(), (float)this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, 122, 122, this.flipX, this.flipY);
      sb.setBlendFunction(770, 771);
   }

   public void dispose() {
   }
}
