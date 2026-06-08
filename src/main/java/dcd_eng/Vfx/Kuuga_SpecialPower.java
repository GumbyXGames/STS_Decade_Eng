package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_SpecialPower extends AbstractGameEffect {
   private final String id;
   private boolean start = true;
   private AbstractCreature target;
   public static int x;
   private AbstractAnimation Attacked = null;

   public Kuuga_SpecialPower(AbstractCreature target) {
      this.duration = 0.96F;
      this.startingDuration = 0.96F;
      this.target = target;
      this.id = "kuuga_SpecialPower" + x;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         ++x;
         String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR/Kuuga_SpecialPower.atlas";
         String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR/Kuuga_SpecialPower.json";
         new AbstractAnimation(this.id, KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8F, this.target.drawX, this.target.drawY + this.target.hb_h / 2.0F, this.target.hb_w, this.target.hb_h, 1.0F);
         this.Attacked = AbstractAnimation.getAnimation(this.id);
         this.Attacked.setMovable(false);
         this.Attacked.state.setAnimation(0, "normal", true);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear(this.id);
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      this.Attacked = null;
   }
}
