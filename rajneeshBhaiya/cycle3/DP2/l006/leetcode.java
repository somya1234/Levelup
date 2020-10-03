public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /*****************************************************************************/

    // leetcode 300 - O(n2)
    public int lengthOfLIS(int[] nums) {
        int n = nums.length; 
        int[] dp = new int[n]; 
        Arrays.fill(dp, 1); 
        for(int i=1; i<n; i++){
            int max = 0;
            for(int j =0; j<i; j++){
                if(nums[i]>nums[j]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] += max; 
        }
        
        int max = 0; 
        for(int i =0; i<n; i++){
            max = Math.max(max, dp[i]);
        }
        return max; 
    }

    /*****************************************************************************/

    // leetcode 673 
    public int findNumberOfLIS(int[] nums) {
        
        int n = nums.length; 
        if(n == 0 ) return 0; 
        
        int[] dp = new int[n]; 
        int[] count = new int[n]; 
        dp[0] = 1; count[0] = 1; 
        int maxLen = 1; 
        int maxCount = 1; 
        
        for(int i =1; i<n; i++){
            for(int j =0; j<i; j++){
                if(nums[j]<nums[i]){
                    if(dp[i]<dp[j]+1){
                        dp[i] = dp[j]+1;
                        count[i] = count[j];
                    } else if(dp[i]==dp[j]+1){
                        count[i]+=count[j];
                    }
                } else if(dp[i]==0){
                        // eg 2. 2. 2, 2
                        // when no LIS
                        dp[i] = 1; 
                        count[i] = 1; 
                    }
            }
            if(dp[i]>maxLen){
                maxLen = dp[i]; 
                maxCount = count[i]; 
            } else if(dp[i]==maxLen){
                maxCount+= count[i]; 
            }
        }
        return maxCount; 
    }

    /*****************************************************************************/
}
