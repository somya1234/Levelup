import java.io.*;
import java.util.*;

public class Main {
    //sort using width or height (anything)
    
    public static class Pair implements Comparable<Pair>{
        int w;
        int h;
        public int compareTo(Pair o){
            return this.h - o.h;
        }
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        Pair boxes[] = new Pair[n];
        for(int i=0;i<n;i++){
            int w = scn.nextInt();
            int h = scn.nextInt();
            boxes[i] = new Pair();
            boxes[i].w = w;
            boxes[i].h = h;
        }
        Arrays.sort(boxes);
        int[] dp = new int[n];
        for(int i=0;i<n;i++){
            int max = 0;
            Pair curr = boxes[i];
            for(int j=0;j<i;j++){
                Pair old = boxes[j];
                //their width can never be equal to put in one another
                if(curr.w>old.w){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
        }
        int count = 0;
        for(int i=0;i<n;i++){
            count = Math.max(count,dp[i]);
        }
        System.out.println(count);
    }

}
