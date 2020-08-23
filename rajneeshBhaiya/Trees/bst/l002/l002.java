import java.util.*;
public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] arr = {16,25,41,42,46,53,55,60,62,63,64,65,70,74};
        Node root = construct(arr, 0, arr.length-1);
        display(root);
        predSuccBst_(root);
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
        int data = 42;
        bstPair p = new bstPair();
        predSuccBst(node, data, p);
        System.out.println("pred is "+ p.pred.data);
        System.out.println("succ is "+ p.succ.data);
    }
    
    public static void predSuccBst(Node node, int data, bstPair p){
        // if(node == null){
        //     return;
        // }
        Node curr = node;
        while(curr!=null){ //traverses O(log n )
            if(curr.data<data){

                p.pred = curr;
                predSuccBst(curr.right, data, p);
    
            } else if(curr.data>data){
    
                p.succ = curr;
                predSuccBst(curr.left, data, p);
    
            } else{
    
                Node temp = curr;
                while(temp.left!=null){
                    p.pred = temp.left;
                    temp = temp.left;
                }
    
                temp = curr;
                while(temp.right!=null){
                    p.succ = temp.right;
                    temp = temp.right;
                }
            }
        }
       
    }


    /********************************************************************************** */
//     public static void predSuccBst(Node node, int data){
       
//         Node curr = node;
//         Node pred = null,succ = null;

//         while(curr!=null){
//             if(curr.data == data){

//                 if(curr.left!=null){
//                     pred = curr.left;
//                     while(pred.right!=null){
//                         pred = pred.right;
//                     }
//                 }

//                 if(curr.right!=null){
//                     succ = curr.right;
//                     while(succ.left!=null){
//                         succ = succ.left;
//                     }
//                 }
//                 break;

//             }else if(curr.data < data){
                
//                 pred = curr;
//                 curr = curr.right;

//             } else{
//                 succ = curr;
//                 curr = curr.left;
//             }
//         }
//     }    
//     /****************************************************************************************************************** */
//     public static Node addNode(Node node, int data){
//         if(node == null){
//             return new Node(data);
//         }

//         if(data<node.data){
//             node.left = addNode(node.left, data);
//         } else {
//             node.right = addNode(node.right, data);
//         }
//         return node;
//     }
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