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
            long total = money; 
            for(int i =0; i<days-1; i++){
                total=((money) *(total) )%mod; 
            }
            System.out.println(total);
        }
    }
}


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
            boolean isOdd = false; 
            if(days%2!=0){
                isOdd = true; 
                days--; 
            }
            long ans = power(money, days);
            if(isOdd) ans = ans*money; 
            System.out.println(ans%mod);
        }
    }
    
    public static long power(int money, int days){
        int mod = (int)1e9+7;
        if(days == 0) return 1; 
        long ans = 0; 
        if(days%2!=0){
            ans = power(money, days/2);
            ans = ans*ans*money;
        } else{
            ans = power(money,days/2);
            ans = ans*ans;
        }
        return ans%mod; 
    }
}