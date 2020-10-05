public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /******************************************************************************************/

    //1218 

    // wrong soln due to time limit 
    public int longestSubsequence(int[] arr, int difference) {
        
        int n = arr.length; 
        int[] dp = new int[n]; 
        dp[0] = 1; 
        
        for(int i =1; i<n; i++){
            int max = 0;
            for(int j =i-1; j>=0; j--){
                if(arr[i]-arr[j]==difference){
                    max = Math.max(max, dp[j]); 
                }
            }
            dp[i] = max+1; 
        }
        
        int count =0; 
        for(int i=0; i<n;i++){
            count = Math.max(count, dp[i]);
        }
        return count; 
    }

    // correct solution 
    public int longestSubsequence(int[] arr, int difference) {
        
        int d = difference; 
        int maxLen = 0; 
        HashMap<Integer, Integer> hm = new HashMap<>(); 
        for(int ele: arr){
            // find longest len of AP of ele-d or return default value, i.e 0
            hm.put(ele, hm.getOrDefault(ele-d, 0)+1); 
            maxLen = Math.max(maxLen, hm.get(ele)); 
        }
        return maxLen; 
    }

    /******************************************************************************************/
    
    // 1027. Longest Arithmetic Subsequence

    public int longestArithSeqLength(int[] A) {
        int n = A.length; 
        
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for(int i= 0; i<n; i++){
            dp[i] = new HashMap<>(); 
        }
        
        int len = 0; 
        
        dp[0].put(0, 1);
        for(int i= 1; i<n; i++){
            for(int j=i-1; j>=0; j--){
                int diff = A[i]-A[j];
                int currLen = dp[i].getOrDefault(diff, 0);
                int newLen =  dp[j].getOrDefault(diff,1)+1;  
                
                dp[i].put(diff, Math.max(currLen, newLen));
                len = Math.max(len, dp[i].get(diff));
            }
        }
        return len;
    }

    /******************************************************************************************/
}
