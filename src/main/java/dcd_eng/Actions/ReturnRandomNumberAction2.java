package dcd_eng.Actions;

public class ReturnRandomNumberAction2 {
   private static int number;

   public static int ReturnRandomNumber() {
      int min = (int)Math.ceil((double)1.0F);
      int max = (int)Math.floor((double)100.0F);
      number = (int)(Math.floor(Math.random() * (double)(max - min)) + (double)min);
      return number;
   }
}
