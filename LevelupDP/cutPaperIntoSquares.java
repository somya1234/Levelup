import java.io.*;
import java.util.*;

public class Main {

    //tabulation 
	public static int solution(int n, int m){
		//write your code here
        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            dp[i][1] = i;
        }
        for(int j=1;j<=m;j++){
            dp[1][j] = j;
        }
        for(int i=2;i<=n;i++){
            for(int j=2;j<=m;j++){
                if(i==j){
                    dp[i][j] = 1;
                } else {
                    int val = Integer.MAX_VALUE;
                    //row - wise cut
                    for(int cp = 1;cp<=i/2;cp++){
                        int left = dp[cp][j];
                        int right = dp[i-cp][j];
                        val = Math.min(val,(left+right));
                    }
                    //col- wise cut
                    for(int cp = 1;cp<=j/2;cp++){
                        int left = dp[i][cp];
                        int right = dp[i][j-cp];
                        val = Math.min(val,(left+right));
                    }
                    dp[i][j] = val;
                }
            }
        }
        
		return dp[n][m];
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
		System.out.println(solution(n,m));
	}

}