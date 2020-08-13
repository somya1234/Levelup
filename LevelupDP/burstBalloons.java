import java.io.*;
import java.util.*;

public class Main {
    // sir method 

    public static int solution_rec(int[] arr, int si, int ei) {
        //write your code here
        int n = arr.length;

        int max = Integer.MIN_VALUE;
        //after a group is solved, multiply with its leftval which is outside the gorup
        //inside the array.
        int leftVal = (si - 1) >= 0 ? arr[si - 1] : 1;
        int rightVal = (ei + 1) < n ? arr[ei + 1] : 1;
        if (si == ei) {
            return leftVal * rightVal * arr[si];
        }
        if (si > ei) {
            return 0;
        }
        for (int cp = si; cp <= ei; cp++) {
            int leftCost = solution_rec(arr, si, cp - 1);
            int rightCost = solution_rec(arr, cp + 1, ei);
            int myCost = leftCost + rightCost + (leftVal * arr[cp] * rightVal);
            max = Math.max(max, myCost);

        }
        return max;
    }

    public static int solution_mem(int[] arr, int si, int ei, int[][] dp) {
        //write your code here
        int n = arr.length;

        //after a group is solved, multiply with its leftval which is outside the gorup
        //inside the array.
        int leftVal = (si - 1) >= 0 ? arr[si - 1] : 1;
        int rightVal = (ei + 1) < n ? arr[ei + 1] : 1;

        if (si > ei) {
            return 0;
        }
        if (si == ei) {
            return arr[si] * leftVal * rightVal;
        }
        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }

        int max = Integer.MIN_VALUE;

        for (int cp = si; cp <= ei; cp++) {
            int leftCost = solution_mem(arr, si, cp - 1, dp);
            int rightCost = solution_mem(arr, cp + 1, ei, dp);
            int myCost = leftCost + rightCost + (leftVal * arr[cp] * rightVal);
            max = Math.max(max, myCost);

        }
        return dp[si][ei] = max;
    }

    public static int solution_tab(int[] arr) {
        //write your code here
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int idx = 0; idx < n; idx++) {
            for (int si = 0, ei = idx; ei < n; si++, ei++) {
                int leftVal = (si - 1) >= 0 ? arr[si - 1] : 1;
                int rightVal = (ei + 1) < n ? arr[ei + 1] : 1;

                if (si == ei) {
                    //at the time of burst
                    dp[si][ei] = arr[si] * leftVal * rightVal;
                    continue;
                }
               
                int max = Integer.MIN_VALUE;

                for (int cp = si; cp <= ei; cp++) {
                    // should I call on left side and right side.
                    int leftCost = (cp-1>=si) ? dp[si][cp-1] : 0;
                    int rightCost = (cp+1<n) ? dp[cp+1][ei] : 0;
                    int myCost = leftCost + rightCost + (leftVal * arr[cp] * rightVal);
                    max = Math.max(max, myCost);
                }
                dp[si][ei] = max;
                continue;
            }
        }
        return dp[0][n-1];
    }


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        // System.out.println(solution_rec(arr,0,n-1));
        // System.out.println(solution_mem(arr, 0, n - 1, new int[n][n]));
        System.out.println(solution_tab(arr));
    }

}