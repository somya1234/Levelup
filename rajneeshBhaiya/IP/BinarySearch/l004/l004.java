import java.util.ArrayList;

public class l004 {
    public static void main(String[] args) {
        solve();
    }

    /***********************************************************************************************/
    //leetcode 04 
    public double findMedianSortedArr(){
        int n = nums1.size();
        int m = nums2.size(); 
        if(n>m)
            return findMedianSortedArr(nums2, nums1);

        int omid = (n+m+1) >> 1; 
        int si = 0; 
        int ei = n; 
        while(si<=ei){
            //small mid and large mid
            int sMid = (si+ei) >> 1; 
            int lMid = omid - sMid; 

            //small left and small right 
            int sl = (sMid == 0) ? -(int)1e8 : nums1[sMid-1];
            int sr = (sMid == n ) ? (int)1e8  : nums1[sMid]; 

            int ll = (lMid == 0) ? -(int)1e8 : nums2[lMid-1]; 
            int lr = (sMid == m) ? (int)1e8 : nums2[lMid]; 

            if(sl>lr) ei = sMid -1; 
            else if(ll>sr) si = sMid +1; 
            else {
                //here 2 things apne app check 
                int boundaryOfLeft = Math.max(sl, ll); 
                int boundaryOfRight = Math.min(sr, lr); 

                if((n+m) % 2 !=0) return boundaryOfLeft; 
                else return (boundaryOfLeft + boundaryOfRight) / 2.0; 
            }
        }
        //though yaha pe kabhi aayega hi nhi 
        return 0.0; 
    }

    /***********************************************************************************************/
    // ..leetcode 134

    public int canCompleteCircuit(int[] gas, int[] cost){
        int n = gas.length; 
        int extraGas=0, sp = 0, deficit = 0; 

        for(int i=0; i<gas.length; i++){
            extraGas+= gas[i] - cost[i]; 
            if(extraGas<0){
                deficit+= extraGas; 
                extraGas = 0; 
                sp = i+1; 
            }
        }

        return ((sp==n ) || (extraGas + deficit<0)) ? -1 : sp; 
    }

    /***********************************************************************************************/

    //leetcode 802
     public boolean isCyclePresent(int[][] graph, int src, int[] vis){
         vis[src] = 1; 
        for(int e: graph[src]){
            if(vis[e]==0){
                if(isCyclePresent(graph, e, vis)) return true; 
            } else if(vis[e]==1) return true; 
        }
        vis[src] = 2; 
     }

     public List<Integer> eventualSafeNodes(int[][] graph){
         int n = graph.length; 
         int[] vis = new int[n+1]; 

         List<Integer> ans = new ArrayList<>(); 
         for(int i =0; i<n; i++){
             if(vis[i]==1) continue ; 
            if(vis[i]==2 || !isCyclePresent(graph, i, vis)) ans.add(i);
         }
         return ans; 
     }

     /********************************************************************************************/

}
