import java.util.Stack;
import java.util.Arrays; 

public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int[] arr = {5,2,4,3,2,1,4,7,6}; 
        // int[] ans = nextGreaterRight(arr); 
        // int[] ans = nextGreaterRightII(arr);
        // int[] ans = nextGreaterLeft(arr);
        // int[] ans = nextSmallerRight(arr); 
        // int[] ans = nextSmallerLeft(arr);
        // for(int i=0; i<ans.length; i++){
        //     System.out.print(ans[i]+" ");
        // }
        // System.out.println();

        
    }

    /********************************************************************************** */

    // 1. store idx in stack.
    public static int[] nextGreaterRight(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=0;i<arr.length;i++){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()]<ele){
                ans[st.pop()] = ele;
            }
            st.push(i);
        }
        return ans;
    }

    //method 2 ===== (from right)
    public static int[] nextGreaterRightII(int[] arr){
        Stack<Integer> st = new Stack<>(); 
        int n = arr.length; 
        int[] ans = new int[n]; 
        ans[n-1] = -1; 
        st.push(arr[n-1]); 
        for(int i=n-2; i>=0; i--){
            int ele = arr[i]; 
            while(st.size()>0 && st.peek()<=ele) st.pop(); 
            if(st.size()>0 && st.peek()>ele) {
                ans[i] = st.peek(); 
            } 
            else if(st.size()==0) ans[i] = -1; 

            st.push(ele); 
        }
        return ans; 
    }


    // returns idx of next greater left elements.
    public static int[] nextGreaterLeft(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=ans.length-1;i>=0;i--){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()]<ele){
                ans[st.pop()] = ele;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] nextSmallerRight(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans,-1);
        for(int i=0;i<arr.length;i++){
            int ele = arr[i];
            while(st.size()>0 && arr[st.peek()] > ele){
                ans[st.pop()] = ele;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] nextSmallerLeft(int[] arr){
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1); 
        for(int i=arr.length-1; i>=0; i--){
            int ele = arr[i]; 
            while(st.size()>0 && arr[st.peek()]>ele){
                int idx = st.pop(); 
                ans[idx] = ele; 
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
