public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    //Leetcode 876 
    public Node middle(){
        Node s,f = this.head;
        while(f.next!=null && f.next.next!=null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }


    public listNode reverseList(ListNode head){

        ListNode prev = null;
        ListNode curr = null;
        ListNode forw = null;

        while(curr!=null){
            forw = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forw;
        }
        return prev;
    }
}