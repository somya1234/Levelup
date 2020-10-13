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
        // mpath is answer.
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
        int mpath = Math.max(left.mpath,right.mpath);
        if(node.left!=null && node.right!=null)
             mpath = Math.max(mpath, left.msum+right.msum+node.data);
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
           // if it is a leaf, 
        if(node.left == null && node.right == null){
            // do not update max path sum here.
            // if both null so return (-inf,-inf)+node.data 
            // so as to avoid this condn return only node.data
            return node.data;
        }

        int lsum = maxPathSum(node.left);
        int rsum = maxPathSum(node.right);
        // when node has both leaves, then only ans will be calculated.
        if(node.left!=null && node.right!=null){
            mapth = Math.max(mpath, lsum+rsum+node.data);
        }
        return Math.max(lsum,rsum)+node.data;
    }

    /*********************************************************************************************** */
    //leetcode 124 Binary Tree Maximum Path Sum -> Google question 

    public int maxPathSum(TreeNode root) {
        return maxPathSum_(root).ans;
    }
    
    public class pair{
        int sum = 0;
        int ans = 0;
        pair(int sum, int ans){
            this.sum = sum;
            this.ans = ans;
        }
    }
    
    public pair maxPathSum_(TreeNode root){
        if(root == null){
            // if we return Integer.MIN_VALUE, then error below because no condns 
            // so add in -inf, can let it go out of range.
            return new pair(-(int)1e8,-(int)1e8);
        }
          
        pair left = maxPathSum_(root.left);
        pair right = maxPathSum_(root.right);
        // if both -inf, then ans -> (-inf+ -inf + root.val) = -inf.
        int sum = Math.max(left.sum, right.sum)+root.val;
        sum = Math.max(sum,root.val);
        int ans = Math.max(left.ans,Math.max(right.ans,root.val));
        ans = Math.max(ans,sum);
        // no condn for left!=null && right!=null as we find max of it
        ans = Math.max(ans,left.sum+right.sum+root.val);
        return new pair(sum,ans);
    }

    /*******************************************************************************************/

    //leetcode - 476. Number Complement
    /*
    num = 00000110
    mask= 00000111
    ~num= 11111001
    mask & ~num  = 000000001
    */
    
    public int findComplement(int num) {
        int mask = 0, temp = num; 
        while(temp>0){
            temp >>=1; 
            mask  =((mask << 1) | 1);
        }
        return ~num & mask; 
    }


    /*******************************************************************************************/
    
}