package dcd_eng.Patches;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import java.lang.reflect.Field;

public class OpenFix {
   @SpirePatch(
      cls = "com.megacrit.cardcrawl.screens.SingleCardViewPopup",
      method = "open",
      paramtypes = {"com.megacrit.cardcrawl.cards.AbstractCard"}
   )
   public static class OpenTextureFix {
      public static void Postfix(Object __obj_instance, Object cardObj) {
         AbstractCard card = (AbstractCard)cardObj;
         SingleCardViewPopup popup = (SingleCardViewPopup)__obj_instance;

         try {
            Field portraitImageField = popup.getClass().getDeclaredField("portraitImg");
            portraitImageField.setAccessible(true);
            if (portraitImageField.get(popup) == null && card instanceof CustomCard) {
               portraitImageField.set(popup, CustomCard.getPortraitImage((CustomCard)card));
            }
         } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            ((Exception)e).printStackTrace();
         }

      }
   }
}
