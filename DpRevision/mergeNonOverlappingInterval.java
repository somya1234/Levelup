import java.io.*;
import java.util.*;

public class Main {
    
    public static class Pair implements Comparable<Pair>{
        int nb; 
        int sb;
        public int compareTo(Pair o){
            //to sort in ascending order according to north point
            if(this.nb==o.nb){
                return this.sb-o.sb;
            } else {
                return this.nb- o.nb;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        Pair[] bridges = new Pair[n];
        for(int i=0;i<n;i++){
            int nb = scn.nextInt();
            int sb = scn.nextInt();
            Pair p = new Pair();
            p.nb = nb;
            p.sb = sb;
            bridges[i] = p; 
        }
        Arrays.sort(bridges);
        //LIS
        int[] dp = new int[n];
        for(int i=0;i<n;i++){
            Pair curr = bridges[i];
            int max = 0;
            for(int j=i-1;j>=0;j--){
                Pair old = bridges[j];
                // don't know about this, should we take equal to or not ? 
                if(curr.sb>=old.sb){
                    max = Math.max(max,dp[j]);
                }
            }
            max += 1;
            dp[i] = max;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max = Math.max(max,dp[i]);
        }
        System.out.println(max);
    }
}
