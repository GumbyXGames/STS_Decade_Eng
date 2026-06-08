package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Characters.Decade;
import dcd_eng.Power.XuanyunPower;

public class DenO_kotaewa_kiite_nai extends AbstractGameEffect {
   private boolean start = true;
   private boolean A1 = true;

   public DenO_kotaewa_kiite_nai() {
      this.duration = 2.5F;
      this.startingDuration = 2.5F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      AbstractPlayer p = AbstractDungeon.player;
      if (this.duration < 1.0F && this.start) {
         Decade Decade = (Decade)AbstractDungeon.player;
         Decade.Trickster(69);
         CardCrawlGame.sound.playA("deno_kotaewa_kiite_nai", 0.0F);
         this.start = false;

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying && (monster.intent.equals(Intent.ATTACK) || monster.intent.equals(Intent.ATTACK_BUFF) || monster.intent.equals(Intent.ATTACK_DEBUFF) || monster.intent.equals(Intent.ATTACK_DEFEND))) {
               AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, monster, new XuanyunPower(monster)));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
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
