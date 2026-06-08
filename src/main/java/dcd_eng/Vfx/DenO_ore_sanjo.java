package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Power.DenOAwePower;

public class DenO_ore_sanjo extends AbstractGameEffect {
   private boolean start = true;
   private boolean A1 = true;

   public DenO_ore_sanjo() {
      this.duration = 2.3F;
      this.startingDuration = 2.3F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      AbstractPlayer p = AbstractDungeon.player;
      if (this.duration < this.startingDuration - 1.0F && this.start) {
         Decade Decade = (Decade)p;
         Decade.Trickster(167);
         CardCrawlGame.sound.playA("deno_oresanjo", 0.0F);

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, monster, new DenOAwePower(monster, 5)));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
         this.start = false;
      }

      if (this.duration < 0.0F) {
         if (Decade.cf != 3) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(66);
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
      if (this.A1) {
         CardCrawlGame.sound.playA("attackride", 0.0F);
         this.A1 = false;
      }

   }

   public void dispose() {
   }
}
