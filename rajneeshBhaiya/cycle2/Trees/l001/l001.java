import java.util.*;
public class l001{
    public static void main(String[] args) {
        solve();
    }

    /***************************************************************************************** */
    // public static class Node{
    //     int data = 0;
    //     Node left;
    //     Node right = null;
    //     Node(int data){
    //         this.data = data;
    //     }
    // }

    //to calculate diameter 
    public static class Node{
        int data = 0;
        Node left;
        Node right = null;
        int h = -1;
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
        // iterativeTraversal1(root);
        System.out.println(height(root));
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

    public static class hPair{
        Node node = null;
        boolean leftDone = false;
        boolean rightDone = false;
        boolean selfWork = false;
        hPair(Node node, boolean leftDone, boolean rightDone, boolean selfWork){
            this.node = node;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
            this.selfWork = selfWork;
        }
    }

    public static int height(Node node){
        Stack<hPair> st = new Stack<>();
        hPair rootPair = new hPair(node,false,false,false);
        st.push(rootPair);
        while(st.size()>0){
            hPair top = st.peek();

            if(top.leftDone==false){
                top.leftDone = true;
                if(top.node.left!=null){
                    st.push(new hPair(node,false,false,false));
                }
            } else if(top.rightDone == false){
                top.rightDone = true;
                if(top.node.right!=null){
                    st.push(new hPair(node,false,false,false));
                }
            } else if(top.selfWork == false){
                top.selfWork = true;
                int h = -1;
                if(top.node.left==null && top.node.right==null){
                    h = 0;
                } else if(top.node.left==null){
                    h = top.node.right.h+1;
                } else if(top.node.right==null){
                    h = top.node.left.h+1;
                } else {
                    h = Math.max(top.node.left.h, top.node.right.h)+1;
                }
                top.node.h = h;
            } else{
                st.pop();
            }
        }
        return node.h;
        // return 1;
    }

    /******************************************************************************************* */
    // Morris Traversal 

    public static Node rightMost(Node node, Node curr){
        while(node.right!=null && node.right!=curr){
            node = node.right;
        }
        return node;
    }

    public static void morrisInTraversal(Node node){
        Node curr = node;
        while(curr!=null){
            Node next = curr.left;
            if(next == null){
                System.out.print(curr.data +" ");
                curr = curr.right;
            } else {
                Node rightMost = rightMost(next,curr);
                if(rightMost.right== null){
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    System.out.print(curr.data+" ");
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }


    /******************************************************************************************* */
}