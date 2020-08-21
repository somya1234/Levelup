import java.util.ArrayList;

public class l001 {
    public static void main(String[] args) {
        
    }

    public static Node constructBST(ArrayList<Integer> arr, int si, int ei){
        if(si>ei) { return null; }

        int mid = (si+ei)>>1; //divide by 2 . (normally triple right shift, here data not too long.)
        Node node = new Node(arr.get(mid));

        node.left = constructBST(arr, si, ei-1);
        node.right = constructBST(arr, si+1, ei);
        return node;
    }
}