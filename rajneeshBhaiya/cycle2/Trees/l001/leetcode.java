import javax.swing.tree.TreeNode;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }
/*************************************************************************************************/
    //Leetcode 979 
    int totalCoins = 0;
    public int distributeCoins_(TreeNode root){
        if(root==null){  return 0; }

        int leftDefeGain = distributeCoins_(root.left);
        int rightDefeGain = distributeCoins_(root.right);

        totalCoins += Math.abs(leftDefeGain) + Math.abs(rightDefeGain);
        //or you can add what you are returning into totalCoins , because that is what you are moving.
        return root.val - 1 + leftDefeGain + rightDefeGain;
    }

    public int distributeCoins(TreeNode root){
        if(distributeCoins_(root)!=0) return -1;
        return totalCoins;
    }

/************************************************************************************************ */
    //Que 968 
    int cameras = 0;
    int minCamerasCover_(TreeNode root){
        if(root == null){ return 1; }

        int lans = minCamerasCover_(root.left);
        int rans = minCamerasCover_(root.right);

        if(lans == -1 || rans == -1){
            //requirement will be heard first
            cameras++;
            return 0;
        }

        if(lans ==0 || rans == 0){
            //if anyone has camera, then i am Covered
            return 1;
        }

        // else
        //no child has camera, then i require camera now 
        return -1;
    }

    public int minCameraCover(TreeNode root){
        if(root == null){ return 0; }
        //if root needs a camera
        if(minCamerasCover_(root)==-1){ cameras++; }
        return cameras;
    }

    /*************************************************************************************************/
}