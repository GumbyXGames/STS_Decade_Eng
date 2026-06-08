package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Helper.SpecialFaizBox;

public class Axel_SparkleCut extends AbstractGameEffect {
   private float x;
   private float y;
   private Texture img;
   private boolean Start = true;
   private boolean Final = false;
   private int n = 0;

   Axel_SparkleCut(float x, float y) {
      this.img = new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
      this.x = x;
      this.y = y;
      this.duration = 30.0F;
      this.startingDuration = 30.0F;
      this.color = Color.WHITE.cpy();
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (!SpecialFaizBox.AxelForm && this.Start) {
         this.img = new Texture(Gdx.files.internal("img/char/DCD_Animation/faiz_Axel/SparkleCut.png"));
         this.x -= (float)this.img.getWidth() / 2.0F;

         for(int i = 0; i < SpecialFaizBox.EdgePoint; ++i) {
            if (this.n != SpecialFaizBox.EdgePoint) {
               ++this.n;
            }
         }

         this.Start = false;
         this.Final = true;
      }

      if (this.Final && this.n == SpecialFaizBox.EdgePoint && this.duration < 30.0F - (float)SpecialFaizBox.EdgePoint * 2.0F) {
         this.dispose();
         this.isDone = true;
      }

      if (this.duration < 0.0F) {
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      sb.setColor(this.color);
      sb.draw(this.img, this.x, this.y);
   }

   public void dispose() {
      this.img = null;
   }
}
