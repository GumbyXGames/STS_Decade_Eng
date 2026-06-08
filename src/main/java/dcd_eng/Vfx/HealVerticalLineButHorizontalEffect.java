package dcd_eng.Vfx;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.vfx.combat.HealVerticalLineEffect;

public class HealVerticalLineButHorizontalEffect extends HealVerticalLineEffect {
   private float startingY;
   private float privateVY;

   public HealVerticalLineButHorizontalEffect(float x, float y, float width) {
      super(x, y);
      this.startingY = y;
      this.renderBehind = false;
      this.rotation = 0.0F;
      ReflectionHacks.setPrivate(this, HealVerticalLineEffect.class, "staggerTimer", 0.0F);
      this.privateVY = (Float)ReflectionHacks.getPrivate(this, HealVerticalLineEffect.class, "vY");
      this.privateVY *= width / 20.0F;
   }

   public void update() {
      super.update();
      this.scale *= 0.3F;
      ReflectionHacks.setPrivate(this, HealVerticalLineEffect.class, "y", this.startingY);
      ReflectionHacks.setPrivate(this, HealVerticalLineEffect.class, "x", (Float)ReflectionHacks.getPrivate(this, HealVerticalLineEffect.class, "x") + this.privateVY * Gdx.graphics.getDeltaTime());
   }
}
