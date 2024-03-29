public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        mergeTwoLists();
        oddEvenList();
        segregateOddAndEven(); // gfg 
        rotateRight(); 
        reorderList();
        removeNthNodeFromEnd();
        sortList();
        splitLLInParts();
        mergeKSortedLists();
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
            //connect their next (c1.next) //point of error
            c1.next = c2.next;
            c1 = c1.next;
            c2.next = c1.next;
            c2 = c2.next;
        }
        c1.next = ehead;
        return head;
    }

    /******************************************************************************************* */
    //gfg question 
    // https://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/  

    // method 1 -> maintains the even l.l in the original l.l and odd l.l using dummy node.
    public static ListNode segregateOddAndEven(ListNode head){
        ListNode curr = head; // even 
        // ListNode c2 = head; // odd
        ListNode prev = null;
        ListNode dummy = new ListNode(-1); 
        ListNode dummyHead = dummy;
        while(curr!=null){
            if(curr.val%2==0){
                prev = curr;
                curr = curr.next; 
            } else {
                if(head == curr){
                    ListNode temp = curr.next;
                    curr.next = null;
                    dummyHead.next = curr;
                    curr = temp;
                    head = head.next;
                } else {
                    prev.next = curr.next;
                    curr.next = null;
                    dummyHead.next = curr;
                    curr = prev.next;
                }
            }
            dummyHead = dummyHead.next;
        }
        if(prev == null){
            return dummy.next;
        }
        prev.next = dummy.next;
        return head;
    }


    // more good approach -> take 2 dummy nodes 
    public ListNode segregateOddAndEven(ListNode head){
        if(head == null || head.next==null){
            return head;
        }

        ListNode dummy1 = new ListNode(-1);
        ListNode eHead = dummy1;
        ListNode dummy2 = new ListNode(-1);
        ListNode oHead = dummy2;
        ListNode curr = head;

        while(curr!=null){
            if(curr.val%2==0){
                eHead.next = curr;
                curr = curr.next;
                eHead = eHead.next;
            } else {
                Node forw = curr.next;
                curr.next = null;
                oHead.next = curr;
                oHead = oHead.next;
                curr = forw;
            }
        }
        // if you put all the l.l without putting null to its next, do dry run for that, each time 
        // l.l gets changed due to ehead.next, but at last to remove the unnecessary nodes, to null.
        eHead.next = null;
        // works for all cases , odd and even.
        eHead.next = dummy2.next;
        return dummy1.next;

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

    // leetcode 148 -> Sort List. 

    public ListNode sortList(ListNode head) {
        if(head == null || head.next==null){
            return head;
        }
        ListNode ans = sort(head);
        return ans;
    }
    
    public ListNode sort(ListNode temp){
        if(temp.next==null){
            return temp;
        }
        
        ListNode mid = findMid(temp);
        ListNode right = mid.next;
        mid.next = null;
        ListNode left = sort(temp); // left
        ListNode rightll = sort(right);
        ListNode sortedLL = mergeTwoSortedLists(left,rightll);
        return sortedLL;
    }
    
    public ListNode mergeTwoSortedLists(ListNode left, ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy; 
        ListNode c1 = left;
        ListNode c2 = right;
        while(c1!=null && c2!=null){
            if(c1.val<c2.val){
                head.next = c1;
                c1 = c1.next;
            } else {
                head.next = c2;
                c2 = c2.next;
            }
            head = head.next;
        }
        if(c1!=null){
            head.next = c1;
        } else if(c2!=null){
            head.next = c2;
        }
        return dummy.next;
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
    /************************************************************************************** */

    // leetcode -> 725 
    // Split Linked List in parts -> self done (extra.)
    public ListNode[] splitListToParts(ListNode root, int k) {
        if(root == null){
            // return k nulls.
            ListNode[] ans = new ListNode[k];
            return ans;
        }
        ListNode temp = root;
        int size = 0;
        while(temp!=null){
            temp = temp.next;
            size++;
        }
        ListNode[] arr = new ListNode[k];
        int idx = 0;
        if(k>size){
            while(idx!=size){
                arr[idx] = root;
                ListNode next = root.next;
                root.next = null;
                root = next;
                idx++;
            }
            while(idx!=k){
                arr[idx] = root;
                idx++;
            }
            
        } else {
            int div = size/k;
            int rem = size%k;
            ListNode c1 = root;
            while(idx!=k){
                arr[idx] = c1;
                int tempDiv = div;
                while(tempDiv>1){
                    // this doesn't reflect changes in array as we only changed the pointer.
                    c1 = c1.next;
                    tempDiv--;
                }
                if(rem>0){
                    c1 = c1.next;
                    rem--;
                }
                ListNode c2 = c1.next;
                c1.next = null;
                c1 = c2;
                idx++;
            }
        }
        return arr;
    }
    /******************************************************************************************* */

    // Leetcode - 23 
    // Merge K Sorted Lists 
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        return sort(lists, 0, lists.length-1);
    }
    
    public ListNode sort(ListNode[] lists, int si, int ei){
        if(si==ei){
            return lists[si];
        }
        
        int mid = (si+ei)/2;
        ListNode left = sort(lists, si, mid);
        ListNode right = sort(lists, mid+1, ei);
        return sortTwoLL(left,right);
    }
    
    public ListNode sortTwoLL(ListNode left, ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        ListNode c1 = left;
        ListNode c2 = right;
        while(c1!=null && c2!=null){
            if(c1.val<c2.val){
                head.next = c1;
                c1 = c1.next;
            } else{
                head.next = c2;
                c2 = c2.next;
            }
            head = head.next;
        }
        if(c1!=null){
            head.next = c1;
        } else if(c2!=null){ // try.
            head.next = c2;
        }
        return dummy.next;
    }


    //========== method 2 -> very slow comparatively though both are nlogn 

    public class newComp implements Comparator<ListNode>{
        // public int compareTo(newComp o){
        //     it implements compare 
        public int compare(ListNode l1,ListNode l2){
            // if(l1.val<l2.val){
            //     return -1;
            // }
            // else if(l1.val>l2.val)
            //     return 1;
            // else
            //     return 0;
            return l1.val - l2.val;
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new newComp());
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                 pq.add(lists[i]);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while(pq.size()>0){
            ListNode top = pq.remove();
            if(top.next!=null){
                pq.add(top.next);
            }
            top.next = null;
            head.next = top;
            head = head.next;
        }
        return dummy.next;
    }

    /********************************************************************************************* */
}