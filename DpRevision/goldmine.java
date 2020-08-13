import java.io.*;
import java.util.*;

public class Main {
    //somya code
    //tabulation -> O(nm)
    //memoization -> O(nm)
    // recursion -> (n)*(3^m) -> acc to tushar bhaiya 
    //i thought n*(2^(nm))

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j] = scn.nextInt();
            }
        }
        int maxGold = 0;
        int[][] dp = new int[n][m];
        for(int i=0;i<n;i++){
        // maxGold = Math.max(maxGold,gold_rec(arr,i,0));
        maxGold = Math.max(maxGold,gold_mem(arr,i,0,dp));
        }
        // System.out.println(maxGold); //-> mem
        System.out.println(gold_tab(arr)); //-> tab
    }
    public static int gold_rec(int[][] arr, int i, int j){
        int n = arr.length;
        int m = arr[0].length;
        
        if(j == m-1){
            return arr[i][j];
        }
        int a1, a2, a3;
        a1 = a2 = a3 = Integer.MIN_VALUE;
        
        if(i-1>0 && j+1<m){
            a1 = gold_rec(arr,i-1,j+1);
        }
        if(j+1<m){
            a2 = gold_rec(arr,i,j+1);
        }
        if(i+1<n && j+1<m){
            a3 = gold_rec(arr,i+1,j+1);
        }
        return Math.max(a1,Math.max(a2,a3))+arr[i][j];
        
    }
    public static int gold_mem(int[][] arr, int i, int j,int[][] dp){
        int n = arr.length;
        int m = arr[0].length;
        
        if(j == m-1){
            return dp[i][j] = arr[i][j];
        }
        if(dp[i][j]!=0){
            //it won't let again come to the above last point or base condn
            //as its previous values will return answer 
            //so this loop only called once for each row (total -> n times)
            return dp[i][j];
        }
        int a1, a2, a3;
        a1 = a2 = a3 = Integer.MIN_VALUE;
        
        if(i-1>0 && j+1<m){
            a1 = gold_mem(arr,i-1,j+1,dp);
        }
        if(j+1<m){
            a2 = gold_mem(arr,i,j+1,dp);
        }
        if(i+1<n && j+1<m){
            a3 = gold_mem(arr,i+1,j+1,dp);
        }
        return dp[i][j] = Math.max(a1,Math.max(a2,a3))+arr[i][j];
        
    }
    
    public static int gold_tab(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];
        for(int j=m-1;j>=0;j--){
            for(int i=n-1;i>=0;i--){
                if(j == m-1){
                    dp[i][j] = arr[i][j];
                    continue;
                }
                
                int a1, a2, a3;
                a1 = a2 = a3 = Integer.MIN_VALUE;
                
                if(i-1>=0 && j+1<m){
                    a1 = dp[i-1][j+1];
                }
                if(j+1<m){
                    a2 = dp[i][j+1];
                }
                if(i+1<n && j+1<m){
                    a3 = dp[i+1][j+1];
                }
                dp[i][j] = Math.max(a1,Math.max(a2,a3))+arr[i][j];
            }
        }
        int maxGold = 0;
        for(int i=0;i<n;i++){
            maxGold = Math.max(maxGold,dp[i][0]);
        }
        return maxGold;
    }
}
