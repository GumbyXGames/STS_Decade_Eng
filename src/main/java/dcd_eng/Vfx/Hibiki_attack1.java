package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Power.HibikiBurnPower;

public class Hibiki_attack1 extends AbstractGameEffect {
   private boolean A1 = true;
   private boolean start = true;
   private int damage;
   private DamageInfo.DamageType damageType;
   private int magicNumber;

   public Hibiki_attack1(int d, DamageInfo.DamageType damageType, int mn) {
      this.damage = d;
      this.magicNumber = mn;
      this.damageType = damageType;
      this.duration = 1.6F;
      this.startingDuration = 1.6F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.6F && this.A1) {
         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, this.magicNumber, AbstractDungeon.player), this.magicNumber));
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new HibikiBurnPower(monster, this.damage, AbstractDungeon.player), this.damage));
            }
         }

         this.A1 = false;
      }

      if (this.duration < 0.0F) {
         if (Decade.cf != 3) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(55);
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(56);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
