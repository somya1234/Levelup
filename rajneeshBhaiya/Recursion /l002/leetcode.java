import java.util.*;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }

    /********************************************************************************* */
    // question 38 -> Combination Sum
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (candidates.length == 0) {
            return ans;
        }
        List<Integer> slist = new ArrayList<>();
        comb(candidates, target, 0, slist, ans);
        return ans;
    }

    public static void comb(int[] candidates, int target, int idx, List<Integer> slist, List<List<Integer>> ans) {
        if (target == 0) {
            List<Integer> base = new ArrayList<>(slist);
            ans.add(base);
            return;
        }

        int n = candidates.length;
        for (int i = idx; i < n; i++) {
            if (target - candidates[i] >= 0) {
                slist.add(candidates[i]);
                comb(candidates, target - candidates[i], i, slist, ans);
                // to remove from arraylist
                slist.remove(slist.size() - 1);
                // slist.remove(new Integer(candidates[i])); -> deprecated error.
            }
        }
    }

    /********************************************************************************* */
    /**************************************************************************************** */

    // Question 40. Combination Sum II (V.Imp)
    // It is like Unique combinations (as we have done unique permutations in
    // lecture 1.)
    // not talking about single combinations.

    // note - On leetcode, output of sample example is wrong.
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        Arrays.sort(candidates);
        findC(candidates, target, sans, ans, 0);
        return ans;
    }

    public void findC(int[] candidates, int target, List<Integer> sans, List<List<Integer>> ans, int idx) {
        if (target == 0) {
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }

        int n = candidates.length;
        HashSet<Integer> hs = new HashSet<>();
        for (int i = idx; i < n; i++) {
            if (target - candidates[i] >= 0 && !(hs.contains(candidates[i]))) {
                sans.add(candidates[i]);
                hs.add(candidates[i]); // for uniqueness of elements.
                findC(candidates, target - candidates[i], sans, ans, i + 1);
                sans.remove(sans.size() - 1);
            }
        }
    }

    /**************************************************************************************** */
    /********************************************************************************************* */
    // Question 77. -> Combinations

    // ( 1--> n ), find all combinations pair of size k
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        findCombinations(1, n, k, sans, ans);
        return ans;
    }

    public void findCombinations(int idx, int n, int k, List<Integer> sans, List<List<Integer>> ans) {

        if (sans.size() == k) {
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }

        for (int i = idx; i <= n; i++) {
            sans.add(i);
            // do (i+1) in the call -> major error.
            findCombinations(i + 1, n, k, sans, ans);
            sans.remove(sans.size() - 1);
        }
    }

    /********************************************************************************************* */
    /********************************************************************************************* */
    // Q 216 -> Combinations Sum III (like single combinations.)
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        find3PairC(n, k, 1, sans, ans);
        return ans;
    }

    public void find3PairC(int n, int k, int idx, List<Integer> sans, List<List<Integer>> ans) {
        if (n == 0 && k == 0) {
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }

        for (int i = idx; i <= 9; i++) {
            if (n - i >= 0) {
                sans.add(i);
                find3PairC(n - i, k - 1, i + 1, sans, ans);
                sans.remove(sans.size() - 1);
            }
        }
    }

    /********************************************************************************************* */
    /*********************************************************************************************** */
    // Q90 -> Subsets II -> (v.imp)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //this was a set, it automatically maintains unique values.
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> sans = new ArrayList<>();
        // it is power set, wo we sort the array first.
        Arrays.sort(nums);
        subsets(nums, 0, sans, ans);
        return new ArrayList<>(ans);
    }

    public void subsets(int[] nums, int idx, List<Integer> sans, Set<List<Integer>> ans) {
        if (idx == nums.length) {
            List<Integer> base = new ArrayList<>(sans);
            ans.add(base);
            return;
        }

        HashSet<Integer> hs = new HashSet<>(); // for unique combinations
        for (int i = idx; i < nums.length; i++) {
            if (!hs.contains(nums[i])) { // for unique combinations
                hs.add(nums[i]);
                if (!ans.contains(sans)) { // to remove duplicate sets like [], an empty list
                    ans.add(new ArrayList<>(sans));
                }
                sans.add(nums[i]);
                subsets(nums, i + 1, sans, ans);
                sans.remove(sans.size() - 1);
            }
        }
    }

    /****************************************************************************************** */
    //better code Q90
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        Arrays.sort(nums);
        subsets(nums, 0, sans, ans);
        return ans;
    }

    public void subsets(int[] nums, int idx, List<Integer> sans, List<List<Integer>> ans) {
        List<Integer> base = new ArrayList<>(sans);
        ans.add(base);

        int prev = (int) -1e8; // to make unique combinations
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] != prev) {
                prev = nums[i];
                sans.add(nums[i]);
                subsets(nums, i + 1, sans, ans);
                sans.remove(sans.size() - 1);
            }
        }
    }
    /********************************************************************************************* */
}