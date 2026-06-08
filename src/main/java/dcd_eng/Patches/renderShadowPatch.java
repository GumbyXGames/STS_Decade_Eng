package dcd_eng.Patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dcd_eng.DCDmod;

@SpirePatch(
   clz = AbstractCard.class,
   method = "renderHoverShadow",
   paramtypez = {SpriteBatch.class}
)
public class renderShadowPatch {
   public static SpireReturn<Object> Prefix(AbstractCard the_AbstractCard, SpriteBatch sb) {
      return the_AbstractCard.hasTag(DCDmod.TriggerCard) ? SpireReturn.Return((Object)null) : SpireReturn.Continue();
   }
}
