public class client2 {
    public static void main(String[] args) {
        linkedlist2 ll = new linkedlist2();
        for(int i=1;i<=20;i++){
            ll.addLast(i*10);
        }
        System.out.println(ll);
    }
}