public class l004 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        int n = 247; //7 1's
        // System.out.println(countBits(n));
        // System.out.println(countBits02(n));
        System.out.println(countBits03(n));

    }

    /*************************************************************** */
    public static int countBits(int n ){
        //it counts all the bits which are (1).
        int mask = (1);
        int count = 0;
        for(int i=0;i<32;i++){
            //here, we can't put condition for ==1.
            if( ( n & mask ) != 0){
                count++;
            }
            //left shift by 1, so that can check the next bit.
            mask = (mask<<1);
        }
        return count;
    }

    /**************************************************************** */

    public static int countBits02(int n ){
        int count = 0;
        while(n!=0){
            //here, (n&1)==1, check can be used as we are checking last bit, so it will 
            //always give 1, if last bit is 1.
            if((n & 1)!=0){
                count++;
            }
            //triple right shift by 1.
            n = (n>>>1);
        }
        return count;
    }
    /********************************************************** */
    public static int countBits03( int n ){
        int count = 0;
        while(n!=0){
            count++;
            int mask = ( n & (-n)); // and with 2's complement gives right most set bit. 
            n = (n ^ mask); 
        }
        return count;
    }
}