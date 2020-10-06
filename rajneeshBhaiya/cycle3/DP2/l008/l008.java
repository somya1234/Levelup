public class l008{
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // solve1();
        // solve2();
        solve3(); 
    }

    /***************************************************************************************/

    //gfg - min max value expression
    //https://www.geeksforgeeks.org/minimum-maximum-values-expression/

    public static void solve1(){
        String str = "1+2*3+4*5"; 
        int n = str.length(); 
        minMaxPair[][] dp = new minMaxPair[n][n]; 

        //memoization
        // minMaxPair ans = minMaxValue(str, 0, n-1, dp); 
        // System.out.println(ans.minVal+" "+ans.maxVal);
        // ans.toString();

        //tabulation 
        minMaxPair ans = minMaxValue_tab(str, 0, n-1, dp); 
        System.out.println(ans); // it uses toString()
        // System.out.println(ans.minVal+" "+ans.maxVal);
        // System.out.println(dp[1][1].minVal+" "+dp[1][1].maxVal);
    }

    // memoization 
    public static class minMaxPair{
        int minVal = (int)1e8; 
        int maxVal = 0; 
        minMaxPair(){

        }

        minMaxPair(int minVal, int maxVal){
            this.minVal = minVal; 
            this.maxVal = maxVal; 
        }

        public String toString(){
            return "(" + this.minVal + ", "+ this.maxVal + ")"; 
        }
    }

    public static minMaxPair minMaxValue(String str, int si, int ei, minMaxPair[][] dp){
        if(si == ei){
            int val = str.charAt(si) - '0'; 
            return dp[si][ei] = new minMaxPair(val, val); 
        }

        if(dp[si][ei] != null ) return dp[si][ei]; 

        minMaxPair ans = new minMaxPair(); 
        // cut+=2
        for(int cut = si+1 ; cut<ei; cut+=2){
            minMaxPair leftTree = minMaxValue(str, si, cut-1, dp); 
            minMaxPair rightTree = minMaxValue(str, cut +1 , ei, dp); 

            char ch = str.charAt(cut); 
            minMaxPair myAns = new minMaxPair();
            myAns.minVal = evaluate(ch, leftTree.minVal, rightTree.minVal); 
            myAns.maxVal = evaluate(ch, leftTree.maxVal, rightTree.maxVal);
            
           ans.minVal = Math.min(ans.minVal, myAns.minVal); 
           ans.maxVal = Math.max(ans.maxVal, myAns.maxVal); 
        }

        return dp[si][ei] = ans; 
    }

    public static int evaluate(char ch, int v1, int v2){
        if(ch == '+'){
            return v1+v2; 
        }
        else if(ch == '*') return v1 * v2; 
        else return 0;
    }

    //tabulation 
    public static minMaxPair minMaxValue_tab(String str, int si, int ei, minMaxPair[][] dp){
        int Ei = ei; 
        for(int gap = 0; gap<=Ei; gap++){
            for(si = 0, ei = gap; ei<=Ei; si++, ei++){
                if(si == ei){
                    int val = str.charAt(si) - '0'; 
                    dp[si][ei] = new minMaxPair(val, val); 
                    continue; 
                }
        
                minMaxPair ans = new minMaxPair(); 
                // cut+=2
                for(int cut = si+1 ; cut<ei; cut+=2){
                    minMaxPair leftTree = dp[si][cut-1];
                    minMaxPair rightTree = dp[cut+1][ei]; 
        
                    char ch = str.charAt(cut); 
                    minMaxPair myAns = new minMaxPair();
                    myAns.minVal = evaluate(ch, leftTree.minVal, rightTree.minVal); 
                    myAns.maxVal = evaluate(ch, leftTree.maxVal, rightTree.maxVal);
                    
                   ans.minVal = Math.min(ans.minVal, myAns.minVal); 
                   ans.maxVal = Math.max(ans.maxVal, myAns.maxVal); 
                }
        
                dp[si][ei] = ans; 
            }
        }
        return dp[0][Ei]; 
    }
    /***************************************************************************************/

    public static void solve2(){
        int[] arr = {40, 20, 30, 10, 30}; 
        int n = arr.length; 
        int[][] dp = new int[n][n]; 
        String[][] ans = new String[n][n]; 
        System.out.println(mcm_String(arr, 0, n-1, dp, ans));
        System.out.println(ans[0][n-1]);
    }

    public static int mcm_String(int[] arr, int si, int ei,  int[][] dp, String[][] ans){
        int Ei = ei; 
        for(int gap = 1; gap<=Ei; gap++ ){
            for(si = 0, ei = gap; ei<=Ei; si++, ei++){
                if(si+1 == ei) {
                    ans[si][ei] = ""+ (char)('A'+si);
                    dp[si][ei] = 0;  
                    continue; 
               }
       
               int res = (int)1e8; 
               for(int cut = si+1; cut<ei; cut++){
                   int leftTree = dp[si][cut]; 
                   int rightTree = dp[cut][ei];  
                   int myAns = leftTree + rightTree + arr[si] * arr[cut] * arr[ei]; 
                   if(myAns < res){
                       res = myAns; 
                       ans[si][ei] = "(" + ans[si][cut] +ans[cut][ei] +")"; 
                   }
               }
                dp[si][ei] = res; 
            }
        }
        return dp[0][Ei]; 
    }

    /******************************************************************************************/

    public static void solve3(){
        int[] numArr = {1,2,3,4,5}; 
        char[] chArr = {'+', '*', '+', '*'}; 
        int n = numArr.length; 
        minMaxPair[][] dp = new minMaxPair[n][n]; 
        minMaxPair ans = minMaxValue_02(numArr, chArr, 0 , n-1, dp); 
        System.out.println(ans);

        for(minMaxPair[] d : dp){
            for(minMaxPair e : d){
                System.out.print(e);
            }
            System.out.println();
        }
    }

    public static minMaxPair evalCombination(char operator, minMaxPair p1, minMaxPair p2){
        int a = evaluate(operator, p1.minVal, p2.minVal); 
        int b = evaluate(operator, p1.maxVal, p2.maxVal); 
        int c = evaluate(operator, p1.minVal, p2.maxVal);
        int d = evaluate ( operator, p1.maxVal, p2.minVal); 

        minMaxPair p = new minMaxPair(); 
        p.minVal = Math.min(Math.min(a, b), Math.min(c, d)); 
        p.maxVal = Math.max(Math.max(a, b), Math.max(c, d)); 

        return p; 
    }

    public static minMaxPair minMaxValue_02(int[] numArr, char[] chArr, int si, int ei, minMaxPair[][] dp){

        if(si == ei ){
            dp[si][ei] = new minMaxPair(numArr[si], numArr[ei]); 
            return dp[si][ei]; 
        }

        if(dp[si][ei]!=null) return dp[si][ei]; 

        minMaxPair ans = new minMaxPair(); 
        for(int cut = si; cut<ei; cut++){
            minMaxPair leftTree = minMaxValue_02(numArr, chArr, si, cut, dp); 
            minMaxPair rightTree = minMaxValue_02(numArr, chArr, cut+1, ei, dp); 

            char ch = chArr[cut]; 
            minMaxPair myans  = evalCombination(ch, leftTree, rightTree); 
            ans.minVal = Math.min(ans.minVal, myans.minVal); 
            ans.maxVal = Math.max(ans.maxVal, myans.maxVal); 
        }
        return dp[si][ei] = ans; 
    }

    /******************************************************************************************/
}