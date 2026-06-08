package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Power.HibikiBurnPower;

public class Hibiki_attack3 extends AbstractGameEffect {
   private boolean A1 = true;
   private boolean start = true;
   private int damage;
   private DamageInfo.DamageType damageType;
   private int magicNumber;
   private AbstractMonster m;

   public Hibiki_attack3(AbstractMonster m, int d, DamageInfo.DamageType damageType, int mn) {
      this.m = m;
      this.damage = d;
      this.magicNumber = mn;
      this.damageType = damageType;
      this.duration = 1.7F;
      this.startingDuration = 1.7F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 0.9F && this.A1) {
         AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.m, AbstractDungeon.player, "HibikiBurnPower"));
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, this.damage, this.damageType), AttackEffect.FIRE));
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.m, AbstractDungeon.player, new HibikiBurnPower(this.m, this.damage, AbstractDungeon.player), this.damage));
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
         Decade.Trickster(58);
         this.start = false;
      }

   }

   public void dispose() {
   }
}
