package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;

public class Decade_Blast extends AbstractGameEffect {
   private final int damage;
   private final DamageInfo.DamageType Type;
   private boolean start = true;
   private boolean attack = true;

   public Decade_Blast(int x, DamageInfo.DamageType Type) {
      this.damage = x;
      this.Type = Type;
      this.duration = 1.4F;
      this.startingDuration = 1.4F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(80);
         this.start = false;
      }

      if (this.duration < 0.9F && this.attack) {
         for(int i = 0; i < 5; ++i) {
            AbstractMonster m1 = AbstractDungeon.getMonsters().getRandomMonster(true);
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m1, new DamageInfo(p, this.damage, this.Type), AttackEffect.SLASH_HORIZONTAL));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m1, p, new WeakPower(m1, 2, false), 2));
         }

         this.attack = false;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
