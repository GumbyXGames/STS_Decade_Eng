package dcd_eng.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcd_eng.Actions.FaizAnimationAction;
import dcd_eng.Actions.ReturnRandomNumberAction;
import dcd_eng.Helper.SpecialFaizBox;
import dcd_eng.Patches.AbstractAnimation;

public class Axel_CrimsonSmash_1A extends AbstractGameEffect {
   public static boolean A1 = true;
   private float x;
   private float y;
   private boolean Attack = true;
   private boolean Start = true;
   private boolean Second = true;
   private final AbstractCreature m;
   private final int damage;
   private boolean Animation = true;
   private AbstractAnimation AXEL1;

   Axel_CrimsonSmash_1A(int d) {
      this.damage = d;
      this.m = AbstractDungeon.getMonsters().getRandomMonster(true);
      if (this.m != null) {
         this.x = this.m.drawX;
         this.y = this.m.drawY;
      }

      this.duration = 2.0F;
      this.startingDuration = 2.0F;
   }

   public void update() {
      if (this.Animation) {
         String AXEL_ATLAS = "img/char/DCD_Animation/faiz_Axel/Axel_CrimsonSmash.atlas";
         String AXEL_JSON1 = "img/char/DCD_Animation/faiz_Axel/Axel_CrimsonSmash_1A.json";
         this.AXEL1 = new AbstractAnimation(AXEL_ATLAS, AXEL_JSON1, 0.8F, this.x, this.y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0F, 1.0F);
         this.Animation = false;
      }

      if (this.AXEL1 != null) {
         this.AXEL1.setMovable(false);
      }

      this.duration -= Gdx.graphics.getDeltaTime();
      if (this.duration < 2.0F && this.Start) {
         AbstractAnimation.changeAnimation(this.AXEL1, FaizAnimationAction.axel_FAR_1A);
         this.AXEL1.state.setAnimation(0, "1A", false);
         this.Start = false;
      }

      if (this.duration < 1.7F && this.Attack) {
         for(int i = 0; i < 5; ++i) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS), AttackEffect.BLUNT_HEAVY));
         }

         this.Attack = false;
      }

      if (this.duration < 1.5F && this.Second) {
         AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
         this.Second = false;
      }

      if (this.duration < 0.3F) {
         A1 = true;
         int n = ReturnRandomNumberAction.ReturnRandomNumber();
         if (SpecialFaizBox.PointerPoint != 0 && this.m != null) {
            if (n <= 5 && Axel_CrimsonSmash_2A.A2) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_2A(this.damage), 0.0F));
               Axel_CrimsonSmash_2A.A2 = false;
               --SpecialFaizBox.PointerPoint;
            } else if (Axel_CrimsonSmash_3A.A3) {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_3A(this.damage), 0.0F));
               Axel_CrimsonSmash_3A.A3 = false;
               --SpecialFaizBox.PointerPoint;
            } else {
               AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_1A(this.damage), 0.0F));
               A1 = false;
               --SpecialFaizBox.PointerPoint;
            }
         }

         this.dispose();
         this.isDone = true;
      }

   }

   public void render(SpriteBatch sb) {
   }

   public void dispose() {
      this.AXEL1 = null;
   }
}
