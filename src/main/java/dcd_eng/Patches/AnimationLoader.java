package dcd_eng.Patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import java.lang.reflect.Field;
import org.lwjgl.opengl.ContextCapabilities;

public class AnimationLoader {
   public TextureAtlas atlas;
   public Skeleton skeleton;
   public AnimationState state;
   public AnimationStateData stateData;
   private static final ContextCapabilities MAIN_CONTEXT = null;

   public AnimationLoader(String atlasUrl, String skeletonUrl, float scale) {
      this(atlasUrl, skeletonUrl, scale, true);
   }

   public AnimationLoader() {
   }

   public AnimationLoader(TextureAtlas atlas, String skeletonUrl, float scale) {
      SkeletonJson json = new SkeletonJson(atlas);
      json.setScale(Settings.scale / scale);
      SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
      this.skeleton = new Skeleton(skeletonData);
      this.skeleton.setColor(Color.WHITE);
      this.stateData = new AnimationStateData(skeletonData);
      this.state = new AnimationState(this.stateData);
      this.atlas = atlas;
   }

   public AnimationLoader(String atlasUrl, String skeletonUrl, float scale, boolean isPlayer) {
      this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
      SkeletonJson json = new SkeletonJson(this.atlas);
      if (CardCrawlGame.dungeon != null && AbstractDungeon.player != null) {
         if (AbstractDungeon.player.hasRelic("PreservedInsect") && !isPlayer && AbstractDungeon.getCurrRoom().eliteTrigger) {
            scale += 0.3F;
         }

         if (ModHelper.isModEnabled("MonsterHunter") && !isPlayer) {
            scale -= 0.3F;
         }
      }

      json.setScale(Settings.scale / scale);
      SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
      this.skeleton = new Skeleton(skeletonData);
      this.skeleton.setColor(Color.WHITE);
      this.stateData = new AnimationStateData(skeletonData);
      this.state = new AnimationState(this.stateData);
   }

   public static void loadAnimation(AbstractCreature creature, AnimationLoader animation) {
      Invoker.setField(creature, "atlas", animation.atlas);
      Invoker.setField(creature, "skeleton", animation.skeleton);
      Invoker.setField(creature, "stateData", animation.stateData);
      creature.state = animation.state;
   }

   public static void loadAnimationReflect(AbstractCreature creature, AnimationLoader animation) {
      try {
         Field field = AbstractCreature.class.getDeclaredField("atlas");
         field.setAccessible(true);
         field.set(creature, animation.atlas);
         field = AbstractCreature.class.getDeclaredField("skeleton");
         field.setAccessible(true);
         field.set(creature, animation.skeleton);
         field = AbstractCreature.class.getDeclaredField("stateData");
         field.setAccessible(true);
         field.set(creature, animation.stateData);
         creature.state = animation.state;
      } catch (IllegalAccessException | NoSuchFieldException e) {
         ((ReflectiveOperationException)e).printStackTrace();
      }

   }

   public static void loadAnimation(AbstractCreature creature, String atlasUrl, String skeletonUrl, float scale, int trackIndex, String animationName, boolean loop) {
      AnimationLoader loader = new AnimationLoader();
      loader.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
      (new Thread(new LoadAnimationTask(creature, loader, skeletonUrl, scale, trackIndex, animationName, loop))).start();
   }

   private static class LoadAnimationTask implements Runnable {
      AbstractCreature creature;
      AnimationLoader loader;
      String skeletonUrl;
      float scale;
      Integer trackIndex;
      String animationName;
      Boolean loop;

      public LoadAnimationTask(AbstractCreature creature, AnimationLoader loader, String skeletonUrl, float scale, Integer trackIndex, String animationName, Boolean loop) {
         this.creature = creature;
         this.loader = loader;
         this.skeletonUrl = skeletonUrl;
         this.scale = scale;
         this.trackIndex = trackIndex;
         this.animationName = animationName;
         this.loop = loop;
      }

      public void run() {
         synchronized(AbstractDungeon.player) {
            AnimationLoader.loadAnimation(this.creature, new AnimationLoader(this.loader.atlas, this.skeletonUrl, this.scale));
            if (this.trackIndex != null && this.animationName != null && this.loop != null) {
               this.creature.state.setAnimation(this.trackIndex, this.animationName, this.loop);
            }

         }
      }
   }
}
