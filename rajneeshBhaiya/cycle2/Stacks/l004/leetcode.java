public class leetcode {
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){

    }

    // leetcode 42. 

    /*****************************************************************************************/

    // method 1 -> 
    public int trap(int[] height){
        if(height.length==0){ return 0; }
        int n = height.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        int prev = -1;
        for(int i=0;i<n;i++){
            leftMax[i] = Math.max(prev,height[i]);
            prev = leftMax[i];
        }

        prev = -1;
        for(int i=n-1;i>=0;i--){
            rightMax[i] = Math.max(prev, height[i]);
            prev = rightMax[i];
        }

        int water = 0;
        for(int i=0;i<n;i++){
            int w = Math.min(leftMax[i],rightMax[i]) - height[i];
            water += w;
        }
        return water;
    }

    // method 2 -> 
    public int trap(int[] height){
        if(height.length==0){ return 0; }
        int n = height.length;

        Stack<Integer> st = new Stack<>();
        int water = 0;
        for(int i=0;i<n;i++){
            while(st.size()!=0 && height[st.peek()]<=height[i]){
                int h = height[st.peek()];
                st.pop();

                if(st.size()==0){
                    break;
                }

                int W = i - st.peek() - 1;

                int h1 = height[i];
                int h2 = height[st.peek()];
                int H = Math.min(h1,h2) - h;
                water += W * H;
            }
        }
        return water;
    }


    // method 3 
    public int trap(int[] height){
        if(height.length==0){return 0;}
        int n = height.length; 
        int lmax = 0, rmax = 0, li = 0, ri = n-1, water = 0;
        while(li<ri){
            // update lmax and rmax 
            lmax = Math.max(lmax,height[li]);
            rmax = Math.max(rmax,height[ri]);

            if(lmax<=rmax){ water+= lmax - height[li++]; }
            else water += rmax - height[ri--];
        }
        return water;
    }

    /*****************************************************************************************/
}
