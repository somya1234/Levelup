import java.util.Arrays;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        // solve1();
        solve2();
    }

    /*********************************************************************************************** */

    public static void print(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void print2d(int[][] arr) {
        for (int[] ar : arr) {
            print(ar);
        }
        System.out.println();
    }

    /**************************************************************************************/
    // leetcode 91 - decoding ways 
    public static void solve1() {
        String str = "226";
        // System.out.println(decodeWays_Rec(str, 0, ""));

        int n = str.length();
        int[] dp = new int[n + 1];
        // memoization 
        // n+1 to store 1 of str.length() idx.
        // System.out.println(numDecodings(str, 0, dp));
        // print(dp);

        //tabulation 
        // System.out.println(numDecodings_tab(str, n, dp));

        // optimized
        System.out.println(numDecodings_opti(str, n-1));
    }

    // recursion
    // also prints the string .
    public static int decodeWays_Rec(String str, int i, String ans) {
        int n = str.length();

        if (i == n) {
            System.out.println(ans);
            return 1;
        }
        int single = 0;
        if (str.charAt(i) != '0')
            single = decodeWays_Rec(str, i + 1, ans + (char) ((str.charAt(i) - '0') + 'a' - 1));

        int pair = 0;
        // due to e.g 10., single is 0 but only ans here is (1) due to ("10")
        if (i < n - 1) {
            String temp = "" + str.charAt(i);
            temp += str.charAt(i + 1);
            int num = Integer.parseInt(temp);
            if (num >= 10 && num <= 26) {
                String t = ans + (char) (num + 'a' - 1);
                pair = decodeWays_Rec(str, i + 2, t);
            }

        }
        return single + pair;
    }

    // ====== sir method - memoization 
    public static int numDecodings(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != 0)
            return dp[idx];

        char ch = s.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;

        int count = 0;

        count += numDecodings(s, idx + 1, dp);
        if (idx < s.length() - 1) {
            char ch1 = s.charAt(idx + 1);
            int num = (ch - '0') * 10 + (ch1 - '0');
            // we returned above if '0', otherwise put check >=10 and <=26
            if (num <= 26) {
                count += numDecodings(s, idx + 2, dp);
            }
        }

        return dp[idx] = count;

    }

    // ====tabulation

    public static int numDecodings_tab(String s, int idx, int[] dp) {
        for (; idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch = s.charAt(idx);

            if (ch == '0') {
                dp[idx] = 0;
                continue;
            }

            dp[idx] += dp[idx + 1];
            if (idx < s.length() - 1) {
                char ch1 = s.charAt(idx + 1);
                int num = (ch - '0') * 10 + (ch1 - '0');
                if (num <= 26) {
                    dp[idx]+= dp[idx + 2];
                }
           }
        }
        return dp[0]; 
    }

    //optimized method - space - O(1), time - O(n)
    public static int numDecodings_opti(String s, int idx){
        int a = 1, b = 0; 
        for( ; idx>=0; idx--){
            if(s.charAt(idx)=='0'){
                b = a; 
                a = 0; 
            } else{
                int c = a; 
                if(idx < s.length()-1){
                    int num = (s.charAt(idx)-'0')*10 + (s.charAt(idx+1)-'0'); 
                    if(num<=26){
                        c+=b; 
                    }
                }
                b = a; 
                a = c; 
            }
        }
        return a; 
    }
    /**************************************************************************************/

    // leetcode 639 - Decoding Ways II 
    public static void solve2(){
        // String str = "1*"; // 18 ans 
        // String str = "*"; // 9 ans
        String str = "1*1"; // 20 ans
        int n = str.length();

        // System.out.println(decode_bruteforce(str, 0));
        // System.out.println(decode_rec(str, 0));

        // memoization 
        long[] dp = new long[n+1]; 
        Arrays.fill(dp, -1);
        System.out.println(decode_mem(str, 0, dp));
        // print(dp);
    }

    public static int decode_bruteforce(String str, int idx){
        int n = str.length();

        if(idx == n ) return 1; 

        char ch = str.charAt(idx);
        if( ch == '0') return 0; 

        int count = 0; 
        if(ch == '*'){
            // 9 single time call krenge, baaki ke ans m add single calls
            count+=decode_bruteforce(str, idx+1) * 9;
            // System.out.println(count);
        } else count+= decode_bruteforce(str, idx+1);

        if(idx<n-1){
            if(ch == '*'){
                for(int val = 1; val<=9; val++){
                    int num = val; 
                    char ch1 = str.charAt(idx+1); 
                    if(ch1 == '*'){
                        for(int i=1; i<=9; i++){
                            int number = num*10+i; 
                            if(number<=26) count+=decode_bruteforce(str, idx+2);
                        }
                    } else{
                        int number = num*10 + (ch1 - '0');
                        if(number<=26) count+= decode_bruteforce(str, idx+2);
                    }
                }
            } else{
                int num = ch - '0'; 
                char ch1 = str.charAt(idx+1); 
                if(ch1 == '*'){
                    for(int i =1; i<=9; i++){
                        int number  = num*10+i; 
                        if(number<=26){
                            count+=decode_bruteforce(str, idx+2);
                        } 
                    }
                } else{
                    num = num*10+(ch1 - '0'); 
                    if(num<=26) count+=decode_bruteforce(str, idx+2);
                }
            }
        }

        return count; 
    
    }

    // Recursion --- 
    public static int decode_rec(String str, int idx){
        int n = str.length(); 
        int mod = (int)1e9+7; 

        if(idx == n ) return 1; 

        char ch = str.charAt(idx); 
        if(ch == '0') return 0; 

        int count = 0; 
        if(ch == '*') count= (count % mod + 9 * decode_rec(str, idx+1)  % mod) % mod ; 
        else count= (count % mod + decode_rec(str, idx+1) % mod) % mod; 

        if(idx<n-1){
            char ch1 = str.charAt(idx+1); 
            // case - **
            if(ch == '*' && ch1=='*') count= ( count%mod + 15 * decode_rec(str, idx+2) %mod) % mod; 
            // case - *num
            else if( ch == '*'){
                if(ch1>='0' && ch1<='6') count = (count % mod + 2 * decode_rec(str, idx+2) % mod) % mod; 
                else count = (count % mod + decode_rec(str, idx+2) % mod ) % mod; 
            //case - num*
            } else if(ch>='1' && ch<='9' && ch1=='*'){
                if(ch == '1') count = ( count % mod + 9 * decode_rec(str, idx+2) % mod) % mod; 
                else if( ch == '2')  count = ( count % mod + 6 * decode_rec(str, idx+2) % mod) % mod; 
            // case - num*num
            } else{
                int num = ch - '0'; 
                num = num * 10 + ch1- '0'; 
                if(num <= 26 ) count = ( count % mod + decode_rec(str, idx+2) % mod) % mod;
            }
        
        }
        return count; 
    }

    // memoization 
    // good to make mod of long type.- no overflow chakkar 
    public static long decode_mem(String str, int idx, long[] dp){
        int n = str.length(); 
        long mod = (int)1e9+7; 

        if(idx == n ) return dp[idx] = 1; 

        if(dp[idx]!=-1) return dp[idx]; 

        char ch = str.charAt(idx); 
        if(ch == '0') return dp[idx] = 0; 

        long count = 0; 
        if(ch == '*') count= (count % mod + 9 * decode_mem(str, idx+1, dp)  % mod) % mod ; 
        else count= (count % mod + decode_mem(str, idx+1, dp) % mod) % mod; 

        if(idx<n-1){
            char ch1 = str.charAt(idx+1); 
            // case - **
            if(ch == '*' && ch1=='*') count= ( count%mod + 15 * decode_mem(str, idx+2, dp) %mod) % mod; 
            // case - *num
            else if( ch == '*'){
                if(ch1>='0' && ch1<='6') count = (count % mod + 2 * decode_mem(str, idx+2, dp) % mod) % mod; 
                else count = (count % mod + decode_mem(str, idx+2, dp) % mod ) % mod; 
            //case - num*
            } else if(ch>='1' && ch<='9' && ch1=='*'){
                if(ch == '1') count = ( count % mod + 9 * decode_mem(str, idx+2, dp) % mod) % mod; 
                else if( ch == '2')  count = ( count % mod + 6 * decode_mem(str, idx+2, dp) % mod) % mod; 
            // case - num*num
            } else{
                int num = ch - '0'; 
                num = num * 10 + ch1- '0'; 
                if(num <= 26 ) count = ( count % mod + decode_mem(str, idx+2, dp) % mod) % mod;
            }
        
        }
        return dp[idx] = count % mod; 
    }

    /**************************************************************************************/

}
