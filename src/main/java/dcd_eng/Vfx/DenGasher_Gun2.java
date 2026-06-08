package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class DenGasher_Gun2 extends AbstractGameEffect {
   private final AbstractMonster m;
   private final int damage;
   private int stage;

   public DenGasher_Gun2(AbstractMonster m, int d) {
      this.duration = 2.73F;
      this.startingDuration = 2.73F;
      this.m = m;
      this.damage = d;
      this.stage = 0;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      AbstractPlayer p = AbstractDungeon.player;
      if (this.duration < this.startingDuration && this.stage == 0) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(173);

         for(int i = 0; i < 3; ++i) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.1F && this.stage == 1) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.4F && this.stage == 2) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.6F && this.stage == 3) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         ++this.stage;
      } else if (this.duration < this.startingDuration - 1.87F && this.stage == 4) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         ++this.stage;
      } else if (this.duration < this.startingDuration - 2.17F && this.stage == 5) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(66);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
