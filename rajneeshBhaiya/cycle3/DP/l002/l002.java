public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        solve1();
    }

    /***********************************************************************************************/
    public static void solve1(){
        //recursion
        // int maxValue = 0;
        // for(int x = 0;x<mat.length;x++)
        //     maxValue = Math.max(maxValue, maxGold(mat, x, 0));
        // System.out.println(maxValue);

        // memoization 
        int maxValue = 0;
        int[][] mat = {{1, 3, 3},
                    {2, 1, 4},
                    {0, 6, 4}};
        int[][] dp = new int[mat.length][mat[0].length];
        for(int x = 0;x<mat.length;x++)
            maxValue = Math.max(maxValue, maxGold2(mat, x, 0, dp));
        System.out.println(maxValue);
    }

    public static int goldMinProblem(int[][] coins){
        int[][] dp = new int[mat.length][mat[0].length];
        int[][] dir = {{-1,1},{0,1},{1,1}};

        int max = 0;
        for(int i=0;i<grid.length; i++){
            max = math.max(max,goldMinProblem(coins,0, 0, dp, dir));
        }
        return max;
    }

    // memoization 
    public static int goldMinProblem(int[][] coins, int sr, int sc, int[][] dp, int[][] dir){
        if(sc==coins[0].length-1){
            return dp[sr][sc] = coins[sr][sc];
        }

        if(dp[sr][sc]!=0) return dp[sr][sc];
        for(int d=0;d<3;d++){
            int r = sr+ dir[d][0];
            int c = sc+ dir[d][1];

            if(r>=0 && c>=0 && r<grid.length && c< grid[0].length){
                dp[sr][sc] = Math.max(dp[sr][sc], goldMinProblem(coins, sr, sc, dp, dir)+ coins[sr][sc]);
            }
        }
        return dp[sr][sc];
    }

    //recursion 
    public static int maxGold(int[][] mat, int x, int y){

        int n = mat.length;
        int m = mat[0].length;

        if(y==m-1){
            return mat[x][y];
        }

        int v1 = 0, v2 = 0, v3 = 0;
        if(x-1>=0 && y+1<m)
            v1 = maxGold(mat, x-1, y+1);
        if(y+1<m)
            v2 = maxGold(mat, x, y+1);
        if(x+1<n && y+1<m)
            v3 = maxGold(mat, x+1, y+1);
        return Math.max(v1, Math.max(v2,v3)) + mat[x][y];
    }

    public static int maxGold2(int[][] mat, int x, int y, int[][] dp ){

        int n = mat.length;
        int m = mat[0].length;

        if(y==m-1){
            return dp[x][y] = mat[x][y];
        }
        if(dp[x][y]!=0) return dp[x][y];

        int v1 = 0, v2 = 0, v3 = 0;
        if(x-1>=0 && y+1<m)
            v1 = maxGold2(mat, x-1, y+1,dp);
        if(y+1<m)
            v2 = maxGold2(mat, x, y+1,dp);
        if(x+1<n && y+1<m)
            v3 = maxGold2(mat, x+1, y+1, dp);
        return dp[x][y] =  Math.max(v1, Math.max(v2,v3)) + mat[x][y];
    }

/***********************************************************************************************/
// https://www.geeksforgeeks.org/friends-pairing-problem/
    public static void solve2(){
        int[] dp = new int[n+1];
    }

    // recursion 
    public static int friendsPairing(int n ){
        if(n==0) return 1;

        int a = 0, b = 0;
        if(n-1>=0)
        a = friendsPairing(n-1);
        if(n-2>=0)
        b = friendsPairing(n-2);
        int ans = a + ((n-1) * b);
        return ans; 
    }

    // memoization -> v.imp
    public static int friendsPairing(int n , int[] dp ){
        if(n<=0) return dp[n] = 1;
        if(dp[n]!=0) return dp[n];

        int single  = friendsPairing(n-1, dp);
        int pairUp = friendsPairing(n-2, dp) * (n-1);
       return dp[n] = single + pairUp; 
    }

    // tabulation 
    public static int friendsPairing2(int n , int[] dp ){
        int N = n;
        for(n = 0; n<=N;n++){
            if(n==0) {
                dp[n] = 1;
                continue;
            } 
        
            int a = 0, b = 0;
            if(n-1>=0)
            a = dp[n-1];
            if(n-2>=0)
            b = dp[n-2];
            int ans = a + ((n-1) * b);
             dp[n] = ans; 
        }

        return dp[N];
    }
    
    // optimized code 
    public static int friendsPairing4(int n){
        int N = n;
        int a = 1;
        int b = 1;
        for(n = 2; n<=N;n++){
            int sum = b+(n-1)*a;
            a = b;
            b = sum;
        }
        return b;
    }

/***********************************************************************************************/
// https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/



/***********************************************************************************************/
}
