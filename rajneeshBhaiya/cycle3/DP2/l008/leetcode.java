public class leetcode {
    public static void main(String[] args) {
        
    }

    /********************************************************************************************/
    // Leetcode 312 - Burst Balloons

    public int maxCoins(int[] nums) {
        int n = nums.length; 
        int[][] dp = new int[n][n]; 
        return burstCoins(nums, 0, n-1, dp);
    }
    
    public int burstCoins(int[] nums, int si, int ei, int[][] dp){
        int n = nums.length; 
        if(si == ei) return dp[si][ei] = nums[si]; 
        if(si>ei ) return 0; 
        
        if(dp[si][ei]!=0) return dp[si][ei];
        
        int ans = 0; 
        for(int cut = si; cut<=ei; cut++){
           int leftTree =  burstCoins(nums, si, cut-1, dp); 
           int rightTree = burstCoins(nums, cut+1, ei, dp);
            int myAns = nums[cut]; 
            if((cut-1)>=0) myAns*=nums[cut-1]; 
            if((cut+1)<n) myAns*=nums[cut+1];
             myAns = leftTree+ rightTree + myAns; 
            ans = Math.max(ans, myAns); 
        }
        return dp[si][ei] =  ans; 
    }

    /********************************************************************************************/

}
