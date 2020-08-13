import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        // int ans = findWays_rec(n);
        // System.out.println(ans);
        // System.out.println(findWays_rec2(n,0));
        // System.out.println(findWays_rec_preActive(n));
        // System.out.println(findWays_mem(n,new int[n+1]));
        System.out.println(findWays_tab(n));
    }
    public static int findWays_rec(int n){
        if(n==0){
            return 1;
        } else if(n<0){
            return 0;
        }
        
        int ans = 0;
        ans += findWays_rec(n-1);
        ans += findWays_rec(n-2);
        ans += findWays_rec(n-3);
        return ans;
    }
    
    public static int findWays_rec_preActive(int n){
        if(n==0){
            return 1;
        } 
        
        int ans = 0;
        //better as we don't call unnecessarily, but overall complexity same.
        if((n-1)>=0){
        ans += findWays_rec(n-1);
        } 
        if(n-2>=0){
        ans += findWays_rec(n-2);
        }
        if(n-3>=0){
        ans += findWays_rec(n-3);
        }
        return ans;
    }
    
    public static int findWays_rec2(int n, int step){
        if(step==n){
            return 1;
        } else if(step>n){
            return 0;
        }
        
        int ans = 0;
        ans += findWays_rec2(n,step+1);
        ans += findWays_rec2(n,step+2);
        ans += findWays_rec2(n,step+3);
        return ans;
    }
    
    
    public static int findWays_mem(int n, int[] dp){
        if(n==0){
            dp[n] = 1;
            return dp[n];
        } else if(n<0){
            //here, we can't return dp[n] as for n<0,
            //then dp will don't have index with (n<0).
            return 0;
        }
        
        if(dp[n]!=0){
            return dp[n];
        }
        
        int ans = 0;
        ans += findWays_mem(n-1,dp);
        ans += findWays_mem(n-2,dp);
        ans += findWays_mem(n-3,dp);
        dp[n] = ans;
        return dp[n];
    }
    
    public static int findWays_tab(int n){
        int[] dp = new int[n+1]; //no of ways to climb n stairs.
        for(int i=0;i<=n;i++){
            if(i==0){
            dp[i] = 1;
            continue;
        }  
        //can't do this loop.
        // if(i<0){
        //     continue;
        // }
        int ans = 0;
        //if you want to save these loops, then you can set base case for 1, 2 and 3. 
        if(i-1>=0)
            ans += dp[i-1];
        if(i-2>=0)
            ans += dp[i-2];
        if(i-3>=0)
            ans += dp[i-3];
        dp[i] = ans;
        continue;
        }
        return dp[n];
        
    }

}
