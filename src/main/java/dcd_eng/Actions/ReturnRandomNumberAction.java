package dcd_eng.Actions;

public class ReturnRandomNumberAction {
   public static int ReturnRandomNumber() {
      int min = (int)Math.ceil((double)1.0F);
      int max = (int)Math.floor((double)10.0F);
      return (int)(Math.floor(Math.random() * (double)(max - min)) + (double)min);
   }
}
