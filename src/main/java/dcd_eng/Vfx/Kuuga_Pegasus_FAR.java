package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Pegasus_FAR extends AbstractGameEffect {
   private final int damage;
   private int stage;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 current;
   private Vector2 Tstart;
   private AbstractAnimation kuuga_pegasus_far = null;
   private String id;
   private static int x = 0;

   public Kuuga_Pegasus_FAR(AbstractCreature source, AbstractCreature target, int damage) {
      this.duration = 5.0F;
      this.startingDuration = this.duration;
      this.stage = 0;
      this.source = source;
      this.target = target;
      this.current = new Vector2(source.hb.cX, source.hb.cY - source.hb.height / 2.0F + target.hb.height / 2.0F);
      this.damage = damage;
      this.id = "kuuga_attacked" + x;
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
         Decade.Trickster(133);
         CardCrawlGame.sound.playA("pegasus_charge", 0.0F);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration - 3.23F && this.stage == 0) {
         String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/pegasus_bullet.atlas";
         String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/pegasus/pegasus_bullet.json";
         new AbstractAnimation("kuuga_pegasus_far", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8F, this.source.drawX + this.source.hb_w / 2.0F, this.source.drawY + this.source.hb_h / 2.0F, this.source.hb_w, this.source.hb_h, 1.0F);
         this.kuuga_pegasus_far = AbstractAnimation.getAnimation("kuuga_pegasus_far");
         this.kuuga_pegasus_far.setMovable(false);
         this.kuuga_pegasus_far.state.setAnimation(0, "bullet", true);
         CardCrawlGame.sound.playA("pegasus_attack", 0.0F);
         ++this.stage;
      } else if (this.stage == 1) {
         if (this.kuuga_pegasus_far.drawY > this.target.drawY) {
            AbstractAnimation var10 = this.kuuga_pegasus_far;
            var10.drawY = (float)((double)var10.drawY + (double)(this.target.hb.cY - this.current.y) / 0.1 * (double)Gdx.graphics.getDeltaTime());
         }

         if (this.kuuga_pegasus_far.drawX < this.target.drawX) {
            AbstractAnimation var11 = this.kuuga_pegasus_far;
            var11.drawX = (float)((double)var11.drawX + (double)(this.target.hb.cX - this.current.x) / 0.1 * (double)Gdx.graphics.getDeltaTime());
         } else {
            ++this.stage;
         }
      } else if (this.stage == 2) {
         AbstractAnimation.clear("kuuga_pegasus_far");

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               ++x;
               this.id = "kuuga_attacked" + x;
               String KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/pegasus_far_effect.atlas";
               String KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/pegasus_far_effect.json";
               new AbstractAnimation(this.id, KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8F, monster.drawX, monster.drawY + monster.hb_h / 2.0F, monster.hb_w, monster.hb_h, 1.0F);
               AbstractAnimation kuuga_attacked = AbstractAnimation.getAnimation(this.id);
               kuuga_attacked.setMovable(false);
               kuuga_attacked.state.setAnimation(0, "effect", false);
               ++this.stage;
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_Pegasus_MonsterAttacked(monster, this.damage, this.id)));
            }
         }

         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractDungeon.effectsQueue.add(new Kuuga_FAR_Background(true, false));
         Decade Decade = (Decade)AbstractDungeon.player;
         if (TurnTimer.BattleEnd) {
            Decade.Trickster(179);
         } else {
            Decade.Trickster(4);
         }

         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("kuuga_OP1.ogg");
         }

         AbstractDungeon.player.showHealthBar();
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      this.kuuga_pegasus_far = null;
   }
}
