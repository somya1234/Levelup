public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        Node root = constructBST(arr, 0, arr.length-1);
        // display(root);

    }

    /******************************************************************************************** */
    public static Node constructBST(ArrayList<Integer> arr, int si, int ei){
        if(si>ei) { return null; }

        int mid = (si+ei)>>1; //divide by 2 . (normally triple right shift, here data not too long.)
        Node node = new Node(arr.get(mid));

        node.left = constructBST(arr, si, ei-1);
        node.right = constructBST(arr, si+1, ei);
        return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(node.left!=null ? node.left.data : ".");
        ab.append(" <- "+node.data+" -> ");
        sb.append(node.right!=null ? node.right.data:".");
        System.out.println(sb.toString());

        if(node.left!=null) { display(node.left); }
        if(node.right!=null){ display(node.right); }
    }

    /********************************************************************************************/

    public static class allSolutionPair(){
        int height = 0;
        int size = 0;
        boolean find = false;
        Node prev = null, succ = null, pred = null;

        int ceil = (int)1e8;
        int floor = -(int)1e8;
    }

    //binary tree
    public static void allSolutions(Node node, int level, int data, allSolutionPair pair ){
        if(node == null){  return; }

        //traversal in preorder 
        pair.height = Math.max(pair.height, level++);
        pair.size++;
        pair.find = pair.find || (node.data == data); //anyone out of this is true;

        if(node.data>data && node.data<pair.ceil){
            pair.ceil = node.data;
        }

        if(node.data<data){
            pair.floor = Math.max(pair.floor,node.data);
        }

        if(pair.node == data && pair.pred == null){
            pair.pred = prev;
        }
        if(pair.prev!=null && pair.prev.data == data ){
            pair.succ = node;
        }

        pair.prev = node;
        allSolutions(node.left, level+1, data, pair);
        allSolutions(node.right, level+1, data, pair);
    }


    /************************************************************************************************ */
    
    public static void predSuccBst(Node node, int data){
       
        Node curr = node;
        Node pred = null,succ = null;

        while(curr!=null){
            if(curr.data == data){

                if(curr.left!=null){
                    pred = curr.left;
                    while(pred.right!=null){
                        pred = pred.right;
                    }
                }

                if(curr.right!=null){
                    succ = curr.right;
                    while(succ.left!=null){
                        succ = succ.left;
                    }
                }
                break;

            }else if(curr.data < data){
                
                pred = curr;
                curr = curr.right;

            } else{
                succ = curr;
                curr = curr.left;
            }
        }
    }    

}