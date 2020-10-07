public class l009 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /*************************************************************************************/
    public static int optimalBst(int[] val, int[] freq, int si, int ei, int[][] dp){

        int cost = (int)1e9; 
        for(int cut = si; cut<=ei; cut++){
            int leftTree = (cut == si) ? 0 : optimalBst(val, freq, si, cut - 1, dp); 
            int rightTree = (cut == ei) ? 0 : optimalBst(val, freq, cut + 1, ei, dp); 
            int myCost = ; 
            cost = Math.min(cost, myCost);
        }

        dp[si][ei] = cost; 
    }

    /**********************************************************************************************/

    //leetcode 132 
    public static int minCut_01(String str, int si, int ei, int[][] dp){

        for(int cut = si; cut<ei; cut++){
            int leftTree = minCut_01(str, si, , dp)
        }

    }

    /**********************************************************************************************/
}
