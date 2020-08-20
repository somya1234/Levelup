public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /************************************************************************ */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> ans1 = new ArrayList<>();
        NodeToRootPath(root,ans1,p);
        List<TreeNode> ans2 = new ArrayList<>();
        NodeToRootPath(root,ans2,q);
        int len1 = ans1.size();
        int len2 = ans2.size();
        while(len1!=len2){
            if(len1>len2){
                ans1.remove(ans1.size()-1);
                len1--;
            } else if(len2>len1){
                ans2.remove(ans2.size()-1);
                len2--;
            }
        }
        int val1 = ans1.get(ans1.size()-1).val;
        int val2 = ans2.get(ans2.size()-1).val;
        while(val1!=val2){
            ans1.remove(ans1.size()-1);
            ans2.remove(ans2.size()-1);
            val1 = ans1.get(ans1.size()-1).val;
         val2 = ans2.get(ans2.size()-1).val;
        }
        return ans1.get(ans1.size()-1);
    }
    
    public void NodeToRootPath(TreeNode root, List<TreeNode> ans, TreeNode data){
        if(root == null){
            return;
        }
        
        ans.add(root);
        if(root.val == data.val){
            return;
        }
        NodeToRootPath(root.left,ans,data);
        NodeToRootPath(root.right,ans,data);
    }
    /****************************************************** */
    
    public static boolean rootToNodePath(Node node, int data, ArrayList<Node> path){
        if(node == null){
            return false;
        }

        if(node.data == data){
            path.add(node);
            return true;
        }
    }
}