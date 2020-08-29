public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        mergeTwoLists();
        oddEvenList();
        rotateRight(); 
        reorderList();
        removeNthNodeFromEnd();
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
        if(head == null || head.next==null || k == 0){
            return head;
        }
        int len = 0;
        ListNode temp = head;
        while(temp!=null){
            len++;
            temp = temp.next;
        }
        k = k % len;
        // works without this also, but better to put it for safety rather than giving errors.
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
        // v. imp step, do it in this order only.
        curr2.next = head;
        ListNode nHead = curr1.next;
        curr1.next = null;
        return nHead;
    }

    /********************************************************************************************* */

    // leetcode 143 // O(n)
    // Reorder List 
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode mid = findMid(head); // n /2
        ListNode right = mid.next;
        mid.next = null;
        ListNode c2 = reverseList(right); // n/2
        ListNode c1 = head;
        while(c1!=null && c2!=null){ // n /2
            // don't update after you have already changed next 
        //     c1.next = c2;
        //     c1 = c1.next;
        //     c2.next = c1;
        //     c2 = c2.next;
            
            ListNode next1 = c1.next;
            ListNode next2 = c2.next; 
            
            c1.next = c2;
            c2.next = next1;
            
            c1 = next1;
            c2 = next2;
        }
        return;
    }
    
    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;
        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    public ListNode findMid(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*********************************************************************************************** */
    //leetcode 19 
    // Remove nth node from end of list 
    // in one pass 
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || (head.next==null && n==1)){
            return null;
        }
      
        ListNode c1 = head;
        ListNode c2 = head;
        while(n-->0){
            c2 = c2.next;
        }
        
          //remove first node ? -> in one pass.
        if(c2 == null){
            return head.next;
        }
        
        while(c2.next!=null){
            c1 = c1.next;
            c2 = c2.next;
        }
        ListNode rnode = c1.next;
        ListNode forw = rnode.next;
        c1.next = forw;
        rnode.next = null;
        return head;
    }
    

    /*************************************************************************** */

    
    /************************************************************************************** */
}