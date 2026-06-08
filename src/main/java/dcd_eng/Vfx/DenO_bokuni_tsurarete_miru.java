package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.DenORodAction;
import dcd_eng.Characters.Decade;

public class DenO_bokuni_tsurarete_miru extends AbstractGameEffect {
   private boolean start = true;
   private boolean A1 = true;

   public DenO_bokuni_tsurarete_miru() {
      this.duration = 2.5F;
      this.startingDuration = 2.5F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      AbstractPlayer p = AbstractDungeon.player;
      if (this.duration < 1.0F && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(68);
         CardCrawlGame.sound.playA("deno_bokuni_tsurarete_miru", 0.0F);
         this.start = false;

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToBottom(new DenORodAction(monster));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
      }

      if (this.duration < 0.0F) {
         this.isDone = true;
         if (Decade.cf != 3) {
            Decade Decade = (Decade)AbstractDungeon.player;
            Decade.Trickster(66);
         }
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
