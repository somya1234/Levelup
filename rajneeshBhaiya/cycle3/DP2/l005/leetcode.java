public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        solve1();
    }

    /******************************************************************************************/

    // leetcode 494. Target Sum

    public static void solve1(){
        int[] nums = {1,1,1,1,1}; 
        int tar = 3; 
        System.out.println(targetSum_Rec(arr, 0, tar));
    }

    public static int targetSum_Rec(int[] arr, int i, int tar){
        if(i==arr.length){
            if(tar == 0 ) return 1; 
            return 0; 
        }
        
        int ans1 = targetSum_Rec(arr, i+1, tar - arr[i]);
        int ans2 = targetSum_Rec(arr, i+1, tar + arr[i]); 

        return ans1+ans2; 
    }

/******************************************************************************************/

}
