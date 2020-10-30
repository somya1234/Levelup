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

    //leetcode 99 - Recover BST
    //time complexity - O(n), space - O(1)
    TreeNode A= null, B = null, prev=null; 
    public void recoverTree(TreeNode root) {
        recoverTree_(root); 
        int temp = A.val; 
        A.val = B.val; 
        B.val = temp; 
        return; 
    }
    
     public boolean recoverTree_(TreeNode curr) {
        if(curr == null) return false; 
         
         if(recoverTree_(curr.left)) return true; 
         
        if(prev!=null && prev.val>curr.val){
            if(A!=null) {
                B = curr; 
                return true; 
            }
            if(A==null) A = prev; 
            B = curr; 
        }
         
         prev = curr;
         
         if(recoverTree_(curr.right)) return true;
         return false; 
    }

    /*********************************************************************************************/
// 1008. Construct Binary Search Tree from Preorder Traversal
    public TreeNode bstFromPreorder(int[] preorder) {
        idx  =0; 
        TreeNode root = bstFromPreorder_(preorder, -(int)1e9, (int)1e9);
        return root; 
    }
    
    static int idx; 
    public TreeNode bstFromPreorder_(int[] preorder, int low, int high) {
        if(idx>=preorder.length) return null; 
        int val = preorder[idx]; 
        if(val< low || val>high) return null; 
        
        TreeNode node = new TreeNode(preorder[idx++]); 
        node.left = bstFromPreorder_(preorder, low, val);
        node.right = bstFromPreorder_(preorder, val, high); 
        return node; 
    }

    /*********************************************************************************************/