package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_MonsterAttacked extends AbstractGameEffect {
   private final String id;
   private boolean start = true;
   private AbstractCreature target;
   public static int x;
   private static AbstractAnimation kuuga_attacked = null;

   public Kuuga_MonsterAttacked(AbstractCreature target) {
      this.duration = 0.58F;
      this.startingDuration = 0.58F;
      this.target = target;
      this.id = "kuuga_attacked" + x;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         ++x;
         String KUUGA_ATTACKED_ATLAS;
         String KUUGA_ATTACKED_JSON;
         if (!AbstractDungeon.player.hasPower("KuugaDragonPower") && !AbstractDungeon.player.hasPower("RisingDragonPower")) {
            if (!AbstractDungeon.player.hasPower("KuugaTitanPower") && !AbstractDungeon.player.hasPower("RisingTitanPower")) {
               if (!AbstractDungeon.player.hasPower("KuugaPegasusPower") && !AbstractDungeon.player.hasPower("RisingPegasusPower")) {
                  KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/kuuga_monsterattacked.atlas";
                  KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/kuuga_monsterattacked.json";
               } else {
                  KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/pegasus/kuuga_monsterattacked4.atlas";
                  KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/pegasus/kuuga_monsterattacked4.json";
               }
            } else {
               KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/titan/kuuga_monsterattacked3.atlas";
               KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/titan/kuuga_monsterattacked3.json";
            }
         } else {
            KUUGA_ATTACKED_ATLAS = "img/char/DCD_Animation/kuuga/dragon/kuuga_monsterattacked2.atlas";
            KUUGA_ATTACKED_JSON = "img/char/DCD_Animation/kuuga/dragon/kuuga_monsterattacked2.json";
         }

         new AbstractAnimation(this.id, KUUGA_ATTACKED_ATLAS, KUUGA_ATTACKED_JSON, 0.8F, this.target.drawX, this.target.drawY + this.target.hb_h / 2.0F, this.target.hb_w, this.target.hb_h, 1.0F);
         kuuga_attacked = AbstractAnimation.getAnimation(this.id);
         kuuga_attacked.setMovable(false);
         kuuga_attacked.state.setAnimation(0, "attacked", false);
         CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
         this.start = false;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear(this.id);
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      kuuga_attacked = null;
   }
}
