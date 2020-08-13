import java.util.*;
public class leetcode {
    public static void main(String[] args) {
        question17();
    }

    /*********************************************************************************************** */
    //Leetcode 17. -> Letter Combinations of a Phone 
    public static void question17() {
        String digits = "23";
        ArrayList<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            System.out.println(ans);
            return;
        }
        lcFn(digits, "", ans);
        System.out.println(ans);
    }

    public static String[] keys = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public static void lcFn(String digits, String asf, ArrayList<String> ans) {
        if (digits.length() == 0) {
            ans.add(asf);
            return;
        }
        char ch = digits.charAt(0);
        int idx = (int) (ch - '0');
        String word = keys[idx];
        digits = digits.substring(1);
        for (int i = 0; i < word.length(); i++) {
            lcFn(digits, asf + word.charAt(i), ans);
        }
        return;
    }
    /*********************************************************************************************** */
    /*********************************************************************************************** */
    // Leetode 91  -> Decode Ways (v.imp)

    public int numDecodings(String s) {
        return decodeString(s,0,"");
    }
    public int decodeString(String s, int idx,String ans){
        if(idx == s.length()){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        char ch1 = s.charAt(idx);
        int cidx1 = ch1 - '0';
        if(cidx1==0){
            //wrong no to decode (incorrect info.)
            return 0;
        }
        count+= decodeString(s,idx+1,ans+(char)(cidx1+'A'-1)+" ");
        
        if(idx+1<s.length()){
                int num = Integer.parseInt(s.substring(idx,idx+2));            
                if(num>=10 && num<=26){
                    count+= decodeString(s,idx+2,ans+(char) (num+'A'-1) +" ");
                }   
            
        }
       
        return count;
       
    }
    /*********************************************************************************************** */

    /*********************************************************************************************** */
    /*********************************************************************************************** */

}