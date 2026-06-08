package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Gouram_Boom extends AbstractGameEffect {
   private boolean start = true;
   private AbstractCreature target;

   Kuuga_Gouram_Boom(AbstractCreature target) {
      this.duration = 1.37F;
      this.startingDuration = 1.37F;
      this.target = target;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/gouram/gouram_boom.atlas";
         String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/gouram/gouram_boom.json";
         new AbstractAnimation("kuuga_gouram_boom", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.1F, this.target.drawX, (float)(Settings.M_H / 2), this.target.hb_w, this.target.hb_h, 1.0F);
         VfxController.Henshin_A = AbstractAnimation.getAnimation("kuuga_gouram_boom");
         VfxController.Henshin_A.setMovable(false);
         VfxController.Henshin_A.state.setAnimation(0, "boom", false);
         CardCrawlGame.sound.playA("kuuga_boom", 0.0F);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("kuuga_gouram_boom");
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Henshin_A = null;
   }
}
