public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {

    }

    // Leetcode 876
    // approach 1
    public Node middle() {
        Node s, f = this.head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    // but, in this question, we consider second mid when there are two mids
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /******************************************************************************************* */

    // Leetcode 206
    // method 1 -> reverse the pointers taking 3 pointers approach
    public ListNode reverseList(ListNode head) {
        // think for all 5 cases, otherwise error without below condition.
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev; // new head
    }

    // reverseList method 2 -> Rajneesh Bhaiya
    // similar to 3 pointer approach.
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // change the pointers direction
        ListNode nHead = null; // new head
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr;
            curr = curr.next;
            temp.next = null;

            if (nHead == null) {
                nHead = temp;
            } else {
                temp.next = nHead;
                nHead = temp;
            }
        }
        return nHead;
    }

    // approach3 -> by swapping data. (not a good approach ) -> Mohit Sir
    // time complexity -> n+n+ (n/2(n)) -> 2n+n^2 -> n^2 -> very bad complexity
    // but can be done without taking size or idx or get fn into account on
    // Leetcode.
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode right = head;
        // set right pointer
        while (right.next != null) { // (n)
            right = right.next;
        }
        // find mid
        ListNode mid = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) { // (n)
            slow = slow.next;
            fast = fast.next.next;
        }
        mid = slow.next; // to stop when left becomes greater than (actual mid)
        while (left != mid) { // n/2
            // swap the data.
            int data = left.val;
            left.val = right.val;
            right.val = data;
            ListNode nRight = left;
            left = left.next;
            // n
            // two conditions for even and odd cases respectively.
            while (nRight.next != right && nRight != right) {
                nRight = nRight.next;
            }
            right = nRight;
        }
        return head;
    }

    // approach 4 -> Mohit Sir
    // recursive approach -> O(n) and no space complex
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        ListNode left = head;
        reverseRec(head, 0, left, size);
        return head;
    }

    // wasn't able to initialize left node as static variable here.
    // so, made return type of reverseRec fn as Node to return left node and
    // update it after shifting.
    public ListNode reverseRec(ListNode right, int floor, ListNode left, int size) {
        if (right == null) {
            return left;
        }
        left = reverseRec(right.next, floor + 1, left, size);
        if (floor >= size / 2) {
            int data = left.val;
            left.val = right.val;
            right.val = data;
            left = left.next;
        }
        return left;
    }

    /******************************************************************************************* */

    // Leetcode 234.
    // isPalindorme -> O(n) time complexity -> (2n exact )
    // O(1) space
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = mid(head); // n/2
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null;

        right = reverseList(right); // n/2
        ListNode nRight = right;

        boolean res = true;
        // after considering odd and even cases.
        while (right != null) { // n/2
            if (left.val != right.val) {
                res = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        right = reverseList(nRight); // n/2
        mid.next = right;
        return res;

    }

    public ListNode mid(ListNode head) {
        // return 1st mid in case of even ll.
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = curr.next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /*********************************************************************************************** */

    // Leetcode 21 -> merge two sorted lists to make a sorted list (v.imp)
    // no extra space is taken 
    // time complexity - O(n)
    //space -> O(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-10);
        ListNode temp = dummy;
        // it works for all 5 cases of LL
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 != null) {
            temp.next = l1;
        } else if (l2 != null) {
            temp.next = l2;
        }
        return dummy.next;
    }

    /*************************************************************************************** */
}