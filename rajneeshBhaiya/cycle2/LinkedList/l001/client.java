import java.util.LinkedList;

public class client {
    public static void main(String[] args) throws Exception {
        Linkedlist ll = new Linkedlist();
        for(int i=1;i<=20;i++){
            ll.addLast(i*10);
        }
        ll.removeFirst();
        System.out.println(ll);
    }
}

//execute by running this command.
// javac client.java && java client
