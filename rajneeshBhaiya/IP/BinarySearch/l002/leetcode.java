import java.util.ArrayList;
import java.util.Collections;

public class leetcode {
    public static void main(String[] args) {
        
    }

    /*************************************************************************************/
    //leetcode 300 
    //time - (nlogn), LIS 
    public int lengthOfLIS(int[] nums){
        int n = nums.length; 
        if(n<=1) return n; 

        ArrayList<Integer> list = new ArrayList<>(); 
        list.add(nums[0]); 

        for(int i=1; i<n; i++){
            // fn of index of insert element in java 
            int idx = Collections.binarySearch(list, nums[i]); 
            if(idx>=0) continue; 
            else{
                idx = -idx-1; 
                if(idx == list.size()) list.add(nums[i]); 
                else list.set(idx, nums[i]);  
            }
        }

        return list.size();
    }

    /*************************************************************************************/
    // 875. Koko Eating Bananas
    
    public int minEatingSpeed(int[] piles, int H) {
        int si = 1, ei = 1000000; 
        
        while(si<ei){
            int eatingSpeed = (si+ei) >> 1; 
            if(isPossibleToEat(piles, eatingSpeed, H)) ei = eatingSpeed; 
            else si = eatingSpeed+1; 
            
        }
        return si; 
    }
    
    public boolean isPossibleToEat(int[] arr, int eatingSpeed, int H){
        int n = arr.length; 
        int hours = 0; 
        for(int i=0; i<n; i++){
            //convert in double.
            // hours+= (int)Math.ceil((arr[i]*1.0)/(eatingSpeed*1.0));
            //another way of finding ceil.
            hours+=(arr[i]-1)/eatingSpeed+1; 
            if(hours>H) return false; 
        }
        return true; 
    }

    /*************************************************************************************/

}
