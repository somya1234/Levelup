import java.io.*;
import java.util.*;

public class Main {

  public static void radixSort(int[] arr) {
    //write your code here
    int max = Integer.MIN_VALUE;
    for(int val:arr){
        if(val>max){
            max = val;
        }
    }
    
    for(int exp = 1, itr =1; max/exp>0; exp*=10){
        countSort(arr,exp,itr++);
    }

  }

  public static void countSort(int[] arr, int exp, int itr) {
    //write your code here
    //farr
    int[] farr = new int[10];
    for(int val:arr){
        int idx = (val/exp)%10;
        farr[idx]+=1;
    }
    //prefix sum array 
    int[] psf = new int[10];
    psf[0] = farr[0];
    for(int i=1;i<psf.length;i++){
        psf[i] = farr[i]+psf[i-1];
    }
    //ans
    int[] ans = new int[arr.length];
    for(int i=arr.length-1;i>=0;i--){
        int val = arr[i];
        int digit = (val/exp)%10;
        // psf[digit]--;
        psf[digit]=psf[digit]-1;
        int newIdx = psf[digit];
        ans[newIdx] = val;
    }
    
    //copy array
    for(int i=0;i<ans.length;i++){
        arr[i] = ans[i];
    }
    
    System.out.print("After iteration" + " no. "+itr+ " -> ");
    print(arr);
  }

  public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    radixSort(arr);
    print(arr);
  }

}