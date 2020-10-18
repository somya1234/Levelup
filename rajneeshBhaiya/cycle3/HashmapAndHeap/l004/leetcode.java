public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    /*******************************************************************************/
    // 525. Contiguous Array
    public int findMaxLength(int[] nums) {
        
        HashMap<Integer, Integer> map = new HashMap<>(); 
        map.put(0, -1);
        int sum = 0; 
        int maxLen = 0; 
        
        for(int i=0; i<nums.length; i++){
            // add -1 if ele is 0, otherwise 1 hi
            sum+= nums[i] ==1 ? 1 : -1; 
            int val = sum - 0 ; 
            if(map.containsKey(val)) maxLen = Math.max(maxLen, i- map.get(val)); 
            
            if(!map.containsKey(sum)) map.put(sum, i); 
        }
        
        return maxLen; 
    }

    /*******************************************************************************/
    
    // 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(); 
        if(n<=1) return n; 
        int[] freq = new int[256]; 
        int si = 0, ei = 0,len = 0; 
        
        int count = 0; 
        while(ei<n){
            char ch = s.charAt(ei++);
            //yaani phle se aa chuka tha 
            if(freq[ch]++ > 0 ) count++; 
            
            while(count>0){
                if(freq[s.charAt(si++)]-->1) count--; 
            }
            
            len = Math.max(len, ei-si); 
        }
        return len; 
    }

    /*******************************************************************************/
}
