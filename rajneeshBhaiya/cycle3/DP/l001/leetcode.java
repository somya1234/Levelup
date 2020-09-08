public class leetcode {
    public static void main(String[] args) {

    }

    /**************************************************************************************/

    // memoization
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climbStairs_(n, dp);
    }

    public int climbStairs_(int n, int[] dp) {
        if (n == 0)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        if (n - 1 >= 0)
            count += climbStairs_(n - 1, dp);
        if (n - 2 >= 0)
            count += climbStairs_(n - 2, dp);
        return dp[n] = count;
    }

    // tabulation
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climbStairs_(n, dp);
    }

    public int climbStairs_(int n, int[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            int count = 0;
            if (n - 1 >= 0)
                count += dp[n - 1];
            if (n - 2 >= 0)
                count += dp[n - 2];
            dp[n] = count;
        }
        return dp[N];
    }

    /**************************************************************************************/

    // leetcode 746 -> Min Cost Climbing Stairs
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        int x = minCost_(cost, 0, dp);
        int y = minCost_(cost, 1, new int[cost.length + 1]);
        return Math.min(x, y);
    }

    public int minCost_(int[] cost, int idx, int[] dp){
        if(idx == cost.length){
            return dp[idx] = 0;
        }
        if(idx>cost.length){
            return (int)1e8;
        }
        if(dp[idx]!=0) return dp[idx];
        
        // both will never be inf.
        int cost1 = (int)1e8;
        int cost2 = (int)1e8;
        if(idx+1<=cost.length){
          cost1 =  minCost_(cost, idx+1, dp);
        }
        if(idx+2 <= cost.length) 
            cost2 = minCost_(cost, idx+2,dp);
        return dp[idx] = Math.min(cost1,cost2)+cost[idx];
    }

    //==========
    // tabulation from front
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        int x = minCost_(cost, dp, 0);
        int y = minCost_(cost, new int[cost.length+1], 1);
        return Math.min(x,y);
    }
    

    public int minCost_(int[] cost, int[] dp, int si){
        for(int idx = cost.length;idx>=si;idx--){
            if(idx == cost.length){
                dp[idx] = 0;
                continue;
            }
        
            int cost1 = 0;
            int cost2 = 0;
            if(idx+1<=cost.length){
            cost1 =  dp[idx+1];
            }
            if(idx+2 <= cost.length) 
                cost2 = dp[idx+2];
            dp[idx] = Math.min(cost1,cost2)+cost[idx];
            }
        return dp[si];
    }

    //=== from baclwards 
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        // return minCost(cost,cost.length,dp);
        return minCost2(cost,dp);
    }
    // memoization 
    public int minCost(int[] cost, int idx, int[] dp){
        if(idx == 0 || idx==1){
            return dp[idx] = cost[idx];
        }
        if(dp[idx]!=0) return dp[idx];
        
        int cost1 = minCost(cost, idx-1,dp);
        int cost2 = minCost(cost,idx-2,dp);
        
        // we can use ternary operator also while return time.
        if(idx == cost.length){
            return dp[idx] = Math.min(cost1,cost2);
        }
        return dp[idx] = Math.min(cost1,cost2)+cost[idx];
    }
    // tabulation 
    public int minCost2(int[] cost,int[] dp){
        for(int idx = 0; idx<=cost.length;idx++){
            if(idx == 0 || idx==1){
                 dp[idx] = cost[idx];
                continue;
            }
           
            int cost1 = dp[idx-1];
            int cost2 = dp[idx-2];

            if(idx == cost.length){
                 dp[idx] = Math.min(cost1,cost2);
                continue;
            }
             dp[idx] = Math.min(cost1,cost2)+cost[idx];
        }
        return dp[cost.length];
    }

    /**************************************************************************************/

}
