import java.io.*;
import java.util.*;

public class Main {
    
    //n time
    public static int findPivot(int[] arr){
        for(int i=1;i<arr.length;i++){
            int curr = arr[i];
            int prev = arr[i-1];
            if(curr<prev){
                return i;
            }
        }
        return 0;
    }
    
    //n time
    public static int findPivot1(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            int curr = arr[i];
            int prev = arr[i-1];
            if(curr<prev){
                return i;
            }
        }
        return 0;
    }
    
    //do findPivot2 -> in less than n time

  public static void targetSumPair(int[] arr, int target) {
    //write your code here
    int pivot = findPivot(arr);
    int i = pivot;
    int j = i==0 ? arr.length-1 : i-1;
    int count = 0;
    //n time
    while(arr.length - count > 1){
        if(arr[i]+arr[j]<target){
            i = (i+1)%arr.length;
            count++;
            //or
            // if(i==arr.length){
            //     i=0;
            // }
        } else if(arr[i]+arr[j]>target){
            j = (j-1+arr.length)%arr.length;
            count++;
            //or
            // if(j==-1){
            //     j = arr.length-1;
            // }
        } else {
            System.out.println(arr[i]+", "+arr[j]);
            count+=2;
            i = (i+1)%arr.length;
            j = (j-1+arr.length)%arr.length;
        }
    }

  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
    }
    int target = scn.nextInt();
    targetSumPair(arr,target);
  }

}