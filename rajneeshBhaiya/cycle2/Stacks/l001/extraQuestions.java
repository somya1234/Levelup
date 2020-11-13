public class extraQuestions{
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        nextGreaterElementI();
        removeDuplicates();
        calcPointsInBaseBall();
    }

    /***************************************************************************************/
    // leetcode 496 - EASY
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            hm.put(nums1[i],i);
        }
        Arrays.fill(ans,-1);
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<nums2.length;i++){
            while(st.size()>0 && nums2[st.peek()]<nums2[i]){
                int ele = nums2[st.pop()];
                if(hm.get(ele)!=null)
                    ans[hm.get(ele)] = nums2[i];
            }
            st.push(i);
        }
        return ans;
    }

    /***************************************************************************************/
    // letcode 1047 - EASY
    public String removeDuplicates(String S) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(st.size()>0 && st.peek()==ch){
                st.pop();
            } else {
                st.push(ch);
            }
        }
        // StringBuilder sb = new StringBuilder();
        // while(st.size()>0){
        //     char ch = st.pop();
        //     sb.insert(0,ch);
        // }
        char[] ans = new char[st.size()];
        int i = st.size()-1;
        while(st.size()>0){
            ans[i--] = st.pop();
        }
        // return sb.toString();
        return String.valueOf(ans);
    }

    /***************************************************************************************/
    // leetcode 682 - see the writing style of Strings and characters.
    public int calPoints(String[] ops) {
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<ops.length;i++){
            // -ve sign also , so can't take character, take strings. 
            String ch = ops[i];
            if(ch.equals("C")){
                st.pop();
            } else if(ch.equals("D")){
                st.push(2*st.peek());
            } else if(ch.equals("+")){
                int score1 = st.pop();
                int score2 = st.peek();
                st.push(score1);
                st.push(score1+score2);
            } else{
                st.push(Integer.parseInt(ch));
            }
        }
        int sum = 0;
        while(st.size()>0){
            sum+=st.pop();
        }
        return sum;
    }

    /***************************************************************************************/


    /***************************************************************************************/
}