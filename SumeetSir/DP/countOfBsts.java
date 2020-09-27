import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner scn = new Scanner(System.in); 
    int n = scn.nextInt(); 
    int[] dp = new int[n+1]; 
    dp[0] = dp[1] = 1; 
    for(int idx = 2; idx<=n ; idx++){
        for(int j = 0; j<idx; j++){
            // left subtree * right subtree se jitne ways aayenge 
            // after making this node as root node.
            dp[idx] += dp[j]*dp[idx-j-1]; 
        }
    }
    
    System.out.println(dp[n]);
 }

}