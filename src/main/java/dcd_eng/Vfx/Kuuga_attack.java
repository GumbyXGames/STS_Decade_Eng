package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_attack extends AbstractGameEffect {
   private boolean start = true;
   private AbstractCreature target;

   public Kuuga_attack(AbstractCreature target) {
      this.duration = 0.33F;
      this.startingDuration = 0.33F;
      this.target = target;
   }

   public void update() {
      if (this.duration == this.startingDuration) {
         AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(86);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(4);
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
