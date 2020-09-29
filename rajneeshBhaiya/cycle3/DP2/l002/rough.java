public class rough {
    
}

public static int lCSS_02(String s1,String s2,int n,int m,int[][] dp){
    if(n == 0 || m == 0){
        return dp[i][j] = 0;
    }

    if(dp[i][j] != 0) return dp[i][j];

    if(s1.charAt(n-1) == s2.charAt(m-1)) return dp[i][j] = lCSS(s1,s2,n-1,m-1,dp) + 1;
    else return dp[i][j] = Math.max(lCSS(s1,s2,n-1,j,dp),lCSS(s1,s2,i,m-1,dp));        
}