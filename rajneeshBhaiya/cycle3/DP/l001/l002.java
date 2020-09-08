public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // solve1();
        // solve2();
        // solve3();
        solve4();
        // solve5();
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
    public static void solve1(){
        int n = 7;
        int[] dp = new int[n+1];
        System.out.println(fibo(n));
        System.out.println(fibo2(n,dp));
        print(dp);
        System.out.println(fibo3(n, new int[n+1]));
        System.out.println(fibo4(n));
    }

    public static int fibo(int n ){
        if(n<=1){
            return n;
        }

        int a = fibo(n-1);
        int b = fibo(n-2);
        return a + b;
    }

    public static int fibo2(int n, int[] dp ){
        if(n<=1){
            return  dp[n] = n;
        }

        if(dp[n]!=0)
        {
            return dp[n];
        }

        int a = fibo2(n-1,dp);
        int b = fibo2(n-2,dp);
        return dp[n]  = a + b;
    }


    public static int fibo3(int n , int[] dp){
        int N = n ;
        for(n=0;n<=N;n++){
            if(n<=1){
                dp[n] = n;
                continue;
            }
            int a = dp[n-1];
            int b = dp[n-2];

            dp[n] = a+b;
        }
        return dp[N];
    }

    // optimized code 
    public static int fibo4(int n ){
        int N = n ;
        int a = 0,b = 1;
        for(n=1;n<=N;n++){
            int sum = a+b;
            a = b;
            b = sum;
        }
        // if loop from n=2, then return b.
        return a;
    }

    /************************************************************************************/

    // Question 2-> horizontal, vertical 

    public static void solve2(){
        int n = 3;
        int m = 3;
        int[][] dp = new int [n][m];
        System.out.println(horiVert(0, 0, n, m));
        System.out.println(horiVert2(0,0, n, m, dp));
        print2d(dp);
        dp = new int [n][m];
        System.out.println(horiVert3(0, 0, n, m, dp));
    }

    public static int horiVert(int x, int y , int n , int m ){
       if(x==n-1 && y==m-1){
            return 1;
        }

        int h = 0, v = 0;
        if(y+1<m)
         h = horiVert(x, y+1, n, m);
        if(x+1<n)
         v = horiVert(x+1, y, n, m);
        return h+v;
    }

    public static int horiVert2(int x, int y , int n , int m , int[][]dp){
        if(x==n || y==m){
            return 0;
        } else if(x==n-1 && y==m-1){
            return dp[x][y] = 1;
        }

        if(dp[x][y]!=0){ return dp[x][y]; }

        int h = horiVert2(x, y+1, n, m, dp);
        int v = horiVert2(x+1, y, n, m, dp);
        return dp[x][y] = h+v;
    }

    // in tabulation pre-ctive approaches are used 
    // sp prefer them in memoization. 
    public static int horiVert3(int x, int y , int n , int m , int[][]dp){
        for(x=n-1;x>=0;x--){
            for(y=m-1;y>=0;y--){
                if(x==n-1 && y==m-1){
                     dp[x][y] = 1;
                     continue;
                }

                int h=0,v = 0;
                if(y+1<m)
                 h = dp[x][y+1];
                if(x+1<n)
                 v = dp[x+1][y];
                 dp[x][y] = h+v;
            }
        }
        return dp[0][0];
    }

    /************************************************************************************/

    public static void solve3(){
        int n = 3;
        int[][] dp = new int[n][n];
        System.out.println(mazePathHVD(0, 0 , n-1, n-1 , dp));
        print2d(dp);
        dp = new int[n][n];
        System.out.println(mazePathHVD2(0, 0, n-1, n-1, dp));
        print2d(dp);
    }

    // memoization 
    public static int mazePathHVD(int sr, int sc, int er, int ec, int[][] dp){
        if(sr==er && sc==ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc]!=0) return dp[sr][sc];

        int count = 0;
        if(sc+1<=ec) count+= mazePathHVD(sr, sc+1, er, ec, dp);
        if(sr+1<=er) count+= mazePathHVD(sr+1, sc, er, ec, dp);
        if(sr+1<=er && sc+1<=ec) count+=mazePathHVD(sr+1, sc+1, er, ec, dp);

        return dp[sr][sc] = count;
    }

    //tabulation 
    public static int mazePathHVD2(int sr, int sc, int er, int ec, int[][] dp){

        for( sr = er;sr>=0; sr--){
            for(sc = ec; sc>=0; sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count = 0;
                if(sc+1<=ec) count+=   dp[sr][sc+1]; //mazePathHVD(sr, sc+1, er, ec, dp);
                if(sr+1<=er) count+=  dp[sr+1][sc]; //mazePathHVD(sr+1, sc, er, ec, dp);
                if(sr+1<=er && sc+1<=ec) count+= dp[sr+1][sc+1]; //mazePathHVD(sr+1, sc+1, er, ec, dp);
        
                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
        
    }

    /************************************************************************************/

    public static void solve4(){
        int n = 4;
        System.out.println(mazePathJumps(0, 0, n));
        int[][] dp = new int[n][n];
        System.out.println(mazePathHVDJump(0, 0, n-1, n-1, dp));
        print2d(dp);
    }

    // recursion 
    public static int mazePathJumps(int x, int y, int n ){
        if(x==n-1 && y == n-1){
            return 1;
        }
        int count = 0;
        for(int jump=1;x+jump<n ; jump++) count+= mazePathJumps(x+jump, y, n);
        for(int jump=1;y+jump<n ; jump++) count+= mazePathJumps(x, y+jump, n);
        for(int jump=1;x+jump<n && y+jump<n ; jump++) count+= mazePathJumps(x+jump, y+jump, n);
        return count;
    }

    // tabulation 
    public static int mazePathHVDJump(int sr, int sc, int er, int ec, int[][] dp){

        for( sr = er;sr>=0; sr--){
            for(sc = ec; sc>=0; sc--){
                if(sr==er && sc==ec){
                    dp[sr][sc] = 1;
                    continue;
                }
        
                int count = 0;
                for(int jump = 1; sc+jump<=ec; jump++) count+=   dp[sr][sc+jump]; //mazePathHVD(sr, sc+1, er, ec, dp);
                for(int jump = 1; sr+jump<=er; jump++) count+=  dp[sr+jump][sc]; //mazePathHVD(sr+1, sc, er, ec, dp);
                for(int jump = 1; sc+jump<=ec && sr+jump<=er; jump++) count+= dp[sr+jump][sc+jump]; //mazePathHVD(sr+1, sc+1, er, ec, dp);
        
                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
        
    }

    /*******************************************************************************************/

    public static void solve5(){
        int n = 10;
        int[] dp = new int[n+1];
        // System.out.println(diceboard2(n, dp));
        System.out.println(boardPath2(0, n, dp));
        dp = new int[n+1];
        System.out.println(boardPath3(0, n, dp));
    }

    // recursion from method 2 
    public static int diceboard(int n ){
        if(n==0){
            return 1;
        }

        int count = 0;
        for(int step = 1;step<=n && n-step>=0; step++){
            count+=diceboard(n-step);
        }
        return count;
    }

    // memoization 
    public static int boardPath2(int si,int ei,int[] dp){
        if(si==ei){
            return dp[si] = 1;
        }

        if(dp[si]!=0) return dp[si];

        int count=0;
        for(int dice = 1; dice <= 6 && si + dice <= ei ; dice++){
            count+=boardPath2(si+dice,ei,dp);
        }

        return dp[si] = count;
    }

    //tabulation 
    public static int boardPath3(int si,int ei,int[] dp){
        for(si= ei;si>=0;si--){
            if(si==ei){
                 dp[si] = 1;
                 continue;
            }
    
            int count=0;
            for(int dice = 1; dice <= 6 && si + dice <= ei ; dice++){
                count+=dp[si+dice];
            }
    
            dp[si] = count;
        }
        return dp[0];
       
    }


    /*******************************************************************************************/
}
