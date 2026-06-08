package dcd_eng.Manager;

import java.util.ArrayList;

public class ModSaverFile {
   public ArrayList<String> Events = new ArrayList();

   public ModSaverFile() {
      this.Events = PlayerManager.getInstance().Events;
   }
}
