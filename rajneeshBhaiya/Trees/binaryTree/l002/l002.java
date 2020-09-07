public class l002{
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        
    }

    static int sum = -(int)1e8;
    public static int maxSumBwTwoLeaves(Node node){
        if(node==null){
            return -(int)1e8;
        }

        if(node.left == null && node.right == null){
            return node.data;
        }

        int ls = maxSumBwTwoLeaves(node.left);
        int rs = maxSumBwTwoLeaves(node.right);
        if(node.left!=null && node.right!=null)
            sum = Math.max(sum,ls+rs+node.data);
        return Math.max(ls,rs)+node.data;
    }
}