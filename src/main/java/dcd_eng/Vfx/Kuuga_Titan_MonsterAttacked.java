package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Titan_MonsterAttacked extends AbstractGameEffect {
   private final String id;
   private boolean start = true;
   private AbstractCreature target;
   public static int x;
   private AbstractAnimation Attacked = null;

   public Kuuga_Titan_MonsterAttacked(AbstractCreature target) {
      this.duration = 0.5F;
      this.startingDuration = 0.5F;
      this.target = target;
      this.id = "titan_m_attacked" + x;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         ++x;
         String TITAN_M_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_slash.atlas";
         String TITAN_M_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/titan/titan_slash.json";
         new AbstractAnimation(this.id, TITAN_M_ATTACKED_ATLAS, TITAN_M_ATTACKED_JSON, 0.8F, this.target.drawX, this.target.drawY + this.target.hb_h / 2.0F, this.target.hb_w, this.target.hb_h, 1.0F);
         this.Attacked = AbstractAnimation.getAnimation(this.id);
         this.Attacked.setMovable(false);
         this.Attacked.state.setAnimation(0, "slash", false);
         CardCrawlGame.sound.playA("titan_slash", 0.0F);
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
