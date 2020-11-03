public class l005 {
    public static void main(String[] args) {
        
    }

    //leetcode 887

    /***************************************************************************************/
    //sumeet sir

    //==== recursion
    public int superEggDrop(int K, int N) {
        if(K==0 || N==0) return 0; 
        if(N==1) return 1; 
        if(K==1) return N; 
        
        int ans = (int)1e9; 
        for(int i=1; i<=N; i++){
            int eggBreak = superEggDrop(K-1, K-1); 
            int eggNotBreak = superEggDrop(K, N-K); 
            ans = Math.min(ans, Math.max(eggBreak, eggNotBreak));
        }
        
        return ans + 1; 
    }

    //====== memoization - TLE (for many floors eg 1000)
    public int superEggDrop(int K, int N){
        int[][] dp = new int[K+1][N+1]; 
        return superEggDrop(K, N, dp);
    }
    public int superEggDrop(int K, int N, int[][] dp) {
        if(K==0 || N==0) return dp[K][N] = 0; 
        if(N==1) return dp[K][N] = 1; 
        if(K==1) return dp[K][N] = N; 
        
        if(dp[K][N]!=0) return dp[K][N];
        
        int ans = (int)1e9; 
        for(int i=1; i<=N; i++){
            int eggBreak = superEggDrop(K-1, i-1, dp); 
            int eggNotBreak = superEggDrop(K, N-i, dp); 
            ans = Math.min(ans, Math.max(eggBreak, eggNotBreak));
        }
        
        return dp[K][N] = ans + 1; 
    } 

    //tabulation  - TLE

    public int superEggDrop(int K, int N) {
        int k = K, n = N ;
        int[][] dp = new int[k+1][n+1]; 
        for(int i=1; i<=k; i++){
            for(int j=1; j<=n; j++){
                if(j==1) {
                    dp[i][j] = 1; 
                    continue; 
                } else if( i ==1){
                    dp[i][j] = j; 
                    continue; 
                } else {
                    int ans = (int)1e9; 
                    for(int x = j-1, y=0; x>=0; x--, y++){
                        int res = Math.max(dp[i][x], dp[i-1][y]); 
                        ans = Math.min(ans, res);
                    }
                    dp[i][j] = ans+1; 
                }
            }
        }
        return dp[k][n];
    }

    /***************************************************************************************/

    //passed - rajneesh bhaiya 
    public int superEggDrop(int K, int N){
        int[][] dp = new int[K+1][N+1]; 
        return superEggDrop(K, N, dp);
    }
    public int superEggDrop(int K, int N, int[][] dp) {
        int k = K, n = N;
        if(K==0 || N==0) return dp[K][N] = 0; 
        if(N==1) return dp[K][N] = 1; 
        if(K==1) return dp[K][N] = N; 
        
        if(dp[K][N]!=0) return dp[K][N];
        
        int ans = (int)1e9; 
        int si =1, ei = N; 
        while(si<=ei){
            int mid = (si+ei)>>1; 
            int eggBreak = superEggDrop(k-1, mid-1, dp); 
            int eggNotBreak = superEggDrop(k,n-mid, dp); 
            
            if(eggBreak<eggNotBreak) si = mid+1; 
            else ei = mid-1;
            
            int temp = Math.max(eggBreak, eggNotBreak);
            
            ans = Math.min(ans, temp); 
        }
        
        return dp[K][N] = ans + 1; 
    } 
    
}
