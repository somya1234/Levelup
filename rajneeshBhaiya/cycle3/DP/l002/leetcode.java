public class leetcode {
    
    public static void solve(){

    }

/************************************************************************************************/
    // leetcode - 64 

    // two fns same name, but different arguments.
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return minPathSum(grid, 0, 0, dp);
    }
    // memoization 
    public int minPathSum(int[][] grid, int x, int y , int[][] dp) {
        if(x==grid.length-1 && y==grid[0].length-1){
            return dp[x][y] = grid[x][y];
        }
        if(dp[x][y]!=0) return dp[x][y];
        
        int ans = (int)1e8;
        if(x+1<grid.length)
            ans = Math.min(ans, minPathSum(grid, x+1, y, dp));
        if(y+1<grid[0].length)
            ans = Math.min(ans, minPathSum(grid, x, y+1, dp));
        return dp[x][y] = ans+grid[x][y];
    }

    // tabulation 
    public int minPathSum(int[][] grid, int sr, int sc, int[][] dp ) {
        for(sr = grid.length-1;sr>=0; sr--){
            for(sc = grid[0].length-1 ;sc>=0; sc-- ){
                if(sr==grid.length-1 && sc == grid[0].length-1){
                     dp[sr][sc] = grid[sr][sc];
                     continue;
                }
        
                int minCost = (int)1e8;
                if(sr+1<grid.length) minCost = Math.min(minCost,dp[sr+1]);
                if(sc+1<grid[0].length) minCost = Math.min(minCost, dp[sc+1]);
        
                 dp[sr][sc] = minCost+grid[sr][sc];
            }
        }
        return dp[0][0];
    }

    /************************************************************************************************/
    // gfg -> friends pairing 
    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem/0
    public static void main (String[] args) {
	    long mod = (long)(1e9+7);
		//code
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		for(int idx = 0; idx<t; idx++){
		    int n = scn.nextInt();
		    long a = 1; 
		    long b = 1;
		    for(int i = 2; i<=n;i++){
		        long sum = (a*(i-1)+b)%mod;
		        a = b;
		        b = sum;
		    }
		    System.out.println(b%(mod));
		}
	}


    /************************************************************************************************/

}




