package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_DragonAttack extends AbstractGameEffect {
   private final int damage;
   private final boolean isFAR;
   private final float x;
   private final float y;
   private AbstractCreature target;
   private int combo;
   private int n = 2;
   private AbstractPlayer p;
   private Vector2 Tstart;

   public Kuuga_DragonAttack(AbstractCreature target, int damage, int combo, boolean isFAR, float x, float y, float tsX, float tsY) {
      this.duration = 0.0F;
      this.startingDuration = 0.0F;
      this.target = target;
      this.combo = combo;
      this.damage = damage;
      this.isFAR = isFAR;
      this.x = x;
      this.y = y;
      this.p = AbstractDungeon.player;
      this.Tstart = new Vector2(tsX, tsY);
   }

   public void update() {
      if (this.combo == 1 && this.isFAR) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.p, "KuugaSpecialPower"));
      }

      if (this.combo > 0 && this.duration <= 0.0F) {
         if (this.n == 2) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(109);
            if (this.isFAR) {
               AbstractAnimation.clear("dragon_FAR");
               String DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.atlas";
               String DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.json";
               new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
               VfxController.far_effect3_A = AbstractAnimation.getAnimation("dragon_FAR");
               VfxController.far_effect3_A.setMovable(false);
               VfxController.far_effect3_A.state.setAnimation(0, "attack", false);
            }

            CardCrawlGame.sound.playA("attack_slash", 0.0F);
            AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL)));
            this.duration += 0.33F;
            --this.n;
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(110);
            if (this.isFAR) {
               AbstractAnimation.clear("dragon_FAR");
               String DRAGON_FAR_ATLAS = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.atlas";
               String DRAGON_FAR_JSON = "img/char/DCD_Animation/kuuga/dragon/dragon_attack1.json";
               new AbstractAnimation("dragon_FAR", DRAGON_FAR_ATLAS, DRAGON_FAR_JSON, 0.8F, this.p.drawX, this.p.drawY, this.p.hb_w, this.p.hb_h, 1.0F);
               VfxController.far_effect3_A = AbstractAnimation.getAnimation("dragon_FAR");
               VfxController.far_effect3_A.setMovable(false);
               VfxController.far_effect3_A.state.setAnimation(0, "attack", false);
            }

            CardCrawlGame.sound.playA("attack_slash", 0.0F);
            AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL)));
            this.duration += 0.33F;
            this.n = 2;
         }

         --this.combo;
      } else if (this.combo == 0 && this.duration < 0.0F) {
         if (this.isFAR) {
            AbstractAnimation.clear("dragon_FAR");
            AbstractDungeon.effectsQueue.add(new Kuuga_FAR_Background(true, false));
            AbstractDungeon.player.drawX = this.x;
            AbstractDungeon.player.drawY = this.y;
            AbstractDungeon.player.showHealthBar();
            this.target.drawX = this.Tstart.x;
            this.target.drawY = this.Tstart.y;
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
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
      VfxController.far_effect3_A = null;
   }
}
