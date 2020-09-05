public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /*************************************************************************************/

    // leetcode 1249 ->  Minimum Remove to Make Valid Parentheses
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch==')'){
                if(st.size()!=0){ st.pop(); }
                else sb.setCharAt(i,'#');
            } else if(ch=='('){
                st.push(i);
            }
            // do not do anything for letters
        }
        while(st.size()>0){
            int idx = st.pop();
            sb.setCharAt(idx,'#');
        }
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)!='#'){
                ans.append(sb.charAt(i));
            }
        }
        return ans.toString();
    }

    /*************************************************************************************/

    // leetcode 735 
    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> st = new Stack<>();
        
        for(int i=0;i<n;i++){
            int val = asteroids[i];
            // +ve number
            if(val>0){
                st.push(val);
            } else{
                // -ve number, -ve number
                if(st.size()==0 || st.peek()<0){
                    st.push(val);
                } else {
                    // +ve numver < -ve number 
                    while(st.size()>0 && st.peek()>0 && st.peek()< -(val)){
                        st.pop();
                    }
                    // -ve number,-ve number -> 
                    if(st.size()==0 || st.peek()<0){ st.push(val); }
                    // +ve number == --ve number
                    else if(st.peek()== (-val)){ st.pop(); }
                    // +ve number > -ve number.
                    else {//, he is smaller., dont push in stack.
                    }
                }
            }
        }
        int[] ans = new int[st.size()];
        for(int i=st.size()-1;i>=0;i--){
            ans[i] = st.pop();
        }
        return ans;
    }
    
    /*************************************************************************************/

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
