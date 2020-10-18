public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /*****************************************************************************************/

    //1. 863. All Nodes Distance K in Binary Tree

    // method 1 - T.c - O(n) and space - O(n).
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<TreeNode> path = new ArrayList<>(); 
        rootToNodePath(root, target, path);
        TreeNode prev = null; 
        List<Integer> ans = new ArrayList<>(); 
        for(int i=0; i<path.size(); i++){
            kdown(path.get(i), prev, K-i, ans); 
            prev = path.get(i); 
        }
        return ans; 
    }
    
    public boolean rootToNodePath(TreeNode node, TreeNode target, List<TreeNode> path){
        if(node == null) return false; 
        
        if(node == target){
            path.add(node); 
            return true; 
        }
        
        boolean res = false; 
        res = res || rootToNodePath(node.left, target, path); 
        res = res || rootToNodePath(node.right, target, path); 
        
        if(res){
            path.add(node); 
            return true; 
        }
        
        return false;
    }
    
    public void kdown(TreeNode node, TreeNode block , int k , List<Integer> ans){
        
        if(k<0 || node ==null || node==block ) return ; 
        
        if(k==0){
            ans.add(node.val); 
            return; 
        }
        
        kdown(node.left, block, k-1, ans); 
        kdown(node.right, block , k-1, ans); 
    }

    //============method 2 - T.c - O(n), space - O(1)

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>(); 
        kFar(root, target, K, ans); 
        return ans; 
    }
    
    public int kFar(TreeNode node, TreeNode target, int k , List<Integer> ans){
        if(node == null) return -1; 
        
        if(node == target){
            kdown(node, null, k, ans); 
            return 1; 
        }
        
        int ld = kFar(node.left, target, k, ans);
        if(ld!=-1){
            kdown(node, node.left, k-ld, ans);
            return ld+1; 
        }
        
        int rd = kFar(node.right, target, k , ans); 
        if(rd!=-1){
            kdown(node, node.right, k-rd, ans); 
            return rd+1; 
        }
        
        return -1; 
    }
    
    public void kdown(TreeNode node, TreeNode block , int k , List<Integer> ans){
        if(k<0 || node == null || node==block) return; 
        
        if(k==0){
            ans.add(node.val); 
            return;
        }
        
        kdown(node.left, block, k-1, ans); 
        kdown(node.right, block, k-1, ans);
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

    //================= method 2 - old method 
    //space -O(n) and time - O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path1 = new ArrayList<>();
        List<TreeNode> path2 = new ArrayList<>();
         nodeToRootPath(root, p , path1); 
        nodeToRootPath(root, q , path2);
        
        
        int i = path1.size()-1; 
        int j = path2.size()-1; 
        while(i>=0 && j>=0){
            if(path1.get(i).val==path2.get(j).val){
                i--; j--; 
            }else break; 
        }
        return path1.get(i+1); 
    }
    
    public boolean nodeToRootPath(TreeNode node, TreeNode tar, List<TreeNode> path){
        if(node==null) return false; 
        
        if(node == tar){
            path.add(node); 
            return true; 
        }
        
        boolean res = false; 
        res = res || nodeToRootPath(node.left, tar, path); 
        res = res || nodeToRootPath(node.right, tar, path); 
        
        if(res){
            path.add(node);
            return true; 
        }
        
        return false; 
    }
    /****************************************************** */
    
    
}