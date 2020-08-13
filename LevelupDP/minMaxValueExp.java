import java.io.*;
import java.util.*;

public class Main {

    public static class Pair {
        int min;
        int max;

    }
    //recursion 
    public static int[] solution(String str1, String str2, int si, int ei) {
        //write your code here
        if (si == ei) {
            int[] base = new int[2];
            //imp 
            base[0] = base[1] = str1.charAt(si) - '0';
            return base;
        }


        int[] ans = new int[2];
        ans[0] = Integer.MAX_VALUE;
        ans[1] = Integer.MIN_VALUE;
        for (int cp = si; cp < ei; cp++) {
            int[] left = solution(str1, str2, si, cp);
            int[] right = solution(str1, str2, cp + 1, ei);
            char op = str2.charAt(cp);
            int[] res = operate(left, right, op);
            ans[0] = Math.min(res[0], ans[0]);
            ans[1] = Math.max(res[1], ans[1]);
        }
        return ans;
    }

    //memoization 
    public static Pair sol_mem(String str1, String str2, int si, int ei, Pair[][] dp) {

        if (si == ei) {
            Pair base = new Pair();
            base.min = base.max = str1.charAt(si) - '0';
            dp[si][ei] = base;
            return base;
        }
        
        Pair ret = dp[si][ei];
        if (ret.min != 0 && ret.max != 0) {
            return ret;
        }

        Pair ans = new Pair();
        ans.min = Integer.MAX_VALUE;
        ans.max = Integer.MIN_VALUE;
        for (int cp = si; cp < ei; cp++) {
            Pair left = sol_mem(str1, str2, si, cp, dp);
            Pair right = sol_mem(str1, str2, cp + 1, ei, dp);
            char op = str2.charAt(cp);
            Pair res = operate(left, right, op);
            ans.min = Math.min(res.min, ans.min);
            ans.max = Math.max(res.max, ans.max);
        }
        dp[si][ei] = ans;
        return dp[si][ei];
    }
    
    //tabulation 
    public static Pair sol_tab(String str1, String str2){
        int n = str1.length();
        Pair[][] dp = new Pair[n][n];
        for(int idx=0;idx<n;idx++){
            for(int i=0,j=idx;j<n;i++,j++){
                if(i==j){
                    Pair p = new Pair();
                    p.min = Integer.parseInt(str1.charAt(i)+"");
                    p.max = p.min;
                    dp[i][j] = p;
                    continue;
                } else {
                    Pair ans = new Pair(); 
                    ans.min = Integer.MAX_VALUE;
                    ans.max = Integer.MIN_VALUE;
                    for(int cp = i;cp<j;cp++){
                        Pair left = dp[i][cp];
                        Pair right = dp[cp+1][j];
                        char op = str2.charAt(cp);
                        Pair res = operate(left,right,op);
                        ans.min = Math.min(ans.min,res.min);
                        ans.max = Math.max(ans.max,res.max);
                    }
                    dp[i][j] = ans;
                }
            }
        }
        return dp[0][n-1];
    }

    //recursion helper
    public static int[] operate(int[] left, int[] right, char op) {
        int[] res = new int[2];
        if (op == '+') {
            res[0] = left[0] + right[0];
            res[1] = left[1] + right[1];
        } else if (op == '*') {
            res[0] = left[0] * right[0];
            res[1] = left[1] * right[1];
        }
        return res;
    }

    //mem and tabulation helper
    public static Pair operate(Pair left, Pair right, char op) {
        Pair res = new Pair();
        if (op == '+') {
            res.min = left.min + right.min;
            res.max = left.max + right.max;
        } else if (op == '*') {
            res.min = left.min * right.min;
            res.max = left.max * right.max;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '*') {
                str2 += ch;
            } else {
                str1 += ch;
            }
        }
        int n = str1.length();
        //recursion 
        // int[] arr = solution(str1, str2, 0, n - 1);
        // System.out.println("Minimum Value -> " + arr[0]);
        // System.out.println("Maximum Value -> " + arr[1]);
        
        //memoization 
        Pair[][] dp = new Pair[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Pair p = new Pair();
                dp[i][j] = p;
            }
        }
        // Pair arr = sol_mem(str1, str2, 0, n - 1, dp);
        
        //tabulation 
        Pair arr = sol_tab(str1,str2);
        System.out.println("Minimum Value -> " + arr.min);
        System.out.println("Maximum Value -> " + arr.max);
    }

}