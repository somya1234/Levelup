public class l003 {
    public static void main(String[] args) {
        solve();
    }

    public static class Node{
        int data;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }

    static int idx = 0;

    public static Node construct(int[] arr){
        if(idx == arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        Node node = new Node(arr[idx++]);
        node.left = construct(arr);
        node.right = construct(arr);

        return node;
    }


    public static void solve(){
        int[] arr = {10,20,40,-1,-1,50,60,70,-1,80,-1,-1,-1,90,70,-1,120,-1,-1,110,-1,-1};
        Node root = construct(arr);
        // System.out.println(root.data);
        int[] minMax = new int[2];
        width(root, 0, minMax);
        kDown(root,2);
    }

    public static int width(Node node, int level, int[] minMax){
        if(node == null){
            return ;
        }

        minMax[0] = Math.min(minMax[0],level);
        minMax[1] = Math.max(minMax[1],level);

        width(node.left, level-1, minMax);
        width(node.right, level+1, minMax);
    }

    public static void kDown(Node node, int k ){
        if(node == null){
            return;
        }

        if(k==0){
            System.out.println(node.data);
            return;
        }

        kDown(node.left, k-1);
        kDown(node.right, k-1);

    }
}