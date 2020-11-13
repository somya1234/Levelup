import java.util.Random;
public class client {
    public static void main(String[] args) throws Exception{
        exper1();
    }

    public static void exper1() throws Exception{
        Stack st = new Stack();
        for(int i=0;i<10;i++){
            // put random fn in brackets., otherise int on only math,random which gives decimal values.
            st.push((int)(Math.random()*100));
            // st.push(i*10);
        }
        System.out.println(st);
    }
}