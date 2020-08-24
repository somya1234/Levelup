import java.util.*;
public class l001{
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

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

    //postorder traversal 
    public static void traversal(Node node){
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
        int height = -1;
    }
    public static int height(Node node){
        Stack<hPair> st = new Stack<>();
        st.push(new hPair(node,false,false,-1));
        while(st.size()>0){
            hPair top = st.peek();

            if(top.leftDone==false){
                top.leftDone = true;
                if(top.node.left!=null){
                    st.push(new hPair(node,false,false,-1));
                }
            } else if(top.rightDone == false){
                top.rightDone = true;
                if(top.node.right!=null){
                    st.push(new hPair(node,false,false,-1));
                }
            } else {
                int h = top.height;
                st.pop();
                top = st.peek();
                top.height = Math.max(top.height,h+1);
            }
        }
    }


    /******************************************************************************************* */
}