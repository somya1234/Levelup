public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    public  static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data ){
            this.data = data;
        }
    }
    /****************************************************************************************** */
    //basics
    static int idx = 0;

    public static Node constructTree(int[] arr){
        if(idx == arr.length || arr[idx] == -1){
            idx++;
            return null;
        } 

        Node node = new Node(arr[idx++]);
        node.left = constructTree(arr);
        node.right = constructTree(arr);
        
        return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
        //in preorder style printing 

        StringBuilder sb = new StringBuilder();
        sb.append(node.left!=null?node.left.data+"" :".");
        sb.append(" <- "+node.data+" -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    public static int size(Node node){

    }

    public static int height(Node node){
        //in terms of edges -> return -1
        //in terms of vertices -> return 0;

    }

    /********************************************************************************************* */
    //2. Set1 

    public static 
}