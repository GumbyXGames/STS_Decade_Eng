package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.DCDmod;
import dcd_eng.Actions.RemoveKamenRideAction;
import dcd_eng.Power.KamenRideDecadePower;

public class Agito_FAR_sounds extends AbstractGameEffect {
   private boolean sounds = true;

   public Agito_FAR_sounds() {
      this.duration = 4.0F;
      this.startingDuration = 4.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 1.0F && this.sounds) {
         CardCrawlGame.sound.playA("FAR_AGITO", 0.0F);
         if (!DCDmod.BGMTrigger) {
            CardCrawlGame.music.fadeOutBGM();
            CardCrawlGame.music.playTempBGM("agito_BGM1.ogg");
            AbstractDungeon.actionManager.addToTop(new VFXAction(new FAR_SoundTimer("Agito", true), 0.0F));
         }

         this.sounds = false;
      }

      if (this.duration < 0.0F) {
         if (AbstractDungeon.player.hasPower("AgitoFlamePower") && !AbstractDungeon.player.hasPower("AgitoStormPower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_flame(), 0.0F));
         } else if (AbstractDungeon.player.hasPower("AgitoStormPower") && !AbstractDungeon.player.hasPower("AgitoFlamePower")) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new Agito_storm(), 0.0F));
         } else if (AbstractDungeon.player.hasPower("AgitoFlamePower") && AbstractDungeon.player.hasPower("AgitoStormPower")) {
            CardCrawlGame.sound.playA("driversounds", 0.0F);
            CardCrawlGame.sound.playA("test1", 0.0F);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamenRideDecadePower(AbstractDungeon.player), 1));
            AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(AbstractDungeon.player, AbstractDungeon.player));
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
