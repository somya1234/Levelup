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
        if(head == null || head.next == null) return null; 
        
        ListNode slow = head; 
        ListNode fast = head; 
        
        // cannot do fast.next.next for testcase [1,2] and pos = -1.
        while(fast!=null && fast.next!=null){
            slow = slow.next; 
            fast = fast.next.next; 
            if(slow == fast){
                break;
            }
        }
        
        //no cycle.
        if(slow!=fast) return null; 
        
        //find the point where cycle forms (2 inward arrows.)
        slow = head; 
        while(slow!=fast){
            slow = slow.next; 
            fast = fast.next; 
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


    /*****************************************************************************************/
    // leetcode 92 -> REverse LinkedList II 
    // method 1 -> very much thinking 
    // one pass soln, inplace, constant memory 
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null){
            return head;
        }
        int idx = m-2;
        ListNode curr = head;
         // to reach one node previous from where we have to reverse it
        while(idx-->0){
            curr = curr.next;
        }
        ListNode prev = null;
        if(m!=1){
            prev = curr;
            curr = curr.next;
            prev.next = null;
        }
        ListNode endNode = curr;
        ListNode p = null;
        ListNode forw = curr.next;
        int steps =n-m;
        // reverse it 
        while(steps-->=0){
            forw = curr.next;
            curr.next = p;
            p = curr;
            curr = forw;
        }
        if(prev!=null){
            prev.next = p;   
        } else {
            // when m=1 
            head = p;
        }
        endNode.next = curr;
        return head;
        
    }

    //method 2 - sir method (Easy)

    ListNode th = null, tt = null; 
    
    public void addFirst(ListNode node){
        if(th == null){
            th = node; 
            tt = node; 
        }
        else{
            node.next = th; 
            th = node; 
        }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || n==m) return head; 
        
        int i = 1;
        ListNode curr = head; 
        ListNode prev = null; 
        
        while(curr!=null){
            while(i>=m && i<=n){
                ListNode next = curr.next; 
                curr.next = null; 
                addFirst(curr); 
                curr = next; 
                i++; 
            }
            
            if(i>n){
                if(prev!=null){
                    prev.next = th; 
                    tt.next = curr; 
                } else{
                    tt.next = curr; 
                    head = th; 
                }
                break; 
            }
            
            prev = curr; 
            curr  =curr.next; 
            i++; 
        }
        
        return head; 
    }
    /****************************************************************************************** */

    // 25. Reverse Nodes in k-Group
    
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k==0) return head;
        
        int len = 0; 
        ListNode temp = head; 
        while(temp!=null){
            temp = temp.next; 
            len++;
        }
        
        temp = head; 
        ListNode prev = null; 

        while(true){
            ListNode lastNodeSubList = prev; 
            ListNode subList = temp; 
            for(int i=0; i<k && temp!=null; i++){
                ListNode next = temp.next; 
                temp.next = prev; 
                prev = temp; 
                temp = next; 
            }
            subList.next = temp; 
            if(lastNodeSubList ==null){
                head = prev; 
            } else{
                lastNodeSubList.next = prev; 
            }
            
            if(temp == null) break; 
            
            len-=k; 
            if(k>len) break; 
            prev = subList; 
        }
        return head; 
    }

    /******************************************************************************************/
}