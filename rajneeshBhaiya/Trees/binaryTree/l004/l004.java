import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.*;

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
        // verticalOrderSum(root);
        // bottomView_RightPrefer(root);
        // bottomView_LeftPrefer(root);
        // topView(root);
        topViewLevelWise(root);
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
    public static void verticalOrderSum(Node node){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        int[] ans = new int[minMax[1]-minMax[0]+1];
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(node,Math.abs(minMax[0])));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                vPair top = que.removeFirst();
                Node vnode = top.vnode;
                int level = top.level;
                ans[level]+=vnode.data;
                if(vnode.left!=null) { que.addLast(new vPair(vnode.left, level-1)); }
                if(vnode.right!=null){ que.addLast(new vPair(vnode.right, level+1)); }
            }
        }
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i]);
        }
    }

    //3. bottom view -> prefer to see right element when two are last (behind each other)
    public static void bottomView_RightPrefer(Node node){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        int[] ans = new int[minMax[1]-minMax[0]+1];
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(node, Math.abs(minMax[0])));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                vPair top = que.removeFirst();
                Node vnode = top.vnode;
                int level = top.level;
                ans[level] = vnode.data;
                if(vnode.left!=null) { que.addLast(new vPair(vnode.left, level-1)); }
                if(vnode.right!=null) { que.addLast(new vPair(vnode.right, level+1));}
            }
        }
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i]);
        }
    }

    //3. bottom view -> prefer to see left element when two are last (behind each other)
    public static class bpair{
        Node vnode;
        int level;
        int height = 0;
        bpair(Node vnode, int level, int height){
            this.vnode = vnode;
            this.level = level;
            this.height = height;
        }
    }

    public static void bottomView_LeftPrefer(Node node){
        int[] minMax = new int[2];
        width(node,0 , minMax);
        bpair[] ans = new bpair[minMax[1]-minMax[0]+1];
        LinkedList<bpair> que = new LinkedList<>();
        que.addLast(new bpair(node,Math.abs(minMax[0]),1)); //very important Math.abs() --> for level

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                bpair top = que.removeFirst();
                Node vnode = top.vnode;
                int level = top.level;
                int height = top.height;
                if(ans[level]==null){
                    ans[level] = top;
                }
                if(height>ans[level].height){
                    // can do ans[level] = top; ,or 
                    ans[level].height = height;
                    ans[level].vnode = vnode;
                }
                if(vnode.left!=null){
                    que.addLast(new bpair(vnode.left, level-1, height+1));
                }
                if(vnode.right!=null){
                    que.addLast(new bpair(vnode.right, level+1, height+1));
                }
            }
        }
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i].vnode.data);
        }
    }

    /**************************************************************************************** */
    // 4. TopView 
    public static void topView(Node node){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        Node[] ans = new Node[minMax[1]-minMax[0]+1];
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(node,Math.abs(minMax[0])));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                vPair top = que.removeFirst();
                Node vnode = top.vnode;
                int level = top.level;
                if(ans[level]==null){
                    ans[level] = vnode;
                }
                if(vnode.left!=null) { que.addLast(new vPair(vnode.left, level-1)); }
                if(vnode.right!=null){ que.addLast(new vPair(vnode.right, level+1)); }
            }
        }
        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i].data);
        }
    }

    //5. TopView -> LevelWise from Minimum 
    public static void topViewLevelWise(Node node){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        Node[] ans = new Node[minMax[1]-minMax[0]+1];
        LinkedList<vPair> que = new LinkedList<>();
        que.addLast(new vPair(node,Math.abs(minMax[0])));

        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                vPair top = que.removeFirst();
                Node vnode = top.vnode;
                int level = top.level;
                if(ans[level]==null){
                    ans[level] = vnode;
                }
                if(vnode.left!=null) { que.addLast(new vPair(vnode.left, level-1)); }
                if(vnode.right!=null){ que.addLast(new vPair(vnode.right, level+1)); }
            }
        }
        int idx = Math.abs(minMax[0]);
        if(ans[idx] != null){
            System.out.println(ans[idx].data);
        }
        int left = idx-1;
        int right = idx+1;
        while(left>=0 || right<ans.length){
            if(left>=0 && ans[left]!=null) { System.out.print(ans[left].data+" "); }
            if(right<ans.length && ans[right]!=null) { System.out.println(ans[right].data); }
            left--; right++;
        }
    }

    //6. Diagonal Order 


    //7. boundary Traversal 
    public static void boundaryTraversal(Node node){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        Node[] ans = new Node[minMax[1]-minMax[0]+1];
        LinkedList<bpair> que = new LinkedList<>();
        que.addLast(new bpair(node,Math.abs(minMax[0]),1));

        while(que.size()!=0){
            int size = que.size();
            int maxHeight = 0;
            while(size-->0){
                bpair top = que.removeFirst();
                Node vnode = top.vnode;
                int level = top.level;
                int height = top.height;
                if(ans[level]==null){
                    ans[level] = vnode;
                    maxHeight = height;
                } else if(height>maxHeight){
                    ans[level].add(node);
                    maxHeight = height;
                }
                if(vnode.left!=null) { que.addLast(new bpair(vnode.left, level-1, height+1)); }
                if(vnode.right!=null){ que.addLast(new bpair(vnode.right, level+1, height+1)); }
            }
        }
        int idx = Math.abs(minMax[0]);
        if(ans[idx] != null){
            System.out.println(ans[idx].data);
        }
        int left = idx-1;
        int right = idx+1;
        while(left>=0 || right<ans.length){
            if(left>=0 && ans[left]!=null) { System.out.print(ans[left].data+" "); }
            if(right<ans.length && ans[right]!=null) { System.out.println(ans[right].data); }
            left--; right++;
        }
    }

}