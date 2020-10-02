public class lcsOfThree {
    public static void main(String[] args) {
        solve();
    }   

    public static void solve(){

    }

    public static int lcs_rec(String s1, String s2, String s3, int i , int j , int k ){
        int x = s1.length(); 
        int y = s2.length(); 
        int z = s3.length(); 

        char ch1 = s1.charAt(i); 
        char ch2 = s2.charAt(j); 
        char ch3 = s3.charAt(k); 

        if(ch1 == ch2 == ch3){
            return lcs_rec(s1, s2, s3, i+1, j+1, k+1); 
        }
    }
}
