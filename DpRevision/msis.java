import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scn.nextInt();
        }
        int[] dp = new int[n];
        //cell meaning -> increasing subsq having max sum ending with that 
        //idx of array
        for(int i=0;i<n;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(arr[i]>=arr[j]){
                    max = Math.max(dp[j],max);
                }
            }
            dp[i] = max + arr[i];
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max = Math.max(max,dp[i]);
        }
        System.out.println(max);
    }

}