public class leetcode {
    public static void main(String[] args) {
        
    }

/**************************************************************************************/

    // memoization 
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return climbStairs_(n,dp);
    }
    public int climbStairs_(int n , int[] dp){
        if(n==0) return dp[n] = 1;
        if(dp[n]!=0) return dp[n];
        
        int count = 0;
        if(n-1>=0)
        count+=  climbStairs_(n-1, dp);
        if(n-2>=0)
        count += climbStairs_(n-2, dp);
        return dp[n] = count;   
    }

    // tabulation 
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return climbStairs_(n,dp);
    }
    public int climbStairs_(int n , int[] dp){
        int N = n;
        for( n = 0;n<=N;n++){
            if(n==0){
                 dp[n] = 1;
                continue;
            }
        
            int count = 0;
            if(n-1>=0)
                count+=  dp[n-1];
            if(n-2>=0)
                count += dp[n-2];
             dp[n] = count;  
        }
        return dp[N];
    }

/**************************************************************************************/

public int minCostClimbingStairs(int[] cost, int n , int[] dp ) {
    if(n<=1) return dp[n] = cost[n];
    if(dp[n]!=0) return dp[n];
    int ans = Math.min(minCostClimbingStairs(cost, n-1, dp), minCostClimbingStairs(cost, n-2, dp));
    return dp[n] = ans + n==cost.length ? 0 : cost[n];
}

public int minCostClimbingStairs(int[] cost){
    int[] dp = new int[cost.length+1];

}


}
