import java.io.*;
import java.util.*;

public class Main {
    
    public static class Pair implements Comparable<Pair>{
        int key;
        int freq;
        public int compareTo(Pair o){
            return this.freq - o.freq;
        }
    }
  
  //Recursion 
  private static void optimalbst(int[] keys, int[] frequency, int n) {
    //write your code here
    
        
	}
	
	public static int oBst(int[] frequency, int si, int ei){
	    if(ei == si){
	        return frequency[ei];
	    }
	    if(ei<si){
	        return 0;
	    }
	    int min = Integer.MAX_VALUE;
	    for(int cp = si;cp<=ei;cp++){
            int leftCost = oBst(frequency, si, cp-1);
            int rightCost = oBst(frequency, cp+1, ei);
            int cc = costOfAllNodes(frequency, si, ei);
            int cost = leftCost + rightCost + cc;
            min = Math.min(min,cost);
        }
        return min;
	}
	
	public static int oBst_mem(int[] frequency, int si, int ei, int[][] dp){
	    if(ei == si){
	        return dp[si][ei] = frequency[ei];
	    }
	    if(ei<si){
	        return 0;
	    }
	    if(dp[si][ei]!=0){
	        return dp[si][ei];
	    }
	    int min = Integer.MAX_VALUE;
	    for(int cp = si;cp<=ei;cp++){
            int leftCost = oBst_mem(frequency, si, cp-1,dp);
            int rightCost = oBst_mem(frequency, cp+1, ei,dp);
            int cc = costOfAllNodes(frequency, si, ei);
            int cost = leftCost + rightCost + cc;
            min = Math.min(min,cost);
            dp[si][ei] = min;
        }
        return min;
	}
	
	public static int oBst_tab(int[] freq){
	    int n = freq.length;
	    int[][] dp = new int[n][n];
	    for(int idx=0;idx<n;idx++){
	        for(int i=0,j=idx;j<n;i++,j++){
	            int si = i, ei = j;
	            if(si == ei){
	                dp[si][ei] = freq[si];
	                continue;
	            }
	            int sum = 0;
	            int min = Integer.MAX_VALUE;
	           for(int cp=si;cp<=ei;cp++){
	               sum+=freq[cp];
	               int lcost = cp-1 >= si ? dp[si][cp-1] : 0;
	               int rcost = cp+1 <= ei ? dp[cp+1][ei] : 0;
	               if( min > (lcost+rcost) ){
	                   min = lcost + rcost;
	               }
	           }
	               min+=sum;
	               dp[si][ei] = min;
	        }
	    }
	    return dp[0][n-1];
	}
	
	public static int costOfAllNodes(int[] freq,int si,int ei){
	    int cost = 0;
	    for(int i=si;i<=ei;i++){
	        cost += freq[i];
	    }
	    return cost;
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
    Pair[] pairs = new Pair[n];
    for(int i=0;i<n;i++){
        Pair p = new Pair();
        p.key = keys[i];
        p.freq = frequency[i];
        pairs[i] = p;
    }
    // Arrays.sort(pairs);
// 	int ans = oBst(frequency,0,n-1);
//     int ans = oBst_mem(frequency,0,n-1,new int[n][n]);
// // 	System.out.println(ans);
// 	optimalbst(keys,frequency,n);
	System.out.println(oBst_tab(frequency));
	}

}
