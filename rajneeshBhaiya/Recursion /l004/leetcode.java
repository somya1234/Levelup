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
        int[] nums = {1,2,1,3,2,5};
        int[] arr = singleNumberIII(nums);
        System.out.println(arr[0]+" "+arr[1]);
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
}