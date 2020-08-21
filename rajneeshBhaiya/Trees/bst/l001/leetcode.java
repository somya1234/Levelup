public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        //recoverBst();
    }
Node A = null;
Node B = null;
Node prev = new Node(-(int)1e9);
    public static void recoverBst(Node root){
        if(root == null){ return; }
        recoverBst(root.left);

        if(prev.data>root.data){
            B = root;
            if(A == null){ A = pre; }
            else return true;
        }
    }
}