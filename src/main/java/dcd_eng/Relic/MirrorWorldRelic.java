package dcd_eng.Relic;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic.LandingSound;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import dcd_eng.Power.DragrederPower;

public class MirrorWorldRelic extends CustomRelic {
   public static final String ID = "MirrorWorldRelic";

   public MirrorWorldRelic() {
      super("MirrorWorldRelic", new Texture(Gdx.files.internal("img/1024/orb-dark.png")), new Texture(Gdx.files.internal("img/1024/orb-dark.png")), RelicTier.STARTER, LandingSound.MAGICAL);
   }

   public void onMonsterDeath(AbstractMonster m) {
      if (m.currentHealth == 0) {
         AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth * 10 / 100);
         if (AbstractDungeon.player.hasPower("DragrederPower")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DragrederPower(AbstractDungeon.player, 1), 1));
         }
      }

   }

   public String getUpdatedDescription() {
      return this.DESCRIPTIONS[0];
   }
}
