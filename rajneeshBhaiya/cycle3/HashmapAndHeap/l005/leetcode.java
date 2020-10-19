import java.util.ArrayDeque;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /***********************************************************************************/
    //leetcode 159 

    // method 2 - O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
     int n = arr.length; 
     ArrayDeque<Integer> que = new ArrayDeque<>(); 
     
     
     int[] ans = new int[n-k+1]; 
     int idx = 0; 

     for(int i =0; i<n; i++){
         while(que.size()!=0 && i-k>= que.getFirst()) que.removeFirst();

         while(que.size()!=0 && arr[que.getLast()] <= arr[i]) que.removeLast();

         que.addLast(i);
         if(i >= k-1) ans[idx++] = arr[que.getFirst()]; 
     }
     return ans; 
    }


    /***********************************************************************************/
}

