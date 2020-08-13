import java.io.*;
import java.util.*;

public class Main {

  public static void targetSumPair(int[] arr, int target){
    //write your code here
    Arrays.sort(arr); //nlogn
    int i=0;
    int j = arr.length-1;
    while(i<j){
        //can't be i<=j
        if(arr[i]+arr[j]<target){
            i++;
        } else if(arr[i]+arr[j]>target){
            j--;
        } else{
            System.out.println(arr[i]+", "+arr[j]);
            i++;
            j--;
        }
    }

  }

  //problem here, all the pairs should be in ascending order, so 
//we need to sort the arary first.
public static void targetSumPair1(int[] arr, int target){
  //write your code here
  //O(n2) approach 
  int n = arr.length;
  for(int i=0;i<n;i++){
      for(int j=i+1;j<n;j++){
          if(arr[i]+arr[j]==target){
              int small = arr[i];
              int big = arr[j];
              if(arr[i]>arr[j]){
                  //as the output in ascending order of all the pairs
                  int temp = arr[i];
                  small = big;
                  big = temp;
              }
              System.out.println(small+", "+big );
          }
      }
  }

}

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for(int i = 0 ;i < n; i++){
      arr[i] = scn.nextInt();
    }
    int target = scn.nextInt();
    targetSumPair(arr,target);
  }

}