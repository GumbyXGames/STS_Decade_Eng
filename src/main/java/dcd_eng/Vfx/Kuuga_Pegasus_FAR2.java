package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Pegasus_FAR2 extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 current;
   private Vector2 Tstart;

   public Kuuga_Pegasus_FAR2(AbstractCreature source, AbstractCreature target, int damage) {
      this.duration = 4.67F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.damage = damage;
      this.Tstart = new Vector2(target.drawX, target.drawY);
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

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(113);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.duration > this.startingDuration - 0.2F) {
         AbstractCreature var16 = this.source;
         var16.drawY += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var17 = this.current;
         var17.y += 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 2.18F && this.duration > this.startingDuration - 2.38F) {
         AbstractCreature var18 = this.source;
         var18.drawY -= 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         Vector2 var19 = this.current;
         var19.y -= 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 0.2F && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(134);
         CardCrawlGame.sound.playA("gouram_sound", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.7F && this.stage == 1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(135);
         CardCrawlGame.sound.playA("pegasus_charge", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.93F && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(136);
         CardCrawlGame.sound.playA("pegasus_attack", 0.0F);

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS)));
            }
         }

         String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked1.atlas";
         String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked1.json";
         new AbstractAnimation("Kuuga_Pegasus_MonsterAttacked2", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.4F, this.target.drawX, this.target.drawY + this.target.hb_h / 2.0F, this.target.hb_w, this.target.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("Kuuga_Pegasus_MonsterAttacked2");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "attacked", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 3.07F && this.stage == 3) {
         AbstractAnimation.clear("Kuuga_Pegasus_MonsterAttacked2");
         String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked2.atlas";
         String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/FAR-R/pegasus_far-r_attacked2.json";
         new AbstractAnimation("Kuuga_Pegasus_MonsterAttacked2", KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.4F, this.target.drawX, this.target.drawY + this.target.hb_h / 2.0F, this.target.hb_w, this.target.hb_h, 1.0F);
         VfxController.far_effect2_A = AbstractAnimation.getAnimation("Kuuga_Pegasus_MonsterAttacked2");
         VfxController.far_effect2_A.setMovable(false);
         VfxController.far_effect2_A.state.setAnimation(0, "attacked", false);

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS)));
            }
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 3.87F && this.stage == 4) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS)));
            }
         }

         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("Kuuga_Pegasus_MonsterAttacked2");
         AbstractDungeon.effectsQueue.add(new Kuuga_FAR_Background(true, false));
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         AbstractDungeon.player.showHealthBar();
         if (!DCDmod.BGMTrigger) {
            TurnTimer.StopBGM(false);
            CardCrawlGame.music.playTempBGM("kuuga_OP1.ogg");
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
      VfxController.far_effect2_A = null;
   }
}
