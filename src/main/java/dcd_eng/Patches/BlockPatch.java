package dcd_eng.Patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
   clz = AbstractCreature.class,
   method = "loseBlock",
   paramtypez = {}
)
public class BlockPatch {
   public static SpireReturn<Object> Prefix(AbstractCreature The_AbstractCreature) {
      if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("KamenRideAgitoPower")) {
         The_AbstractCreature.loseBlock(Math.min(The_AbstractCreature.currentBlock, 15));
      } else {
         The_AbstractCreature.loseBlock(The_AbstractCreature.currentBlock);
      }

      return SpireReturn.Return((Object)null);
   }
}
