import java.io.*;
import java.util.*;

public class Main {

  public static void sort01(int[] arr){
    //write your code here
    //somya code 1 -> with for loop instead of while loop.
    int n = arr.length;
    int j = 0;
    for(int i=0;i<n;i++){
        if(arr[i]==0){
            swap(arr,i,j); 
            j++;
            // i++;
        } 
    }
    
  }

  // used for swapping ith and jth elements of array
  public static void swap(int[] arr, int i, int j) {
    System.out.println("Swapping index " + i + " and index " + j);
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void print(int[] arr){
    for(int i = 0 ; i < arr.length; i++){
      System.out.println(arr[i]);
    }
  }
  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for(int i = 0 ;i < n; i++){
      arr[i] = scn.nextInt();
    }
    sort01(arr);
    print(arr);
  }

}