public static void BoundaryTraversal(Node root){
    ArrayList<Integer> ans=new ArrayList<>();
    AddLeft(root,ans);
    AddLeaves(root,ans);
    Addright(root.right,ans);
    System.out.print(ans);
    
}
public static void AddLeft(Node root,ArrayList<Integer> ans){
    while(root.left!=null){
        ans.add(root.data);
        root=root.left;
    }
}
public static void AddLeaves(Node root,ArrayList<Integer> ans){
    if(root==null){
        return;
    }
    if(root.left==null && root.right==null){
        ans.add(root.data);
        return;
    }
    AddLeaves(root.left,ans);
    AddLeaves(root.right,ans);
}
public  static void Addright(Node root,ArrayList<Integer> ans){
    ArrayList<Integer> temp=new ArrayList<>();
   while(root.right!=null){
       temp.add(root.data);
       root=root.right;
   }
   while(temp.size()>0){
       ans.add(temp.remove(temp.size()-1));
   }
}