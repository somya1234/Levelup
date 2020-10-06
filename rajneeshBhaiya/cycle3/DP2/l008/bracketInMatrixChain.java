/*package whatever //do not write package name here */

//gfg 
// https://www.geeksforgeeks.org/printing-brackets-matrix-chain-multiplication-problem/
// https://practice.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication/0

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
		//code
		Scanner scn = new Scanner(System.in); 
		int t = scn.nextInt(); 
		while(t-->0){
		    int n = scn.nextInt(); 
		    int[] arr = new int[n]; 
		    for(int i =0; i<n; i++){
		        arr[i] = scn.nextInt(); 
		    }
		    int[][] dp = new int[n][n]; 
		    String[][] strg = new String[n][n]; 
		    mcm(arr, 0, n-1, dp, strg);
		    System.out.println(strg[0][n-1]);
		}
	}
	
	public static void mcm(int[] arr, int si, int ei, int[][] dp, String[][] strg){
	    int Ei = ei; 
	    for(int gap = 1; gap<=Ei; gap++){
	        for(si = 0, ei = gap; ei<=Ei; si++, ei++){
	            if(si+1 == ei){
        	        dp[si][ei] = 0; 
        	        strg[si][ei] = ""+ (char)('A'+si); 
        	        continue; 
        	    }
        	    
        	    int ans = (int)1e9; 
        	    for(int cut = si+1; cut<ei; cut++){
        	        int leftTree = dp[si][cut]; 
        	        int rightTree = dp[cut][ei]; 
        	        
        	        int myans = leftTree + (arr[si] * arr[cut] * arr[ei])  + rightTree; 
        	        if(myans<ans){
        	            ans = myans; 
        	            strg[si][ei] = "("+strg[si][cut]+strg[cut][ei]+")"; 
        	        }
        	    }
        	    dp[si][ei] = ans;
        	  }
         }
	    
	     return; 
	}
}