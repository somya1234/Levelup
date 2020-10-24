class Solution
{
	ArrayList <Integer> printBoundary(Node node)
	{
	    ArrayList<Integer> ans = new ArrayList<>(); 
	    if(node == null) return ans; 
	    ans.add(node.data);
	    if(node.left==null && node.right==null) return ans; 
        if(node.left!=null) addLeft(node.left, ans); 
        addLeaves(node, ans);
        if(node.right!=null) addRight(node.right, ans); 
        return ans; 
	    
	    
	}
	
	public  void addLeft(Node node, ArrayList<Integer> ans){
        if(node == null) return; 
        // add non leave nodes
        if(node.left!=null || node.right!=null)
            ans.add(node.data);
        if(node.left!=null) addLeft(node.left, ans);
        else if(node.right!=null) addLeft(node.right, ans);
    }

    public  void addLeaves(Node node, ArrayList<Integer> ans){
        if(node == null) return; 

        if(node.left==null && node.right == null) ans.add(node.data); 
        
        addLeaves(node.left, ans);
        addLeaves(node.right, ans);
    
    }

    public  void addRight(Node node, ArrayList<Integer> ans){
        if(node == null) return; 
        
        if(node.right!=null) addRight(node.right, ans);
        else if(node.left!=null) addRight(node.left, ans);
        if(node.right!=null || node.left!=null)
            ans.add(node.data);
    }
}