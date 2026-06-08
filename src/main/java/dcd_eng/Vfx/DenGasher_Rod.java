package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Power.DenORodSpecialPower;

public class DenGasher_Rod extends AbstractGameEffect {
   private final int d;
   private final AbstractMonster m;
   private int stage;

   public DenGasher_Rod(AbstractMonster m, int damage) {
      this.duration = 1.0F;
      this.startingDuration = 1.0F;
      this.d = damage;
      this.m = m;
      this.stage = 0;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      AbstractPlayer p = AbstractDungeon.player;
      if (this.duration < this.startingDuration && this.stage == 0) {
         if (this.m.intent == Intent.ATTACK || this.m.intent == Intent.ATTACK_BUFF || this.m.intent == Intent.ATTACK_DEBUFF || this.m.intent == Intent.ATTACK_DEFEND) {
            Decade Decade = (Decade)p;
            Decade.Trickster(169);
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.3F && this.stage == 1) {
         if (this.m.intent == Intent.ATTACK || this.m.intent == Intent.ATTACK_BUFF || this.m.intent == Intent.ATTACK_DEBUFF || this.m.intent == Intent.ATTACK_DEFEND) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(p, this.d, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         }

         ++this.stage;
      } else if (this.duration < this.startingDuration - 0.75F && this.stage == 2) {
         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DenORodSpecialPower(p)));
         ++this.stage;
      }

      if (this.duration < 0.0F) {
         Decade Decade = (Decade)p;
         Decade.Trickster(66);
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
