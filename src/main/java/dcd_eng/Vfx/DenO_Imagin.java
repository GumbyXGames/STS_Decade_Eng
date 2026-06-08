package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class DenO_Imagin extends AbstractGameEffect {
   private boolean start = true;
   private String ATLAS = "";
   private String JSON = "";
   private String an = "";

   public DenO_Imagin(int Imagin) {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
      switch (Imagin) {
         case 1:
            this.ATLAS = "img/char/DCD_Animation/deno/deno_momotaros.atlas";
            this.JSON = "img/char/DCD_Animation/deno/deno_momotaros.json";
            this.an = "momotaros";
            break;
         case 2:
            this.ATLAS = "img/char/DCD_Animation/deno/deno_urataros.atlas";
            this.JSON = "img/char/DCD_Animation/deno/deno_urataros.json";
            this.an = "urataros";
            break;
         case 3:
            this.ATLAS = "img/char/DCD_Animation/deno/deno_kintaros.atlas";
            this.JSON = "img/char/DCD_Animation/deno/deno_kintaros.json";
            this.an = "kintaros";
            break;
         case 4:
            this.ATLAS = "img/char/DCD_Animation/deno/deno_ryutaros.atlas";
            this.JSON = "img/char/DCD_Animation/deno/deno_ryutaros.json";
            this.an = "ryutaros";
            break;
         case 5:
            this.ATLAS = "img/char/DCD_Animation/deno/deno_sieg.atlas";
            this.JSON = "img/char/DCD_Animation/deno/deno_sieg.json";
            this.an = "sieg";
      }

   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         AbstractPlayer p = AbstractDungeon.player;
         new AbstractAnimation("deno_imagin", this.ATLAS, this.JSON, 0.8F, p.drawX + p.hb_w, p.drawY, p.hb_w, p.hb_h, 1.0F);
         VfxController.far_effect3_A = AbstractAnimation.getAnimation("deno_imagin");
         VfxController.far_effect3_A.setMovable(false);
         VfxController.far_effect3_A.state.setAnimation(0, this.an, false);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("deno_imagin");
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect3_A = null;
   }
}
