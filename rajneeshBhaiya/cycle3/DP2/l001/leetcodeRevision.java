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
}
