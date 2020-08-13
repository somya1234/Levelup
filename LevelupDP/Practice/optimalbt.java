import java.io.*;
import java.util.*;

public class Main {
  
  //recursion 
  private static int optimalbst(int[] freq, int si, int ei) {
    //write your code here
    if(si>ei){
        return 0;
    }
    // if(si == ei){
    //     return freq[si];
    // }
    int allCost = totalCost(freq,si,ei);
    int min = Integer.MAX_VALUE;
    for(int cp = si;cp<=ei;cp++){
        int leftCost = optimalbst(freq,si,cp-1);
        int rightCost = optimalbst(freq,cp+1,ei);
        // int cost = ((2 * leftCost)+ (2*rightCost) + freq[cp]);
        int cost = leftCost+rightCost + allCost;
        min = Math.min(min,cost);
    }
    return min;
    
	}
	
	//memoization  
  private static int optimalbst_mem(int[] freq, int si, int ei, int[][] dp) {
    //write your code here
    if(si>ei){
        return 0;
    }
    if(dp[si][ei]!=0){
        return dp[si][ei];
    }
    int allCost = totalCost(freq,si,ei);
    int min = Integer.MAX_VALUE;
    for(int cp = si;cp<=ei;cp++){
        int leftCost = optimalbst_mem(freq,si,cp-1,dp);
        int rightCost = optimalbst_mem(freq,cp+1,ei,dp);
        int cost = leftCost+rightCost + allCost;
        min = Math.min(min,cost);
    }
    return min;
    
	}
	
	//tabulation 
  private static int optimalbst_tab(int[] freq) {
    //write your code here
    int n = freq.length;
    int[][] dp = new int[n][n+1];
    for(int idx = 0;idx<n;idx++){
        for(int si=0,ei = idx;ei<n;si++,ei++){
            int allCost = totalCost(freq,si,ei);
            int min = Integer.MAX_VALUE;
            for(int cp = si;cp<=ei;cp++){
                int leftCost = dp[si][cp-1];
                int rightCost = dp[cp+1][ei];
                int cost = leftCost + rightCost + allCost;
                min = Math.min(min,cost);
            }
            dp[si][ei] = min;
        }
    }
    return dp[0][n-1];
}
	
	
	public static int totalCost(int[] freq, int si, int ei){
	    int sum = 0;
	    for(int idx = si;idx<=ei;idx++){
	        sum+= freq[idx];
	    }
	    return sum;
	}

    public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
	int[] keys = new int[n];
	int[] frequency = new int[n];
    for(int i= 0 ;i < n ; i++) {
		keys[i] = scn.nextInt();
	}
	for(int i = 0 ; i < n; i++){
      frequency[i] = scn.nextInt();
    }
// 	System.out.println(optimalbst(frequency,0,n-1));
	System.out.println(optimalbst_tab(frequency));
	}

}