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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Titan_FAR2 extends AbstractGameEffect {
   private boolean start1 = true;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 Tstart;
   private final int damage;
   private int stage;
   private AbstractAnimation titan_far = null;

   Kuuga_Titan_FAR2(AbstractCreature source, AbstractCreature target, int x, float drawX, float drawY, float tsX, float tsY) {
      this.duration = 99.0F;
      this.startingDuration = 99.0F;
      this.source = source;
      this.target = target;
      this.damage = x;
      this.start = new Vector2(drawX, drawY);
      this.stage = 0;
      this.Tstart = new Vector2(tsX, tsY);
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start1) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(121);
         String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3.atlas";
         String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3_FAR2-2.json";
         new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         this.titan_far = AbstractAnimation.getAnimation("titan");
         this.titan_far.setMovable(false);
         this.titan_far.state.setAnimation(0, "FAR2-2", false);
         this.start1 = false;
         ++this.stage;
      }

      if (this.duration < this.startingDuration - 0.45F && this.stage == 1) {
         if (this.source.hasPower("RisingMightyPower")) {
            AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
            ++this.stage;
         } else {
            AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
            ++this.stage;
         }
      } else if (this.duration < this.startingDuration - 0.95F && this.stage == 2) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_SpecialPower(this.target)));
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.41F && this.stage == 3) {
         if (!this.source.hasPower("RisingMightyPower")) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target, true)));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KuugaSpecialPower"));
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.45F && this.stage == 4) {
         if (this.source.hasPower("RisingMightyPower")) {
            AbstractAnimation.clear("titan");
            String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3.atlas";
            String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3_FAR3-2.json";
            new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
            this.titan_far = AbstractAnimation.getAnimation("titan");
            this.titan_far.setMovable(false);
            this.titan_far.state.setAnimation(0, "FAR3-2", false);
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(124);
            ++this.stage;
         } else {
            AbstractAnimation.clear("titan");
            String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3.atlas";
            String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3_FAR3-1.json";
            new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
            this.titan_far = AbstractAnimation.getAnimation("titan");
            this.titan_far.setMovable(false);
            this.titan_far.state.setAnimation(0, "FAR3-1", false);
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(123);
            ++this.stage;
         }
      } else if (this.duration < this.startingDuration - 1.51F && this.stage == 5 && !this.source.hasPower("RisingMightyPower")) {
         AbstractAnimation.clear("titan");
         String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4.atlas";
         String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4_FAR4-1.json";
         new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         this.titan_far = AbstractAnimation.getAnimation("titan");
         this.titan_far.setMovable(false);
         this.titan_far.state.setAnimation(0, "FAR4-1", false);
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(119);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.85F && this.stage == 5 && this.source.hasPower("RisingMightyPower")) {
         AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target, true)));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL)));
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KuugaSpecialPower"));
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.01F && this.stage == 6) {
         if (this.source.hasPower("RisingMightyPower")) {
            AbstractAnimation.clear("titan");
            String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4.atlas";
            String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4_FAR4-2.json";
            new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
            this.titan_far = AbstractAnimation.getAnimation("titan");
            this.titan_far.setMovable(false);
            this.titan_far.state.setAnimation(0, "FAR4-2", false);
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(120);
            ++this.stage;
         } else {
            this.isDone = true;
            AbstractAnimation.clear("titan");
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(4);
            this.source.drawX = this.start.x;
            this.source.drawY = this.start.y;
            AbstractDungeon.player.showHealthBar();
            this.target.drawX = this.Tstart.x;
            this.target.drawY = this.Tstart.y;
         }
      } else if (this.duration < this.startingDuration - 2.51F && this.stage == 7) {
         AbstractAnimation.clear("titan");
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
      this.titan_far = null;
   }
}
