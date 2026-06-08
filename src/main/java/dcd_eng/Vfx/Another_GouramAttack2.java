package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Another_GouramAttack2 extends AbstractGameEffect {
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;

   Another_GouramAttack2(AbstractCreature source, AbstractCreature target, float PlayerStartDrawX, float PlayerStartDrawY, float MonsterStartDrawX, float MonsterStartDrawY) {
      this.duration = 2.3F;
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
         AbstractAnimation.clear("another_gouram_attack");
         String ANOTHER_GOURAM_ATTACK_ATLAS = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram2.atlas";
         String ANOTHER_GOURAM_ATTACK_JSON = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram2.json";
         new AbstractAnimation("another_gouram_attack", ANOTHER_GOURAM_ATTACK_ATLAS, ANOTHER_GOURAM_ATTACK_JSON, 0.8F, VfxController.far_effect2_A.drawX, VfxController.far_effect2_A.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("another_gouram_attack");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "gouram", false);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.2F && this.duration > this.startingDuration - 0.5F) {
         AbstractAnimation var10000 = VfxController.far_effect2_A;
         var10000.drawY += 3000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var5 = this.current;
         var5.y += 3000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         AbstractAnimation var6 = VfxController.far_effect2_A;
         var6.drawX -= 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var7 = this.current;
         var7.x -= 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 0.5F && this.stage == 0) {
         if (VfxController.far_effect2_A.drawY > this.start.y) {
            AbstractAnimation var8 = VfxController.far_effect2_A;
            var8.drawY = (float)((double)var8.drawY + (double)(this.target.hb.cY - this.current.y) / 0.3 * (double)Gdx.graphics.getDeltaTime());
            var8 = VfxController.far_effect2_A;
            var8.drawX = (float)((double)var8.drawX - (double)(this.target.hb.cX - this.current.x) / (double)1.0F * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
         }
      } else if (this.stage == 1) {
         AbstractAnimation.clear("another_gouram_attack");
         String ANOTHER_GOURAM_ATTACK_ATLAS = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram3.atlas";
         String ANOTHER_GOURAM_ATTACK_JSON = "img/char/DCD_Animation/kuuga/gouram/kuuga_gouram3.json";
         new AbstractAnimation("another_gouram_attack", ANOTHER_GOURAM_ATTACK_ATLAS, ANOTHER_GOURAM_ATTACK_JSON, 0.8F, VfxController.far_effect2_A.drawX, VfxController.far_effect2_A.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("another_gouram_attack");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "gouram", false);
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("another_gouram_attack");
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect2_A = null;
   }
}
