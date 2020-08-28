public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }


    //leetcode 21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null || l2==null){ return l1!=null?l1:l2;  }
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        while(curr1!=null && curr2!=null){
            if(curr1.val<curr2.val){
                prev.next = curr1;
                curr1 = curr1.next;
            } else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }
        if(curr1!=null){
            prev.next = curr1;
        } else if(curr2!=next){
            prev.next = curr2;
        }
        return dummy.next;
    }
    /******************************************************************************************** */

    //leetcode 328

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next==null || k==0){
            return head;
        }
        int len = 0;
        ListNode curr = head;
        while(curr!=null){
            len++;
            curr = curr.next;
        }
        k = k%len;
        int idx = len-k-1;
        curr = head;
        while(idx-->0){
            curr = curr.next;
        }
        ListNode rightHead = curr.next;
        curr.next = null;
        ListNode right = rightHead;
        while(right.next!=null){
            right = right.next;
        }
        right.next = head;
        return rightHead;
    }

    /*************************************************************************** */
}