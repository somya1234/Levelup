import java.util.ArrayList;
import java.util.Stack;

public class l001 {
    public static void main(String[] args) {
        solve();
    }
/******************************************************************************************* */
    public static class Node{
        int data = 0;
        ArrayList<Node> children = new ArrayList<>();
        Node(int data){
            this.data = data;
        }
    }

    public static Node constructGT(int[] arr){
        Stack<Node> st = new Stack<>();
        st.push(new Node(arr[0])); 
        for(int i=1;i<arr.length-1;i++){
            int ele = arr[i];
            if(ele == -1){
              st.pop();
            } else{
               Node top = st.peek();
               Node node = new Node(ele);
               top.children.add(node);
               st.push(node);
            }
        }
        return st.pop();
    }
/********************************************************************************************/
    public static void solve(){
        int[] arr = {10,20,50,-1,60,-1,-1,30,70,-1,80,120,-1,130,-1,-1,90,-1,-1,40,100,-1,110,-1,-1,-1};
        Node root = constructGT(arr);
        display(root);
        // System.out.println(height(root));
        // System.out.println(size(root));
        // System.out.println(max(root));
        // System.out.println(find(root, 80));
    }

    /********************************************************************************************/
    public static void display(Node node){

        System.out.print(node.data+" -> ");
        for(int i=0;i<node.children.size();i++){
            Node child = node.children.get(i);
            System.out.print(child.data+", ");
        }
        System.out.println(".");

        for(int i=0;i<node.children.size();i++){
            Node child = node.children.get(i);
            display(child);
        }
    }

    public static int size(Node node){
        int size = 1;
        for(Node child:node.children){
            size+= size(child);
        }
        return size;
    }

    public static int height(Node node){
        int h = -1;
        for(Node child:node.children){
            h = Math.max(h,height(child));
        }
        return h+1;
    }

    public static int max(Node node){
        int max = -(int)1e8;
        for(Node child:node.children){
            max = Math.max(max, max(child));
        }
        //work in postarea 
        return Math.max(max, node.data);
    }

    public static boolean find(Node node,int data){
        //work in prearea 
        if(node.data == data){
            return true;
        }
        for(Node child:node.children){
            if(find(child,data)){ return true; }
        }
        return false;
    }
    /********************************************************************************************/



    /******************************************************************************************** */
}