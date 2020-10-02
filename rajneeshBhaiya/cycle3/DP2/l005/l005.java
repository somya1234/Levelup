public class l005 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // solve1();
        solve2();
    }

    /****************************************************************************/
    public static void solve1(){
        int[] arr = {1,1,1,1,1};
        int tar = 3; 
        // System.out.println(targetSum_Rec(arr, 0, tar));

        int[][] dp = new int[arr.length][tar];
    }

    public static int targetSum_Rec(int[] arr, int i, int tar){
        if(i==arr.length){
            if(tar == 0 ) return 1; 
            return 0; 
        }
        // when number is +ve
        int ans1 = targetSum_Rec(arr, i+1, tar - arr[i]);
        // when number is -ve.
        int ans2 = targetSum_Rec(arr, i+1, tar + arr[i]); 

        return ans1+ans2; 
    }

    // public static int targetSum_mem(int[] arr, int i, int tar, int[] dp ){
    //     if(i==arr.length){
    //         if(tar == 0 ) return 1; 
    //         return 0; 
    //     }
        
    //     int ans1 = targetSum_Rec(arr, i+1, tar - arr[i]);
    //     int ans2 = targetSum_Rec(arr, i+1, tar + arr[i]); 

    //     return ans1+ans2; 
    // }


    /****************************************************************************/

        // 0 - 1 Knapsack - gfg
    public static void solve2(){
        int[] val = {60,100,120}; 
        int[] wt = {10,20,30};
        int W = 50;
        System.out.println(knapsack_rec(val, wt, 0,0, W));
     }

    public static int knapsack_rec(int[] val, int[] wt, int i,int value,int W){
        if(i==val.length) return value; 

        int ans = 0; 
        // not include 
        ans = Math.max(ans, knapsack_rec(val, wt, i+1, value, W)); 
        // include
        if(W-wt[i]>=0){
            ans = Math.max(ans, knapsack_rec(val, wt, i+1, value+val[i], W-wt[i]));
        }
        return ans; 
    }





    /****************************************************************************/

}
