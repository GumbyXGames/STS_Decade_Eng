package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Kuuga_TitanAttack extends AbstractGameEffect {
   private final int damage;
   private final int magicNumber;
   private int stage;
   private AbstractCreature target;

   public Kuuga_TitanAttack(AbstractCreature target, int damage, int number) {
      this.duration = 0.75F;
      this.startingDuration = 0.75F;
      this.target = target;
      this.damage = damage;
      this.magicNumber = number;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.stage == 0) {
         if (AbstractDungeon.player.hasPower("RisingTitanPower") && AbstractDungeon.player.hasPower("RisingMightyPower")) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(116);
            AbstractDungeon.effectsQueue.add(new Kuuga_MonsterAttacked(this.target));
            this.duration += 0.75F;
            ++this.stage;
         } else {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(117);
            AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
         }

         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL)));
         ++this.stage;
      } else if (this.duration < this.startingDuration && this.stage == 2) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(117);
         AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL)));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         if (AbstractDungeon.player.hasPower("RisingTitanPower")) {
            for(int i = 0; i < 3; ++i) {
               AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageType.THORNS)));
            }
         } else {
            AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageType.THORNS)));
         }

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
