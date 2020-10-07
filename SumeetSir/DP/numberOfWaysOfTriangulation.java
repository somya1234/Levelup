import java.io.*;
import java.util.*;

public class Main {

	public static int solution(int n){
		// write your code here
		int[] dp = new int[n+1]; 
		dp[0]  =1; 
		dp[1] = 1; 
		for(int i =2; i<=n; i++){
		    int left = i-1; 
		    int right =0; 
		    while(left>=0){
		        dp[i]+=dp[left]*dp[right];
		        left--; 
		        right++;
		    }
		}
		return dp[n];
	 }
	 
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		System.out.println(solution(n));
	}

}