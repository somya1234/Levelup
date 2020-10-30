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
        // display(root);
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

        //construct bst from preorder 
        // int[] arr = new int[]{5,3,1,4,10,7,14};
        // idx = 0;         
        // Node nnode = constructPreorder(arr, 0, arr.length-1);
        // Node nnode = BstFromPreorder(arr); 
        // display(nnode);

        // int[] arr = new int[]{7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};
        // System.out.println(bstPreorderHeight(arr, -(int)1e9,(int)1e9));

        //bst from postorder
        int[] arr = {0,2,1,5,4,6,3,8,10,11,9,14,15,13,12,7};
        idx = arr.length-1; 
        display(bstFromPostOrder(arr, -(int)1e9, (int)1e9));
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
    // v.imp q ->  construct tree using preorder 

    // method 1 - O(n) 
    public static Node constructPreorder(int[] arr, int si, int ei){
        if(si>ei) return null; 

        Node root = new Node(arr[si]); 
        int idx = si+1; 
        for(idx = si+1; idx<=ei; idx++){
            if(arr[idx]>arr[si]) break; 
        }
        idx--; 
        root.left = constructPreorder(arr, si+1, idx);
        root.right = constructPreorder(arr, idx+1, ei);
        return root; 
    }

    //=== method2  - O(n), can be used for all conversions.
    static int idx; 
    public static Node BstFromPreorder(int[] arr){          
        Node node = BstFromPreorder_(arr, -(int)1e9, (int)1e9); 
        return node; 
    }

    public static Node BstFromPreorder_(int[] arr, int low, int high){
        if(idx == arr.length) return null; 

        int val = arr[idx]; 
       
        Node node = null; 
        if(val>=low && val<=high) {  
            node = new Node(arr[idx++]); 
        }
        else return null; 
        node.left = BstFromPreorder_(arr, low, val); 
        node.right = BstFromPreorder_(arr, low, high); 

        return node; 
        
    }

    /***********************************************************************************/

    public static int bstPreorderHeight(int[] arr, int low, int high){

        if(idx == arr.length) return -1; 
        int val = arr[idx]; 
        if(val<low || val>high) return -1; 

        idx++; 

        int lh = bstPreorderHeight(arr, low, val);
        int rh = bstPreorderHeight(arr, val, high); 

        return Math.max(lh, rh )+1; 
    }

    /****************************************************************************************/

    public static Node bstFromPostOrder(int[] arr, int low, int high){

        if(idx<0) return null; 
        int val = arr[idx]; 
        if(val<low || val>high) return null; 

        Node node = new Node(arr[idx--]); 
        node.right = bstFromPostOrder(arr, val, high);
        node.left = bstFromPostOrder(arr, low, val); 
        return node; 
    }

}