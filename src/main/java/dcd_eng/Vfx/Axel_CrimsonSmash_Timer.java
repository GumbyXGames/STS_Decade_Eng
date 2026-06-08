package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Helper.SpecialFaizBox;

public class Axel_CrimsonSmash_Timer extends AbstractGameEffect {
   private int damage;

   public Axel_CrimsonSmash_Timer(int d) {
      this.damage = d;
      this.duration = 30.0F;
      this.startingDuration = 30.0F;
   }

   public void update() {
      this.duration -= Gdx.graphics.getDeltaTime();
      if (!SpecialFaizBox.AxelForm) {
         int n = ReturnRandomNumberAction.ReturnRandomNumber();
         if (SpecialFaizBox.PointerPoint != 0) {
            if (n <= 3) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_1A(this.damage), 0.0F));
               --SpecialFaizBox.PointerPoint;
               Axel_CrimsonSmash_1A.A1 = false;
            } else if (n <= 6) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_2A(this.damage), 0.0F));
               --SpecialFaizBox.PointerPoint;
               Axel_CrimsonSmash_2A.A2 = false;
            } else {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_3A(this.damage), 0.0F));
               --SpecialFaizBox.PointerPoint;
               Axel_CrimsonSmash_3A.A3 = false;
            }
         }

         this.isDone = true;
      }

      if (this.duration < 20.0F) {
         int n = ReturnRandomNumberAction.ReturnRandomNumber();
         if (SpecialFaizBox.PointerPoint != 0) {
            if (n <= 3) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_1A(this.damage), 0.0F));
               --SpecialFaizBox.PointerPoint;
               Axel_CrimsonSmash_1A.A1 = false;
            } else if (n <= 6) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_2A(this.damage), 0.0F));
               --SpecialFaizBox.PointerPoint;
               Axel_CrimsonSmash_2A.A2 = false;
            } else {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_3A(this.damage), 0.0F));
               --SpecialFaizBox.PointerPoint;
               Axel_CrimsonSmash_3A.A3 = false;
            }
         }

         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
   }
}
