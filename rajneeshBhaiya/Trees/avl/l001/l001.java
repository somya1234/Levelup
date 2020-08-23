public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {

    }

    public static class Node {
        int data = 0;
        Node left;
        Node right;

        int height = 0;
        int bal = 0;

        Node(int data) {
            this.data = data;
        }
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
/********************************************************************************************* */
    public static void updateHeightBalance(Node node) {
        int lh = -1;
        int rh = -1;
        if (node.left != null) {
            lh = node.left.height;
        }
        if (node.right != null) {
            rh = node.right.height;
        }

        int bal = lh - rh;
        int height = Math.max(lh, rh) + 1;

        node.height = height;
        node.bal = bal;
    }

    public static Node rightRotation(Node A) {
        Node B = A.left;
        Node Bright = B.right;

        B.right = A;
        A.left = Bright;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    public static Node leftRotation(Node A) {
        Node B = A.right;
        Node Bleft = B.left;

        B.left = A;
        A.right = Bleft;

        updateHeightBalance(A);
        updateHeightBalance(B);

        return B;
    }

    public static Node getRotation(Node node) { // O (1).
        updateHeightBalance(node);
        if (node.bal == 2) { // ll or lr
            if (node.bal == -1) { // ll
                rightRotation(A);
            } else { // lr

            }
        } else if (node.bal == -2) { // rr or rl
            if (node.right.bal == -1) { // rr
                leftRotation(A);
            } else { // rl

            }
        }
        return node;
    }

    public static Node addNode(Node node, int data) { //time complexity - O(log n)
        //now time complexity -> n logn 
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = addNode(node.left, data);
        } else {
            node.right = addNode(node.right, data);
        }
        return node;
    }

    /*************************************************************************************************** */

    public static Node removeData(Node node, int data) {
        if (node == null) {
            return node;
        }

        if (data < node.data) {
            node.left = removeData(node.left, data);
        } else if (data > node.data) {
            node.right = removeData(node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                return node.left != null ? node.left : node.right;
            }
            int minEle = minimum(node.right);
            node.data = minEle;
            node.right = removeData(node.right, minEle);
        }
        return node;
    }

}