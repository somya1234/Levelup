import java.io.*;
import java.util.*;

public class Main {
    //method 1 -> Memoization
    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        // int ans = fib_rec(n);
        int ans = fib_mem(n,new int[n+1]);
        System.out.println(ans);
    }

    //Recursion
    public static int fib_rec(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int fib1 = fib_rec(n - 1);
        int fib2 = fib_rec(n - 2);
        return fib1 + fib2;
    }
    
    //Memoization
    public static int fib_mem(int n,int[] dp){
        if(n==0 || n==1){
            dp[n] = n;
            return n;
        }
        
        if(dp[n]!=0){
            return dp[n];
        }
        
        int fib1 = fib_mem(n-1,dp);
        int fib2 = fib_mem(n-2,dp);
        dp[n] = fib1+fib2;
        return dp[n];
    }
    
    //Tabulation
    public static int fib_tab(int n){
        int[] dp = new int[n+1];
        
        for(int i=0;i<=n;i++){
            if(i==0 || i==1){
            dp[i] = i;
            continue; //important here, run-time error otherwise.
        }
        
        int fib1 = dp[i-1];
        int fib2 = dp[i-2];
        dp[n] = fib1+fib2;
        continue; //optional here
        }
        return dp[n];
    }
}
