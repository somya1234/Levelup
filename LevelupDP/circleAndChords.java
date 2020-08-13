import java.io.*;
import java.util.*;

public class Main {

    //tabulation 
    public static long NumberOfChords(int n){
        //write your code here
        //use long as the input is very large
        long[] dp = new long[2*n+1];
        dp[0] = 1;
        dp[2] = 1;
        for(int idx=4;idx<=2*n;idx++){
            int i = 0;
            int j = idx-2;
            long ans = 0;
            while(j>=0){
                //left * right 
                ans += dp[i]*dp[j];
                i+=2;
                j-=2;
            }
            dp[idx] = ans;
        }        
        
        return dp[2*n];
    }
  public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(NumberOfChords(n));
	}
}