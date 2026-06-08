package dcd_eng.Patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.vfx.TintEffect;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class AbstractSummonedAnimation {
   public static List<AbstractSummonedAnimation> S_animations = new ArrayList();
   SoftReference<Object> softRef1;
   public static SkeletonMeshRenderer sr = new SkeletonMeshRenderer();
   public static Color AColor;
   protected Texture img;
   protected TextureAtlas atlas;
   public Skeleton skeleton;
   protected AnimationStateData stateData;
   public AnimationState state;
   public Hitbox hb;
   public TintEffect tint;
   public String id;
   public float drawX;
   public float drawY;
   public boolean flipHorizontal;
   public boolean flipVertical;
   private boolean movable;
   private boolean visible;
   GifAnimation gifAnimation;

   public void setMovable(boolean movable) {
      this.movable = movable;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public static void changeAnimation(AbstractSummonedAnimation animation, AnimationLoader loader) {
      animation.atlas = loader.atlas;
      animation.skeleton = loader.skeleton;
      animation.stateData = loader.stateData;
      animation.state = loader.state;
      animation.visible = true;
   }

   public AbstractSummonedAnimation(String id, String atlasUrl, String skeletonUrl, float scale, float positionX, float positionY, float hb_w, float hb_h, float hbScale) {
      this.softRef1 = new SoftReference(S_animations);
      this.movable = true;
      this.visible = true;
      this.id = id;
      this.loadAnimation(atlasUrl, skeletonUrl, scale);
      if (hb_h == 0.0F && hb_w == 0.0F) {
         hb_w = this.skeleton.getData().getWidth();
         hb_h = this.skeleton.getData().getHeight();
      }

      this.hb = new Hitbox(hb_w * hbScale, hb_h * hbScale);
      this.tint = new TintEffect();
      this.setPosition(positionX, positionY);
      addAnimation(this);
   }

   public AbstractSummonedAnimation(String atlasUrl, String skeletonUrl, float scale, float positionX, float positionY, float hb_x, float hb_y, float hb_w, float hb_h) {
      this((String)null, atlasUrl, skeletonUrl, scale, positionX, positionY, hb_w, hb_h, 1.0F);
   }

   public AbstractSummonedAnimation(String atlasUrl, String skeletonUrl, float scale, float positionX, float positionY, float hb_x, float hb_y) {
      this((String)null, atlasUrl, skeletonUrl, scale, positionX, positionY, 0.0F, 0.0F, 1.0F);
   }

   public AbstractSummonedAnimation(String imgUrl, float positionX, float positionY, float hb_w, float hb_h, float hbScale) {
      this.softRef1 = new SoftReference(S_animations);
      this.movable = true;
      this.visible = true;
      this.loadImg(imgUrl);
      this.tint = new TintEffect();
      if (hb_h == 0.0F && hb_w == 0.0F) {
         hb_w = (float)this.img.getWidth();
         hb_h = (float)this.img.getHeight();
      }

      this.hb = new Hitbox(hb_w * hbScale, hb_h * hbScale);
      this.setPosition(positionX, positionY);
      addAnimation(this);
   }

   public static void addAnimation(AbstractSummonedAnimation abstractAnimation) {
      if (abstractAnimation != null) {
         for(AbstractSummonedAnimation animation : S_animations) {
            if (animation.id != null && animation.id.equals(abstractAnimation.id)) {
               return;
            }
         }

         S_animations.add(abstractAnimation);
      }
   }

   public AbstractSummonedAnimation(String imgUrl, float positionX, float positionY, float hb_w, float hb_h) {
      this(imgUrl, positionX, positionY, 1.0F, hb_w, hb_h);
   }

   public AbstractSummonedAnimation(String imgUrl, float positionX, float positionY) {
      this(imgUrl, positionX, positionY, 1.0F, 0.0F, 0.0F);
   }

   public void setAnimation(int trackIndex, String animationName, boolean loop) {
      this.state.setAnimation(trackIndex, animationName, loop);
   }

   public void setPosition(float positionX, float positionY) {
      this.drawX = positionX;
      this.drawY = positionY;
   }

   public void move(float positionX, float positionY) {
      this.hb.move(positionX, positionY);
      this.drawX = this.hb.x + this.hb.width / 2.0F;
      this.drawY = this.hb.y + this.hb.height / 2.0F;
   }

   public void update() {
      if (this.visible) {
         this.hb.x = this.drawX - this.hb.width / 2.0F;
         this.hb.y = this.drawY - this.hb.height / 2.0F;
         this.hb.update();
         this.tint.update();
         if (this.movable && this.hb.hovered && InputHelper.isMouseDown) {
            this.move((float)InputHelper.mX, (float)InputHelper.mY);
         }
      }

   }

   public void render(SpriteBatch sb) {
      if (this.visible) {
         if (this.gifAnimation != null) {
            sb.setColor(Color.WHITE);
            this.gifAnimation.render(sb, this.drawX - (float)this.gifAnimation.getWidth() * Settings.scale / 2.0F, this.drawY + AbstractDungeon.sceneOffsetY, (float)this.gifAnimation.getWidth() * Settings.scale, (float)this.gifAnimation.getHeight() * Settings.scale);
         } else if (this.atlas == null) {
            sb.setColor(this.tint.color);
            if (this.img != null) {
               sb.draw(this.img, this.drawX - (float)this.img.getWidth() * Settings.scale / 2.0F, this.drawY + AbstractDungeon.sceneOffsetY, (float)this.img.getWidth() * Settings.scale, (float)this.img.getHeight() * Settings.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), this.flipHorizontal, this.flipVertical);
            }
         } else {
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(this.drawX, this.drawY + AbstractDungeon.sceneOffsetY);
            this.skeleton.setColor(this.tint.color);
            this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
            sb.end();
            CardCrawlGame.psb.begin();
            sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
            sb.setBlendFunction(770, 771);
         }

         this.hb.render(sb);
      } else {
         sb.dispose();
      }

   }

   public void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
      this.visible = true;
      this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
      SkeletonJson json = new SkeletonJson(this.atlas);
      json.setScale(Settings.scale / scale);
      SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
      this.skeleton = new Skeleton(skeletonData);
      this.skeleton.setColor(Color.WHITE);
      this.stateData = new AnimationStateData(skeletonData);
      this.state = new AnimationState(this.stateData);
      this.img = null;
   }

   protected void loadImg(String imgUrl) {
      this.visible = true;
      if (imgUrl.endsWith(".gif")) {
         this.gifAnimation = new GifAnimation(imgUrl);
         this.img = null;
      } else {
         this.img = ImageMaster.loadImage(imgUrl);
         this.gifAnimation = null;
      }

      this.atlas = null;
   }

   public static AbstractSummonedAnimation getAnimation(String id) {
      if (id == null) {
         return null;
      } else {
         for(AbstractSummonedAnimation animation : S_animations) {
            if (id.equals(animation.id)) {
               return animation;
            }
         }

         return null;
      }
   }

   public static void clearAll() {
      hideAll();
      S_animations.clear();
      System.gc();
   }

   public static AbstractSummonedAnimation clear(String id) {
      if (id == null) {
         return null;
      } else {
         int x = 0;

         for(AbstractSummonedAnimation animation : S_animations) {
            if (id.equals(animation.id)) {
               S_animations.remove(x);
               break;
            }

            ++x;
         }

         return null;
      }
   }

   public static void hideAll() {
      for(AbstractSummonedAnimation animation : S_animations) {
         animation.visible = false;
      }

   }

   public static void showAll() {
      for(AbstractSummonedAnimation animation : S_animations) {
         animation.visible = true;
      }

   }

   static {
      sr.setPremultipliedAlpha(true);
      AColor = new Color(1.0F, 1.0F, 1.0F, 0.1F);
   }
}
