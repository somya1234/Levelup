import java.util.*;
//Leetcode Question 62, 63 ->> Unique Paths I and II.

//question -> allowed only one move -> horizontal or vertical 
public class printMazePath {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        System.out.println(findW(0, 0, n,m, ""));
    }
    // Recursion 
    public static int findW(int i, int j, int n,int m, String ans){
        if(i==n-1 && j==n-1){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if(j+1<m){
            count+= findW(i, j+1, n,m, ans+"h");
        }
        if(i+1<n){
            count+=findW(i+1, j, n,m, ans+"v");
        }
        return count;
    }
    //tabulation 
    public int uniquePaths(int m, int n) {
        // 
           int[][] dp = new int[n][m];
           for(int i=n-1;i>=0;i--){
               for(int j=m-1;j>=0;j--){
                   if(i==n-1 && j==m-1){
                       dp[i][j] = 1;
                       continue;
                   } else if(i==n-1){
                       dp[i][j] = dp[i][j+1];
                   } else if(j==m-1){
                       dp[i][j] = dp[i+1][j];
                   } else {
                       dp[i][j] = dp[i+1][j]+dp[i][j+1];
                   }
               }
           }
           return dp[0][0];
       }
}