import java.io.*;
import java.util.*;

public class Main {
    //somya code

    //recursion 
    public static int mcm(int[] arr, int st, int ed) {
        //write your code here
        int cost = Integer.MAX_VALUE;
        if (ed == st + 1) {
            return 0;
        }
        for (int cp = st + 1; cp < ed; cp++) {
            int leftCost = mcm(arr, st, cp);
            int rightCost = mcm(arr, cp, ed);
            cost = Math.min(cost, leftCost + rightCost + (arr[st] * arr[ed] * arr[cp]));
        }


        return cost;
    }

    public static int mcm_mem(int[] arr, int st, int ed, int[][] dp) {
        //write your code here
        int cost = Integer.MAX_VALUE;
        if (ed == st + 1) {
            return dp[st][ed] = 0;
        }
        if (dp[st][ed] != 0) {
            return dp[st][ed];
        }
        for (int cp = st + 1; cp < ed; cp++) {
            int leftCost = mcm_mem(arr, st, cp, dp);
            int rightCost = mcm_mem(arr, cp, ed, dp);
            cost = Math.min(cost, leftCost + rightCost + (arr[st] * arr[ed] * arr[cp]));
            dp[st][ed] = cost;
        }

        return dp[st][ed];
    }

    public static int mcm_tab(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int idx = 2; idx < n; idx++) {
            for (int i = 0, j = idx; j < n; i++, j++) {
                int cost = Integer.MAX_VALUE;
                    for(int cp=i+1;cp<j;cp++){
                        int leftCost = dp[i][cp];
                        int rightCost = dp[cp][j];
                         cost = Math.min(cost,leftCost + rightCost+ (arr[i]*arr[j]*arr[cp]));
                    }
                    dp[i][j] = cost;
            }
        }
        return dp[0][n-1];
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        // 		System.out.println(mcm(arr,0,n-1));
        // System.out.println(mcm_mem(arr, 0, n - 1, new int[n][n]));
        System.out.println(mcm_tab(arr));
    }
}