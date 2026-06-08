package dcd_eng.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Patches.AbstractSummonedAnimation;

public class SpecialTaikoEffects {
   public static Hitbox hb;
   public static Texture img;
   public static float cX;
   public static float cY;
   private static float draw_width;
   private static float draw_height;
   public static int a;
   public static int cf;
   public static float TaikoTimer;
   public static AbstractSummonedAnimation TAIKO;
   private static final String TAIKO_ATLAS1 = "img/char/DCD_Animation/hibiki/taiko_appear.atlas";
   private static final String TAIKO_JSON1 = "img/char/DCD_Animation/hibiki/taiko_appear_appear.json";
   private static final AbstractSummonedAnimation TAIKO1;
   private static final String TAIKO_ATLAS2 = "img/char/DCD_Animation/hibiki/taiko_p.atlas";
   private static final String TAIKO_JSON2 = "img/char/DCD_Animation/hibiki/taiko_p_taiko.json";
   private static final AbstractSummonedAnimation TAIKO2;
   private static final String TAIKO_JSON3 = "img/char/DCD_Animation/hibiki/taiko_p_stop.json";
   private static final AbstractSummonedAnimation TAIKO3;
   private static final String TAIKO_JSON4 = "img/char/DCD_Animation/hibiki/taiko_appear_disappear.json";
   private static final AbstractSummonedAnimation TAIKO4;

   public SpecialTaikoEffects() {
      draw_height = 84.0F;
      draw_width = 88.0F;
   }

   public static void update() {
      if (!TurnTimer.BattleEnd && AbstractDungeon.screen.name().equals("NONE")) {
         if (a == cf) {
            System.out.println(cf);
            return;
         }

         cf = a;
         if (a == 1) {
            TAIKO = TAIKO1;
            TAIKO.setMovable(false);
            TAIKO.state.setAnimation(0, "appear", false);
         } else if (a == 2) {
            TAIKO = TAIKO2;
            TAIKO.setMovable(false);
            TAIKO.state.setAnimation(0, "taiko", false);
         } else if (a == 3) {
            TAIKO = TAIKO3;
            TAIKO.setMovable(false);
            TAIKO.state.setAnimation(0, "stop", false);
         } else if (a == 4) {
            TAIKO = TAIKO4;
            TAIKO.setMovable(false);
            TAIKO.state.setAnimation(0, "disappear", false);
         }
      } else if (TaikoTimer > 0.0F) {
         TaikoTimer -= Gdx.graphics.getDeltaTime();
      } else {
         TaikoTimer = 0.0F;
         TAIKO = null;
      }

   }

   public static void render(SpriteBatch sb) {
      sb.setColor(Color.WHITE.cpy());
      sb.draw(img, hb.cX - draw_width / 2.0F, hb.cY - draw_height / 2.0F, draw_width / 2.0F, draw_height / 2.0F, draw_width, draw_height, Settings.scale, Settings.scale, 0.0F, 0, 0, (int)draw_width, (int)draw_height, false, false);
   }

   static {
      TAIKO1 = new AbstractSummonedAnimation("TAIKO1", "img/char/DCD_Animation/hibiki/taiko_appear.atlas", "img/char/DCD_Animation/hibiki/taiko_appear_appear.json", 0.8F, AbstractDungeon.player.drawX + 50.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      TAIKO2 = new AbstractSummonedAnimation("TAIKO2", "img/char/DCD_Animation/hibiki/taiko_p.atlas", "img/char/DCD_Animation/hibiki/taiko_p_taiko.json", 0.8F, AbstractDungeon.player.drawX + 50.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      TAIKO3 = new AbstractSummonedAnimation("TAIKO3", "img/char/DCD_Animation/hibiki/taiko_p.atlas", "img/char/DCD_Animation/hibiki/taiko_p_stop.json", 0.8F, AbstractDungeon.player.drawX + 50.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
      TAIKO4 = new AbstractSummonedAnimation("TAIKO4", "img/char/DCD_Animation/hibiki/taiko_appear.atlas", "img/char/DCD_Animation/hibiki/taiko_appear_disappear.json", 0.8F, AbstractDungeon.player.drawX + 50.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
   }
}
