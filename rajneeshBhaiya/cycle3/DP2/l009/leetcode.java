public class leetcode {
    public static void main(String[] args) {
        
    }

    // leetcode 45 
    public int jump(int[] nums) {
        int n = nums.length; 
        int[] dp = new int[n]; 
        Arrays.fill(dp, -1); 
        return jump(nums, 0, dp); 
    }
    
    public int jump(int[] nums, int idx, int[] dp){
        if(idx == nums.length -1) return dp[idx] = 0; 
        
        if(nums[idx] == 0) return dp[idx] = (int)1e8; 
        
        if(dp[idx]!=-1) return dp[idx]; 
        
        
        int minSteps = (int)1e8; 
        for(int jump = 1; jump+idx<nums.length && jump<=nums[idx]; jump++ ){
            int recAns = jump(nums, idx+jump, dp); 
            if(recAns!=(int)1e8){
                minSteps = Math.min(minSteps, recAns + 1); 
            }
        }
        return dp[idx] = minSteps; 
    }
}
