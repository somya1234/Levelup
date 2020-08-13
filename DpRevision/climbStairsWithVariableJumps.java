import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scn.nextInt();
        }
        // System.out.println(findWays_rec(arr,0));
        // System.out.println(findWays_mem(arr,0,new int[n]));
        System.out.println(findWays_tab(arr));
    }
    public static int findWays_rec(int[] arr, int idx){
        int n = arr.length;
        if(idx == n-1){
            return 1;
        } 
        
        int ans = 0;
        for(int i=1;i<=arr[idx];i++){
            //pre-active condn
            if(idx+i<n){
            ans += findWays_rec(arr,idx+i);
            }
        }
        return ans;
    }

    public static int findWays_mem(int[] arr, int idx,int[] dp){
        //Time Complexity -> O(n).
        int n = arr.length;
        
        // if(dp[idx]!=0){
        //     return dp[idx];
        // }
        
        
        if(idx == n-1){
            dp[idx] = 1;
            return dp[idx];
        }
        
        int ans = 0;
        for(int i = 1;i<=arr[idx] && idx+i<n; i++){
            //this will save calls, and will give absolute complexity to approximately '2n'.
            //ont ime go and other to return back.
            if(dp[idx+i]!=0){
                ans+=dp[idx+i];
            } else {
                ans += findWays_mem(arr,idx+i,dp);
            }
        }
        dp[idx] = ans;
        return dp[idx];
    }
    
    public static int findWays_tab(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        for(int idx=n-1;idx>=0;idx--){
            if(idx == n-1){
                dp[idx] = 1;
                continue;
            }
            
            int ans = 0;
            for(int i = 1;i<=arr[idx] && idx+i<n; i++){
                ans += dp[idx+i];
            }
            dp[idx] = ans;
            continue;
        }
        return dp[0];
    }

}
//updated code, go to (n+1) stair
/*
import java.io.*;
import java.util.*;

public class Main {
//go till n+1 step, dp of size (n+1)
    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scn.nextInt();
        }
        // System.out.println(findWays_rec(arr,0));
        // System.out.println(findWays_mem(arr,0,new int[n+1]));
        System.out.println(findWays_tab(arr));
    }
    public static int findWays_rec(int[] arr, int idx){
        int n = arr.length;
        if(idx == n){
            return 1;
        } 
        
        int ans = 0;
        for(int i=1;i<=arr[idx];i++){
            //pre-active condn
            if(idx+i<=n){
            ans += findWays_rec(arr,idx+i);
            }
        }
        return ans;
    }

    public static int findWays_mem(int[] arr, int idx,int[] dp){
        //Time Complexity -> O(n).
        int n = arr.length;
        
        // if(dp[idx]!=0){
        //     return dp[idx];
        // }
        
        
        if(idx == n){
            dp[idx] = 1;
            return dp[idx];
        }
        
        int ans = 0;
        for(int i = 1;i<=arr[idx] && idx+i<=n; i++){
            //this will save calls, and will give absolute complexity to approximately '2n'.
            //ont ime go and other to return back.
            if(dp[idx+i]!=0){
                ans+=dp[idx+i];
            } else {
                ans += findWays_mem(arr,idx+i,dp);
            }
        }
        dp[idx] = ans;
        return dp[idx];
    }
    
    public static int findWays_tab(int[] arr){
        int n = arr.length;
        int[] dp = new int[n+1];
        for(int idx=n;idx>=0;idx--){
            if(idx == n){
                dp[idx] = 1;
                continue;
            }
            
            int ans = 0;
            for(int i = 1;i<=arr[idx] && idx+i<=n; i++){
                ans += dp[idx+i];
            }
            dp[idx] = ans;
            continue;
        }
        return dp[0];
    }

}
*/