public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // solve1();
        // solve2();
        solve3();
    }

    /*********************************************************************************************** */

    public static void print(int[] arr){
        for(int ele:arr){
            System.out.print(ele+" ");
        }
        System.out.println();
    }

    public static void print2d(int[][] arr){
        for(int[] ar:arr){
            print(ar);
        }
        System.out.println();
    }

    /************************************************************************************/

    /***********************************************************************************************/
    // GFG -> Goldmine Problem 
    // https://www.geeksforgeeks.org/gold-mine-problem/

    public static void solve1(){
        int[][] mat = {{1, 3, 3},
                    {2, 1, 4},
                    {0, 6, 4}};

        //recursion
        // int maxValue = 0;
        // for(int x = 0;x<mat.length;x++)
        //     maxValue = Math.max(maxValue, maxGold(mat, x, 0));
        // System.out.println(maxValue);

        // memoization 
        // int maxValue = 0;
        // int[][] dp = new int[mat.length][mat[0].length];
        // for(int x = 0;x<mat.length;x++)
        //     maxValue = Math.max(maxValue, maxGold2(mat, x, 0, dp));
        // System.out.println(maxValue);

        // recursive 2 
        int n = 3;
        int[][] dir = {{-1,1},{0,1},{1,1}};
        int[][] dp = new int[n][n];
        int maxValue = 0;
        for(int i=0;i<n;i++){
            // maxValue = Math.max(maxValue, maxGold3(mat, dir,i, 0));
            maxValue = Math.max(maxValue, maxGold4(mat, dir,i, 0,dp));
            // maxValue = Math.max(maxValue, maxGold5(mat, dir,i, 0, dp));
        }
        print2d(dp);
        System.out.println(maxValue);
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

    // memoization 
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

    //recursive 2 
    public static int maxGold3(int[][] mat, int[][] dir, int x, int y ){
        if(y == mat[0].length-1){
            return mat[x][y];
        }

        int ans = 0;
        for(int d=0;d<dir.length;d++){
            int row = x+dir[d][0];
            int col = y+dir[d][1];
            if(row>=0 && row<mat.length && col<mat[0].length)
                ans = Math.max(ans,maxGold3(mat, dir, row, col));
        }
        return ans+mat[x][y];
    }

    // memoization 2 
    public static int maxGold4(int[][] mat, int[][] dir, int x, int y , int[][] dp){
        if(y == mat[0].length-1){
            return dp[x][y] = mat[x][y];
        }
        if(dp[x][y]!=0) return dp[x][y];
        int ans = 0;
        for(int d=0;d<dir.length;d++){
            int row = x+dir[d][0];
            int col = y+dir[d][1];
            if(row>=0 && row<mat.length && col<mat[0].length)
                ans = Math.max(ans,maxGold4(mat, dir, row, col,dp));
        }
        return dp[x][y] = ans+mat[x][y];
    }

    // tabulation 
    public static int maxGold5(int[][] mat, int[][] dir, int x, int y, int[][] dp){
        int i = x;
        for(y = mat[0].length-1 ; y >=0; y--){
            for(x = mat.length-1;x>=i; x--){
                if(y == mat[0].length-1){
                     dp[x][y] = mat[x][y];
                     continue;
                }
                int ans = 0;
                for(int d=0;d<dir.length;d++){
                    int row = x+dir[d][0];
                    int col = y+dir[d][1];
                    if(row>=0 && row<mat.length && col<mat[0].length)
                        ans = Math.max(ans,dp[row][col]);
                }
                dp[x][y] = ans+mat[x][y];
            }
        }
        return dp[i][0];
    }


/***********************************************************************************************/
// https://www.geeksforgeeks.org/friends-pairing-problem/
    public static void solve2(){
        int n = 7;
        int[] dp = new int[n+1];
        System.out.println(friendsPairing(n));
        // System.out.println(friendsPairing(n, dp));
        System.out.println(friendsPairing2(n, dp));
        print(dp);
        System.out.println(friendsPairing4(n));
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
        if(n<=1) return dp[n] = 1;
        if(dp[n]!=0) return dp[n];

        int single  = friendsPairing(n-1, dp);
        int pairUp = friendsPairing(n-2, dp) * (n-1);
       return dp[n] = single + pairUp; 
    }

    // tabulation 
    public static int friendsPairing2(int n , int[] dp ){
        int N = n;
        for(n = 0; n<=N;n++){
            if(n<=01) {
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

public static void solve3(){
    int n = 5;
    int k = 3;
    if(n<k){
        System.out.println(0);
        return;
    }
    System.out.println(countPartitionWays(n, k));
    int[][] dp = new int[n][k];
    // System.out.println(countPartitionWays2(n, k, dp));
    System.out.println(countPartitionWays3(n, k, dp));
    print2d(dp);
}

//recursion 
public static int countPartitionWays(int n, int k){
    if(k==1 || n==k){
        return 1;
    }

    int alone = countPartitionWays(n-1, k-1);
    int withOthers = countPartitionWays(n-1, k);
    int ans = alone + (k*withOthers);
    return ans;
}

// memoization 
public static int countPartitionWays2(int n , int k , int[][] dp){
    if(k==1 || n<=k){
        return dp[n-1][k-1] = 1;
    }
    if(dp[n-1][k-1]!=0) return dp[n-1][k-1];

    int ownSet = countPartitionWays2(n-1, k-1, dp);
    int partOfSet = countPartitionWays2(n-1, k, dp);

    return dp[n-1][k-1] = ownSet+ ((k)*partOfSet);
}

// tabulation 
public static int countPartitionWays3(int n , int k , int[][] dp){
    int N = n;
    int K = k;
    for(n=1;n<=N;n++){
        for(k=1;k<=K;k++){
            if(k==1 || n<=k){
                 dp[n-1][k-1] = 1;
                 continue;
            }
        
            int ownSet =  dp[n-2][k-2]; //countPartitionWays2(n-1, k-1, dp);
            int partOfSet =  dp[n-2][k-1]; //countPartitionWays2(n-1, k, dp);
        
            dp[n-1][k-1] = ownSet+ ((k)*partOfSet);
        }
    }
    return dp[N-1][K-1];
}

/***********************************************************************************************/
}
