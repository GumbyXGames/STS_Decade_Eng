package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class Kuuga_FAR_Background extends AbstractGameEffect {
   private static boolean isEnd;
   private final boolean isGouram;
   private boolean isStart = true;
   private boolean start = true;
   private Texture img;
   public static int x;

   public Kuuga_FAR_Background(boolean isEnd, boolean isGouram) {
      if (isEnd) {
         this.img = new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
      } else if (isGouram) {
         this.img = new Texture(Gdx.files.internal("img/char/DCD_Animation/kuuga/FAR-R/kuuga_far-m_background.png"));
      } else if (AbstractDungeon.player.hasPower("RisingDragonPower")) {
         this.img = new Texture(Gdx.files.internal("img/char/DCD_Animation/kuuga/dragon/kuuga_far-d_background.png"));
      } else if (AbstractDungeon.player.hasPower("RisingTitanPower") && AbstractDungeon.player.hasPower("RisingMightyPower")) {
         this.img = new Texture(Gdx.files.internal("img/char/DCD_Animation/kuuga/titan/kuuga_far-t_background.png"));
      } else if (AbstractDungeon.player.hasPower("RisingPegasusPower")) {
         this.img = new Texture(Gdx.files.internal("img/char/DCD_Animation/kuuga/pegasus/kuuga_far-p_background.png"));
      } else if (AbstractDungeon.player.hasPower("RisingMightyPower")) {
         this.img = new Texture(Gdx.files.internal("img/char/DCD_Animation/kuuga/FAR-R/kuuga_far-m_background.png"));
      } else {
         this.img = new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
      }

      this.duration = 99.0F;
      this.startingDuration = 99.0F;
      Kuuga_FAR_Background.isEnd = isEnd;
      this.color = Color.WHITE.cpy();
      this.isGouram = isGouram;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (isEnd) {
         AbstractSummonedAnimation.clear("kuuga_FAR_Background");
         this.dispose();
         this.isDone = true;
      } else if (this.start) {
         String KUUGA_ATTACKED_ATLAS = null;
         String KUUGA_ATTACKED_JSON = null;
         if (this.isGouram) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/kuuga_far_background.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR-R/kuuga_far_background.json";
            this.duration -= 2.7F;
         } else if (AbstractDungeon.player.hasPower("RisingDragonPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/dragon/kuuga_far_background2.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/dragon/kuuga_far_background2.json";
         } else if (AbstractDungeon.player.hasPower("RisingTitanPower") && AbstractDungeon.player.hasPower("RisingMightyPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/titan/kuuga_far_background3.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/titan/kuuga_far_background3.json";
         } else if (AbstractDungeon.player.hasPower("RisingPegasusPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/kuuga_far_background4.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/kuuga_far_background4.json";
         } else if (AbstractDungeon.player.hasPower("RisingMightyPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/kuuga_far_background.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR-R/kuuga_far_background.json";
         } else {
            this.isDone = true;
         }

         if (KUUGA_ATTACKED_ATLAS != null && !isEnd) {
            ++x;
            new AbstractSummonedAnimation("kuuga_FAR_Background", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.4F, (float)Settings.M_W / 2.0F, (float)Settings.M_H / 2.0F, 1.0F, 1.0F, 1.0F);
            VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("kuuga_FAR_Background");
            VfxController.far_effect_S.setMovable(false);
            VfxController.far_effect_S.state.setAnimation(0, "background", true);
            this.start = false;
         }
      }

      if ((double)this.duration < (double)this.startingDuration - 3.2 && this.isStart) {
         AbstractSummonedAnimation.clear("kuuga_FAR_Background");
         String animationName;
         String KUUGA_ATTACKED_ATLAS;
         String KUUGA_ATTACKED_JSON;
         if (this.isGouram) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/gouram/kuuga_far_gouram.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/gouram/kuuga_far_gouram.json";
            animationName = "far_gouram";
         } else if (AbstractDungeon.player.hasPower("RisingDragonPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/dragon/kuuga_far_dragon.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/dragon/kuuga_far_dragon.json";
            animationName = "far_dragon";
         } else if (AbstractDungeon.player.hasPower("RisingTitanPower") && AbstractDungeon.player.hasPower("RisingMightyPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/titan/kuuga_far_titan.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/titan/kuuga_far_titan.json";
            animationName = "far_titan";
         } else if (AbstractDungeon.player.hasPower("RisingPegasusPower")) {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/kuuga_far_pegasus.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/kuuga_far_pegasus.json";
            animationName = "far_pegasus";
         } else {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/kuuga_far_fire.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/FAR-R/kuuga_far_fire.json";
            animationName = "far_fire";
         }

         new AbstractSummonedAnimation("kuuga_FAR_Background", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8F, (float)Settings.M_W / 2.0F, (float)Settings.M_H / 2.0F, 1.0F, 1.0F, 1.0F);
         VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("kuuga_FAR_Background");
         VfxController.far_effect_S.setMovable(false);
         VfxController.far_effect_S.state.setAnimation(0, animationName, true);
         this.isStart = false;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.isStart) {
         sb.setColor(this.color);
         sb.draw(this.img, 0.0F, (float)((double)AbstractDungeon.player.drawY + (double)AbstractDungeon.player.hb_h * (double)1.5F));
      }

   }

   public void dispose() {
      this.img.dispose();
      VfxController.far_effect_S = null;
   }
}
