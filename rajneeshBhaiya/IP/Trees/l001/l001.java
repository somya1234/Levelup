public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    public static class Node{
        int data; 
        Node left; 
        Node right; 
        Node(int data, Node left, Node right){
            this.data = data; 
            this.left = left; 
            this.right = right; 
        }
    }

    public static Node BST(Node head, Node tail ){
        if(head.right == null && head.left == null) return head;

        if(head == tail) return head; 

        Node mid = findMid(head); 
        Node prev = mid.left; 
        Node next = mid.right; 
        if(prev!=null) prev.right = null; 
        if(next!=null) next.left = null; 

        mid.left = BST(head, prev); 
        mid.right = BST(next, tail); 

        return mid; 
    }

    public static Node findMid(Node head){
        Node slow = head; 
        Node fast = head; 

        while(fast.right!=null && fast.right.right!=null){
            slow = slow.right;
            fast = fast.right.right; 
        }
        return slow; 
    }

}
