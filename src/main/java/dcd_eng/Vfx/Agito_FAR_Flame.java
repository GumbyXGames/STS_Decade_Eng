package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractHandAnimation;

public class Agito_FAR_Flame extends AbstractGameEffect {
   private final int damage;
   private final AbstractCreature source;
   private final AbstractCreature target;
   int stage;
   AbstractPlayer p;
   private final Vector2 Tstart;

   public Agito_FAR_Flame(AbstractCreature source, AbstractCreature target, int x) {
      this.p = AbstractDungeon.player;
      this.duration = 4.93F;
      this.startingDuration = 4.93F;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.damage = x;
      this.Tstart = new Vector2(target.drawX, target.drawY);
   }

   public void update() {
      if (this.duration == this.startingDuration && this.stage == 0) {
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }

         Decade Decade = (Decade)this.p;
         Decade.Trickster(215);
         ++this.stage;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 1.3F && this.stage == 1) {
         Decade Decade = (Decade)this.p;
         Decade.Trickster(224);
         Decade_BlackScreen.isBlackScreen = true;
         CardCrawlGame.sound.playA("jump", 0.0F);
         CardCrawlGame.sound.playA("fire", 0.0F);
         String FAR_EFFECT_ATLAS = "img/char/DCD_Animation/agito/far_f/fire_effect.atlas";
         String FAR_EFFECT_JSON = "img/char/DCD_Animation/agito/far_f/fire_effect.json";
         new AbstractHandAnimation("FIRE_EFFECT", FAR_EFFECT_ATLAS, FAR_EFFECT_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.far_effect3_H = AbstractHandAnimation.getAnimation("FIRE_EFFECT");
         VfxController.far_effect3_H.setMovable(false);
         VfxController.far_effect3_H.state.setAnimation(0, "fire", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.73F && this.stage == 2) {
         String AGITO_FAR_ATLAS = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f.atlas";
         String AGITO_FAR_JSON = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f_2.json";
         new AbstractHandAnimation("AGITO_FAR2", AGITO_FAR_ATLAS, AGITO_FAR_JSON, 0.8F, this.target.drawX, this.target.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.Rider_far2_H = AbstractHandAnimation.getAnimation("AGITO_FAR2");
         VfxController.Rider_far2_H.setMovable(false);
         VfxController.Rider_far2_H.state.setAnimation(0, "2", false);
         CardCrawlGame.sound.playA("FireSlash1", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.13F && this.stage == 3) {
         AbstractHandAnimation.clear("FIRE_EFFECT");
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.63F && this.stage == 4) {
         AbstractHandAnimation.clear("AGITO_FAR2");
         ++this.stage;
      } else if (this.duration < this.startingDuration - 3.13F && this.stage == 5) {
         String AGITO_FAR_ATLAS = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f.atlas";
         String AGITO_FAR_JSON = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f_4.json";
         new AbstractHandAnimation("AGITO_FAR2", AGITO_FAR_ATLAS, AGITO_FAR_JSON, 0.8F, this.target.drawX, this.target.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.Rider_far2_H = AbstractHandAnimation.getAnimation("AGITO_FAR2");
         VfxController.Rider_far2_H.setMovable(false);
         VfxController.Rider_far2_H.state.setAnimation(0, "4", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 3.43F && this.stage == 6) {
         AbstractHandAnimation.clear("AGITO_FAR2");
         String AGITO_FAR_ATLAS = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f.atlas";
         String AGITO_FAR_JSON = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f_5.json";
         new AbstractHandAnimation("AGITO_FAR2", AGITO_FAR_ATLAS, AGITO_FAR_JSON, 0.8F, this.target.drawX, this.target.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.Rider_far2_H = AbstractHandAnimation.getAnimation("AGITO_FAR2");
         VfxController.Rider_far2_H.setMovable(false);
         VfxController.Rider_far2_H.state.setAnimation(0, "5", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 3.93F && this.stage == 7) {
         AbstractHandAnimation.clear("AGITO_FAR2");
         String AGITO_FAR_ATLAS = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f.atlas";
         String AGITO_FAR_JSON = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f_6.json";
         new AbstractHandAnimation("AGITO_FAR2", AGITO_FAR_ATLAS, AGITO_FAR_JSON, 0.8F, this.target.drawX, this.target.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.Rider_far2_H = AbstractHandAnimation.getAnimation("AGITO_FAR2");
         VfxController.Rider_far2_H.setMovable(false);
         VfxController.Rider_far2_H.state.setAnimation(0, "6", false);
         CardCrawlGame.sound.playA("FireSlash2", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 4.4300003F && this.stage == 8) {
         AbstractHandAnimation.clear("AGITO_FAR2");
         Decade_BlackScreen.isBlackScreen = false;
         String AGITO_FAR_ATLAS = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f.atlas";
         String AGITO_FAR_JSON = "img/char/DCD_Animation/agito/far_f/Agito_FAR2_f_7.json";
         new AbstractHandAnimation("AGITO_FAR2", AGITO_FAR_ATLAS, AGITO_FAR_JSON, 0.8F, this.target.drawX, this.target.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         VfxController.Rider_far2_H = AbstractHandAnimation.getAnimation("AGITO_FAR2");
         VfxController.Rider_far2_H.setMovable(false);
         VfxController.Rider_far2_H.state.setAnimation(0, "7", false);
         AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.SLASH_DIAGONAL));
         AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractHandAnimation.clear("AGITO_FAR2");
         this.p.showHealthBar();
         if (this.p.hasPower("AgitoPowerPower")) {
            Decade Decade = (Decade)this.p;
            Decade.Trickster(186);
         } else {
            Decade Decade = (Decade)this.p;
            Decade.Trickster(185);
         }

         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Rider_far2_H = null;
      VfxController.far_effect3_H = null;
   }
}
