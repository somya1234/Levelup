import java.io.*;
import java.util.*;

public class Main {
    public static class Pair {
        int tc; //true count
        int fc; // false count
        Pair(int tc, int fc) {
            this.tc = tc;
            this.fc = fc;
        }
        Pair() {

        }
    }
    //recursion code
    public static Pair solution(String str1, String str2, int si, int ei) {
        //write your code here
        if (si == ei) {
            if (str1.charAt(si) == 'T') {
                return new Pair(1, 0);
            } else {
                return new Pair(0, 1);
            }
        }

        Pair res = new Pair();
        for (int cp = si; cp < ei; cp++) {
            Pair left = solution(str1, str2, si, cp);
            Pair right = solution(str1, str2, cp + 1, ei);
            char op = str2.charAt(cp);
            Pair ans = operate(left, right, op);
            res.tc += ans.tc;
            res.fc += ans.fc;
        }
        return res;
    }

    //memoization  -> check this
    public static Pair solution_mem(String str1, String str2, int si, int ei, Pair[][] dp, boolean[][] visited) {
        //write your code here
        if (si == ei) {
            if (str1.charAt(si) == 'T') {
                return dp[si][ei] = new Pair(1, 0);
            } else {
                return dp[si][ei] = new Pair(0, 1);
            }
        }
        if (visited[si][ei]==true) {
            return dp[si][ei];
        }


        Pair res = new Pair();
        for (int cp = si; cp < ei; cp++) {
            Pair left = solution_mem(str1, str2, si, cp, dp,visited);
            Pair right = solution_mem(str1, str2, cp + 1, ei, dp,visited);
            char op = str2.charAt(cp);
            Pair ans = operate(left, right, op);
            res.tc += ans.tc;
            res.fc += ans.fc;
        }
        visited[si][ei] = true;
        return dp[si][ei] = res;
    }

    //tabulation 
    public static int solution_tab(String str1, String str2) {
        int n = str1.length();
        Pair[][] dp = new Pair[n][n];
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (gap == 0) {
                    if (str1.charAt(si) == 'T') {
                        dp[si][ei] = new Pair(1, 0);
                    } else {
                        dp[si][ei] = new Pair(0, 1);
                    }
                } else if (gap == 1) {
                    dp[si][ei] = new Pair();
                    char op = str2.charAt(si);
                    if (op == '&') {
                        Pair left = dp[si][ei-1];
                        Pair right = dp[si+1][ei];
                        dp[si][ei].tc = (left.tc*right.tc);
                        dp[si][ei].fc = (left.tc*right.fc)+(left.fc*right.tc)+(left.fc*right.fc);
                    } else if (op == '|') {
                        Pair left = dp[si][ei-1];
                        Pair right = dp[si+1][ei];
                        dp[si][ei].tc = (left.tc*right.fc)+(left.fc*right.tc)+(left.tc*right.tc) ;
                        dp[si][ei].fc = left.fc*right.fc ;
                    } else if (op == '^') {
                        Pair left = dp[si][ei-1];
                        Pair right = dp[si+1][ei];
                        dp[si][ei].tc = left.tc*right.fc + (left.fc*right.tc) ;
                        dp[si][ei].fc = (left.tc*right.tc) + right.fc*left.fc ;
                    }
                } else {
                    Pair res = new Pair();
                    for (int cp = si; cp < ei; cp++){
                        Pair left = dp[si][cp];
                        Pair right = dp[cp+1][ei];
                        char op = str2.charAt(cp);
                        Pair ans = operate(left,right,op);
                        res.tc += ans.tc;
                        res.fc += ans.fc;
                    }
                        dp[si][ei] = res;

                }
            }
        }
        return dp[0][n-1].tc;
    }

    public static Pair operate(Pair left, Pair right, char op) {
        Pair res = new Pair();
        if (op == '|') {
            res.tc = (left.tc * right.fc) + (left.fc * right.tc) + (left.tc * right.tc);
            res.fc = (left.fc * right.fc);
        } else if (op == '^') {
            res.tc = (left.tc * right.fc) + (left.fc * right.tc);
            res.fc = (left.tc * right.tc) + (left.fc * right.fc);
        } else if (op == '&') {
            res.tc = (left.tc * right.tc);
            res.fc = (left.tc * right.fc) + (left.fc * right.tc) + (left.fc * right.fc);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s1 = scn.next();
        int n = s1.length();
        String s2 = scn.next();
        // System.out.println(solution(s1, s2, 0, n - 1).tc);
        System.out.println(solution_mem(s1, s2, 0, n - 1, new Pair[n][n],new boolean[n][n]).tc);
        // System.out.println(solution_tab(s1,s2));
    }

}