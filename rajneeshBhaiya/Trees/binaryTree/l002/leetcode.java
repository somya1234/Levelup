public class leetcode{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        System.out.println(hasPath(root,sum));
    }

    /*********************************************************************************** */
    //leetcode - 112 Path Sum 
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPath(root,sum,0);
    }
    public boolean hasPath(TreeNode root, int sum, int val){
       
        if(root == null){
            return false;
        }
        val = val+root.val;
        //necessary to check for leaf node.
         if(sum == val && root.left==null && root.right == null){
            return true;
        }
        if(hasPath(root.left, sum,val)){
            return true;
        }
        if(hasPath(root.right, sum, val)){
            return true;
        }
        return false;
    }
    /*********************************************************************************** */
    // Leetcode 113 -> Path Sum II
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        path_sum(root,sum,sans,ans);
        return ans;
    }
    
    public void path_sum(TreeNode root, int sum, List<Integer> sans, List<List<Integer>> ans){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null && sum==root.val){
            sans.add(root.val);
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            sans.remove(sans.size()-1);
        }
        sans.add(root.val);
        path_sum(root.left,sum-root.val,sans,ans);
        path_sum(root.right,sum-root.val,sans,ans);
        sans.remove(sans.size()-1);
    }
    /*********************************************************************************************** */
    //gfg -> https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/


    /*********************************************************************************************** */
    //leetcode 124 Binary Tree Maximum Path Sum 
    
}