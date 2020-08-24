import java.util.*;
public class l001{
    public static void main(String[] args) {
        solve();
    }

    /***************************************************************************************** */
    public static class Node{
        int data = 0;
        Node left;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }

    static int constructIdx = 0;
    public static Node construct(int[] arr){
        if(constructIdx==arr.length || arr[constructIdx]==-1){
            constructIdx++;
            return null;
        }

        Node node = new Node(arr[constructIdx++]);
        node.left = construct(arr);
        node.right = construct(arr);
        return node;
    }

    public static void display(Node node){
        if(node==null) return;
        StringBuilder sb = new StringBuilder();
        
        sb.append(node.left!=null?node.left.data+"" : ".");
        sb.append(" <- " + node.data + " -> ");
        sb.append(node.right!=null?node.right.data+"" : ".");

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);
    }

    /******************************************************************************************/

    public static void solve(){
        int[] arr = {1,2,3,4,-1,-1,-1,5,-1,6,-1,-1,7,8,-1,-1,9,-1,-1};
        Node root = construct(arr);
        // display(root);
        iterativeTraversal1(root);
    }

    /**************************************************************************************** */
    //Question1 -> Iteratively postorder traversal 
    public static class tPair{
        Node node = null;
        boolean selfDone = false;
        boolean leftDone = false;
        boolean rightDone = false;

        tPair(Node node, boolean selfDone, boolean leftDone, boolean rightDone){
            this.node = node;
            this.selfDone = selfDone;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }
    }

    //postorder traversal ,
    //similarly can be done in preorder and inorder
    public static void iterativeTraversal1(Node node){
        Stack<tPair> st = new Stack<>();
        st.push(new tPair(node, false, false, false));
        while(st.size()>0){
            tPair top = st.peek();

            if(top.leftDone==false){
                top.leftDone = true;
                if(top.node.left!=null){
                    st.push(new tPair(top.node.left,false,false,false));
                }
            } else if(top.rightDone == false){
                top.rightDone = true;
                if(top.node.right!=null)
                    st.push(new tPair(top.node.right, false, false, false));
            } else if(top.selfDone == false){
                top.selfDone = true;
                System.out.print(top.node.data +" ");
            } else {
                st.pop();
            }
        }
    }
    /***************************************************************************************** */

    //Question 2-> Find diameter using postorder traversal 

    // public static class hPair{
    //     Node node = null;
    //     boolean leftDone = false;
    //     boolean rightDone = false;
    //     int height = -1;
    // }

    // public static int height(Node node){
    //     Stack<hPair> st = new Stack<>();
    //     st.push(new hPair(node,false,false,-1));
    //     while(st.size()>1){
    //         hPair top = st.peek();

    //         if(top.leftDone==false){
    //             top.leftDone = true;
    //             if(top.node.left!=null){
    //                 st.push(new hPair(node,false,false,-1));
    //             }
    //         } else if(top.rightDone == false){
    //             top.rightDone = true;
    //             if(top.node.right!=null){
    //                 st.push(new hPair(node,false,false,-1));
    //             }
    //         } else {
    //             int h = top.height+1;
    //             st.pop();
    //             top = st.peek();
    //             top.height = Math.max(top.height,h);
    //         }
    //     }
    // }


    /******************************************************************************************* */
}