public class leetcodeRevision {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        
    }

    /************************************************************************************************/
    // leetcode 746 

    //==============================Front se -------------------
    // recursive 
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length; 
        if(n == 0 || n==1 ) return 0; 
        if(cost[0]>cost[1]) return rec(cost, 1);
        else {
            int min = (int)1e8; 
            min = Math.min(min, rec(cost, 0));
            min = Math.min(min, rec(cost, 1));
            return min; 
        }
    }
    
    public int rec(int[] cost, int src){
        int n = cost.length;
        if(src == n ) return 0; 
        
        int min = Integer.MAX_VALUE;
        if(src+1<=n){
            min = Math.min(min, rec(cost, src+1));
        } 
        if(src+2<=n){
            min = Math.min(min, rec(cost, src+2));
        } 
        return min+cost[src];
    }

    // memoization 
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length; 
        int[] dp = new int[n+1];
        if(n == 0 || n==1 ) return 0; 
        if(cost[0]>cost[1]) return mem(cost, 1, dp);
        else {
            int min = (int)1e8; 
            min = Math.min(min, mem(cost, 1,dp));
            int c = cost[0]+dp[2]; 
            min = Math.min(c, min);
            return min; 
        }
    }
    
    public int mem(int[] cost, int src, int[] dp){
        int n = cost.length;
        if(src == n ) return dp[src] = 0; 
        
        if(dp[src]!=0) return dp[src];
        
        int min = Integer.MAX_VALUE;
        if(src+1<=n){
            min = Math.min(min, mem(cost, src+1, dp));
        } 
        if(src+2<=n){
            min = Math.min(min, mem(cost, src+2, dp));
        } 
        return dp[src]= min+cost[src];
    }

    //========================= Backward se ---------------------------------
    


    /************************************************************************************************/

    public void solve3(){
        int[][] dp = new int[n+1][k+1]; 
    }

    // GFG - Count number of ways to partition a set into k subsets
    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/

    public static int countWays_Rec(int n , int k ){
        if(n == k || k==1 ) return 1; 

        int a = countWays_Rec(n-1, k-1);
        int b = countWays_Rec(n-1, k);
        int ans = a + (k*b); 
        return ans; 
    }

    public static int countWays_Mem(int n , int k , int[][] dp){
        if(n == k || k==1) return dp[n][k] = 1;

        if(dp[n][k]!=0) return dp[n][k]; 

        int a = countWays_Mem(n-1, k-1, dp);
        int b = countWays_Mem(n-1, k, dp );
        int ans = a + (k*b); 
        return dp[n][k] = ans; 
    }

    public static int countWays_Tab(int n , int k ){
        int N = n; int K = k ; 
        for( n = 1; n<N; n++){
            for(k = 1; k<K; k++){
                if(n == k || k==1) {
                    dp[n][k] = 1;
                } 

                int a = dp[n-1][k-1]
                int b = dp[n-1][k]; 
                int ans = a + (k*b); 
                 dp[n][k] = ans; 
            }
        }
        return dp[n][k]; 
    }

    /**************************************************************************************/



    public static int friendsPairing_mem(int n , int[] dp){
        if(n<=1) return dp[n] = 1; 

        if(dp[n]!=0) return dp[n];

        int single = friendsPairing_mem(n-1, dp); 
        int pairUp = friendsPairing_mem(n-2, dp) * (n-1); 

        return dp[n] = single + pairUp;
    }

    public static int friendsPairing_DP(int n , int[] dp){
        int N = n; 
        for(n =0; n<=N; n++){
            if(n<=1){
                dp[n] = 1; 
                continue; 
            } 

            int single = dp[n-1]; 
            int pairUp = dp[n-2] * (n-1); 

            dp[n] = single + pairUp;
        }
        // do not return dp[n], because n++.
        return dp[N]; 
    }


    /**************************************************************************************/

   
}
