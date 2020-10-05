import java.util.Arrays;

public class leetcode2 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        solve1();
    }

    /***************************************************************************************/

    // leetcode 322. Coin Change

    public static void solve1() {
        int tar = 11;
        int[] coins = { 1, 2, 5 };
        int[] dp = new int[tar + 1];
        Arrays.fill(dp, (int) 1e9);
        // System.out.println(coinChange_mem(coins, tar, dp));

        System.out.println(coinChange_tab(coins, tar));
    }

    // infinite permutations
    public static int coinChange_mem(int[] coins, int amount, int[] dp) {
        if (amount == 0)
            return dp[amount] = 0;

        if (dp[amount] != (int) 1e9)
            return dp[amount];

        int min = (int) 1e9;
        for (int ele : coins) {
            if (amount - ele >= 0)
                min = Math.min(min, coinChange_mem(coins, amount - ele, dp));
        }
        return dp[amount] = min + 1;
    }

    // call for above memoization on leetcode.
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, (int) 1e9);

        int ans = coinChange_mem(coins, amount, dp);
        // as we are adding 1 in (int)1e9.
        return ans >= (int) 1e9 ? -1 : ans;

    }

    // infinite combiantions.
    public static int coinChange_tab(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] dp = new int[amount + 1];

        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;

        for (int ele : coins) {
            for (int tar = ele; tar <= amount; tar++) {
                if (tar - ele >= 0) {
                    if (dp[tar - ele] != (int) 1e9) {
                        int len = dp[tar - ele] + 1;
                        dp[tar] = Math.min(dp[tar], len);
                    }

                }

            }
        }
        return dp[amount] == (int) 1e9 ? -1 : dp[amount];
    }

    /***************************************************************************************/
}
