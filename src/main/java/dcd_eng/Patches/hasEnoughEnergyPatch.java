package dcd_eng.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dcd_eng.Characters.Decade;

@SpirePatch(
   clz = AbstractCard.class,
   method = "hasEnoughEnergy"
)
public class hasEnoughEnergyPatch {
   public static SpireReturn<Boolean> Prefix() {
      return AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("KamenRideKivaPower") ? SpireReturn.Return(true) : SpireReturn.Continue();
   }
}
