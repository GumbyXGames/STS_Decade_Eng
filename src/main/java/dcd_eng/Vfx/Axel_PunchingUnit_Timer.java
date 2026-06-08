package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.unique.SwordBoomerangAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.FaizAnimationAction;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractAnimation;

public class Axel_PunchingUnit_Timer extends AbstractGameEffect {
   private float x;
   private float y;
   private int damage;
   private boolean Start = true;
   private boolean Final = false;
   private int n = 0;
   private AbstractAnimation AXEL4 = null;

   public Axel_PunchingUnit_Timer(int d) {
      AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(true);
      if (m != null) {
         this.x = m.drawX;
         this.y = m.drawY;
      }

      this.damage = d;
      this.duration = 30.0F;
      this.startingDuration = 30.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (!SpecialFaizBox.AxelForm && this.Start) {
         String FAIZ_ATLAS = "img/char/DCD_Animation/faiz_Axel/Axel_PunchingUnit.atlas";
         String FAIZ_JSON1 = "img/char/DCD_Animation/faiz_Axel/Axel_PunchingUnit.json";
         this.AXEL4 = new AbstractAnimation(FAIZ_ATLAS, FAIZ_JSON1, 0.8F, this.x, this.y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F, 1.0F);
         this.AXEL4.setMovable(false);
         AbstractAnimation.changeAnimation(this.AXEL4, FaizAnimationAction.axel_FAR_P);
         this.AXEL4.state.setAnimation(0, "PunchingUnit", true);

         for(int i = 0; i < SpecialFaizBox.ShotPoint; ++i) {
            if (this.n != SpecialFaizBox.ShotPoint) {
               ++this.n;
            }
         }

         for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDead && !monster.isDying) {
               AbstractDungeon.actionManager.addToTop(new RemoveAllBlockAction(monster, AbstractDungeon.player));
            }
         }

         AbstractDungeon.actionManager.addToBottom(new SwordBoomerangAction(AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(AbstractDungeon.player, this.damage), SpecialFaizBox.ShotPoint));
         this.Start = false;
         this.Final = true;
      }

      if (this.Final && this.n == SpecialFaizBox.ShotPoint && this.duration < 30.0F - (float)SpecialFaizBox.ShotPoint * 2.0F) {
         this.AXEL4.state.setAnimation(0, "PunchingUnit", false);
         SpecialFaizBox.ShotPoint = 0;
         this.dispose();
         this.isDone = true;
      }

      if (this.duration < 0.0F) {
         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      this.AXEL4 = null;
   }
}
