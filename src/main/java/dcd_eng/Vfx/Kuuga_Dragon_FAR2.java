package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Dragon_FAR2 extends AbstractGameEffect {
   private final int damage;
   private final float x;
   private final float y;
   private final AbstractCreature target;
   private int combo;
   private final Vector2 Tstart;
   private final AbstractPlayer p;

   Kuuga_Dragon_FAR2(AbstractCreature target, int damage, int combo, float x, float y, float tsX, float tsY) {
      this.duration = 0.0F;
      this.startingDuration = 0.0F;
      this.target = target;
      this.damage = damage;
      this.combo = combo;
      this.x = x;
      this.y = y;
      this.p = AbstractDungeon.player;
      this.Tstart = new Vector2(tsX, tsY);
   }

   public void update() {
      if (this.combo > 0 && this.duration <= 0.0F) {
         Decade Decade = (Decade)this.p;
         if (this.combo % 2 == 0) {
            Decade.Trickster(110);
            if (AbstractAnimation.getAnimation("dragon_FAR1") == null) {
               String DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack2.atlas";
               String DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack2.json";
               new AbstractAnimation("dragon_FAR1", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
            }

            VfxController.far_effect2_A = AbstractAnimation.getAnimation("dragon_FAR1");
         } else {
            Decade.Trickster(109);
            if (AbstractAnimation.getAnimation("dragon_FAR2") == null) {
               String DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.atlas";
               String DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.json";
               new AbstractAnimation("dragon_FAR2", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
            }

            VfxController.far_effect2_A = AbstractAnimation.getAnimation("dragon_FAR2");
         }

         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "attack", false);
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.p, this.damage, DamageType.NORMAL)));
         this.duration += 0.33F;
         --this.combo;
      } else if (this.combo == 0 && this.duration < 0.0F) {
         Decade Decade = (Decade)this.p;
         Decade.Trickster(111);
         if (AbstractAnimation.getAnimation("dragon_FAR3") == null) {
            String DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack3.atlas";
            String DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack3.json";
            new AbstractAnimation("dragon_FAR3", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         }

         VfxController.far_effect2_A = AbstractAnimation.getAnimation("dragon_FAR3");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "attack", false);
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.p, this.damage, DamageType.NORMAL)));
         this.duration += 0.4F;
         --this.combo;
      } else if (this.combo == -1 && this.duration < 0.0F) {
         Decade Decade = (Decade)this.p;
         Decade.Trickster(112);
         if (AbstractAnimation.getAnimation("dragon_FAR4") == null) {
            String DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_finalattack.atlas";
            String DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_finalattack.json";
            new AbstractAnimation("dragon_FAR4", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
         }

         VfxController.far_effect2_A = AbstractAnimation.getAnimation("dragon_FAR4");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "attack", false);
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.p, this.damage, DamageType.NORMAL)));
         this.duration += 0.87F;
         --this.combo;
      } else if (this.combo == -2 && this.duration > 0.62F) {
         AbstractCreature var10000 = this.target;
         var10000.drawX += 1200.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      } else if (this.combo == -2) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_SpecialPower(this.target), 0.0F));
         CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
         --this.combo;
      } else if (this.combo == -3 && this.duration < 0.0F) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target, true)));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.p, this.damage, DamageType.NORMAL)));
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.p, "KuugaSpecialPower"));
         ++this.duration;
         --this.combo;
      } else if (this.combo == -4 && this.duration < 0.0F) {
         AbstractDungeon.effectsQueue.add(new Kuuga_FAR_Background(true, false));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         this.p.drawX = this.x;
         this.p.drawY = this.y;
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         AbstractDungeon.player.showHealthBar();
         if (!DCDmod.BGMTrigger) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.music.playTempBGM("kuuga_OP1.ogg");
         }

         this.dispose();
         this.isDone = true;
      }

      if (this.duration > 0.0F) {
         this.duration -= Gdx.graphics.getDeltaTime();
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect2_A = null;
   }
}
