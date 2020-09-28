import java.util.LinkedList;

public class revision {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // solve1();
        solve2();
    }
     /************************************************************************************************/

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

    /*******************************************************************************/
    public static void solve1(){
        int n = 3, m = 3;
        System.out.println(multiJumpsPath_Rec(n, m , 0, 0 ));
        int[][] dp = new int[n][m]; 
        System.out.println(multiJumpsPath_Mem(n, m, 0, 0, dp));
    }

    public static int multiJumpsPath_Rec(int n , int m , int row, int col){
        if(row == n-1 && col == n-1) return 1; 

        int ans = 0; 
        for(int step = 1; row+step<n ; step++){
            ans+= multiJumpsPath_Rec(n, m, row+step, col);
        }

        for(int step = 1; col+step<m; step++){
            ans+= multiJumpsPath_Rec(n, m, row, col+step);
        }

        return ans; 
    }

    public static int multiJumpsPath_Mem(int n , int m , int row, int col, int[][] dp){
        if(row == n-1 && col == n-1) return dp[row][col] = 1; 

        if(dp[row][col]!=0) return dp[row][col];

        int ans = 0; 
        for(int step = 1; row+step<n ; step++){
            ans+= multiJumpsPath_Mem(n, m, row+step, col, dp);
        }

        for(int step = 1; col+step<m; step++){
            ans+= multiJumpsPath_Mem(n, m, row, col+step, dp);
        }

        return dp[row][col] = ans; 
    }

    //take direction array 
    public static int multiJumpsPath_Mem2(int n , int m , int row, int col, int[][] dp, int[][] dir){
        if(row == n-1 && col == n-1) return dp[row][col] = 1; 

        if(dp[row][col]!=0) return dp[row][col];

        int ans = 0; 
        for(int d= 0; d<dir.length; d++){
            for(int jump = 1; jump<=Math.max(n,m); jump++){
                int r = row+jump*dir[d][0]; 
                int c = col + jump*dir[d][1]; 

                if(r>=0 && c>=0 && r<n && c<m){
                    ans+=multiJumpsPath_Mem2(n, m, r, c, dp, dir);
                } else break;
            }
        }

        return dp[row][col] = ans; 
    }


    public static int multiJumpsPath_Tab(int n , int m , int row, int col, int[][] dp, int[][] dir){
        
        for(row = n-1; row>=0; row--){
            for(col = m-1; col>=0; col--){
                if(row == n-1 && col == n-1) {
                     dp[row][col] = 1; 
                    continue;
                }

                int ans = 0; 
                for(int d= 0; d<dir.length; d++){
                    for(int jump = 1; jump<=Math.max(n,m); jump++){
                        int r = row+jump*dir[d][0]; 
                        int c = col + jump*dir[d][1]; 

                        if(r>=0 && c>=0 && r<n && c<m){
                            ans+=dp[r][c];
                        } else break;
                    }
                }

                 dp[row][col] = ans; 
            }
        }
        return dp[0][0];
    }


    /*******************************************************************************/

    public static void solve2(){
        int n = 10; 
        System.out.println(diceboard_Rec(n, 0));
    }

    public static int diceboard_Rec(int n, int src){
        if(src == n )return 1;

        int count = 0; 
        for(int jump = 1; jump<=6; jump++){
            if(src+jump<=n){
                count+= diceboard_Rec(n, src+jump);
            }
        }
        return count; 
    }
    
    public static int diceboard_Mem(int n, int src, int[] dp){
        if(src == n )return dp[src] = 1;

        if(dp[src]!=0) return dp[src]; 

        int count = 0; 
        for(int jump = 1; jump<=6 && src+jump<=n; jump++){
            count+= diceboard_Mem(n, src+jump, dp);
        }
        return dp[src] = count; 
    }

    public static int diceboard_Tab(int n, int src, int[] dp){
        for(src = n; src >=0; src--){
            if(src == n ){
                dp[src] = 1;
                continue;
            } 

            int count = 0; 
            for(int jump = 1; jump<=6 && src+jump<=n; jump++){
                count+= dp[src+jump];
            }
            dp[src] = count; 
        }
        return dp[0];
    }

    public static int diceboard_opti(int n ){
        LinkedList<Integer> list = new LinkedList<>();
        for(int src = n; src>=0; src--){
            if(src>=n-1){
                list.addFirst(1); 
                continue; 
            }

            if(list.size()<=6) list.addFirst(list.getFirst()*2);
            else{
                int lval = list.removeLast(); 
                list.addFirst(list.getFirst()*2 - lval);
            }
        }
        return list.getFirst();
    }

    // optimized 2 (using array )

    /*******************************************************************************/
}
