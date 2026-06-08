package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Boom extends AbstractGameEffect {
   private static boolean again;
   private boolean start = true;
   private AbstractCreature target;
   private static AbstractAnimation Boom;

   public Kuuga_Boom(AbstractCreature target, boolean again) {
      this.duration = 0.96F;
      this.startingDuration = 0.96F;
      this.target = target;
      Kuuga_Boom.again = again;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR/Kuuga_Boom.atlas";
         String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR/Kuuga_Boom.json";
         new AbstractAnimation("kuuga_boom", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8F, this.target.drawX, this.target.drawY + this.target.hb_h / 2.0F, this.target.hb_w, this.target.hb_h, 1.0F);
         Boom = AbstractAnimation.getAnimation("kuuga_boom");
         Boom.setMovable(false);
         Boom.setAnimation(0, "boom", false);
         CardCrawlGame.sound.playA("kuuga_boom", 0.0F);
         again = false;
         this.start = false;
      }

      if (this.duration < 0.0F) {
         if (!again) {
            AbstractAnimation.clear("kuuga_boom");
         }

         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      Boom = null;
   }
}
