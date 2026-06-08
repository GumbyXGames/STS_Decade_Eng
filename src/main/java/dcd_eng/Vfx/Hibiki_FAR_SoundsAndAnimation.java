package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import dcd_eng.Characters.Decade;
import dcd_eng.Helper.SpecialTaikoEffects;
import dcd_eng.Patches.AbstractSummonedAnimation;
import dcd_eng.Patches.HibikiTaikoKeyEvent;

public class Hibiki_FAR_SoundsAndAnimation extends AbstractGameEffect {
   private boolean FAR0Start = true;
   private boolean FAR1Start = true;
   private boolean FAR2Start = true;
   private boolean FAR3Start = true;
   private boolean FAR = true;
   private int damage;
   private DamageInfo.DamageType damageType;

   public Hibiki_FAR_SoundsAndAnimation(int d, DamageInfo.DamageType damageType) {
      this.damage = d;
      this.damageType = damageType;
      this.duration = 5.0F;
      this.startingDuration = 5.0F;
      AbstractDungeon.player.flipHorizontal = false;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 4.5F && this.FAR) {
         CardCrawlGame.sound.playA("FAR_HIBIKI", 0.0F);
         this.FAR = false;
      }

      if (this.duration < 4.0F && this.FAR0Start) {
         if (!DCDmod.AnimationTrigger) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(52);
            Decade.state.setAnimation(0, "prepare", true);
            String TAIKO_ATLAS2 = "img/char/DCD_Animation/hibiki/taiko_p.atlas";
            String TAIKO_JSON2 = "img/char/DCD_Animation/hibiki/taiko_p_taiko.json";
            new AbstractSummonedAnimation("TAIKO2", TAIKO_ATLAS2, TAIKO_JSON2, 0.8F, AbstractDungeon.player.drawX + 50.0F, AbstractDungeon.player.drawY, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F);
            VfxController.far_effect_S = AbstractSummonedAnimation.getAnimation("TAIKO2");
            VfxController.far_effect_S.setMovable(false);
            VfxController.far_effect_S.state.setAnimation(0, "taiko", true);
         }

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
            }
         }

         this.FAR0Start = false;
      }

      if (this.duration < 3.0F && this.FAR1Start) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
            }
         }

         this.FAR1Start = false;
      }

      if (this.duration < 2.0F && this.FAR2Start) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
            }
         }

         this.FAR2Start = false;
      }

      if (this.duration < 1.0F && this.FAR3Start) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
            }
         }

         this.FAR3Start = false;
      }

      if (this.duration < 0.0F) {
         AbstractSummonedAnimation.clear("TAIKO2");

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
            }
         }

         Decade Decade = (Decade)AbstractDungeon.player;
         if (TurnTimer.BattleEnd) {
            Decade.Trickster(179);
         } else {
            Decade.Trickster(53);
         }

         if (!DCDmod.AnimationTrigger && SpecialTaikoEffects.a != 4) {
            SpecialTaikoEffects.a = 3;
            SpecialTaikoEffects.update();
         }

         HibikiTaikoKeyEvent.ComboPoint -= 9;
         HibikiTaikoKeyEvent.FeverOut(false);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.playTempBGM("hibiki_BGM.ogg");
         }

         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.far_effect_S = null;
   }
}
