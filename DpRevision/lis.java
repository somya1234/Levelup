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
        for(int i=0;i<n;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
        }
        int count = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            count = Math.max(count,dp[i]);
        }
        System.out.println(count);
    }

}

//variation code.
public static void printLIS(){
    int[] dp = new int[n];
        ArrayList<String> list = new ArrayList<>();
        //total complexity - n(2n) -> 2n^2 -> O(n2)
        for(int i=0;i<n;i++){ // O(n)
            int idx = -1;
            int max = 0;
            for(int j=0;j<i;j++){ // O(n)
                if(arr[i]>arr[j]){
                    if(dp[j]>max){
                        max = dp[j];
                        idx=j;
                    }
                }
            }
            dp[i] = max+1;
            
            if(idx == -1){
                list.add(Integer.toString(arr[i])+",");
            } else {
                String str = list.get(idx); // O(n)
                str+=Integer.toString(arr[i])+",";
                list.add(str);
            }
        
        }
        // System.out.println(list);
        int count = Integer.MIN_VALUE;
        int idx = 0;
        for(int i=0;i<n;i++){
            if(dp[i]>count){
                count = dp[i];
                idx = i;
            }
        }
        System.out.println(count);
        System.out.println(list.get(idx));
}

public static void lisWays(){
    //variation code -> to print the all possible increasing subsequences
    // as well in arraylist for all the indices.

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scn.nextInt();
        }
        int[] dp = new int[n];
        ArrayList<String> list = new ArrayList<>();
        ArrayList<List<String>> paths = new ArrayList<List<String>>();
        //total complexity - n(2n) -> 2n^2 -> O(n2)
        for(int i=0;i<n;i++){ // O(n)
            int idx = -1;
            int max = 0;
            ArrayList<String> temp = new ArrayList<>();
            for(int j=0;j<i;j++){ // O(n)
                if(arr[i]>arr[j]){
                    if(dp[j]>max){
                        max = dp[j];
                        idx=j;
                        String str = list.get(idx); // O(n)
                        str+=Integer.toString(arr[i])+",";
                        temp.add(str);
                    }
                }
            }
            dp[i] = max+1;
            
            if(idx == -1){
                list.add(Integer.toString(arr[i])+",");
                temp.add(Integer.toString(arr[i])+",");
                paths.add(temp);
            } else {
                String str = list.get(idx); // O(n)
                str+=Integer.toString(arr[i])+",";
                list.add(str);
                paths.add(temp);
            }
        
        }
        // System.out.println(list);
        System.out.println(paths);
        int count = Integer.MIN_VALUE;
        int idx = 0;
        for(int i=0;i<n;i++){
            if(dp[i]>count){
                count = dp[i];
                idx = i;
            }
        }
        System.out.println(count);
        System.out.println(list.get(idx));
    }

}
}