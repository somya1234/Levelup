import java.util.*;
public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] arr = {1,2,4,8,-1,-1,9,-1,-1,5,10,-1,-1,11,-1,-1,3,6,-1,13,-1,-1,7,14,-1,-1,-1};
        Node root = construct(arr);
        display(root);
        allSolutions(root);

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

    static int idx = 0;
    public static Node construct(int[] arr){
        if(idx == arr.length || arr[idx]==-1){
            idx++;
            return null;
        }

        Node node = new Node(arr[idx++]);
        node.left = construct(arr);
        node.right = construct(arr);

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

    /********************************************************************************************/

    public static void allSolutions(Node root){
        apair p = new apair();
        int data = 10;
        allSolutions2(root, data, p, 0);
        System.out.println("size is "+ p.size);
        System.out.println("height is "+ p.height);
        System.out.println("find is "+ p.find);
        System.out.println("pred is "+p.pred.data);
        System.out.println("succ is "+p.succ.data);
        System.out.println("ceil is "+p.ceil);
        System.out.println("floor is "+p.floor);
        
    }

    public static class apair{
        Node prev= null, pred, succ;
        int size = 0;
        int height = 0;
        boolean find = false;
        int ceil = (int)1e8 ;
        int floor = -(int)1e8 ;
    }

    public static void allSolutions2(Node node, int data, apair p, int level){
        if(node == null){ return; }

        allSolutions2(node.left, data, p, level+1);

        //inorder 
        //p.pred == null, not important check.
        if(p.pred==null && node.data == data){
            p.pred = p.prev;
        }
        //this check is very important because in starting prev is null.
        if(p.prev!=null && p.prev.data == data){
            p.succ = node;
        }
        p.prev = node;

        p.size++;
        if(level>p.height){
            p.height = level;
        }
        if(node.data == data || p.find){
            p.find = true;
        }
        if(node.data<p.ceil && node.data>data){
            p.ceil = node.data;
        }
        if(node.data>p.floor && node.data<data){
            p.floor = node.data;
        }

        allSolutions2(node.right, data, p, level+1);
    }


    /************************************************************************************************ */
    
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