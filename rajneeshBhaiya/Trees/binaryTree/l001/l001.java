import java.util.*;
public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] arr = {10,20,30,-1,-1,40,-1,-1,50,60,70,-1,80,-1,-1,-1,90,100,-1,120,-1,-1,110,-1,-1};
        Node root = constructTree(arr);
        // Node root = construct2(arr);
        // display(root);

        System.out.println(size(root));
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

    public static class Pair{
        Node node;
        int level;
        Pair(Node node, int level){
            this.node = node;
            this.level = level;
        }
    }

    public static Node construct2(int[] arr){
        int i = 0;
        Node root = new Node(arr[i]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root,1));
        for(i=1;i<arr.length;i++){
            Pair top = st.peek();
            int inputValue = arr[i];
            if(top.level == 1){
                if(inputValue!=-1){
                    Node node =  new Node(inputValue);
                    top.node.left = node;
                    st.push(new Pair(node,1));
                }
                top.level++;
            } else if(top.level == 2){
                if(inputValue!=-1){
                    Node node =  new Node(inputValue);
                    top.node.right = node;
                    st.pop();
                    st.push(new Pair(node,1));
                } else
                    st.pop();
            }
        }
        return root;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
        //in preorder style printing 

        StringBuilder sb = new StringBuilder();
        sb.append(node.left!=null?node.left.data :".");
        sb.append(" <- "+node.data+" -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    /******************************************************************************************** */

    public static int size(Node node){
        if(node == null){
            return 0;
        }
        int size = 1;
        size+= size(node.left);
        size+= size(node.right);
        return size;
    }

    public static int height(Node node){
        //in terms of edges -> return -1
        //in terms of vertices -> return 0;
        
    }
    

    /********************************************************************************************* */
    //2. Set1 

    // public static 
}