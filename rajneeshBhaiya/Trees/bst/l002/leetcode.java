import java.util.Stack;

import org.w3c.dom.Node;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /************************************************************************************************************** */
    //Leetcode 510 (approach 2 -> method 1 )
    public static Node inorderSuccessor(Node node){
        Node curr = node;
        Node succ = null;
        if(curr.right!=null){
            succ = curr.right;
            while(succ.left!=null){
                succ = succ.left;
            }
            return succ;
        }
        int val = curr.val;
        while(curr.parent!=null){
            curr = curr.parent;
            if(curr.val>val){
                return curr;
            }
        }
        return succ; //it will return null
    }

    //method 2 -> without comapring value when it has no right child 
    public static Node inorderSuccessor2(Node node){
        Node curr = node;
        Node succ = null;
        if(curr.right!=null){
            succ = curr.right;
            while(succ.left!=null){
                succ = succ.left;
            }
            return succ;
        }
        Node prev = null;
        while(curr.parent!=null){
            prev = curr;
            curr = curr.parent;
            if(curr.left == prev){ return curr; }
        }
        return succ;
    }

    /**************************************************************************************************************/
    // Leetcode 210 

    //method 1 
    int kthSmallestAns = -1;
    int kth= 0;
    public boolean kthSmallest_(TreeNode root){
        if(root == null){
            return false;
        }

        if(kthSmallest_(root.left)){
            return true;
        }

        if(--kth==0){
            kthSmallestAns = root.val;
        }
        if(kthSmallest_(root.right)){  return true; }
        return false;
    }

    public int kthSmallest(TreeNode root, int k){
        kth = k;
        kthSmallest_(root);
        return kthSmallestAns;
    }

    //method 2 
    public void pushAllNext(Stack<Node> st, TreeNode node){
        while(node!=null){
            st.push(node);
            node = node.left;
        }
    }

    public int kthSmallest(TreeNode root, int k){
        Stack<Node> st = new Stack<>();
        pushAllNext(st, root);

        while(--k != 0){
            Node rNode = st.pop();
            pushAllNext(st, rNode.right);
        }
        return st.peek().val;
    }


}