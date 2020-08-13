import java.awt.List;
import java.util.ArrayList;

public class permutationsLeetcode {
    public static void main(String[] args) {
        permutations2();
    }

    /**************************************************************************** */
    //question 46. -> Permutations
    // Time complexity -> O(n!)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        List<Integer> sans = new ArrayList<>();
        boolean[] vis = new boolean[n];
        permute_(nums,vis,sans,ans);
        return ans;
    }
    public void permute_(int[] nums, boolean[] vis,List<Integer> sans, List<List<Integer>> ans ){
        if(sans.size()==nums.length){ 
            //if we directly add sans to l, then it gets removed while backtaccking and the result
            //is showed to ans also.
            List<Integer> l = new ArrayList<>(sans);
            ans.add(l);
            return;
        }
        
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(!vis[i]){
                vis[i] = true;
                sans.add(nums[i]);
                permute_(nums,vis,sans,ans);
                vis[i] = false;
                sans.remove(sans.size()-1);
            }
        }
    }
    /****************************************************************************************** */
    /************************************************************************************************ */
    //Question 47 -> Permutations - II 
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        boolean[] vis1 = new boolean[n];
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        permuteUnique_(nums,vis1,ans,sans);
        return ans;
    }
    public void permuteUnique_(int[] nums, boolean[] vis1, List<List<Integer>> ans, List<Integer> sans){
        if(nums.length == sans.size()){
            List<Integer> res = new ArrayList<>(sans);
            ans.add(res);
            return;
        }
        
        int n = nums.length;
        HashSet<Integer> vis2 = new HashSet<>(); //as we need to see the duplicate values.
        for(int i=0;i<n;i++){
            if(vis1[i]==false && !vis2.contains(nums[i])){
                vis1[i] = true;
                vis2.add(nums[i]);
                sans.add(nums[i]);
                permuteUnique_(nums,vis1,ans,sans);
                vis1[i] = false;
                sans.remove(sans.size()-1);
            }       
        }
        return;
    }
    /********************************************************************************************* */
    // 2 approach by sorting array and using prev instead of Hashset 
    // (not using extra space to check unique Permutations. ) =====
    public List<List<Integer>> permuteUnique02(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
           if(nums.length == 0){
               return ans;
           }
           List<Integer> sans = new ArrayList<>();
           Arrays.sort(nums);
           boolean[] vis = new boolean[nums.length]; // to call permuatations in array
           permuteII(nums, vis, ans, sans);
           return ans;
       }
       public void permuteII(int[] nums, boolean[] vis, List<List<Integer>> ans, List<Integer> sans){
           if(sans.size()==nums.length){
               List<Integer> res = new ArrayList<>(sans);
               ans.add(res);
               return;
           }
           
           int prev = (int) 1e8; //unique Permuatations
           int n= nums.length;
           
           for(int i=0;i<n;i++){
               if(prev!=nums[i] && vis[i]!=true ){
                   vis[i] = true;
                   sans.add(nums[i]);
                   prev = nums[i];
                   permuteII(nums,vis,ans,sans);
                   sans.remove(sans.size()-1);
                   vis[i] = false;
               }
           }
       }
       //****************************************************************************************** */
}