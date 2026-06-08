package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Power.RideBookerAttackPower;

public class RiderBooker_attack extends AbstractGameEffect {
   private final int damage;
   private final DamageInfo.DamageType Type;
   private final AbstractMonster m;
   private boolean start = true;
   private boolean attack = true;
   private AbstractCard card;

   public RiderBooker_attack(AbstractMonster m, int x, DamageInfo.DamageType Type, AbstractCard c) {
      this.damage = x;
      this.Type = Type;
      this.m = m;
      this.duration = 0.92F;
      this.startingDuration = 0.92F;
      this.color = Color.WHITE.cpy();
      this.card = c;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < this.startingDuration && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(78);
         this.start = false;
      }

      if ((double)this.duration < 0.2 && this.attack) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, this.damage, this.Type), AttackEffect.BLUNT_HEAVY));
         this.attack = false;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(1);
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RideBookerAttackPower(AbstractDungeon.player, 1, this.card), 1));
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
