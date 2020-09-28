import java.io.*;
import java.util.*;

public class Main{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner scn = new Scanner(System.in); 
    int n = scn.nextInt(); 
    int[] dp = new int[n+1]; 
    dp[0] = 1; dp[1] = 1;  
    for(int i = 2;i<=n ; i++){
        int inside = i-1; 
        int outside = 0; 
        while(inside>=0){
            dp[i] += dp[inside]*dp[outside]; 
            inside--; 
            outside++; 
        }
    }
    System.out.println(dp[n]);
 }

}