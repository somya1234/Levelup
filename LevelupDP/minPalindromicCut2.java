import java.io.*;
import java.util.*;

public class Main {
    //somya code 1

    //recursion 
    public static int minPalindromicCut(String s, int si, int ei) {
        //write your code here
        if (si >= ei) {
            return 0;
        }
        if (s.charAt(si)==s.charAt(ei)) {
            if(minPalindromicCut(s,si+1,ei-1)==0){
                return 0;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int cp = si; cp < ei; cp++) {
            int leftCut = minPalindromicCut(s, si, cp);
            int rightCut = minPalindromicCut(s, cp + 1, ei);
            int value = leftCut + rightCut + 1;
            min = Math.min(min, value);
        }
        return min;
    }

    //memoization 
    public static int minPalindromicCut_mem(String s, int si, int ei, int[][] dp) {
        //write your code here
        if (si >= ei) {
            return dp[si][ei] = 0;
        }

        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }

        if (s.charAt(si) == s.charAt(ei)) {
            if (minPalindromicCut_mem(s, si + 1, ei - 1, dp) == 0) {
                return dp[si][ei] = 0;
            }
        }

        //this controls si>=ei condition handles here
        // if(palindromic(s,si,ei)){
        //     return 0;
        // }

        int min = Integer.MAX_VALUE;
        for (int cp = si; cp < ei; cp++) {
            int leftCut = minPalindromicCut_mem(s, si, cp, dp);
            int rightCut = minPalindromicCut_mem(s, cp + 1, ei, dp);
            int value = leftCut + rightCut + 1;
            min = Math.min(min, value);
        }
        return dp[si][ei] = min;
    }

    //tabulation 
    public static int minPalindromicCut_tab(String s) {
        //write your code here
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int idx = 1; idx < n; idx++) {
            for (int si = 0, ei = idx; ei < n; si++, ei++) {
                if (s.charAt(si) == s.charAt(ei) && dp[si + 1][ei - 1] == 0) {
                    dp[si][ei] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int cp = si; cp < ei; cp++) {
                    int leftCut = dp[si][cp];
                    int rightCut = dp[cp + 1][ei];
                    int value = leftCut + rightCut + 1;
                    min = Math.min(min, value);
                }
                dp[si][ei] = min;
            }
        }
        return dp[0][n - 1];
    }
    // if(palindromic(s,si,ei)){
    //     return 0;
    // }


    public static boolean palindromic(String s, int si, int ei) {
        //not use this fn 
        while (si < ei) {
            char ch1 = s.charAt(si);
            char ch2 = s.charAt(ei);
            if (ch1 != ch2) {
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
        		System.out.println(minPalindromicCut(str,0,str.length()-1));
        // System.out.println(minPalindromicCut_mem(str, 0, str.length() - 1, new int[n][n]));
        // System.out.println(minPalindromicCut_tab(str));
    }
}
