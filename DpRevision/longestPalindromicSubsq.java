import java.io.*;
import java.util.*;

public class Main {
    //Time complexity -> O(n2)
    //dp[i][j] = cell meaning is it contains the length of the longest palindromic
    //subsequence in that sequence possible, by taking all considerations
    //that is all possibilities of all types of subsequences that can be 
    //formed.

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        int n = str.length();
        int[][] dp = new int[n][n];
        //diagonal traversal 
        for(int idx=0;idx<n;idx++){
            for(int i=0,j=idx;j<n;i++,j++){
                if(i==j){
                    dp[i][j] = 1;
                } else if(idx == 1){
                    if(str.charAt(i)==str.charAt(j)){
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    if(str.charAt(i)==str.charAt(j)){
                        dp[i][j] = 2+dp[i+1][j-1];
                    } else {
                        dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                    }
                }
            }   
        }
        System.out.println(dp[0][n-1]);
    }

}