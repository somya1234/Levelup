public class leetcode{
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        hasCycle();
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

    //leetcode 142. Linked List Cycle II.
    public ListNode detectCycle(ListNode head) {
        if (head == null || head == null)
            return null;

        
        ListNode slow = head;
        ListNode fast = head;
    
        // cannot do fast.next.next for testcase [1,2] and pos = -1.
        while(fast!=null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }

        if(slow!=fast) return null;

        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;;
        }

        return slow;
    }
    /**************************************************************************************** */

    //leetcode 160 -> Intersection of two linked lists.
    // method 1 -> O(n) - time complexity 
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        ListNode temp1 =headA;
        ListNode temp2 = headB;
        int size1 = 0;
        int size2 = 0;
        while(temp1!=null){
            temp1 = temp1.next;
            size1++;
        }
        while(temp2!=null){
            temp2 = temp2.next;
            size2++;
        }
        ListNode curr1 =headA;
        ListNode curr2 = headB;
        while(size1!=size2){
            if(size1>size2){
                size1--;
                curr1 = curr1.next;
            } else {
                size2--;
                curr2 = curr2.next;
            }
        }
        while(curr1!=null && curr2!=null){
            if(curr1 == curr2){
                return curr1;
            } else {
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
        }
        return null;
    }

    //=== method 2 - 
    // find cycle 
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode tail = headA;
        while(tail.next!=null){
            tail = tail.next;
        }
        tail.next = headB;
        ListNode node = detectCycle(headA);
        tail.next = null;
        return node;
    }
    
    public ListNode detectCycle(ListNode head){
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        // no cycle is present.
        if(slow!=fast){
            return null;
        }
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    /**************************************************************************************** */


}