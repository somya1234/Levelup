import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
       
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int idx = 2; idx <= n; idx++) {
             int i = 0, j = idx- 1;
            while (j >= 0) {
                dp[idx]+= dp[i]*dp[j];
                i++;
                j--;
            }
        }

        System.out.println(dp[n]);
    }

}