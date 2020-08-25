import java.util.ArrayList;
import java.util.LinkedList;
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
        // int[] arr = {10,20,-1,30,-1,40,-1,-1}; // for linearize 
        Node root = constructGT(arr);
        // display(root);
        // System.out.println(height(root));
        // System.out.println(size(root));
        // System.out.println(max(root));
        // System.out.println(find(root, 80));
        // zigZag(root);
        // System.out.println(isMirror(root,root));
        // linearize(root);
        // linearize(root);
        // display(root);
        attachLeaves(root);
        display(root);
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

    public static boolean nodetoRootPath(Node node, int data , ArrayList<Node> path){
        if(node.data == data){
            path.add(node);
            return true;
        }

        boolean res = false;
        for(Node child:node.children){
            return res || nodetoRootPath(child, data, path);
        }
        if(res){
            path.add(node);
        }
        return res;
    }

    public static void lca(Node node, int a, int b){
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        nodetoRootPath(node, a, path1);
        nodetoRootPath(node, b, path2);

        int i = path1.size()-1;
        int j = path2.size()-1;

        Node lca = null;
        while(i>=0 && j>=0){
            if(path1.get(i)!=path2.get(j)){
                break;
            }
            lca = path1.get(i);
            i--;
            j--;
        }

        System.out.println(lca.data);
    }

    public static void zigZag(Node node){
        int level = 0;
        LinkedList<Node> ms = new LinkedList<>();
        LinkedList<Node> hs = new LinkedList<>();
        ms.add(node);
        while(ms.size()>0){
            Node top = ms.removeLast();
            System.out.print(top.data +" ");
            //to check modulo, we check if it even number or odd
            if((level&1)==0){ 
                //even number
                for(Node child:top.children){
                    hs.add(child);
                }
            } else {
                for(int i=top.children.size()-1;i>=0;i--){
                    Node child = top.children.get(i);
                    hs.add(child);
                    // System.out.println("child is "+child.data);
                }
            }
            if(ms.size()==0){
                level++;
                System.out.println();
                //swap it 
                LinkedList<Node> temp = ms;
                ms = hs;
                hs = temp;
            }
            
        }
    }

    public static boolean isMirror(Node node1, Node node2){
        //prearea 

        if(node1.data!=node2.data){
            return false;
        }
        if(node1.children.size()!=node2.children.size()){
            return false;
        }
        for(int i=0;i<Math.ceil(node1.children.size()/2);i++){
            Node child1 = node1.children.get(i);
            Node child2 = node2.children.get(node2.children.size()-1-i);
            boolean ans = isMirror(child1, child2);
            if(!ans) { return false; }
        }
        return true;
    }

    /******************************************************************************************** */

    //O(n2) approach 
    public static void linearize(Node node){

        for(int i= node.children.size()-1;i>=0;i--){
            Node child = node.children.get(i);
            if(child.children.size()>1){
                linearize(child);
            }
        }

        for(int i=node.children.size()-1;i>0;i--){
            Node child = node.children.get(i);
            node.children.remove(node.children.size()-1);
            Node parent = node.children.get(node.children.size()-1);
            Node parentKiTail = getTail(parent);
            parentKiTail.children.add(child);
        }
    }

    public static Node getTail(Node node){
        while(node.children.size()>0){
            node = node.children.get(0);
        }
        return node;
    }

    //=====
    //O(n) approach 
    public static Node linearize2(Node node){

        //last node gets linearize gives tail
        int n = node.children.size();
        Node oTail =  linearize2(node.children.get(node.children.size()-1)); ;//overall tail 
        for(int i=n-2;i>=0;i--){
            //start from second last node
            Node tail = linearize2(node.children.get(i));
            tail.children.add(node.children.get(i+1));
            node.children.remove(node.children.size()-1);
        }
        return oTail; //think like root will return its tail, which is tail of last children 
    }


    //company question -> 32 lpa
    // public static Node attachLeaves(Node node){
    //     if(node.children.size()==0){
    //         return node;
    //     }
    //     Node tail = node;
    //     while(node.children.size()==1){
    //         node = node.children.get(0);
    //     }
    //     tail = node;
    //     for(int i=0;i<node.children.size()-1;i++){
    //         Node tail1 = attachLeaves(node.children.get(i));
    //         Node tail2 = attachLeaves(node.children.get(i+1));
    //         tail1.children.add(tail2);
    //         tail2.children.add(tail1);
    //         tail = tail2;
    //     }
    //     return tail;
    // }


    //binary tree
    static Node prev = null;
        public static void attachLeaves(Node node){
            if(node == null){ return; }
            if(node.left == null && node.right == null){
                if(prev == null){
                    node.left = prev;
                    prev = node;
                    return;
                } else {
                    node.left = prev;
                    prev.right = node;
                    prev = node;
                    return;
                }
            }

            attachLeaves(node.left);

            attachLeaves(node.right);
        }

    /********************************************************************************************* */
}