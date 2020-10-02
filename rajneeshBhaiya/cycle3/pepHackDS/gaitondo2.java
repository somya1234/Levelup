import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in); 
        int t = scn.nextInt(); 
        int mod = (int)1e9+7; 
        while(t-->0){
            int money = scn.nextInt(); 
            int days = scn.nextInt(); 
            
            long ans = fastExp(money,days);
            System.out.println(ans);
        }
    }
    
    public static long fastExp(int money, int days){
         int mod = (int)1e9+7;
        long ans = 1; 
        while(days>1){
            if(days%2==1){
                ans *= money;
                days--;
            }
            money = (money*money)%mod;
            days = days/2;
        }
        return (ans*money)%mod;
    }
}