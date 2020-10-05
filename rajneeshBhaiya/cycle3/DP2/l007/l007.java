import java.util.Arrays;

public class l007 {
    public static void main(String[] args) {
        solve();
    }

    /*********************************************************************************/

    public static void print(int[] arr){
        for(int ele: arr)
        System.out.print(ele+" ");

        System.out.println();
    }

    public static void print2D(int[][] arr){
        for(int[] ar: arr) print(ar);
        System.out.println();
    }

    /************************************************************************************/


    public static void solve(){
        // solve1();
        solve2(); 
    }

    /************************************************************************************/

    //gfg - Matrix Chain Multiplication 
    // https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/

    public static void solve1(){
        // int[] arr = {1,2,3,4,3,7,5,10,45,234};
        int[] arr = {40, 20, 30, 10, 30};
        int n = arr.length;

        int[][] dp = new int[n][n];
        
        // memoization
        // for(int[] d: dp) Arrays.fill(d, -1);
        // System.out.println(mcm_rec(arr,0,n-1,dp));
        // print2D(dp);

        //tabulation 
        System.out.println(mcm_tab(arr, 0, n-1, dp));
        print2D(dp);
    }

    public static int mcm_rec(int[] arr, int si, int ei, int[][] dp){
        if(si+1 == ei) return dp[si][ei] = 0 ; 

        if(dp[si][ei]!=-1) return dp[si][ei]; 

        int myAns = (int)1e9; 

        for(int cut = si+1; cut<ei; cut++){
            int leftTree = mcm_rec(arr, si, cut , dp); 
            int rightTree = mcm_rec(arr, cut , ei, dp); 

            int myCost = leftTree + arr[si]* arr[cut] * arr[ei] + rightTree; 
            myAns = Math.min(myAns,myCost); 

        }

        return dp[si][ei] = myAns; 
    }

    // O(n3) complexity time.
    //space - O(n2)
    public static int mcm_tab(int[] arr, int si, int ei, int[][] dp){
        int Ei = ei; 
        for(int gap = 1; gap<=Ei; gap++){
            for(si = 0, ei = gap; ei<=Ei; si++, ei++){
                if(si+1 == ei ){
                    dp[si][ei] = 0; 
                    continue; 
                } else{
                    int myAns = (int)1e9; 
                    for(int cut = si+1; cut<ei; cut++){
                        int myCost = dp[si][cut] + dp[cut][ei] + (arr[si] * arr[cut] * arr[ei] ); 
                        myAns = Math.min(myAns, myCost); 
                    }
                    dp[si][ei] = myAns; 
                }
            }
        }
        return dp[0][Ei]; 
    }

    /************************************************************************************/

    //gfg - Min Max Values of Expression 
    // https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    public static void solve2(){
        String expr = "1+2*3+4*5"; 
        StringBuilder num = new StringBuilder(); 
        StringBuilder symbol = new StringBuilder(); 
        int n = expr.length();
        for(int i =0; i<n; i++){
            if(i%2==0) num.append(expr.charAt(i)); 
            else symbol.append(expr.charAt(i));
        }
        String nums = num.toString(); 
        n = nums.length(); 
        String symbols = symbol.toString(); 
        int[][] dp = new int[n][n]; 
        int[] ans = minMaxValue(0, n-1, nums, symbols, dp);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }

    public static int[] minMaxValue(int si, int ei, String nums, String symbols, int[][] dp){
        if(si == ei) return new int[]{nums.charAt(si) - '0', nums.charAt(si) - '0'}; 

        int minAns = (int)1e9; 
        int maxAns = -(int)1e9; 

        for(int cut = si+1; cut<ei ;cut++){
            int[] leftTree = minMaxValue(si, cut, nums, symbols, dp); 
            int[] rightTree = minMaxValue(cut, ei, nums, symbols, dp);
            char ch = symbols.charAt(cut-1); 
            int myCostMin = evaluate(ch, leftTree[0], rightTree[0]); 
            int myCostMax = evaluate(ch, leftTree[1], rightTree[1]); 
            minAns = Math.min(minAns, myCostMin); 
            maxAns = Math.max(maxAns, myCostMax); 
        }
        int[] ans = new int[]{minAns, maxAns};
        return ans; 

    }

    public static int evaluate(char ch, int x, int y){
        if(ch == '+'){
            return x + y; 
        } else if(ch == '-') return x-y; 
        else if(ch == '*') return x*y; 
        else if(ch == '/') return x/y; 
         else return 0; 
    }


    /************************************************************************************/
}
