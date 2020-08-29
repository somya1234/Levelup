public class leetcode{
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /************************************************************************** */
    // 141 -> Leetcode .
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast  = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        
        return false;
    }
    /********************************************************************************** */
}