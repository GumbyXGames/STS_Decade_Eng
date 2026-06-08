package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class Decade_FAR_Card extends AbstractGameEffect {
   private final int damage;
   private final AbstractCreature source;
   private final AbstractCreature target;
   private Texture img;
   private final float[] x = new float[10];
   private final float[] y = new float[10];
   private final boolean[] draw = new boolean[10];
   private float timer;
   private int counter;
   private boolean attack = true;
   private boolean isdead = false;

   Decade_FAR_Card(AbstractCreature source, AbstractCreature target, int x) {
      this.duration = 2.47F;
      this.startingDuration = this.duration;
      this.img = ImageMaster.loadImage("img/char/DCD_Animation/decade/FAR/card.png");
      this.source = source;
      this.target = target;

      for(int i = 0; i < 10; ++i) {
         this.x[i] = this.source.hb.cX + (this.target.hb.cX - this.source.hb.cX) / 11.0F * (float)(i + 1) - 38.0F;
         this.y[i] = this.source.hb.cY + (this.target.hb.cY - this.source.hb.cY) / 9.0F * (float)i - 200.0F;
         this.draw[i] = false;
      }

      this.timer = 0.05F;
      this.counter = 0;
      this.damage = x;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration >= this.startingDuration - 0.55F) {
         this.timer -= Gdx.graphics.getDeltaTime();
         if (this.timer <= 0.0F) {
            this.draw[this.counter] = true;
            ++this.counter;
            this.timer += 0.05F;
         }
      }

      if (this.duration < 2.0F && this.attack) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         this.attack = false;
      }

      if (this.duration < 2.0F && this.isdead) {
         AbstractDungeon.player.showHealthBar();
         this.dispose();
         this.isDone = true;
      }

      if (this.duration < this.startingDuration - 1.1F && this.duration > this.startingDuration - 1.5F) {
         if (!this.target.isDead && !this.target.isDying) {
            for(int i = 0; i < 10; ++i) {
               this.draw[i] = true;
               float[] var10000 = this.y;
               var10000[i] += (float)(9 - i) * 300.0F / 9.0F / 0.4F * Settings.scale * Gdx.graphics.getDeltaTime();
            }

            this.counter = 0;
            this.timer = 0.01F;
         } else {
            this.isdead = true;
         }
      } else if (this.duration <= 0.66F && this.counter < 10) {
         this.timer -= Gdx.graphics.getDeltaTime();
         if (this.timer <= 0.0F) {
            this.draw[this.counter] = false;
            ++this.counter;
            this.timer += 0.05F;
         }
      }

      if (this.duration <= 0.0F) {
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());

      for(int i = 0; i < 10; ++i) {
         if (this.draw[i]) {
            sb.draw(this.img, this.x[i], this.y[i], 68.0F, 200.0F, 136.0F, 400.0F, this.scale, this.scale, this.rotation, 0, 0, 136, 400, false, false);
         }
      }

   }

   public void dispose() {
      this.img = null;
   }
}
