public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    //Leetcode 876 
    //approach 1 
    public Node middle(){
        Node s,f = this.head;
        while(f.next!=null && f.next.next!=null){
            s = s.next;
            f = f.next.next;
        }
        return s;
    }
    //but, in this question, we consider second mid when there are two mids
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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