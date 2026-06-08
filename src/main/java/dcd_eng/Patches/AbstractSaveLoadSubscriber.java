package dcd_eng.Patches;

import basemod.interfaces.StartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.Settings;
import java.io.IOException;

public abstract class AbstractSaveLoadSubscriber implements StartGameSubscriber {
   private SpireConfig config;
   private static final String KEY = "SLSeed";

   private Long getSavedSeed() {
      String tmp = this.config.getString("SLSeed");
      return tmp == null ? 0L : Long.parseLong(tmp);
   }

   public void receiveStartGame() {
      if (this.getSavedSeed() != Settings.seed) {
         this.config.setString("SLSeed", Settings.seed.toString());

         try {
            this.config.save();
         } catch (IOException e) {
            e.printStackTrace();
         }

         System.out.println("初次结束");
      } else {
         this.onLoadGame();
      }

   }

   protected abstract void onLoadGame();

   protected void setConfig(SpireConfig config) {
      this.config = config;
   }
}
