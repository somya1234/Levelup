public class leetcode {
    public static void main(String[] args) {
        solve();
    }


    public static void solve() {
        longestPalindromeSubseq(); // 516

    }

    /**********************************************************************************/

    // leetcode 516. Longest Palindromic Subsequence

    public int longestPalindromeSubseq(String s) {
        if(s.length()==0) return 0;
        
        int[][] dp = new int[s.length()][s.length()];
        for(int[] d: dp)
            Arrays.fill(d,-1);
        
        return lps(s,0,s.length()-1,dp);
    }
    
    public int lps(String s, int si, int ei, int[][] dp){
        if(si==ei) return dp[si][ei] = 1; 
        if(si>ei) return dp[si][ei] = 0; 
        
        if(dp[si][ei]!=-1) return dp[si][ei];
        
        if(s.charAt(si)==s.charAt(ei)) return dp[si][ei] =lps(s,si+1,ei-1,dp)+2;
        else{
            return dp[si][ei] = Math.max(lps(s,si,ei-1,dp),lps(s,si+1,ei,dp));
        } 
    }

    /****************************************************************************/
}
