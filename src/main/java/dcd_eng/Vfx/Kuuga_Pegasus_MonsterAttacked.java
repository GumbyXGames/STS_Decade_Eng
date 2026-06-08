package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Patches.AbstractAnimation;

public class Kuuga_Pegasus_MonsterAttacked extends AbstractGameEffect {
   private final String id;
   private final int damage;
   private int stage;
   private AbstractCreature target;

   Kuuga_Pegasus_MonsterAttacked(AbstractCreature target, int damage, String id) {
      this.duration = 1.33F;
      this.startingDuration = 1.33F;
      this.target = target;
      this.id = id;
      this.stage = 0;
      this.damage = damage;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.66F && this.stage == 1) {
         CardCrawlGame.sound.playA("kuuga_attack", 0.0F);
         int x = 2;
         if (AbstractDungeon.player.hasPower("RisingPegasusPower")) {
            ++x;
         }

         for(int i = 0; i < x; ++i) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS)));
         }

         ++this.stage;
      }

      if (this.duration < 0.0F) {
         AbstractAnimation.clear(this.id);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
