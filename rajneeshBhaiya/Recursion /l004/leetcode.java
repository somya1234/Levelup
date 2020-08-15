import java.util.*;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // int n = 16;
        // System.out.println(isPowerOfTwo(n));
        // System.out.println(isPowerOfTwo02(n));
        // System.out.println(isPowerOfFour(n));
        // System.out.println(isPowerOfFour02(n));

        // int[] nums = {4,1,2,1,2};
        // System.out.println(singleNumber(nums));
        // int[] nums = {2,2,1,2};
        // System.out.println(singleNumberII(nums));
        // int[] nums = {1,2,1,3,2,5};
        // int[] arr = singleNumberIII(nums);
        // System.out.println(arr[0]+" "+arr[1]);

        // int[] nums = {1,2,3};
        // System.out.println(subsets01(nums));
        // System.out.println(subsets02(nums));
        // System.out.println(subsets03(nums));
        // System.out.println(subsets04(nums));

        // int[] nums = {3,2,3};
        // System.out.println(majorityElement(nums));

        int a = -2;
        int b  = 3;
        System.out.println(getSum(a, b));

    }

    /************************************************************************ */
    public static boolean isPowerOfTwo(int n) {
        int count = 0;
        long num = n;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num = (num >>> 1);
        }
        return (count == 1);
    }

    // === 2nd approach
    public static boolean isPowerOfTwo02(int n) {
        return (n > 0) && ((n & (n - 1)) == 0);
    }

    /************************************************************************ */
    public static boolean isPowerOfFour(int num) {
        int count = -1;
        // a power of two
        if (num > 0 && ((num & (num - 1)) == 0)) {
            count = 0;
            while (num != 1) {
                // count all 0's
                if ((num & 1) == 0) {
                    count++;
                }
                num = (num >>> 1);
            }
        }
        // even numbers have 0 at their 0th bit.
        return ((count & 1) == 0);
    }

    // 2nd method
    public static boolean isPowerOfFour02(int num) {
        if (num > 0 && (num - 1 & num) == 0) {
            return (num - 1) % 3 == 0;
        }
        return false;
    }

    /******************************************************************************************** */
    // Leetcode 136 -> Single Number I 
    public static int singleNumber(int[] nums) {
        // using bits, without taking extra space.
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor = (xor ^ nums[i]);
        }
        return xor;
    }

    /********************************************************************************************/
    //Leetcode 137 -> Single Number II (imp).
    public static int singleNumberII(int[] nums) {
        int ans = 0;
        for(int i=0;i<32;i++){
            int mask = ( 1 << i); //to check whether the bit is 1 or 0
            int count = 0;
            for(int idx = 0;idx<nums.length;idx++){
                if( (nums[idx] & mask)!=0 ){
                    count++;
                    
                }
            }
            if(count%3!=0){
                ans = (ans | mask);
            }
        }
        return ans;
    }
    /********************************************************************************************* */
    // Leetcode 260 -> Single Number III (imp)
    public static int[] singleNumberIII(int[] nums) {
        int xor = 0;
        int x = 0;
        int y = 0;
        for(int i=0;i<nums.length;i++){
            xor = (xor ^ nums[i]);
        }
        int mask = (xor & (- xor));
        for(int i=0;i<nums.length;i++){
            if( (mask & nums[i]) == 0 ){
                x = (x ^ nums[i]);
            } else {
                y = (y ^ nums[i]);
            }
        }
        int[] ans = new int[2];
        ans[0] = x;
        ans[1] = y;
        return ans;
    }
    /******************************************************************************************* */

    //Leetcode Q 78 -> Subsets 
    //method 1 -> using 2 calls (Recursion.)
    public static List<List<Integer>> subsets01(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        findSubset01(nums,0,sans,ans);
        return ans;
    }
    //2 choices 
    public static void findSubset01(int[] nums, int idx, List<Integer> sans, List<List<Integer>> ans){
        if(idx == nums.length){
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }
        
        //include
        sans.add(nums[idx]);
        findSubset01(nums,idx+1,sans,ans);
        sans.remove(sans.size()-1);
        
        //not include in subset 
        findSubset01(nums,idx+1,sans,ans);
    }
    //===================== method 2 -> using for loop (Recursion)
    public static List<List<Integer>> subsets02(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        findSubset02(nums,0,sans,ans);
        return ans;
    }
    // for loop  
    public static void findSubset02(int[] nums, int idx, List<Integer> sans, List<List<Integer>> ans){
        List<Integer> base = new ArrayList<>(sans);
        ans.add(base);
        
        for(int i=idx;i<nums.length;i++){
            sans.add(nums[i]);
            findSubset02(nums,i+1,sans,ans);
            sans.remove(sans.size()-1);
        }
    }
    //========== method 3 -> (Recursion).(Without changing return type)
    public static List<List<Integer>> subsets03(int[] nums) {
        return findSubsets03(nums,0);
    }
    public  static List<List<Integer>> findSubsets03(int[] nums, int idx){
        if(idx == nums.length){
            List<List<Integer>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> sol = findSubsets03(nums,idx+1);
        for(List<Integer> l: sol){
            ans.add(new ArrayList<>(l));
            l.add(0,nums[idx]);
            ans.add(new ArrayList<>(l));
        }
        
        return ans;
    }

    //====== method 4 -> (Bits method )
    // public static List<List<Integer>> subsets04(int[] nums){
    //     List<List<Integer>> ans = new ArrayList<>();
    //     int n = nums.length;
    //     for(int i=0; i < (1<<n); i++){
    //         List<Integer> res = new ArrayList();
    //         for(int j=0;j<n;j++){
    //             if((i & (1<<j)) !=0 ){
    //                 res.add(nums[j]);
    //             }
    //         }
    //         ans.add(res);
    //     }
    //     return ans;
    // }
    /***************************************************************************************** */
    //Leetcode Q 169 -> Majority Element 

    public static int majorityElement(int[] nums){
        //valid only when majority element appears more than n/2 times.
        int count = 1;
        int idx = 0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[idx]){
                count++;
            } else {
                count--;
            }
            if(count == 0){
                idx = i; //change the majority element 
                count = 1;
            }
        }
        // return nums[idx];

        //you can also verify that the number you think is majority is actually having 
        //count>n/2 or not
        // <--- optional code -->
        count = 0;
        for(int ele:nums){
            if(ele == nums[idx]){ count++; }
        }
        if(count > nums.length/2){
            return nums[idx];
        }
        return -1;
    }
    /**************************************************************************************** */
    //Q 371 -> Sum Of Two Integers
    //Formula -> learn it 
    //time complexity and space complexity -> o(1).
    public static int getSum(int a, int b) {
        // /this is a  formula, it handles -ve numbers also by implicityly making mask of long
        return ((a^b)+2*(a&b));
    }
}