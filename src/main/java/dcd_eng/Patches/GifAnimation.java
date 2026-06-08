package dcd_eng.Patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import java.util.HashMap;

public class GifAnimation {
   Animation<TextureRegion> animation;
   float elapsed;
   public GifDecoder gdec;
   public static HashMap<String, Animation<TextureRegion>> cache = new HashMap();

   public GifAnimation(String filePath) {
      this.create(filePath);
   }

   public void create(String filePath) {
      if (cache.containsKey(filePath)) {
         this.animation = (Animation)cache.get(filePath);
      } else {
         this.animation = GifDecoder.loadGIFAnimation(PlayMode.LOOP, Gdx.files.internal(filePath).read(), this);
         cache.put(filePath, this.animation);
      }

   }

   public void render(SpriteBatch sb, float x, float y, float width, float height) {
      this.elapsed += Gdx.graphics.getDeltaTime();
      sb.draw((TextureRegion)this.animation.getKeyFrame(this.elapsed), x, y, width, height);
   }

   public void render(SpriteBatch sb, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
      this.elapsed += Gdx.graphics.getDeltaTime();
      sb.draw((TextureRegion)this.animation.getKeyFrame(this.elapsed), x, y, originX, originY, width, height, scaleX, scaleY, rotation);
   }

   public void dispose(SpriteBatch sb) {
      sb.dispose();
   }

   public int getWidth() {
      return this.gdec.getWidth();
   }

   public int getHeight() {
      return this.gdec.getHeight();
   }
}
