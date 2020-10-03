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
        String str = "1*";
        System.out.println(decode_rec(str, 0));
    }

    public static int decode_rec(String str, int idx){
        int n = str.length();

        if(idx == n ) return 1; 

        char ch = str.charAt(idx);
        if( ch == '0') return 0; 

        int count = 0; 
        if(ch == '*'){
            // 9 single time call krenge, baaki ke ans m add single calls
            count+=decode_rec(str, idx+1) * 9;
        } else count+= decode_rec(str, idx+1);

        if(idx<n-1){
            if(ch == '*'){
                for(int val = 1; val<=9; val++){
                    int num = val; 
                    char ch1 = str.charAt(idx+1); 
                    if(ch1 == '*'){
                        for(int i=1; i<=9; i++){
                            num = num*10+i; 
                            if(num<=26) count+=decode_rec(str, idx+2);
                        }
                    } else{
                        num = num*10 + (ch1 - '0');
                        if(num<=26) count+= decode_rec(str, idx+2);
                    }
                }
            } else{
                int num = ch - '0'; 
                char ch1 = str.charAt(idx+1); 
                if(ch1 == '*'){
                    for(int i =1; i<=9; i++){
                        num = num*10+i; 
                        if(num<=26) count+=decode_rec(str, idx+2);
                    }
                } else{
                    num = num*10+(ch1 - '0'); 
                    if(num<=26) count+=decode_rec(str, idx+2);
                }
            }
        }

        return count; 
    
    }


    /**************************************************************************************/

}
