package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.DecadeSlashAction;
import dcd_eng.Characters.Decade;
import dcd_eng.Patches.AbstractAnimation;

public class Decade_Slash2 extends AbstractGameEffect {
   private boolean start1 = true;
   private AbstractCreature source;
   private AbstractCreature target;
   private Vector2 start;
   private Vector2 Tstart;
   private final int damage;
   private int stage;

   Decade_Slash2(AbstractCreature source, AbstractCreature target, int x, float drawX, float drawY, float tsX, float tsY) {
      this.duration = 2.9F;
      this.startingDuration = 2.9F;
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
         Decade.Trickster(84);
         String DECADE_SLASH_ATLAS = "img/char/DCD_Animation/decade/decade_slash3.atlas";
         String DECADE_SLASH_JSON = "img/char/DCD_Animation/decade/decade_slash3.json";
         new AbstractAnimation("decade_slash", DECADE_SLASH_ATLAS, DECADE_SLASH_JSON, 0.8F, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0F);
         VfxController.Rider_far_kick_A = AbstractAnimation.getAnimation("decade_slash");
         VfxController.Rider_far_kick_A.setMovable(false);
         VfxController.Rider_far_kick_A.state.setAnimation(0, "slash", false);
         this.start1 = false;
         ++this.stage;
      }

      if (this.duration < this.startingDuration - 0.27F && this.stage == 1) {
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.77F && this.stage == 2) {
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.39F && this.stage == 3) {
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.56F && this.stage == 4) {
         CardCrawlGame.sound.playA("attack_slash", 0.0F);
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.18F && this.stage == 5) {
         for(int i = 0; i < 5; ++i) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(this.source, this.damage, DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
         }

         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear("decade_slash");
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         this.source.drawX = this.start.x;
         this.source.drawY = this.start.y;
         this.target.drawX = this.Tstart.x;
         this.target.drawY = this.Tstart.y;
         AbstractDungeon.actionManager.addToBottom(new DecadeSlashAction(this.target));
         AbstractDungeon.player.showHealthBar();
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      VfxController.Rider_far_kick_A = null;
   }
}
