import java.util.Arrays;

public class leetcode {
    public static void main(String[] args) {
        maxCoins();
    }

    /***************************************************************************************/

    public static void print2d(int[][] dp) {
        for (int[] d : dp)
            print(d);
    }

    public static void print(int[] d) {
        for (int val : d) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /********************************************************************************************/
    // Leetcode 312 - Burst Balloons

    public static void maxCoins() {
        int[] nums = { 3, 1, 5, 8 };
        int n = nums.length;
        if (n == 0)
            return;

        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        // int ans = burstCoins(nums, 0, n - 1, dp);
        int ans = burstCoin_dp(nums, 0, n-1, dp); 
        System.out.println(ans);
        print2d(dp);
    }

    public static int burstCoins(int[] nums, int si, int ei, int[][] dp) {
        int n = nums.length;

        if (dp[si][ei] != -1)
            return dp[si][ei];

        int ans = 0;
        int lval = (si == 0) ? 1 : nums[si - 1];
        int rval = (ei == n - 1) ? 1 : nums[ei + 1];

        for (int cut = si; cut <= ei; cut++) {
            int leftTree = (cut == si) ? 0 : burstCoins(nums, si, cut - 1, dp);
            int rightTree = (cut == ei) ? 0 : burstCoins(nums, cut + 1, ei, dp);
            int myAns = nums[cut] * lval * rval;
            myAns = leftTree + rightTree + myAns;
            ans = Math.max(ans, myAns);
        }
        return dp[si][ei] = ans;
    }

    public static int burstCoin_dp(int[] nums, int si, int ei, int[][] dp) {
        int Ei = ei;
        for (int gap = 0; gap <= Ei; gap++) {
            for (si = 0, ei = gap; ei <= Ei; si++, ei++) {
                int n = nums.length;

                int ans = 0;
                int lval = (si == 0) ? 1 : nums[si - 1];
                int rval = (ei == n - 1) ? 1 : nums[ei + 1];

                for (int cut = si; cut <= ei; cut++) {
                    int leftTree = (cut == si) ? 0 : dp[si][cut-1];
                    int rightTree = (cut == ei) ? 0 : dp[cut+1][ei];
                    int myAns = nums[cut] * lval * rval;
                    myAns = leftTree + rightTree + myAns;
                    ans = Math.max(ans, myAns);
                }
                dp[si][ei] = ans;
            }
        }
        return dp[0][Ei]; 
    }
    /********************************************************************************************/

}
