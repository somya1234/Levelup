import java.util.ArrayList;

public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        Node root = constructBST(arr, 0, arr.length-1);
        display(root);
    }

    /*********************************************************************************** */
    public static Node constructBST(ArrayList<Integer> arr, int si, int ei){
        if(si>ei) { return null; }

        int mid = (si+ei)>>1; //divide by 2 . (normally triple right shift, here data not too long.)
        Node node = new Node(arr.get(mid));

        node.left = constructBST(arr, si, ei-1);
        node.right = constructBST(arr, si+1, ei);
        return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(node.left!=null ? node.left.data : ".");
        ab.append(" <- "+node.data+" -> ");
        sb.append(node.right!=null ? node.right.data:".");
        System.out.println(sb.toString());

        if(node.left!=null) { display(node.left); }
        if(node.right!=null){ display(node.right); }
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