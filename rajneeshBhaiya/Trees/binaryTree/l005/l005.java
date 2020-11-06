import java.util.Stack;

public class l005 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /*********************************************************************************/
    public static class tpair{
        Node node = null; 
        boolean selfDone = false; 
        boolean leftDone = false; 
        boolean rightDone = false; 
        tpair(Node node, boolean selfDone, boolean leftDone, boolean rightDone){
            this.node = node; 
            this.selfDone = selfDone; 
            this.leftDone = leftDone; 
            this.rightDone = rightDone; 
        }
    }

    public static void inorderTraversal(Node node){
        Stack<tpair> st = new Stack<>(); 
        st.push(new tpair(node, false, false, false));
        while(st.size()!=0){
            tpair top = st.peek(); 

            if(!top.leftDone){
                if(top.left!=null) st.push(new tpair(top.left, false, false, false)); 
                top.leftDone = true; 
            } else if(!top.selfDone){
                System.out.print(top.data+" "); 
                top.selfDone = true; 
            } else if(!top.rightDone){
                if(top.right!=null) st.push(new tpair(top.right, false, false, false)); 
                top.rightDone = true; 
            } else{
                st.pop(); 
            }
        }
    }

    public static void postorderTraversal(Node node){
        Stack<tpair> st = new Stack<>(); 
        st.push(new tpair(node, false, false, false));
        while(st.size()!=0){
            tpair top = st.peek(); 

            if(!top.leftDone){
                if(top.left!=null) st.push(new tpair(top.left, false, false, false)); 
                top.leftDone = true; 
            } else if(!top.rightDone){
                if(top.right!=null) st.push(new tpair(top.right, false, false, false)); 
                top.rightDone = true; 
            } else if(!top.selfDone){
                System.out.print(top.data+" "); 
                top.selfDone = true; 
            } else{
                st.pop(); 
            }
        }
    }

    /*********************************************************************************/
}
