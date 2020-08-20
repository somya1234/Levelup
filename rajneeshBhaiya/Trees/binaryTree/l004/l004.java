import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class l004 {
    public static void main(String[] args) {
        int[] arr = {10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = constructTree(arr);
        solve(root);
    }

    /******************************************************** */
    public  static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data ){
            this.data = data;
        }
    }

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

    /******************************************************** */
    public static void solve(Node root){

        // BFS_01(root);
        // BFS_02(root);
        // BFS_03(root);
        // zigZagPrint(root);
        // leftView(root);
        // rightView(root);
        // verticalOrder(root); // giving warning 
    }

    /******************************************************************************************* */
    //level order traversal 
    public static void BFS_01(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addFirst(node);
        while(que.size()!=0){
            Node top = que.removeFirst();
            System.out.print(top.data +" ");
            if(top.left!=null) que.addLast(top.left);
            if(top.right!=null) que.addLast(top.right);
        }
    }

    // Linewise Level order Traversal 
    //=== method 1 
    public static void BFS_02(Node node){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        que.addLast(null);
        while(que.size() != 1){
            Node rvtx = que.removeFirst();
            if(rvtx!=null){
                System.out.print(rvtx.data +" ");
                if(rvtx.left!=null) que.addLast(rvtx.left);
                if(rvtx.right!=null) que.addLast(rvtx.right);
            } else {
                que.addLast(null);
                System.out.println();
            }
            
        }
    }

    //==== method 2 -> count method 
    public static void BFS_03(Node node){
        int level = 0;
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        while(que.size()!=0){
            int size = que.size();
            System.out.print("Level "+level+" : ");
            while(size-->0){ // (size-- > 0)
                Node top = que.removeFirst();
                System.out.print(top.data +" ");
                if(top.left!=null) que.addLast(top.left);
                if(top.right!=null) que.addLast(top.right);
                // size--;
            }
            System.out.println();
            level++;
        }
    }

    /******************************************************************************** */
    //Zig zag printing 
    public static void zigZagPrint(Node node){
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        int level = 1;
        st1.push(node);
        while(st1.size()>0){
            Node top = st1.pop();
            System.out.print(top.data + " ");
            if(level == 1){ 
                //add children in left to right direction 
                if(top.left!=null)  st2.push(top.left);
                if(top.right!=null) st2.push(top.right);
            } else {
                if(top.right!=null) st2.push(top.right);
                if(top.left!=null)  st2.push(top.left);
            }
            if(st1.size()==0){
                st1 = st2;
                st2 = new Stack<>();
                System.out.println();
                if(level == 1) level++;
                else level--;
            }

        }
    }

/*********************************************************************************************** */

public static void leftView(Node node){
    LinkedList<Node> que = new LinkedList<>();
    que.add(node);
    while(que.size()!=0){
        int size = que.size();
        System.out.print(que.getFirst().data+" ");
        while(size-->0){
            Node top = que.removeFirst();
            if(top.left!=null) { que.addLast(top.left);}
            if(top.right!=null) { que.addLast(top.right);}
        }
    }
}

public static void rightView(Node node){
    LinkedList<Node> que = new LinkedList<>();
    que.add(node);
    while(que.size()!=0){
        int size = que.size();
        Node prev = null;
        while(size-->0){
            Node top = que.removeFirst();
            prev = top;
            if(top.left != null ){ que.addLast(top.left); }
            if(top.right!=null ){ que.addLast(top.right); }
        }
        System.out.print(prev.data+" ");
    }
}


//================= vertical Order series 
// 1. vertical order 

public static void width(Node node, int level, int[] minMax){
    if(node == null){
        return;
    }

    minMax[0] = Math.min(minMax[0],level);
    minMax[1] = Math.max(minMax[1],level);

    width(node.left, level-1, minMax);
    width(node.right, level+1, minMax);
}

public static class vPair{
    Node vnode = null;
    int level;
    vPair(Node vnode, int level){
        this.vnode = vnode;
        this.level = level;
    }
}

// public static void verticalOrder(Node node){
    // int[] minMax = new int[2];
    // width(node,0,minMax);
    // ArrayList<Integer>[] ans = new ArrayList[minMax[1]-minMax[0]+1];

    // for(int i=0;i<ans.length;i++){
    //     ans[i] = new ArrayList<Integer>();
    // }

    // LinkedList<vPair> que = new LinkedList<>();
    // que.addLast(new vPair(node, Math.abs(minMax[0])));
    // while(que.size()!=0){
    //     int size = que.size();
    //     while(size-->0){
    //         vPair top = que.removeFirst();

    //         Node topNode = top.vnode;
//             int topLevel = top.level;
//             ans[topLevel].add(topNode.data);

//             if(topNode.left!=null ){
//                 que.addLast(new vPair(topNode.left, topLevel-1));
//             }
//             if(topNode.right!=null){
//                 que.addLast(new vPair(topNode.right, topLevel+1));
//             }

//         }
//     }
//     for(int i=0;i<ans.length;i++){
//         System.out.println(ans[i]);
//     }
// }

// 2. vertical order sum 
    

}