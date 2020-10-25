public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        //recoverBst();
    }

    /*********************************************************************************************/
    // 235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q);
        return lca; 
    }
    
    TreeNode lca = null; 
    
    public void lca(TreeNode root, TreeNode p, TreeNode q){
        
        if(p.val<root.val && q.val<root.val){
            lca(root.left,p , q);
        } else if(p.val>root.val && q.val>root.val){
            lca(root.right, p, q);
        } else lca = root; 
        return; 
    }

    //======iterative 
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        TreeNode curr = root;
        while(curr != null){

            if(p.val < curr.val && q.val < curr.val) curr = curr.left;
            else if (p.val > curr.val && q.val > curr.val) curr = curr.right;
            else return curr;
        }

        return null;
    }

    /*********************************************************************************************/
    // 173. Binary Search Tree Iterator

    class BSTIterator {
        Stack<TreeNode> st = new Stack<>();
    
        public BSTIterator(TreeNode root) {
            pushAllNextElements(root);
        }
        
        public void pushAllNextElements(TreeNode root){
            while(root!=null){
                st.push(root); 
                root = root.left; 
            }
        }
        
        /** @return the next smallest number */
        public int next() {
            TreeNode rv = st.pop(); 
            pushAllNextElements(rv.right); 
            return rv.val; 
        }
        
        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return st.size()!=0; 
        }
    }

    /*********************************************************************************************/


    /*********************************************************************************************/
