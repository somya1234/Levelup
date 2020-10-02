/*package whatever //do not write package name here */

// https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck/0
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) {
        // code
        Scanner scn = new Scanner(System.in); 
		int t = scn.nextInt(); 
		scn.nextLine(); 
		while(t-->0){
		    String str = scn.nextLine(); 
		    int n = str.length(); 
		    int x = 0, y = 0, z = 0; 
		    for(int i =0; i<n; i++){
		        char ch = str.charAt(i); 
		        if(ch == 'a'){
		            x = (2*x)+1; 
		        } else if(ch == 'b'){
		             y = (2*y+x);
		        } else if(ch == 'c'){
		            z = (2*z+y);
		        }
		    }
		    System.out.println(z);
		}
    }

}

    // better method
    // (no need to make dp , store the count in variable.)

public static void countABC_usingStorage(){
    str = "$"+str;
    int n = str.length(); 
    int[][] dp = new int[3][n];
    for(int i =1; i<n; i++){
        char ch = str.charAt(i); 
        dp[0][i] = dp[0][i-1];
        dp[1][i] = dp[1][i-1]; 
        dp[2][i] = dp[2][i-1];
        if(ch == 'a'){
            dp[0][i] = (2*dp[0][i-1])+1;
        } else if(ch == 'b'){
                dp[1][i] = (2*dp[1][i-1])+dp[0][i-1];
        } else if(ch == 'c'){
            dp[2][i] = (2*dp[2][i-1])+dp[1][i-1];
        }
    }
    System.out.println(dp[2][n-1]);
}