/**
 ********************************************************************
 * @author: Sanil Rijal
 *
 * Date: 3/23/2016
 *
 * Description: This MinSteps class determines the optimal steps and
 * and the optimal moves required to reach 1 from from a given token
 * number in a game which can be reduced either by subtracting 1 or 
 * divide by 2 if divisible by 2 and divide by 3 if divisible by 3.
 * The class uses recursive approach as well as dynamic programming 
 * approach to find the optimal solution.
 ********************************************************************
 **/

import java.util.*;

public class MinSteps {

   //Data Fields
   /** The given number of token to be processed*/
   private int numberOfTokens;
   /** An array for the dynamic programming approach*/
   private int[] dp;
   
   private int recSolutionCount,dpSolutionCount;
   
   
   
   /** A constructor to initialize a game with a token number and the arrays to be used.
     * @param n The token number for which optimal steps is to be calculated.
     */
   public MinSteps(int n) {
   
      numberOfTokens=n;
      dp=new int[n+1];
     
   
   }
   
   /** A recursive method to compute the number of steps required for the optimal solution.
     * @param n The token number for which optimal steps is to be calculated.
     * @return optimalStep The number of steps calculated recursively.
     */
   private int steps(int n){
      
      int tokens= n;
      /**The number of steps for the optimal solution.*/
      int optimalStep;
   
      if (tokens==1){
         return 0;
      }
      
      if(tokens % 3 == 0 && tokens % 2 == 0){
         optimalStep= 1+Math.min(steps(tokens/3),Math.min(steps(tokens/2),steps(tokens-1)));
      }
      else if (tokens % 3 == 0){
         optimalStep=1+Math.min(steps(tokens/3),steps(tokens-1));
      }
      else if (tokens % 2 == 0){
         optimalStep=1+Math.min(steps(tokens/2),steps(tokens-1));
      }
      else{
         optimalStep=1+steps(tokens-1);
      }
      recSolutionCount++;
      return optimalStep;
   
   
   }
   /** A method that computes number of steps recursively by calling "int steps(int n)".
     * @return The number of steps calculated recursively by call int steps method.
     */
   public int recSolution(){
      return steps(numberOfTokens);
   }
   /** A method that computes number of steps for the optimal solution using dynamic programming.
     * @return The number of steps calculated using dynamic programming. */
   public int dpSolution() { 
      int i;
     
      dp[1] = 0; // trivial case
      for( i = 2 ; i < dp.length; i ++ )
      {
         if(i%3==0 && i%2==0){
            dp[i]=1+ Math.min(dp[i/3],Math.min(dp[i/2],dp[i-1]));
         }         
         else if(i%3==0){
            dp[i] =1+Math.min (dp[i-1] ,dp[i/3]);
         }
             
         else if(i%2==0){
            dp[i] =1+Math.min (dp[i-1] , dp[i/2]);
         }
         else{
            dp[i] = 1 + dp[i-1];
         }
         dpSolutionCount++;
          
      }
      return dp[dp.length-1];
   
   }
   
  
   /** A method to return the sequence of moves required for the optimal solution.
     * @return The sequence of moves in the optimal solution.
     */
   public String getMoves() {
      dpSolution();
      int moves[]=new int[dp[dp.length-1]+1];
      int num=numberOfTokens;
      moves[0]=num;
      int i=1;
      while(i < moves.length)
      {
         if(num%3==0 && num%2==0){
            if (dp[num/3]<=dp[num/2] && dp[num/3]<=dp[num-1]){
               num=num/3;
               moves[i]=num;}
            else if (dp[num/2]<=dp[num/3] && dp[num/2]<=dp[num-1]){
               num=num/2;
               moves[i]=num;}
            else if (dp[num-1] <= dp[num/3] && dp[num-1] <= dp[num/2]) {
               num = num -1;
               moves[i] = num;
            }
         }
         else if(num%3==0){
            if(dp[num/3]<dp[num-1]){
               num=num/3;
               moves[i]=num;}
            else {
               num=num-1;
               moves[i]=num;
            }
         }
         else if(num%2==0){
            if(dp[num/2]<dp[num-1]){
               num=num/2;
               moves[i]=num;}
            else {
               num=num-1;
               moves[i]=num;
            }
         }
         else{
            num=num-1;
            moves[i]=num;
         }
         i++;
      }
      
      String str=""+moves[0];
      for(int k =1; k < moves.length; k++){
         //if(moves[k]==0){
          //  break;
         //}
         str += " --> " + moves[k];
      }
      
      return str;
          
      
   }
   
   public int getRecCount()
   {
      return recSolutionCount;
   }
   
   public int getDpCount(){
      return dpSolutionCount;}
   
   

}

