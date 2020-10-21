import java.util.HashMap;

import org.w3c.dom.Node;

public class l002 {
    public static void main(String[] args) {
        solve();
    }


    /****************************************************************************************/
    // https://www.geeksforgeeks.org/clone-binary-tree-random-pointers/
    public static Node createTree_HM_dfs1(Node node, HashMap<Node, Node> map){
        if(node == null) return null; 

        Node nnode = new Node(node.data); 
        map.put(node, nnode);
        nnode.left = createTree_HM_dfs1(node.left, map); 
        nnode.right = createTree_HM_dfs1(node.right, map);

        return nnode;

    }

    public static void setRandom_HM_dfs1(Node node , HashMap<Node, Node> map){

        if(node.random!=null)  map.get(node).random = map.get(node.random);
        setRandom_HM_dfs1(node.left, map);
        setRandom_HM_dfs1(node.right, map);

    }

    public static Node cloneBT(Node node){
        HashMap<Node, Node> map = new HashMap<>(); 
        Node nroot = createTree_HM_dfs1(node, map); 
        setRandom_HM_dfs1(node, map);

        return nroot; 
    }


    //===== method 2 - without hashmap 

    public static Node cloneBT_WithoutRandom(Node root){
        if(root == null) return null; 

        Node node = new Node(root.data); 
        node.left = cloneBT_WithoutRandom(root.left); 
        node.right = cloneBT_WithoutRandom(root.right); 

        root.left = node; 
        return root; 
    }

    public static void setRandoms(Node root){
        if(root == null) return; 

        if(root.random!=null) root.left.random = root.random.left; 

        setRandoms(root.left.left);
        setRandoms(root.right);
    }

    public static Node extractTree(Node root){

        //leftclone node
        Node lcNode = null; 
        Node rcNode = null; 
        Node cloneNode = root.left; 

        if(root.left.left!=null) lcNode = extractTree(root.left.left); 
        if(root.right!=null) rcNode = extractTree(root.right); 

        root.left = cloneNode.left; 
        cloneNode.left = lcNode; 
        cloneNode.right = rcNode; 

        return cloneNode; 
    }

    public static void cloneBT(){
        cloneBT_WithoutRandom(node);
        
    }

    /****************************************************************************************/

}
