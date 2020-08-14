import java.util.ArrayList;

public class bits {
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        int n = 00000110100001;
        int k = 5;
        System.out.println(offOn(n, k));
        System.out.println(onOff(n, k));
    }
    /**************************************************************************************** */

    public static int offOn(int n, int k){
        //shift kth bit times or (idx) times.
       int mask = (1<<k);
       n = (n | mask);
       return n;
    }

    public static int onOff(int n, int k){
        // always use bits under brackets
       int mask = (~(1<<k));
       n = (n & mask);
        return n;
    }

    /**************************************************************************************** */
    
    /**************************************************************************************** */

}