package dcd_eng.Patches;

import basemod.BaseMod;
import basemod.interfaces.StartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import dcd_eng.DCDmod;
import dcd_eng.Actions.TurnTimer;
import java.io.IOException;

@SpireInitializer
public class ModBaseClassForSLExample extends AbstractSaveLoadSubscriber implements StartGameSubscriber {
   private SpireConfig config;
   public static int testOutput = 0;
   public static boolean timevent = false;
   public static boolean TimeVentUpgraded = true;

   public static void initialize() {
      BaseMod.subscribe(new ModBaseClassForSLExample());
   }

   public void receiveStartGame() {
      if (this.config == null) {
         try {
            this.config = new SpireConfig("DCDmod", "Decade");
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      this.setConfig(this.config);
      System.out.println("开始/继续游戏");
      System.out.println("onLoadGame触发前，testOutput = " + testOutput);
      super.receiveStartGame();
      System.out.println("onLoadGame触发后，testOutput = " + testOutput);
      System.out.println("开始/继续游戏。");
      AbstractSummonedAnimation.clearAll();
      AbstractAnimation.clearAll();
      AbstractHandAnimation.clearAll();
   }

   protected void onLoadGame() {
      TurnTimer.atEndOfRound();
      DCDmod.loadConfig2();
      AbstractSummonedAnimation.clearAll();
      AbstractAnimation.clearAll();
      AbstractHandAnimation.clearAll();
      if (timevent) {
         ++testOutput;
      } else {
         testOutput = 0;
      }

      System.out.println("本次启动游戏程序开始，累计第" + testOutput + "次SL");
   }
}
