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

    // 328 -> Odd even Linked lists
    //approach1 -> wrong approach -> TLE
    public ListNode oddEvenList(ListNode head) {
        if(head == null && head.next==null){
            return head;
        }
        ListNode c1 = head;
        ListNode c2 = head.next;
        while(c1.next!=null && c1.next.next!=null){
            c1.next = c1.next.next;
            c1 = c1.next;
        }
        c1.next = c2;
        while(c2.next!=null && c2.next.next!=null){
            c2.next = c2.next.next;
            c2 = c2.next;
        }
        c2.next = null;
        return head;
    }

    // correct approach -> O(n)

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next==null){
            return head;
        }
        ListNode c1 = head;
        ListNode c2 = head.next;
        ListNode ehead = head.next;
        // this works for odd size as well as even size linked lists 
        while(c1.next!=null && c2.next!=null){
            c1.next = c2.next;
            c1 = c1.next;
            c2.next = c1.next;
            c2 = c2.next;
        }
        c1.next = ehead;
        return head;
    }

    /******************************************************************************************** */
    // leetcode 61 -> Rotate List 

    // approach 1 -> self done.
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
        if(k==0){
            // otherwise error 
            return head;
        }
        int idx = len -k-1;
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

    //approach 2 -> 
    // method 2 -> sir method 
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next==null){
            return head;
        }
        int len = 0;
        ListNode temp = head;
        while(temp!=null){
            len++;
            temp = temp.next;
        }
        k = k % len;
        if(k== 0){ // optional 
            return head;
        }
        
        ListNode curr1 = head;
        ListNode curr2 = head;
        while(curr2.next!=null){
            if(k-->0){
                curr2 = curr2.next;
            } else {
                curr2 = curr2.next;
                curr1 = curr1.next;
            }  
        }
        ListNode nHead = curr1.next;
        curr1.next = null;
        curr2.next = head;
        return nHead;
    }

    /*********************************************************************************************** */
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