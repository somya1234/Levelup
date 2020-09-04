import java.util.Stack;

public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /********************************************************************************** */

    // 1. store idx in stack.
    public static int[] nextGreaterRight(int[] arr){
        Stack<Pair> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=0;i<arr.length;i++){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()]<ele){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    // returns idx of next greater left elements.
    public static int[] nextGreaterLeft(int[] arr){
        Stack<Pair> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=ans.length-1;i>=0;i--){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()]<ele){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] nextSmallerRight(int[] arr){
        Stack<Pair> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=0;i<arr.length;i++){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()] > ele){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] nextSmallerLeft(int[] arr){
        Stack<Pair> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=ans.length-1;i>=0;i--){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()]>ele){
                ans[st.pop()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    /******************************************************************************************* */

    // leetcode 921 
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
        count+= st.size();
        return count;
    }

    /********************************************************************************************* */


}
