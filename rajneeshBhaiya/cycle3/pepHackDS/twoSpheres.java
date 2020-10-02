import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in); 
        int t = scn.nextInt(); 
        while(t-->0){
            int r1 = scn.nextInt(); 
            int r2 = scn.nextInt(); 
            int x1 = scn.nextInt();
            int y1 = scn.nextInt();
            int z1 = scn.nextInt();
            int ax1 = scn.nextInt();
            int ay1 = scn.nextInt();
            int az1 = scn.nextInt();
            int x2 = scn.nextInt();
            int y2 = scn.nextInt();
            int z2 = scn.nextInt();
            int ax2 = scn.nextInt();
            int ay2 = scn.nextInt();
            int az2 = scn.nextInt();
            boolean ans1 = find(x1, x2, ax1, ax2); 
            boolean ans2 = find(y1, y2, ay1, ay2); 
            boolean ans3 = find(z1, z2, az1, az2); 
            if(ans1 || ans2 || ans3) System.out.println("YES"); 
            else System.out.println("NO");
        }
    }
    
    public static boolean find(int x1, int x2, int ax1, int ax2){
        if(x1>0 && x2>0 ){
            if((ax1>0 && ax2>0) || (ax1>0 && ax2<0)) return true; 
        } else if(x1>0 && x2<0 ){
            if((ax1<0 && ax2>0) || (ax1<0 && ax2<0 ) && (ax1>0 && ax2>0)) return true; 
        } else if(x1<0 && x2>0){
            if((ax1>0 && ax2<0) || (ax1<0 && ax2<0 ) && (ax1>0 && ax2>0)) return true; 
        } else{
            if((ax1>0 && ax2<0) || (ax1<0 && ax2<0 ) && (ax1>0 && ax2>0)) return true; 
        }
        return false;
    }
}