import javax.swing.tree.TreeNode;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        
    }

/***************************************************************************************** */
//Leetcode 105.
public TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei){
    //isi - inorder si 
    //psi  - preorder si 
    if(psi>pei){
        return null;
    }

    int idx = isi;
    while(inorder[idx]!=preorder[psi]){ idx++; }
    int len = idx - isi;

    TreeNode node = new TreeNode(preorder[psi]);
    node.left = buildTree(preorder, psi+1, psi+len, inorder, isi, idx-1);
    node.right = buildTree(preorder, psi+len+1, pei, inorder, idx+1, iei);

    return node;
}

public TreeNode buildTree(int[] preorder, int[] inorder){
    if(preorder.length == 0){
        return null;
    }
    int n = preorder.length;

    return buildTree(preorder, 0, n-1, inorder, 0, n-1);
}


//Leetcode 106
public TreeNode buildTree(int[] postorder, int psi, int pei, int[] inorder, int isi, int iei){
    if(psi>pei){
        return null;
    }
    int idx = isi;
    while(inorder[idx]!=postorder[pei]){ idx++; }
    int len = idx - isi;
    TreeNode node = new TreeNode(postorder[pei]);
    node.left = buildTree(postorder, psi, psi+len-1, inorder, isi, idx-1);
    node.right = buildTree(postorder, psi+len, pei-1, inorder, idx+1, iei);
    return node;
}

public TreeNode buildTree(int[] inorder, int[] postorder){
    if(postorder.length==0){ return null; }
    int n = postorder.length;
    return buildTree(postorder, 0, n-1, inorder, 0, n-1);
}

/********************************************************************************************/
// 98. Validate Binary Search Tree
public boolean isValidBST(TreeNode root) {
    return isValidate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

public boolean isValidate(TreeNode root, long minVal, long maxVal){
    if(root == null) return true; 
    if(root.val<=minVal || root.val>=maxVal) return false;  
    if(!isValidate(root.left, minVal, root.val)) return false; 
    if(!isValidate(root.right, root.val, maxVal)) return false; 
    return true; 
}

/*******************************************************************************************/



}