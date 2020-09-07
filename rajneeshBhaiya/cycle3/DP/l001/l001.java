import java.util.LinkedList;

public class l001 {
    public static void main(String[] args) {
        
    }

    public static void solve(){
        int n = 5;
        int[] dp = new int[n+1];
        System.out.println(fibo2(n,dp));
    }

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
        return dp[n];
    }

    // optimized code 
    public static int fibo4(int n {
        int N = n ;
        int a = 0,b = 1;
        for(n=2;n<=N;n++){
            int sum = a+b;
            a = b;
            b = sum;
        }
        return a;
    }

    /************************************************************************************/

    // Question 2-> horizontal, vertical 

    public static void solve2(){
        int n = 3;
        int m = 3;
        int[][] dp = new int [n][m];
        System.out.println(horiVert2(0,0, n, m, dp));
    }

    public static int horiVert(int x, int y , int n , int m ){
        if(x==n || y==m){
            return 0;
        } else if(x==n-1 && y==m-1){
            return 1;
        }

        int h = horiVert(x, y+1, n, m);
        int v = horiVert(x+1, y, n, m);
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

    public static int horiVert3(int x, int y , int n , int m , int[][]dp){
    }

    /************************************************************************************/

    public static void solve3(){
        int n = 3;
        int[][] dp = new int[n][n];
        System.out.println(mazePathHVD(0, 0 , n-1, n-1 , dp);
    }

    // memoization 
    public static int mazePathHVD(int sr, int sc, int er, int ec, int[][] dp){
        if(sr==er && sc==ec){
            return dp[sr][er] = 1;
        }
        if(dp[sr][sc]!=0) return dp[sr][sc];

        int count = 0;
        if(sc+1<=ec) count+= mazePathHVD(sr, sc+1, er, ec, dp);
        if(sr+1<=er) count+= mazePathHVD(sr+1, sc, er, ec, dp);
        if(sr+1<=er && sc+1<=ec) count+=mazePathHVD(sr+1, sc+1, er, ec, dp);

        return dp[sr][er] = count;
    }

    //tabulation 
    public static int mazePathHVD2(int sr, int sc, int er, int ec, int[][] dp){

        for( sr = er;sr>=0; sr--){
            for(sc = ec; sc>=0; sc--){
                if(sr==er && sc==ec){
                    dp[sr][er] = 1;
                    continue;
                }
        
                int count = 0;
                if(sc+1<=ec) count+=   dp[sr][sc+1]; //mazePathHVD(sr, sc+1, er, ec, dp);
                if(sr+1<=er) count+=  dp[sr+1][sc]; //mazePathHVD(sr+1, sc, er, ec, dp);
                if(sr+1<=er && sc+1<=ec) count+= dp[sr+1][sc+1]; //mazePathHVD(sr+1, sc+1, er, ec, dp);
        
                dp[sr][er] = count;
            }
        }
        return dp[0][0];
        
    }

    /************************************************************************************/

    public static int mazePathHVDJump(int sr, int sc, int er, int ec, int[][] dp){

        for( sr = er;sr>=0; sr--){
            for(sc = ec; sc>=0; sc--){
                if(sr==er && sc==ec){
                    dp[sr][er] = 1;
                    continue;
                }
        
                int count = 0;
                for(int jump = 1; sc+jump<=ec; jump++) count+=   dp[sr][sc+jump]; //mazePathHVD(sr, sc+1, er, ec, dp);
                for(int jump = 1; sr+jump<=er; jump++) count+=  dp[sr+jump][sc]; //mazePathHVD(sr+1, sc, er, ec, dp);
                for(int jump = 1; sc+jump<=ec && sr+jump<=er; jump++) count+= dp[sr+jump][sc+jump]; //mazePathHVD(sr+1, sc+1, er, ec, dp);
        
                dp[sr][er] = count;
            }
        }
        return dp[0][0];
        
    }

    /************************************************************************************/

    // board path 
    public static void solve4(){
        int n = 10;
        int[] dp = new int[n+1];
        System.out.println(diceboard2(n, dp));
    }

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

    public static int diceboard2(int n, int[] dp ){
        if(n==0){
            return dp[n] = 1;
        }
        if(dp[n]!=0) return dp[n];

        int count = 0;
        for(int step = 1;step<=n && n-step>=0; step++){
            count+=diceboard2(n-step, dp);
        }
        return dp[n] = count;
    }

    public static int diceboard3(int n , int[] dp){
        int N = n;
        for( n = 0; n<=N;n++){
            if(n==0){
                dp[n] = 1;
                continue;
            }
    
            int count = 0;
            for(int step = 1;step<=n && n-step>=0; step++){
                count+=  dp[n-step]; //diceboard2(n-step, dp);
            }
            dp[n] = count;
        }
        return dp[n];
    }

    public static int diceboard4(int n ){
        // space complexity -> constant -> O(6)
        LinkedList<Integer> ll = new LinkedList<>();

        for(si=ei;si>=0; si--){
            if(si>=ei-1){
                ll.addFirst(1);
                continue;
            }

            if(ll.size()<7) ll.addFirst(ll.getFirst()*2);
            else {
                // last value.
                int lval = ll.removeLast();
                ll.addFirst(ll.getFirst()*2 - lval);
            }
        }
        return ll.getFirst();
        // ll.addFirst(1);
        // ll.addFirst(1);
        // while(n-->1){
        //     if(ll.size()<7){
        //         Node node = ll.removeFirst();
        //         ll.addFirst(node.data);
        //         ll.addFirst(2*node.data);
        //     } else {
        //         Node data1 = ll.removeFirst().data;
        //         Node data2 = ll.removeLast().data;
        //         ll.addFirst(data1);
        //         ll.addFirst(2*data1 - data2);
        //     }
        // }
        // while(ll.size()>1){
        //     ll.removeLast();
        // }
        // return ll.removeFirst().data;
    }

    /************************************************************************************/
    public static void solve5(){
        int n = 10;
        int[] arr= {1,3,5};
        int[] dp = new int[n+1];
        // no use of idx in repeated permutations.
        System.out.println(facedDice2(arr, 0, n, dp));
    }

    public static int facedDice(int[] arr, int idx, int n ){
        if(n==0) return 1;

        int count = 0;
        for(int i=0;i<arr.length && n-arr[i]>=0;i++){
            count+= facedDice(arr, idx, n-arr[i]);
        }
        return count;

    }

    public static int facedDice2(int[] arr, int idx, int n, int[] dp ){
        if(n==0) return dp[n] = 1;
        if(dp[n]!=0) return dp[n];
        
        int count = 0;
        for(int i=0;i<arr.length && n-arr[i]>=0;i++){
            count+= facedDice2(arr, idx, n-arr[i], dp);
        }
        return dp[n] = count;

    }

    // tabulation 
    public static int facedDice3(int[] arr, int idx, int n, int[] dp ){
        int N = n;
        for(n = 0; n<=N;n++){
            if(n==0) {
                dp[n] = 1;
                continue;
            }
            
            int count = 0;
            for(int i=0;i<arr.length && n-arr[i]>=0;i++){
                count+= dp[[n-arr[i]]];  //acedDice2(arr, idx, n-arr[i], dp);
            }
            dp[n] = count;
        }
        return dp[n];
    }
    /************************************************************************************/
}
