public class l004 {
    
}

public int numDecodings(String s,int idx,int[] dp){
    if(idx >= s.length()){
        return dp[idx] = 1;
    }
    
    if(dp[idx] != 0) return dp[idx];
    
    
    char ch = s.charAt(idx);
    if(ch == '0') return 0;
    
    int count = 0;
    
    count += numDecodings(s,idx+1,dp);
    if(idx < s.length()-1){
        char ch1 = s.charAt(idx+1);
        int num = ( ch - '0') * 10 + (ch1 - '0');
        if(num <= 26){
            count += numDecodings(s,idx + 2,dp);
        }
    }
    
    return dp[idx] = count;
}
