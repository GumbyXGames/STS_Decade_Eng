package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;
import dcd_eng.Power.KuugaSpecialPower;

public class Another_GouramAttack1 extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;

   public Another_GouramAttack1(AbstractCreature source, AbstractCreature target, int damage) {
      this.duration = 2.0F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.start = new Vector2(source.drawX, source.drawY);
      this.Tstart = new Vector2(target.drawX, target.drawY);
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.damage = damage;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }

         AbstractDungeon.player.flipHorizontal = false;
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.stage == 0) {
         String ANOTHER_GOURAM_ATTACK_ATLAS = "img/char/DCD_Animation/kuuga/gouram/gouram_attack.atlas";
         String ANOTHER_GOURAM_ATTACK_JSON = "img/char/DCD_Animation/kuuga/gouram/gouram_attack.json";
         new AbstractAnimation("another_gouram_attack", ANOTHER_GOURAM_ATTACK_ATLAS, ANOTHER_GOURAM_ATTACK_JSON, 0.8F, this.source.drawX - this.source.hb_w * 2.0F, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("another_gouram_attack");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "attack", false);
         CardCrawlGame.sound.playA("motorbike_sound", 0.0F);
         ++this.stage;
      }

      if (this.stage == 1) {
         AbstractAnimation var5 = VfxController.far_effect2_A;
         var5.drawX = (float)((double)var5.drawX + (double)(this.target.hb.cX - this.current.x) / 0.4 * (double)Gdx.graphics.getDeltaTime());
         if (VfxController.far_effect2_A.drawX + this.source.hb_w * 2.0F > this.target.drawX) {
            AbstractCreature var6 = this.target;
            var6.drawX = (float)((double)var6.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }
      }

      if (this.stage == 1) {
         if (VfxController.far_effect2_A.drawX > (float)Settings.M_W + this.source.hb_w) {
            ++this.stage;
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Gouram_Boom(this.target)));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
            if (this.source.hasPower("RisingMightyPower")) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new KuugaSpecialPower(this.target, 1), 1));
            }

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source, new KuugaSpecialPower(this.target, 1), 1));

            for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
               if (!monster.isDead && !monster.isDying && monster != this.target) {
                  AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(this.source, this.damage / 2, DamageType.NORMAL)));
                  if (this.source.hasPower("RisingMightyPower")) {
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.source, new KuugaSpecialPower(monster, 1), 1));
                  }

                  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, this.source, new KuugaSpecialPower(monster, 1), 1));
               }
            }
         }
      } else if (this.stage == 2) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Another_GouramAttack2(this.source, this.target, this.start.x, this.start.y, this.Tstart.x, this.Tstart.y)));
         ++this.stage;
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
