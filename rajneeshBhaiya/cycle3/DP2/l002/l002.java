import java.util.Arrays;

public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        solve1();
    }

    /*********************************************************************************************** */

    public static void print(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2d(int[][] arr) {
        for (int[] ar : arr) {
            print(ar);
        }
        System.out.println();
    }

    /************************************************************************************/
    public static void solve1() {
        // geeksforgeeks - 5
        String str = "geeksforgeeks";
        // bbabcbcab - 7
        // String str = "bbabcbcab";
        // String str = "abbca";
        // System.out.println(longestPlaindromicSubsq_Rec(0, str.length()-1, str));

        int n = str.length();
        int[][] dp = new int[n][n];
        // so that no extra calls on 0 like (si>ei) at this point it should not be
        // called
        // but called in memo due to reference point is 0 or base condn 0
        // eg aaaa, extra call on 0.

        //  if you fill -1.  then remember - (-1+2= 1)
        // for (int[] d : dp)
        //     Arrays.fill(d, -1);
        /*
         * -1 -1 -1 -1 -1 -1 -1 -1 4 4 5 5 5 -1 -1 2 2 2 2 2 2 -1 3 5 5 5 -1 0 1 1 1 1 1
         * 1 1 3 3 4 4 -1 -1 -1 1 1 1 1 1 1 1 -1 4 4 -1 -1 -1 -1 1 1 1 1 1 1 2 -1 4 -1
         * -1 -1 -1 -1 1 1 1 1 1 2 2 -1 -1 -1 -1 -1 -1 -1 1 1 1 1 2 2 -1 -1 -1 -1 -1 -1
         * -1 -1 1 1 1 2 2 -1 -1 -1 -1 -1 -1 -1 -1 -1 1 1 2 2 -1 -1 -1 -1 -1 -1 -1 -1 -1
         * -1 1 2 2 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 0 1 1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
         * -1 1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
         */

        //  memoization 
        // System.out.println(longestPlaindromicSubsq_Mem(0, n - 1, str, dp));
        // System.out.println(longestPlaindromicSubsq_Tab(0, n-1, str, dp));
        // print2d(dp);

        // display the longest palindromic subsequence string 
        int len = longestPlaindromicSubsq_Mem(0, n-1, str, dp);
        char[] ans = new char[len];
        System.out.println(len);
        printLPS(str, 0,n-1, dp, ans, 0, len-1);
    }

    public static int longestPlaindromicSubsq_Rec(int si, int ei, String str) {
        if (si == ei)
            return 1;
        if (si > ei)
            return 0;

        int ans = 0;
        if (str.charAt(si) == str.charAt(ei)) {
            ans = longestPlaindromicSubsq_Rec(si + 1, ei - 1, str) + 2;
        } else {
            int lans = longestPlaindromicSubsq_Rec(si, ei - 1, str);
            int rans = longestPlaindromicSubsq_Rec(si + 1, ei, str);
            ans = Math.max(lans, rans);
        }
        return ans;
    }

    public static int longestPlaindromicSubsq_Mem(int si, int ei, String str, int[][] dp) {
        if (si == ei)
            return dp[si][ei] = 1;
        if (si > ei)
            return dp[si][ei] = 0;

        if (dp[si][ei] != 0)
            return dp[si][ei];

        if (str.charAt(si) == str.charAt(ei))
            return dp[si][ei] = longestPlaindromicSubsq_Mem(si + 1, ei - 1, str, dp) + 2;
        else
            return dp[si][ei] = Math.max(longestPlaindromicSubsq_Mem(si, ei - 1, str, dp),
                    longestPlaindromicSubsq_Mem(si + 1, ei, str, dp));
    }

    public static int longestPlaindromicSubsq_Tab(int si, int ei, String str, int[][] dp) {
        int n = str.length();
        for (int diag = 0; diag < n; diag++) {
            for (si = 0, ei = diag; ei < n; si++, ei++) {
                if (si == ei){
                    dp[si][ei] = 1;
                    continue;
                }

                if (str.charAt(si) == str.charAt(ei))
                    dp[si][ei] = dp[si + 1][ei - 1] + 2;
                else
                     dp[si][ei] = Math.max(dp[si][ ei - 1],dp[si + 1][ ei]);
            }
        }
        return dp[0][n-1];
    }

    /************************************************************************************/
    // print longest plindromic subsequence

    // i and j for char[]
    // si and ei for string 
    // It gives single longest palindromic subsq.
    public static void printLPS(String str, int si, int ei, int[][] dp, char[] ans, int i, int j ){
        if(si>=ei) {
            if(si==ei)
                ans[i] = str.charAt(si);
            for(char ch : ans) System.out.print(ch);
            System.out.println();
            return;
        }

    
        // if(si>ei) return;

        if(str.charAt(si)==str.charAt(ei)){
            ans[i] = ans[j] = str.charAt(si);
            printLPS(str, si+1, ei-1, dp, ans, i+1, j-1);
        } else if(dp[si+1][ei]>dp[si][ei-1]){
            // because, here we only took max out of two, ans is not formed here
            // so can't add anything in our char[] ans.
            printLPS(str, si+1, ei, dp, ans, i, j);
        } else{
            printLPS(str, si, ei-1, dp, ans, i, j );
        }
    }



    /************************************************************************************/
}
