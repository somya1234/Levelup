public class rough {
    
}

public static int longestPSS(String str,int i ,int j,int[][] dp){
    if(i >= j){
        return dp[i][j] = (i == j ? 1 : 0);
    }

    if(dp[i][j] != 0) return dp[i][j];

    if(str.charAt(i) == str.charAt(j)) return dp[i][j] = longestPSS(str,i+1,j-1,dp) + 2;
    else return dp[i][j] = Math.max(longestPSS(str,i+1,j,dp),longestPSS(str,i,j-1,dp));
}
