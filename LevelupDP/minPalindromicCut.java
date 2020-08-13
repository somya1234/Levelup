import java.io.*;
import java.util.*;

public class Main {

	public static int minPalindromicCut_tab(String s) {
		//write your code here
		int n = s.length();
		int[][] dp = new int[n][n];
		for(int idx = 0;idx<n;idx++){
		    for(int i=0,j=idx;j<n;i++,j++){
		        if(i==j){
		            dp[i][j] = 0;
		        } else if(j==i+1){
		            if(s.charAt(i)==s.charAt(j)){
		                dp[i][j] = 0;
		            } else {
		                dp[i][j] = 1;
		            }
		        } else {
		            if(s.charAt(i)==s.charAt(j) && dp[i+1][j-1]==0){
		                    dp[i][j] = 0;
		            } else {
		                int min = Integer.MAX_VALUE;
		                for(int cp = i+1;cp<=j;cp++){
		                    int left = dp[i][cp-1];
		                    int right = dp[cp][j];
		                    int tCut = left+right+1;
		                    min = Math.min(min,tCut);
		                }
		                dp[i][j] = min;
		            }
		        }
		    }
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.nextLine();
		System.out.println(minPalindromicCut_tab(str));
	}
}

// ************************************************************************************** //
//somya code 1 

public class Main {
    //somya code 1

	public static int minPalindromicCut(String s, int si, int ei) {
		//write your code here
        if(si == ei){
            return 0;
        }
        if(palindromic(s,si,ei)){
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for(int cp = si; cp<ei; cp++){
            int leftCut = minPalindromicCut(s,si,cp);
            int rightCut = minPalindromicCut(s,cp+1,ei);
            int value = leftCut + rightCut + 1;
            min = Math.min(min,value);
        }
		return min;
	}
	
	//memoization 
	public static int minPalindromicCut_mem(String s, int si, int ei, int[][] dp) {
		//write your code here
        if(si == ei){
            return dp[si][ei] = 0;
        }
        
        if(dp[si][ei]!=0){
            return dp[si][ei];
        }
        
        if(palindromic(s,si,ei)){
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for(int cp = si; cp<ei; cp++){
            int leftCut = minPalindromicCut_mem(s,si,cp,dp);
            int rightCut = minPalindromicCut_mem(s,cp+1,ei,dp);
            int value = leftCut + rightCut + 1;
            min = Math.min(min,value);
        }
		return dp[si][ei] = min;
	}
	
	public static boolean palindromic(String s, int si, int ei){
	    while(si<ei){
	        char ch1  = s.charAt(si);
	        char ch2 = s.charAt(ei);
	        if(ch1!=ch2){
	            return false;
	        }
	        si++;
	        ei--;
	    }
	    return true;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.nextLine();
		int n = str.length();
// 		System.out.println(minPalindromicCut(str,0,str.length()-1));
		System.out.println(minPalindromicCut_mem(str,0,str.length()-1,new int[n][n]));
	}
}
