public class leetcode {
    public static void main(String[] args) {
        
    }

    /*****************************************************************************************/
    // 74. Search a 2D Matrix
    // time - O(log(n*m)) - better.
    public boolean searchMatrix(int[][] matrix, int target) {
        //[] or [[]]
        if(matrix.length == 0 || matrix[0].length==0) return false; 
        int n = matrix.length; 
        int m = matrix[0].length; 
        
        int si = 0, ei = (n*m)-1; 
        
        while(si<=ei){
            
            int mid = (si+ei) >> 1; 
            
            if(matrix[mid/m][mid%m]==target) return true; 
            else if(matrix[mid/m][mid%m]<target) si = mid+1; 
            else ei = mid-1; 
            
        }
        return false; 
    }

    //========= method 2 - O(n+m)
    public boolean searchMatrix(int[][] matrix, int target) {
        //[] or [[]]
        if(matrix.length == 0 || matrix[0].length==0) return false; 
        int n = matrix.length; 
        int m = matrix[0].length; 
        
        int si = 0, ei = m-1;
        while(si<n && ei>=0){
            if(matrix[si][ei] == target) return true; 
            else if(matrix[si][ei] < target) si++; 
            else ei--; 
        }
        
        return false; 
    }


    /*****************************************************************************************/

    // 34. Find First and Last Position of Element in Sorted Array

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length; 
        if(n==0) return new int[]{-1,-1}; 
        
        int[] ans = new int[2]; 
        ans[0] = firstOcc(nums, target); 
        ans[1] = lastOcc(nums, target);
        return ans; 
    }
    
    public int firstOcc(int[] nums, int target){
        int si = 0; int ei = nums.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 
            
            if(nums[mid]==target){
                if(mid-1>=0 && nums[mid-1]==target) ei = mid-1; 
                else return mid; 
            }
            else if(target>nums[mid]) si = mid+1; 
            else ei = mid-1; 
        }
        return -1; 
    }
    
    public int lastOcc(int[] nums, int target){
        int si = 0; int ei = nums.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 
            
            if(nums[mid]==target){
                if(mid+1<nums.length && nums[mid+1]==target) si = mid+1; 
                else return mid; 
            }
            else if(target>nums[mid]) si = mid+1; 
            else ei = mid-1; 
        }
        return -1; 
    }

    /*****************************************************************************************/

    // 658. Find K Closest Elements
    public List<Integer> findClosestElements(int[] A, int k, int x) {
        List<Integer> arr = new ArrayList<>(); 
        
        for(int ele: A) arr.add(ele); 
        
        int n = A.length; 
        if(x<=A[0]) return arr.subList(0, k);
        else if(x>=A[n-1]) return arr.subList(n-k, n);
        else{
            // it runs the binary search expected position function 
            // which returns the idx where the value should be inserted, if not present in array.
            int idx = Collections.binarySearch(arr, x); 
            if(idx<0){
                idx = - idx - 1; 
            }
            int si = Math.max(0, idx - k); 
            int ei = Math.min(idx+k, n-1);
            
            while(ei-si>=k){
                if((x - A[si]) > (A[ei] - x)) si++; 
                else ei--; 
            }
            return arr.subList(si, ei+1);
        }
    }

    /*****************************************************************************************/
    // 35. Search Insert Position - time - logn , space O(1)
    public int searchInsert(int[] nums, int target) {
        int n = nums.length; 
        int si = 0, ei = n; 
        while(si<ei){
            int mid = (si+ei) >> 1; 
            if(nums[mid] == target) return mid;
            else if(nums[mid]<target) si = mid+1; 
            else ei = mid; 
        }
        return ei; 
    }
    /*****************************************************************************************/

    // 33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int pi = findPivot(nums); 
        int idx1 = binarySearch(nums, target, 0, pi-1);
        if(idx1==-1) {
            int idx2 = binarySearch(nums, target, pi, nums.length-1);
            return idx2; 
        } else return idx1; 
    }
    
    public int findPivot(int[] nums){
        int si = 0; int ei = nums.length-1; 
        while(si<ei){
            int mid = (si+ei) >> 1; 
            if(nums[mid]>nums[ei]) si = mid+1; 
            else ei = mid; 
        }
        return ei; 
    }
    
    public int binarySearch(int[] nums, int target, int si ,int ei){
        while(si<=ei){
            int mid = (si+ei) >> 1; 
            if(nums[mid]==target) return mid; 
            else if(target<nums[mid]) ei = mid-1; 
            else si = mid+1; 
        }
        return -1; 
    }

    //======== method 2 - this is also logn but here we search only 1 side(better)
    public int search(int[] nums, int target) {
        int si = 0; 
        int ei = nums.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 
            if(nums[mid]==target) return mid; 
            
            //if left side is sorted
            else if(nums[si]<nums[mid]){
                // if target is on left side, move to left side
                if(mid-1>=si && target>=nums[si] && target<=nums[mid-1]) ei = mid-1; 
                // else search in right side
                else si = mid+1; 
                
                //if right side array is sorted
            } else{
                //if target lies on sorted side
                // don't do target>=nums[mid+1] because maybe mid+1 is not there.
                if(mid+1<=ei && target>=nums[mid+1] && target<=nums[ei]) si = mid+1; 
                // if target lies on unsorted side
                else ei = mid-1; 
            }
        }
        return -1; 
    }
    /*****************************************************************************************/
}


