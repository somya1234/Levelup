import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(10,20,30, 40, 50, 60, 70, 80, 90,100));
        Node root = constructBST(arr, 0, arr.size()-1);
        display(root);
        solve1(root);
    }

    /*********************************************************************************** */
    public static class Node{
        int data; 
        Node left = null; 
        Node right = null; 
        Node(int data){
            this.data = data; 
        }
        Node(int data, Node left, Node right){
            this.data = data; 
            this.left = left; 
            this.right = right; 
        }
    }

    public static Node constructBST(ArrayList<Integer> arr, int si, int ei){
        if(si>ei) { return null; }

        int mid = (si+ei)>>1; //divide by 2 . (normally triple right shift, here data not too long.)
        Node node = new Node(arr.get(mid));

        node.left = constructBST(arr, si, mid-1);
        node.right = constructBST(arr, mid+1, ei);
        return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(node.left!=null ? node.left.data : ".");
        sb.append(" <- "+node.data+" -> ");
        sb.append(node.right!=null ? node.right.data:".");
        System.out.println(sb.toString());

        if(node.left!=null) { display(node.left); }
        if(node.right!=null){ display(node.right); }
    }

    /*****************************************************************************************/

    public static void solve1(Node root){
        // System.out.println(find(root, 42));
        // System.out.println(findIter(root, 42));
        // System.out.println(size(root));
        // System.out.println(height(root));
        // System.out.println(minimum(root)); 
        // System.out.println(maximum(root));
    }

    public static boolean find(Node root, int target){
        if(root == null) return false; 

        if(root.data == target) return true;
       
        if(target<root.data)return  find(root.left, target);
        else return find(root.right, target);
    }

    public static boolean findIter(Node root, int target){
        Node curr = root; 
        while(curr!=null){
            if(curr.data == target) return true; 
            else if(target<curr.data) curr = curr.left; 
            else curr = curr.right; 
        }
        return false; 
    }


    public static int size(Node root){
        return root==null ? 0 : 1 + size(root.left) + size(root.right); 
    }

    public static int height(Node root){
        return root==null ? -1 : Math.max(height(root.left), height(root.right))+1; 
    }

    public static int minimum(Node root){
        Node curr = root; 
        while(curr.left!=null){
            curr = curr.left; 
        }
        return curr.data; 
    }

    public static int maximum(Node root){
        Node curr = root; 
        while(curr.right!=null){
            curr = curr.right; 
        }
        return curr.data; 
    }


    /**************************************************************************************/
    // https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/

    static Node head = null, prev = null; 
    public static Node BTtoclist(Node node){
        BTtoclist_(node);
        head.left = prev; 
        prev.right = head; 
        return head; 
    }

    public static void BTtoclist_(Node node){
        if(node == null) return; 

        BTtoclist_(node.left);

        if(prev == null) head = node; 
        if(prev!=null){ 
            prev.right = node; 
            node.left = prev; 
        }
        prev = node; 
        BTtoclist_(node.right);
    }


    /************************************************************************************* */
    // v.imp q -> Leetcode construct tree using preorder 
    static int idx = 0;
    public static Node BstUsingPreOrder(int[] arr, int lRange, int rRange){
        if(idx>=arr.length || arr[idx]<lRange || arr[idx]>rRange){
            return null;
        }

        Node node = new Node(arr[idx++]);

        node.left = BstUsingPreOrder(arr, lRange, node.data);
        node.right = BstUsingPreOrder(arr, node.data, rRange);

        return node;
    }

    // public static void BstUsingPostorder(int[] arr, int lRange, int rRange){


    //     Node node = new Node(arr[idx--]);
    //     node.right  BstUsingPostorder(arr,node.data,rRange);
    //     node.left = 
    // }

    // public static Node BstUsingPreorder(){
    //     int[] arr = {7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};
    //     display(BstUsingPreOrder(arr, -(int)1e8, (int)1e8));
    // }

}