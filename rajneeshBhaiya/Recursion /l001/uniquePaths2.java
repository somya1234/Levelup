import java.util.Scanner;
//Problem 63 on Leetcode

public class uniquePaths2 {
    public static void main(String[] args) {
        int[][] arr = {{0,0,0},{0,1,0},{0,0,0}};
        // 1 is an obstacle 
        // int ans = findPaths(0,0,arr,""); //recursion 
        int ans = uniquePathsWithObstacles(arr); //tabulation
        System.out.println(ans);
    }

    //Recursion 
    public static int findPaths(int i, int j, int[][] arr,String path){
        int n = arr.length;
        int m = arr[0].length;
        if(i==n-1 && j == m-1){
            System.out.println(path);
            return 1;
        }

        int count = 0;
        if(j+1<m && arr[i][j+1]!=1){
            count+=findPaths(i, j+1, arr,path+"R");
        }
        if(i+1<n && arr[i+1][j]!=1){
            count+=findPaths(i+1, j, arr,path+"D");
        }
        return count;
    }

    //tabulation 
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n= obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(obstacleGrid[i][j]==1){
                    //including the case when destination has 1 (blocked).
                    dp[i][j] = 0;
                }  else if(i==n-1 && j==m-1){
                    dp[i][j] = 1;
                    continue;
                } else if(i==n-1){
                    dp[i][j] = dp[i][j+1];
                } else if(j==m-1){
                    dp[i][j] = dp[i+1][j];
                } else{
                    dp[i][j] = dp[i+1][j]+dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }

}