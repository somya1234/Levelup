import java.util.*;
public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] arr = {16,25,41,42,46,53,55,60,62,63,64,65,70,74};

        Node root = construct(arr, 0, arr.length-1);
        display(root);
        // predSuccBst_(root);
        // predSucc(root, 42);

        // addNodeItr(root, 53);
        // System.out.println("******************************************************");
        // display(root);

        // addNode(root, 40);
        // System.out.println("******************************************************");
        // display(root);


    }

    /******************************************************************************************** */
    
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
        }
    }

    public static Node construct(int[] arr, int si, int ei){
        if(si>ei){
            return null;
        }

        int mid = ((si+ei)>>>1); //divide by 2
        Node node = new Node(arr[mid]);
        node.left = construct(arr, si, mid-1);
        node.right = construct(arr, mid+1, ei);

        return node;
    }



    public static void display(Node node){
        if(node == null){
            return;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(node.left!=null ? node.left.data : ".");
        sb.append(" <- "+node.data+" -> ");
        sb.append(node.right!=null ? node.right.data:".");
        System.out.println(sb.toString());

        if(node.left!=null) { display(node.left); }
        if(node.right!=null){ display(node.right); }
    }

    /************************************************************************************************ */

    public static class bstPair{
        Node pred, succ = null;
    }

    public static void predSuccBst_(Node node){
        int data = 41;
        bstPair p = new bstPair();
        predSuccBst(node, data, p);
        System.out.println("pred is "+ p.pred.data);
        System.out.println("succ is "+ p.succ.data);
    }

    static Node pred, succ = null; 
    
    //== selfWritten ---
    public static void predSucc(Node node, int data){
        Node curr = node; 
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
            } else if(data<curr.data){
                succ = curr; 
                curr = curr.left; 
            } else {
                pred = curr; 
                curr = curr.right; 
            }
        }
        System.out.println("pred "+ ((pred==null) ? null :  pred.data));
        System.out.println("succ "+  ((succ==null) ? null : succ.data));
        return;
    }


    public static void predSuccBst(Node node, int data, bstPair p){
        // if(node == null){
        //     return;
        // }
        //prev and succ can be declared here also.
        Node curr = node; //iterative method 
        while(curr!=null){ //traverses O(log n )
            if(curr.data<data){

                p.pred = curr;
                // predSuccBst(curr.right, data, p);
                curr = curr.right;
    
            } else if(curr.data>data){
    
                p.succ = curr;
                // predSuccBst(curr.left, data, p);
                curr = curr.left;
    
            } else{
    
                if(curr.left!=null){
                    p.pred = curr.left;
                    while(p.pred.right!=null){
                        p.pred = p.pred.right;
                    }
                }
    
                if(curr.right!=null){
                    p.succ = curr.right;
                    while(p.succ.left!=null){
                        p.succ = p.succ.left;
                    }
                }
                break; //no need to go further now.
            }
        }
       
    }
     
//     /****************************************************************************************************************** */

    public static void addNodeItr(Node node, int data){
        Node curr = node;             
        Node prev = node; 
        if(node == null) return; 
        while(curr!=null){
            prev = curr; 
            if(data<curr.data) curr = curr.left; 
            else if(data>curr.data) curr = curr.right; 
            else break;
        }

        if( data>prev.data) {
            Node nnode = new Node(data); 
            prev.right = nnode; 
        } else {
            Node nnode = new Node(data); 
            prev.left = nnode; 
        }
        return ; 
    }

    //recursive 
    public static Node addNode(Node node, int data){
        if(node == null){
            return new Node(data);
        }

        if(data<node.data){
            node.left = addNode(node.left, data);
        } else {
            node.right = addNode(node.right, data);
        }
        return node;
    }
// /*************************************************************************************************** */

//     public static Node removeData(Node node, int data){
//         if(node == null){
//             return node;
//         }

//         if(data<node.data){
//             node.left = removeData(node.left, data);
//         } else if(data>node.data){
//             node.right = removeData(node.right, data);
//         } else {
//             if(node.left == null || node.right==null){
//                 return node.left!=null ? node.left : node.right;
//             }
//             int minEle = minimum(node.right);
//             node.data = minEle;
//             node.right = removeData(node.right, minEle);
//         }
//         return node;
//     }
    /***************************************************************************************************** */

}