public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }


    // leetcode 84 
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsr = new int[n];
        int[] nsl = new int[n]; // next smaller left
        Stack<Integer> st = new Stack<>();
        Arrays.fill(nsl,-1);
        // right smallest find
        for(int i=0;i<n;i++){
            int ele = heights[i];
            while(st.size()>0 && heights[st.peek()]>ele){
                int idx = st.pop();
                nsr[idx] = i;
            }
            st.push(i);
        }
        while(st.size()>0){
            nsr[st.pop()] = n;
        }
        
        // find smaller left;
        for(int i=n-1;i>=0;i--){
            int ele = heights[i];
            while(st.size()>0 && heights[st.peek()]>ele){
                int idx = st.pop();
                nsl[idx] = i;
            }
            st.push(i);
        }
        
        //
        int area = 0;
        for(int i=0;i<n;i++){
            int calc = (nsr[i]-nsl[i]-1)*heights[i];
            area = Math.max(area,calc);
        }
        return area;
    }
    /***************************************************************************** */
}
