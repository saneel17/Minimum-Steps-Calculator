public class MinStepsAnalysis
{
   public static void main(String args[])
   {
      System.out.printf("%s %30s %20s\n", "Tokens", "Recursive method calls",
         "Dynamic method calls");
      for(int i = 1; i <= 1000; i = i * 3)
      {
         MinSteps aStep = new MinSteps(i);
         aStep.recSolution();
         int recCount = aStep.getRecCount();
         aStep.dpSolution();
         int dpCount = aStep.getDpCount();
         System.out.printf("%4d%20d%20d\n", i, recCount, dpCount);
      }
   }
}