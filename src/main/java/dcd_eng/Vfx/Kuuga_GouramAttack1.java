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
import dcd_eng.Characters.Decade;
import dcd_eng.Power.KuugaSpecialPower;

public class Kuuga_GouramAttack1 extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 current;
   private Vector2 Tstart;

   public Kuuga_GouramAttack1(AbstractCreature source, AbstractCreature target, int damage) {
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
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(137);
         AbstractDungeon.player.hideHealthBar();
         AbstractDungeon.player.flipHorizontal = false;
         if (this.Tstart.x < this.source.drawX) {
            AbstractCreature var10000 = this.target;
            var10000.drawX += (this.source.drawX - this.Tstart.x) * 2.0F;
         }

         if (this.Tstart.y > this.source.drawY) {
            this.target.drawY = this.source.drawY;
         }
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 0.2F && this.duration > this.startingDuration - 0.5F) {
         AbstractCreature var6 = this.source;
         var6.drawY += 1500.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var7 = this.current;
         var7.y += 1500.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         AbstractCreature var8 = this.source;
         var8.drawX -= 3000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var9 = this.current;
         var9.x -= 3000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.stage == 2) {
         AbstractCreature var10 = this.source;
         var10.drawX = (float)((double)var10.drawX + (double)(this.target.hb.cX - this.current.x) / 0.4 * (double)Gdx.graphics.getDeltaTime());
         if (this.source.drawX + this.source.hb_w * 2.0F > this.target.drawX) {
            var10 = this.target;
            var10.drawX = (float)((double)var10.drawX + (double)(this.target.hb.cX - this.current.x) / 0.2 * (double)Gdx.graphics.getDeltaTime());
         }
      }

      if (this.duration < this.startingDuration - 0.5F && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(138);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.5F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(140);
         CardCrawlGame.sound.playA("motorbike_sound", 0.0F);
         this.source.drawY = this.start.y;
         ++this.stage;
      } else if (this.stage == 2) {
         if (this.source.drawX > (float)Settings.M_W + this.source.hb_w) {
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
      } else if (this.stage == 3) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_GouramAttack2(this.source, this.target, this.start.x, this.start.y, this.Tstart.x, this.Tstart.y)));
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
