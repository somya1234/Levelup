import java.util.Arrays;
public class l004 {
    public static void main(String[] args) {
        solve();
    }

    /********************************************************************************************/

    public static void print(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2d(int[][] arr) {
        for (int[] ar : arr) {
            print(ar);
        }
        System.out.println();
    }

    /**************************************************************************************/

    public static void solve() {
        // solve1();
        // solve2();
        // solve3(); 
        solve4();  
    }

    /*****************************************************************************************/

    public static void solve1() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(coinChangePermutation_rec(arr, tar));
        int[] dp = new int[tar+1]; 
        // System.out.println(coinChangePermutation_mem(arr, tar, dp));
        // System.out.println(coinChangePermutation_tab(arr, tar, dp));
    }

    public static int coinChangePermutation_rec(int[] arr, int tar) {
        if (tar == 0)
            return 1;

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += coinChangePermutation_rec(arr, tar - ele);
        }

        return count;
    }

    public static int coinChangePermutation_mem(int[] arr, int tar, int[] dp) {
        if (tar == 0)
            return dp[tar] = 1;

        if (dp[tar] != 0)
            return dp[tar];

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += coinChangePermutation_mem(arr, tar - ele, dp);
        }

        return dp[tar] = count;
    }

    public static int coinChangePermutation_tab(int[] arr, int tar, int[] dp) {
        int Tar = tar;
        for (tar = 0; tar <= Tar; tar++) {
            if (tar == 0) {
                dp[tar] = 1;
                continue;
            }

            for (int ele : arr) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele]; 
            }
        }
        return dp[Tar];
    }

    /*****************************************************************************************/

    public static void solve2(){
        int[] arr = {2,3,5,7}; 
        int tar = 10; 
        int[] dp = new int[tar+1]; 
        System.out.println(coinChangeCombination_mem(arr, tar, 0, dp));
        // System.out.println(coinChangeCombination_tab(arr, tar, dp));
        print(dp); 
        
    }

    public static int coinChangeCombination_mem(int arr[], int tar,int idx,  int[] dp){
        if(tar == 0) return dp[idx] = 1; 

        if(dp[idx]!=0) return dp[idx]; 

        int count = 0; 
        for(int i=idx; i<arr.length; i++ ){
            if(tar - arr[i]>=0)
                count += coinChangeCombination_mem(arr, tar - arr[i], i, dp); 
        }

        return dp[idx] = count; 
    }

    public static int coinChangeCombination_tab(int[] arr, int tar, int[] dp){
        int Tar = tar; 
        dp[0] = 1; 
        for( int ele: arr){
            for(tar = ele; tar<=Tar; tar++){
                if(tar - ele >= 0 )
                    dp[tar] += dp[tar - ele]; 
            }
        }
        return dp[Tar]; 
    }

    /*****************************************************************************************/

    // gfg - Find number of solutions of a linear equation of n variables
    // https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/


    public static void solve3(){
        int[] coeff = {1,2}; // like coins 
        int rhs = 5; // like target 
        int[] dp = new int[rhs+1]; 
        Arrays.fill(dp, -1); 
        System.out.println(linearEqn_mem(coeff, rhs, dp));

    }

    public static int linearEqn_mem(int[] coeff, int rhs, int[] dp){
        if(rhs == 0) return dp[rhs] = 1; 

        // if - 1, that means, default value, unprocessed, so move forward.
        if(dp[rhs]!=-1) return dp[rhs]; 

        int ans = 0; 
        for(int val: coeff){
            if(rhs - val >=0)
                ans+= linearEqn_mem(coeff, rhs- val, dp); 
        }

        return dp[rhs] = ans; 
    }

    /*****************************************************************************************/

    // Single Combination. 
    public static void solve4(){
        int[] coins = {2,3,5,7}; 
        int tar = 10; 
        // System.out.println(targetSum_rec(coins, tar, 0, ""));
        int[][] dp = new int[coins.length+1][tar+1]; 
        for(int[] d : dp) Arrays.fill(d, -1);
        // System.out.println(targetSum_mem(coins, tar, 0, dp));
        // print2d(dp);

        // Backwards memoization 
        int n = coins.length; 
        // System.out.println(targetSum_backwards(coins, tar, n, dp));
        // print2d(dp);

        //tabulation 
        for(int[] d : dp) Arrays.fill(d, 0);
        // System.out.println(targetSum_tab(coins, tar, dp, n));
        // print2d(dp);

        // print all ways to reach target Sum 
        targetSum_tab(coins, tar, dp, n);
        print2d(dp);
        printCoins(coins, tar, n, dp, "");
    }

    // using subsequence method here.
    // Recursion 
    public static int targetSum_rec(int[] coins, int tar, int idx, String str){
        if(tar == 0){
            System.out.println(str);
            return 1;
        } 
        if( idx == coins.length) return 0; 

        int ans = 0; 
        // include
        if(tar - coins[idx]>=0)
            ans+= targetSum_rec(coins, tar - coins[idx], idx+1, str+coins[idx]+" "); 
        // not include.
        ans+= targetSum_rec(coins, tar, idx+1, str); 

        return ans; 
    }

    public static int targetSum_mem(int[] coins, int tar, int idx, int[][] dp){
        if(tar == 0 || idx == coins.length)
            return dp[idx][tar] = (tar == 0 ) ? 1 : 0;

        if(dp[idx][tar]!=-1) return dp[idx][tar]; 

        int ans = 0; 
        if(tar - coins[idx]>=0)
            ans+= targetSum_mem(coins, tar- coins[idx], idx+1, dp);
        ans+= targetSum_mem(coins, tar, idx+1, dp); 

        return dp[idx][tar] = ans; 
    }

    public static int targetSum_backwards(int[] coins, int tar, int n , int[][] dp){
        if(tar == 0 || n == 0) return dp[n][tar] = (tar==0) ? 1: 0; 

        if(dp[n][tar]!=-1) return dp[n][tar]; 

        int ans = 0; 
        if(tar - coins[n-1]>=0) ans+= targetSum_backwards(coins, tar- coins[n-1], n-1, dp);
        ans+= targetSum_backwards(coins, tar, n-1, dp); 

        return dp[n][tar] = ans; 
    }

    // Tabulation 
    public static int targetSum_tab(int[] coins, int tar, int[][] dp, int n ){
        int N = n; int Tar = tar; 
        for(n=0; n<=N; n++){
            for(tar = 0; tar<=Tar; tar++){
                if(tar == 0 || n ==0){
                    dp[n][tar] = (tar==0) ? 1: 0; 
                    continue; 
                }
                if(tar - coins[n-1]>=0) dp[n][tar] = dp[n-1][tar-coins[n-1]]; 
                dp[n][tar]+= dp[n-1][tar]; 
            }
        }
        return dp[N][Tar]; 
    }

    public static void printCoins(int[] coins, int tar, int n, int[][] dp, String ways){
        if(tar == 0 ){
            System.out.println(ways);
            return;
        }

        int val = dp[n][tar]; 
        if(val>0){
            if(tar - coins[n-1]>=0){
                printCoins(coins, tar-coins[n-1], n-1, dp, ways+coins[n-1]+" ");
            }
            printCoins(coins, tar, n-1, dp, ways);
        }
        return; 
    }
    /*****************************************************************************************/
}