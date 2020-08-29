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

    //leetcode 160 

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

    /**************************************************************************************** */


}