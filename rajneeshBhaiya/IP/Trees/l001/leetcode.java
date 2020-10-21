public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    /***********************************************************************************/
    //leetcode 337. House Robber III
    
     // []  - store max value without robbing the current value, store max value when robbing the current value.
     public int rob(TreeNode root) {
        int[] ans = rob_(root); 
        return Math.max(ans[0], ans[1]); 
    }
    
    public int[] rob_(TreeNode root) {
        if(root == null) return new int[]{0,0};
        
        int[] left = rob_(root.left); 
        int[] right = rob_(root.right); 
        
        int[] ans = new int[2]; 
        ans[0] = Math.max(left[0], left[1])+Math.max(right[0], right[1]); 
        ans[1] = left[0] + root.val+right[0]; 
        
        return ans; 
    }

    /***********************************************************************************/
}
