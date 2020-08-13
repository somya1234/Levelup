import java.io.*;
import java.util.*;

public class Main {

  public static void sort012(int[] arr){
    //write your code here
    //somya code 
    int i = 0;
    int j = 0;
    int n = arr.length;
    int k = n-1;
    while(i<=k){
        if(arr[i]==0){
            swap(arr,i,j);
            i++;
            j++;
        } else if(arr[i]==1){
            i++;
        } else if(arr[i]==2){
            swap(arr,i,k);
            k--;
        }
    }  
  }

  public static void sort012b(int[] arr){
    //write your code here
    //somya code 
    int i = 0;
    int j = 0;
    int n = arr.length;
    int k = n-1;
    //or code 2 using for loop.
    for(i=0;i<=k; ){
        if(arr[i]==0){
            swap(arr,i,j);
            i++;
            j++;
        } else if(arr[i]==1){
            i++;
        } else if(arr[i]==2){
            swap(arr,i,k);
            k--;
        }
    }
    
  }

  //somya different method but portal doesn't give accepted for this question.
  public static void sort012c(int[] arr){
    //write your code here
    //somya code -> gives the same answer.
    // method is different.
    int i=0; int j=0; int k = 0;
    for(i=0;i<arr.length; ){
        if(arr[i]==0){
            swap(arr,k,i);
            swap(arr,j,k);
            k++;i++;j++;
        } else if(arr[i]==1){
            swap(arr,k,i);
            i++;
            k++;
        } else if(arr[i]==2){
            i++;
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
    sort012(arr);
    print(arr);
  }

}