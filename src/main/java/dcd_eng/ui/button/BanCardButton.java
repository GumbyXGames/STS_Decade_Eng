package dcd_eng.ui.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;

public class BanCardButton {
   private float showTimer = -1.0F;
   private float current_x;
   private float target_x;
   private boolean isHidden;
   private Color textColor;
   private Color btnColor;
   private static final float HITBOX_W;
   private static final float HITBOX_H;
   public Hitbox hb;
   public boolean banned;
   private final float HIDE_X;
   private float SHOW_X;
   private float SHOW_Y;
   public AbstractCard card;
   private static final UIStrings UIStrings;
   public static final String[] DESCRIPTION;
   private String msg;

   public BanCardButton(AbstractCard card) {
      this.card = card;
      this.SHOW_X = card.target_x;
      this.SHOW_Y = card.target_y + Settings.scale * 200.0F;
      this.HIDE_X = (float)Settings.WIDTH / 2.0F;
      this.current_x = this.HIDE_X;
      this.target_x = this.current_x;
      this.isHidden = true;
      this.textColor = Color.WHITE.cpy();
      this.btnColor = Color.RED.cpy();
      (this.hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H)).move(this.SHOW_X, this.SHOW_Y);
      this.msg = DESCRIPTION[0];
   }

   public void update() {
      if (this.card != null) {
         if (this.showTimer >= 0.0F) {
            this.showTimer -= Gdx.graphics.getDeltaTime();
            if (this.showTimer < 0.0F) {
               this.isHidden = false;
            }
         } else if (!this.isHidden) {
            this.updatePosition();
            this.hb.update();
            if (this.hb.justHovered) {
               CardCrawlGame.sound.play("UI_HOVER");
            }

            if (this.hb.hovered && InputHelper.justClickedLeft) {
               this.hb.clickStarted = true;
               CardCrawlGame.sound.play("UI_CLICK_1");
            }

            if (this.hb.clicked || InputActionSet.cancel.isJustPressed() || CInputActionSet.cancel.isJustPressed()) {
               this.hb.clicked = false;
               this.banned = true;
            }

            if (this.current_x != this.target_x) {
               this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
               if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
                  this.current_x = this.target_x;
                  this.hb.move(this.current_x, this.SHOW_Y);
               }
            }

            this.textColor.a = MathHelper.fadeLerpSnap(this.textColor.a, 1.0F);
            this.btnColor.a = this.textColor.a;
         }
      } else {
         this.isHidden = true;
      }

   }

   private void updatePosition() {
      float target_x = this.card.target_x;
      this.SHOW_X = target_x;
      this.target_x = target_x;
      this.SHOW_Y = this.card.target_y + Settings.scale * 200.0F;
      this.hb.move(this.SHOW_X, this.SHOW_Y);
   }

   public void hideInstantly() {
      this.current_x = this.HIDE_X;
      this.target_x = this.HIDE_X;
      this.isHidden = true;
      this.textColor.a = 0.0F;
      this.btnColor.a = 0.0F;
   }

   public void hide() {
      this.isHidden = true;
   }

   public void show() {
      this.showTimer = 0.5F;
      this.textColor.a = 0.0F;
      this.btnColor.a = 0.0F;
      this.current_x = this.SHOW_X;
      this.target_x = this.SHOW_X;
      this.hb.move(this.SHOW_X, this.SHOW_Y);
   }

   public void render(SpriteBatch sb) {
      if (!this.isHidden) {
         this.renderButton(sb);
         if (FontHelper.getSmartWidth(FontHelper.smallDialogOptionFont, this.msg, 9999.0F, 0.0F) > 200.0F * Settings.scale) {
            FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.msg, this.current_x, this.SHOW_Y, this.textColor, 0.8F);
         } else {
            FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.msg, this.current_x, this.SHOW_Y, this.textColor);
         }
      }

   }

   private void renderButton(SpriteBatch sb) {
      float width = 512.0F;
      float height = 256.0F;
      float originX = 256.0F;
      float originY = 128.0F;
      float scale = Settings.scale * 0.6F;
      sb.setColor(this.btnColor);
      sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.current_x - 256.0F, this.SHOW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, scale, scale, 0.0F, 0, 0, 512, 256, false, false);
      if (this.hb.hovered && !this.hb.clickStarted) {
         sb.setBlendFunction(770, 1);
         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.3F));
         sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.current_x - 256.0F, this.SHOW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, scale, scale, 0.0F, 0, 0, 512, 256, false, false);
         sb.setBlendFunction(770, 771);
      }

      this.hb.render(sb);
   }

   static {
      HITBOX_W = 200.0F * Settings.scale;
      HITBOX_H = 50.0F * Settings.scale;
      UIStrings = CardCrawlGame.languagePack.getUIString("BanCardButton");
      DESCRIPTION = UIStrings.EXTRA_TEXT;
   }
}
