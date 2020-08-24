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
        if(root == null){
            return false;
        }
        if(sum-root.val == 0 && root.left == null && root.right == null){
            return true;
        }
        
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val); 
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

    class dpair{
        int msum = 0;
        int mpath = 0;
        dpair(int msum, int mpath){
            this.msum = msum;
            this.mpath = mpath;
        }
    }
    int maxPathSum(Node root)
    { 
        // code here 
        dpair ans = fn(root);
        return ans.mpath;        
    } 
    
    dpair fn(Node node){
        if(node == null){ //return -inf. only when 1 of child is null.
            return new dpair(-(int)1e8,-(int)1e8);
        }
        if(node.left==null && node.right == null){
            // a single node cannot have max path (as it is only between 2 leave nodes.)
            return new dpair(node.data,-(int)1e8);
        }
        dpair left = fn(node.left);
        dpair right = fn(node.right);
        int msum = Math.max(left.msum, right.msum)+node.data;
        int mpath = Math.max(left.mpath, Math.max(right.mpath, left.msum+right.msum+node.data));
        return new dpair(msum, mpath);
    }

    //approach 2 -> using static method
    //return maxpath from one of the leaves
    //and mpath is the max sum path between any 2 leaves.
    static int mpath = -(int)1e8;
    public static int maxPathSum(Node node){
        if(node == null){
            //as values can also be negative here.
            return -(int)1e8;
        }
        if(node.left == null && node.right == null){
            // do not update max path sum here.
            return node.data;
        }

        int lsum = maxPathSum(node.left);
        int rsum = maxPathSum(node.right);
        mapth = Math.max(mpath, lsum+rsum+node.data);
        return Math.max(lsum,rsum)+node.data;
    }

    /*********************************************************************************************** */
    //leetcode 124 Binary Tree Maximum Path Sum -> Google question 

    public int maxPathSum(TreeNode root) {
        pair ans = maxPathSum_(root);
        return ans.mpath;
    }
    public class pair{
        int msum = 0;
        int mpath = 0;
        pair(int msum, int mpath){
            this.msum = msum;
            this.mpath = mpath;
        }
    }
    
    public pair maxPathSum_(TreeNode node){
        if(node == null){
            return new pair(-(int)1e8, -(int)1e8);
            // return new pair(0,0);
        }
        
        if(node.left == null && node.right == null){
            return new pair(node.val, node.val);
        }
        
        pair left = maxPathSum_(node.left);
        pair right = maxPathSum_(node.right);
        
        int msum = Math.max(Math.max(left.msum, right.msum)+node.val, node.val);
        if(left.msum==-(int)1e8){
            left.msum = 0;
        }
        if(right.msum==-(int)1e8){
            right.msum = 0;
        }
        int mpath =Math.max(msum ,Math.max(node.val,Math.max(left.mpath, Math.max(right.mpath, left.msum+right.msum+node.val))));
        
        return new pair(msum, mpath);
    }

    /****************************************************************************************** */
    
}