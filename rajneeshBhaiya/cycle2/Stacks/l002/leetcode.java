public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /**************************************************************************************** */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch=='{' || ch=='['){
                st.push(ch);
                // eg -> ']'
            } else if(st.size()==0){// more no of closing brackets 
                return false; 
            }  else if(ch==')' && st.peek()!='('){
                    return false;                
            } else if(ch=='}' && st.peek()!='{'){
                return false;                
            }  else if(ch==']' && st.peek()!='['){
                return false;                
            }  else
                st.pop();
        }
        // ege -> '['
        // if(st.size()>0){
        //     return false;
        // }
        // return true;
        return st.size()==0;
    }
    /****************************************************************************************** */

    // leetcode 1021 -> Remove Outermost parantheses

    public String removeOuterParentheses(String S) {
        int cbr = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch=='(' && cbr++!=0){
                sb.append(ch);
            } else if(ch==')' && --cbr!=0){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /*******************************************************************************************/

    // leetcode 503 -> Next Greater Element II
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill(ans,-1);
        for(int i=0;i<n*2;i++){
            int idx = (i)%n;
            int ele = nums[idx];
            while(st.size()>0 && nums[st.peek()]<ele){
                int idx2 = st.pop();
                ans[idx2] = ele;
            }
            if(i<n){
               st.push(i);
            }
        }
        return ans;
    }
    /*******************************************************************************************/

    // leetcode 921 
    // method 1 -> using stack 
    public int minAddToMakeValid(String S) {
        Stack<Character> st = new Stack<>();
        int count = 0;
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            if(ch=='('){
                st.push(ch);
            } else {
                if(st.size()==0){ count++; }
                else st.pop();
            }
        }
        count+= st.size(); // for all single '('
        return count;
    }

    // method 2 -> without stack 
    // a better solution, though both are good enough.
    public int minAddToMakeValid(String S) {
        // store only the requirement and not their count. e.g )(
        int obr = 0;
        int cbr = 0;
        for(int i=0;i<S.length();i++){
            char ch = S.charAt(i);
            // cant satisfy closing bracket.
            if(ch=='('){
                // need of closing bracket to complete it.
                cbr++;
            } else  if(cbr>0){ cbr--; }
                // either complete the group, i.e staisfy someone's need.
               
                // else ask for need to opening bracket.
            else obr++;
        }
        return obr+cbr;
    }

    /*******************************************************************************************/

    // leetcode 32 -> Longest Valid Parantheses (5*)
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int len = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch=='('){
                st.push(i);
            } else if(st.size()>1 && s.charAt(st.peek())=='('){
                st.pop();
                len = Math.max(len, (i-st.peek()) );
            } else{
                // if no '(' on st.peek(), when ')' occurs.
                st.push(i);
            }
        }
        return len;
    }

    /*******************************************************************************************/
}
