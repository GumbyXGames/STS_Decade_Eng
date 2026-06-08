package dcd_eng.Manager;

import java.util.ArrayList;

public class PlayerManager {
   private static final PlayerManager ourInstance = new PlayerManager();
   public ArrayList<String> Events = new ArrayList();

   public static PlayerManager getInstance() {
      return ourInstance;
   }

   private PlayerManager() {
   }

   public void AddEvent(String event) {
      this.Events.add(event);
   }

   public void RemoveEvent(String event) {
      this.Events.remove(event);
   }
}
