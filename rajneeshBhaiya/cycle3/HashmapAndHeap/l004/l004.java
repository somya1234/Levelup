public class l004 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        
    }

    // gfg - https://www.geeksforgeeks.org/number-subarrays-sum-less-k/

    public static int numOfSubArrayLessK(int[] arr, int k ){
        int si = 0, ei = 0; 
        int n = arr.length; 

        int count = 0; 
        int sum = 0; 
        while(ei<n ){
            sum+= arr[ei]; 
            while(sum > k && si<n){
                sum-=arr[si++]; 
            }

            count+= (ei- si + 1); 
            ei++; 
        }

        return count; 
    }
}
