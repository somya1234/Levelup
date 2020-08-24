import java.util.*;
public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] arr = {10,20,30,-1,-1,40,-1,-1,50,60,70,-1,80,-1,-1,-1,90,100,-1,120,-1,-1,110,-1,-1};
        // int[] arr = {1,2,3,4,-1,-1,-1,5,-1,6,-1,-1,7,8,-1,-1,9,-1,-1};
        Node root = constructTree(arr);
        // Node root = construct2(arr);
        display(root);
        // System.out.println(size(root));
        // System.out.println(height(root));
        // System.out.println(find(root, 70));

        // System.out.println(diameter1(root)); //approach 1 
        // System.out.println(diameter2(root).diameter); //approach 2 
        // System.out.println(diameter_02(root)[1]); //approach 2 part 2 
        // diameter3(root); //approach 3 
        // System.out.println(d);
        int[] dia = new int[1]; //appraoch 3 method 2 using array 
        diameter_03(root,dia);
        System.out.println(dia[0]);
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

    public static int height(Node node){ // O(n)
        //in terms of edges -> return -1
        //in terms of vertices -> return 0;
        
        if(node == null){
            return -1;
        }
        int lh = height(node.left);
        int rh = height(node.right);
        return Math.max(lh,rh)+1;
    }
    
    public static boolean find(Node node, int data){
        if(node == null){
            return false;
        }
        return (node.data == data) || find(node.left,data) || find(node.right,data);
    }

    /********************************************************************************************* */
    //2. Set1 

    //Approach 1 -> O(n2)
    public static int diameter1(Node node){
        if(node == null){ return 0; }

        int lh = height(node.left);
        int rh = height(node.right);

        int d1 = diameter1(node.left); //diameter may be present in left subtree
        int d2 = diameter1(node.right); // diamter may be present in right subtree

        return Math.max(d1,Math.max(d2,lh+rh+2));
    }
    //approach 2 
    public static class dpair{
        int height = -1;
        int diameter = 0;
        dpair(int height, int diameter){
            this.height = height;
            this.diameter= diameter;
        }
    }
    //time complexity -> O(n) -> Best method 
    public static dpair diameter2(Node node){
        if(node == null){
            return new dpair(-1,0);
        }

        dpair left = diameter2(node.left);
        dpair right = diameter2(node.right);

        int h = Math.max(left.height,right.height)+1;
        int d = Math.max(left.diameter,Math.max(right.diameter, left.height+right.height+2));
        return new dpair(h,d);
    }

    // approach 2 -> with return type array 
    public static int[] diameter_02(Node node){
        if(node == null){
           return new int[]{-1,0};
        }

        int[] left = diameter_02(node.left);
        int[] right = diameter_02(node.right);
        int[] ans = new int[2];
        ans[0] = Math.max(left[0],right[0])+1;
        ans[1] = Math.max(left[1],Math.max(right[1], left[0]+right[0]+2));
        return ans;
    }

    //approach 3 -> using static  (O(n))

    static int d = 0; // not a good idea 
    public static int diameter3(Node node){
        if(node == null){
            return -1; //return height 
        }
        int lh = diameter3(node.left);
        int rh = diameter3(node.right);
        d = Math.max(d, lh+rh+2);

        return Math.max(lh,rh)+1;
    }

    //better way to write approach 3 is to make array instead of static variable
    //return type is (int) only.
    public static int diameter_03(Node node, int[] res){
        //returns height, ans stores diameter in array.
        if(node == null){
            return -1;
        }
        int lh = diameter_03(node.left, res);
        int rh = diameter_03(node.right, res);
        res[0] = Math.max(res[0],lh+rh+2);
        return Math.max(lh,rh)+1;
    }

    /**************************************************************************************************** */
}