package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.FaizAnimationAction;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Power.XuanyunPower;

public class Faiz_SparkleCut extends AbstractGameEffect {
   private int stage = 0;
   private AbstractCreature m;
   private int damage;
   private Vector2 Tstart;

   public Faiz_SparkleCut(float x, float y, AbstractCreature m, int d) {
      this.damage = d;
      this.m = m;
      this.duration = 4.0F;
      this.startingDuration = 4.0F;
      if (!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
         String FAIZ_ATLAS = "img/char/DCD_Animation/faiz/faiz_FAR3.atlas";
         String FAIZ_JSON1 = "img/char/DCD_Animation/faiz/faiz_FAR3.json";
         new AbstractAnimation("FAIZ_FAR", FAIZ_ATLAS, FAIZ_JSON1, 0.8F, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
         this.Tstart = new Vector2(this.m.drawX, this.m.drawY);
      }

   }

   public void update() {
      if (!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
         if (this.duration == this.startingDuration) {
            if (this.Tstart.x < AbstractDungeon.player.drawX) {
               AbstractCreature var10000 = this.m;
               var10000.drawX += (AbstractDungeon.player.drawX - this.Tstart.x) * 2.0F;
            }

            if (this.Tstart.y > AbstractDungeon.player.drawY) {
               this.m.drawY = AbstractDungeon.player.drawY;
            }
         }

         VfxController.far_effect2_A = AbstractAnimation.getAnimation("FAIZ_FAR");
         if (VfxController.far_effect2_A != null) {
            VfxController.far_effect2_A.setMovable(false);
         }

         this.duration -= Gdx.graphics.getDeltaTime();
         if (this.duration < 3.5F && this.stage == 0) {
            assert VfxController.far_effect2_A != null;

            AbstractAnimation.changeAnimation(VfxController.far_effect2_A, FaizAnimationAction.faiz_sparklecut);
            VfxController.far_effect2_A.state.setAnimation(0, "sparklecut", false);
            ++this.stage;
         }

         if (this.duration < 2.9F && this.stage == 1) {
            CardCrawlGame.sound.playA("faiz_sword", 0.0F);
            ++this.stage;
         }

         if (this.duration < 2.75F && this.stage == 2) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, this.m, new XuanyunPower(this.m), 1));
            ++this.stage;
         }

         if (this.duration < 2.4F && this.stage == 3) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, 9, DamageType.NORMAL), AttackEffect.SMASH));
            ++this.stage;
         }

         if (this.duration < 2.3F && this.stage == 4) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, 9, DamageType.NORMAL), AttackEffect.SMASH));
            ++this.stage;
         }

         if (this.duration < 1.9F && this.stage == 5) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AttackEffect.SMASH));
            ++this.stage;
         }

         if (this.duration < 1.2F) {
            AbstractAnimation.clear("FAIZ_FAR");
            Decade Decade = (Decade)AbstractDungeon.player;
            if (TurnTimer.BattleEnd) {
               Decade.Trickster(179);
            } else {
               Decade.Trickster(34);
            }

            AbstractDungeon.player.showHealthBar();
            this.m.drawX = this.Tstart.x;
            this.m.drawY = this.Tstart.y;
            this.dispose();
            this.isDone = true;
         }
      } else {
         this.duration -= Gdx.graphics.getDeltaTime();
         if (this.duration < 2.9F) {
            CardCrawlGame.sound.playA("faiz_sword", 0.0F);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, this.m, new XuanyunPower(this.m), 1));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AttackEffect.SMASH));
            this.dispose();
            this.isDone = true;
         }
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect2_A = null;
   }
}
