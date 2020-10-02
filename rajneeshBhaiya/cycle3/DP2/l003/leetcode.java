import java.util.Arrays;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // solve1();
        solve2(); 
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

    /***************************************************************************/
    // 940. Distinct Subsequences II

    // time - O(n), space - O(1).
    public int distinctSubseqII(String S) {

        int n = S.length();
        if (n == 0)
            return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        S = "$" + S;
        int[] loc = new int[26];
        Arrays.fill(loc, -1);

        int mod = (int) 1e9 + 7;

        for (int i = 1; i <= n; i++) {
            char ch = S.charAt(i);
            dp[i] = (dp[i - 1] * 2) % mod;

            if (loc[ch - 'a'] != -1) {
                int idx = loc[ch - 'a'];
                dp[i] = (dp[i] - dp[idx - 1] % mod + mod) % mod;
            }
            loc[ch - 'a'] = i;
        }
        // last dp[n]-1 to remove blank character.
        return dp[n] - 1;
    }

    /***************************************************************************/

    // leetcode 72 - Edit Distance. 
    public static void solve1() {
        // String s1 = "horse";
        // String s2 = "ros";

        String s1 = "intention";
        String s2 = "execution";
        // System.out.println(editDistance_Rec(s1, s2, 0, 0));

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        // System.out.println(editDistance_Mem(s1, s2, 0, 0, dp));
        for (int[] d : dp)
            Arrays.fill(d, -1);
        // System.out.println(editDistance_Mem_Backwards(s1, s2, n, m, dp));
        System.out.println(editDistance_tab(s1, s2, 0, 0, dp));
        print2d(dp);
    }

    public static int editDistance_Rec(String s1, String s2, int i, int j) {
        int n = s1.length();
        int m = s2.length();
        if (i == n || j == m)
            return i == 0 ? (m - j) : (n - i);

        char ch1 = s1.charAt(i);
        char ch2 = s2.charAt(j);
        int ans = 0;
        if (ch1 == ch2)
            ans += editDistance_Rec(s1, s2, i + 1, j + 1);
        else {
            // +1 because count 1 operation here also.
            int insert = editDistance_Rec(s1, s2, i, j + 1) + 1;
            int delete = editDistance_Rec(s1, s2, i + 1, j) + 1;
            int replace = editDistance_Rec(s1, s2, i + 1, j + 1) + 1;
            ans = Math.min(insert, Math.min(delete, replace));
        }
        return ans;
    }

    public static int editDistance_Mem(String s1, String s2, int i, int j, int[][] dp) {
        int n = s1.length();
        int m = s2.length();
        if (i == n || j == m)
            return dp[i][j] = i == 0 ? (m - j) : (n - i);

        if (dp[i][j] != 0)
            return dp[i][j];

        char ch1 = s1.charAt(i);
        char ch2 = s2.charAt(j);
        int ans = 0;
        if (ch1 == ch2)
            ans += dp[i][j] = editDistance_Mem(s1, s2, i + 1, j + 1, dp);
        else {
            // +1 because count 1 operation here also.
            int insert = editDistance_Mem(s1, s2, i, j + 1, dp) + 1;
            int delete = editDistance_Mem(s1, s2, i + 1, j, dp) + 1;
            int replace = editDistance_Mem(s1, s2, i + 1, j + 1, dp) + 1;
            ans = Math.min(insert, Math.min(delete, replace));
        }
        return dp[i][j] = ans;
    }

    // preferrable in backwards,
    public static int editDistance_Mem_Backwards(String s1, String s2, int i, int j, int[][] dp) {

        if (i == 0 || j == 0)
            return dp[i][j] = i == 0 ? j : i;

        if (dp[i][j] != -1)
            return dp[i][j];

        // one less in character idx in string
        char ch1 = s1.charAt(i - 1);
        char ch2 = s2.charAt(j - 1);
        int ans = 0;
        if (ch1 == ch2)
            ans += dp[i][j] = editDistance_Mem_Backwards(s1, s2, i - 1, j - 1, dp);
        else {
            // +1 because count 1 operation here also.
            int insert = editDistance_Mem_Backwards(s1, s2, i, j - 1, dp) + 1;
            int delete = editDistance_Mem_Backwards(s1, s2, i - 1, j, dp) + 1;
            int replace = editDistance_Mem_Backwards(s1, s2, i - 1, j - 1, dp) + 1;
            ans = Math.min(insert, Math.min(delete, replace));
        }
        return dp[i][j] = ans;
    }

    // for backwardds memoization , this is tabulation 
    // here, we start from base case. 
    public static int editDistance_tab(String s1, String s2, int i, int j, int[][] dp) {
        int n = s1.length();
        int m = s2.length();
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= m; j++) {
                if (i == 0 || j == 0){
                    dp[i][j] = i == 0 ? j : i;
                    continue; 

                }

                // one less in character idx in string
                char ch1 = s1.charAt(i - 1);
                char ch2 = s2.charAt(j - 1);
                int ans = 0;
                if (ch1 == ch2)
                    ans += dp[i-1][j-1];
                else {
                    // +1 because count 1 operation here also.
                    int insert = dp[i][j-1] + 1;
                    int delete = dp[i-1][j] + 1;
                    int replace = dp[i-1][j-1] + 1;
                    ans = Math.min(insert, Math.min(delete, replace));
                }
                dp[i][j] = ans;

            }
        }
        return dp[n][m]; 

    }

    /***************************************************************************/
    // leetcode 44 Wildcard Matching

    public static void solve2(){
        String s = "adceb"; 
        String p = "*a*b"; 
        // System.out.println(isMatch_rec(s, p, 0, 0));
        System.out.println();
        System.out.println("hi m hu ? ");
    }

    public static boolean isMatch_rec(String s, String p, int i, int j ){
        int n = s.length(); 
        int m = p.length();
        
        if(i == n && j == m ) return true ; 
        else if(i==n || j ==m ) return false; 

        char ch1 = s.charAt(i); 
        char ch2 = s.charAt(j); 
        if(ch1 == ch2 || ch2=='?'){
            return isMatch_rec(s, p, i+1, j+1);
        } else if(ch2 == '*'){
            return isMatch_rec(s, p, i, j+1) || isMatch_rec(s, p, i+1, j);
        } else {
            return false; 
        }
    } 


    /***************************************************************************/

}
