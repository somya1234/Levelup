import java.util.*;
public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates,target));
    }
    /********************************************************************************* */
    //question 38 -> Combination Sum 
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(candidates.length == 0){
            return ans;
        }
        List<Integer> slist = new ArrayList<>();
        comb(candidates, target ,0,slist,ans);
        return ans;
    }
    public static void comb(int[] candidates, int target, int idx, List<Integer> slist, List<List<Integer>> ans){
        if(target == 0){
            List<Integer> base = new ArrayList<>(slist);
            ans.add(base);
            return;
        }
        
        int n = candidates.length;
        for(int i=idx;i<n;i++){
            if(target-candidates[i]>=0){
                slist.add(candidates[i]);
                comb(candidates,target-candidates[i] , i, slist , ans);
                //to remove from arraylist
                slist.remove(slist.size()-1);
                // slist.remove(new Integer(candidates[i])); -> deprecated error.
            }
        }
    }
    /********************************************************************************* */
    
}