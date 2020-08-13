import java.io.*;
import java.util.*;

public class Main {
    //1.

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = scn.nextInt();
            }
        }
        // System.out.println(minCost(arr,0,0));  -> recursion 
        // System.out.println(minCost_mem(arr,0,0,new int[n][m])); -> memoization
        System.out.println(minCost_tab(arr));
    }
    public static int minCost(int[][] arr, int i, int j){
        //recursion-> returns min Cost uptil now i.e. below that idx to destination.
        int n = arr.length;
        int m = arr[0].length;
        if(i==n-1 && j==m-1){
            return arr[i][j];
        } 
        int cost1 = Integer.MAX_VALUE;
        int cost2 = cost1;
        if(j+1<m){
            cost1 = minCost(arr,i,j+1);
        }
        if(i+1<n){
            cost2 = minCost(arr,i+1,j);
        }
        return Math.min(cost1,cost2)+arr[i][j];
    }
    
    public static int minCost_mem(int[][] arr, int i, int j, int[][] dp){
        int n = arr.length;
        int m = arr[0].length;
        if(dp[i][j]!=0){
            return dp[i][j];
        }
        if(i==n-1 && j==m-1){
            return dp[i][j] = arr[i][j];
        }
        int cost1 = Integer.MAX_VALUE;
        int cost2 = cost1;
        if(j+1<m){
            cost1 = minCost_mem(arr,i,j+1,dp);
        }
        if(i+1<n){
            cost2 = minCost_mem(arr,i+1,j,dp);
        }
        return dp[i][j] = Math.min(cost1,cost2)+arr[i][j];
    }
    
    public static int minCost_tab(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(i==n-1 && j==m-1){
                    dp[i][j] = arr[i][j];
                    continue;
                } else if(i+1>n-1){
                    dp[i][j] = dp[i][j+1] + arr[i][j];
                } else if(j+1>m-1){
                    dp[i][j] = dp[i+1][j] + arr[i][j];
                } else{
                    dp[i][j] = Math.min(dp[i+1][j],dp[i][j+1]) + arr[i][j];
                }
            }
        }
        return dp[0][0];
    } 

    public static int minCost_tab(int[][] arr) {
        //converted from memoization 
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = arr[i][j];
                    continue;
                }
                int cost1 = Integer.MAX_VALUE;
                int cost2 = cost1;
                if (j + 1 < m) {
                    cost1 = dp[i][j+1];
                }
                if (i + 1 < n) {
                    cost2 = dp[i+1][j];
                }
               dp[i][j] = Math.min(cost1, cost2) + arr[i][j];
               continue;
            }
        }
        return dp[0][0];
    }
}
