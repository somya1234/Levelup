public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /***********************************************************************************/

    // leetcode 116. Populating Next Right Pointers in Each Node

    // using BFS - time O(n), space - O(n)
    public Node connect(Node root) {
        if(root == null) return root; 
        LinkedList<Node> que = new LinkedList<>(); 
        que.addLast(root); 
        while(que.size()>0){
            int size = que.size(); 
            Node prev = null; 
            while(size-->0){
                Node top = que.removeFirst(); 
                if(prev!=null){ prev.next = top;  }
                
                if(top.left!=null) que.addLast(top.left);
                if(top.right!=null) que.addLast(top.right);
                prev = top; 
            }
        }
        return root; 
    }

    //===method 2 - Recursive Time - O(n) ans space - O(1)

    public Node connect(Node root) {
        connect_(root); 
        return root; 
    }
    
     public void connect_(Node node) {
         if(node==null) return; 
         if(node.left==null ) return; //complete BT , then , no right node will be there.
        
         if(node.left!=null && node.right!=null){
             node.left.next = node.right; 
         }
         if(node.next!=null){
             node.right.next = node.next.left; 
         }
         
         connect_(node.left); 
         connect_(node.right);
    }

    /***********************************************************************************/
}
