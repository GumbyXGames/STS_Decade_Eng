package dcd_eng.Patches;

import dcd_eng.Actions.ReturnRandomNumberAction2;

public class GetButtonSoundKey {
   public static String CustomModeButtonSoundKey() {
      return 10 > ReturnRandomNumberAction2.ReturnRandomNumber() ? "henshin(oilfish)" : "ButtonSoundKey1";
   }
}
