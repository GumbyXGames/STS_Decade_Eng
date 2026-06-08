package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_FAR_R_kick2 extends AbstractGameEffect {
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 Tstart;
   private final int damage;
   private int stage;
   private AbstractAnimation kuuga_far_kick = null;

   Kuuga_FAR_R_kick2(AbstractCreature source, AbstractCreature target, int x, float drawX, float drawY, float tsX, float tsY) {
      this.duration = 2.0F;
      this.startingDuration = 2.0F;
      this.source = source;
      this.target = target;
      this.damage = x;
      this.start = new Vector2(drawX, drawY);
      this.stage = 0;
      this.Tstart = new Vector2(tsX, tsY);
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R6.atlas";
         String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R6.json";
         new AbstractAnimation("kuuga_far_kick", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         this.kuuga_far_kick = AbstractAnimation.getAnimation("kuuga_far_kick");
         this.kuuga_far_kick.setMovable(false);
         this.kuuga_far_kick.state.setAnimation(0, "FAR6", false);
         AbstractDungeon.effectsQueue.add(new Kuuga_SpecialPower(this.target));
         CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         AbstractCreature var10000 = this.source;
         var10000.drawX -= 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
         AbstractAnimation var6 = this.kuuga_far_kick;
         var6.drawX -= 1000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
      }

      if (this.duration < this.startingDuration - 0.37F && this.stage == 0) {
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.96F && this.stage == 1) {
         AbstractDungeon.effectsQueue.add(new Kuuga_SpecialPower(this.target));
         AbstractAnimation.clear("kuuga_far_kick");
         String KUUGA_KICK_ATLAS = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R7.atlas";
         String KUUGA_KICK_JSON = "img/char/DCD_Animation/kuuga/FAR-R/Kuuga_FAR-R7.json";
         new AbstractAnimation("kuuga_far_kick", KUUGA_KICK_ATLAS, KUUGA_KICK_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         this.kuuga_far_kick = AbstractAnimation.getAnimation("kuuga_far_kick");
         this.kuuga_far_kick.setMovable(false);
         this.kuuga_far_kick.state.setAnimation(0, "FAR7", false);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.71F && this.stage == 2) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target, true)));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KuugaSpecialPower"));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractDungeon.effectsQueue.add(new Kuuga_FAR_Background(true, false));
         AbstractAnimation.clear("kuuga_far_kick");
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
         this.source.drawX = this.start.x;
         this.source.drawY = this.start.y;
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
      this.kuuga_far_kick = null;
   }
}
