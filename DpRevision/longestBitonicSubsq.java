import java.io.*;
import java.util.*;

public class Main {
    //complexity - n2+n2+n -> O(n2)

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = scn.nextInt();
        }
        int[] lis = new int[n];
        for(int i=0;i<n;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    max = Math.max(max,lis[j]);
                }
            }
            lis[i] = max+1;
        }
        int[] lds = new int[n];
        for(int i=n-1;i>=0;i--){
            int max = 0;
            for(int j = i+1; j<n;j++){
                if(arr[i]>arr[j]){
                    max = Math.max(max,lds[j]);
                }
            }
            lds[i] = max+1;
        }
        int bitonic = 0;
        for(int i=0;i<n;i++){
            int val = lis[i]+lds[i];
            bitonic = Math.max(bitonic,val);
        }
        System.out.println(bitonic-1);
    }

}
