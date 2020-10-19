import java.util.ArrayList;

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


    public static void solve(){
        int[] arr = {10,20,40,-1,-1,50,60,70,-1,80,-1,-1,-1,90,70,-1,120,-1,-1,110,-1,-1};
        Node root = construct(arr);
        display(root);

        //width 
        // int[] minMax = new int[2];
        // width(root, 0, minMax);
        // int width = minMax[1]-minMax[0]; 
        // System.out.println(width);

        //kdown
        kDown(root,2);
    }

    // instead of making minMax static.
    public static void width(Node node, int level, int[] minMax){
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

    /*******************************************************************************************/

    //leetcode 863 
    public static void kFar(Node node, int tar, int k){
        ArrayList<Node> path = new ArrayList<>(); 
        rootToNodePath(node, tar, path); 

        Node prev = null; 
        for(int i=0; i<path.size(); i++){
            kdown(path.get(i), prev, k-i); 
            prev = path.get(i); 
        }
    }

    public static boolean rootToNodePath(Node node , int tar, ArrayList<Node> path){
        if(node==null) return false; 

        if(node.data == tar){
            path.add(node); 
            return true; 
        }

        boolean res = false; 
        res = res || rootToNodePath(node.left, tar, path); 
        res = res || rootToNodePath(node.right, tar, path);

        if(res){
            path.add(node); 
            return true; 
        }

        return false; 
    }

    public static void kdown(Node node, Node block, int k ){
        if(node == null || node == block || k<0) return; 

        if(k==0){
            System.out.println(node.data);
            return;
        }  

        kdown(node.left, block, k-1);
        kdown(node.right, block , k-1);
    }

    //method 2 
    public static int kFar_better(Node node, int data, int k ){
        if(node==null) return -1; 

        if(node.data == data){
            kdown(node, null, k); 
            return 1; 
        }

        int ld = kFar_better(node.left, data, k); 
        if(ld!=-1){
            kdown(node,node.left,k-ld );
            return ld+1; 
        }

        int rd = kFar_better(node.right, data, k); 
        if(rd!=-1){
            kdown(node, node.right, k-rd); 
            return rd+1; 
        }

        return -1; 
    }
    /*******************************************************************************************/

    //gfg - https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/

    public static void burnTree(Node root, Node target){

    }

    public static int kFarBurn(Node node, Node target){

        if(node == target){
            kdownBurn(node, null, )
        }

        int ld = kFarBurn(node.left, target); 
        
        int rd = kFarBurn(node.right, target);
    }

    /*******************************************************************************************/
}