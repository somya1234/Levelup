import java.util.Stack; 

public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        System.out.println(checkDuplicate());
    }

    /***************************************************************************************/

    // https://www.geeksforgeeks.org/find-expression-duplicate-parenthesis-not/
    public static boolean checkDuplicate(){
        // String str = "((a+b)+((c+d)))";
        // String str = "(((a+(b)))+(c+d))";
        // String str = "(((a+(b))+c+d))";
        String str = "((a+(b))+(c+d))";
        Stack<Character> st = new Stack<>();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i); 
            if(ch == ')'){
                if(st.size()>0 && st.peek()=='(') return true; 
                else{
                    while(st.size()>0 && st.peek()!='('){
                        st.pop();
                    }
                    st.pop();
                }
            }  else  
                st.push(ch);
        }
        return false; 
    }

    /***************************************************************************************/

    //leetcode 256 
    // https://www.lintcode.com/problem/paint-house/description
    public int minCost(int[][] costs) {
        // write your code here
        int n = costs.length; 
        if(n==0) return 0; 
        int m = costs[0].length; 
        if(m==0 ) return 0; 
        
        int[][] dp = new int[n][m]; 
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i==0) dp[i][j] = costs[i][j];
                else{
                    if(j==0) dp[i][j] = Math.min(dp[i-1][1], dp[i-1][2])+costs[i][j];
                    else if(j==1) dp[i][j] = Math.min(dp[i-1][0], dp[i-1][2])+costs[i][j];
                    else if(j==2) dp[i][j] = Math.min(dp[i-1][0], dp[i-1][1])+costs[i][j];
                }
            } 
        }
        
        int ans = (int)1e8; 
        for(int i=0; i<m; i++){
            ans = Math.min(ans, dp[n-1][i]);
        }
        return ans; 
    }

    /***************************************************************************************/
    
    // 946. Validate Stack Sequences
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length; 
        int m = popped.length; 
        int i=0, j =0; 
        Stack<Integer> st = new Stack<>();
        while(i<n){
            st.push(pushed[i]); 
            i++; 
            while(st.size()>0 && st.peek()==popped[j]){
                st.pop(); 
                j++; 
            }
            
        }
        
        return st.size()==0; 
    }

    /***************************************************************************************/
}
