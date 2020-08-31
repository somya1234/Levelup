import java.util.HashMap;

public class leetcode {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /**************************************************************************************** */
    // leetcode 138  -> Deep copy with random pointer 
    // method 1 -> O(n)
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node c1 = head;
        
        // join both the l.l
        while(c1!=null){
            Node c2 = new Node(c1.val);
            Node f1 = c1.next;
            c1.next = c2;
            c2.next = f1;
            c1 = f1;
        }
        
        c1 = head;
        // add random to nodes of new l.l
        while(c1!=null){
            if(c1.random!=null){
                c1.next.random = c1.random.next;
            }
            c1 = c1.next.next;
        }
        
        
        c1 = head;
        Node c2 = c1.next;
        Node nHead = c2;
        // separate the two l.l.
        while(c1.next!=null && c2.next!=null){
            c1.next = c2.next;
            c1 = c1.next;
            c2.next = c1.next;
            c2 = c2.next;
        }
        c1.next = null;
        c2.next = null;
        
        
        return nHead;
    }

    /****************************************************************************************** */

    class LRUCache {
        // doublu linked list structure 
        private class Node{
            int key = 0;
            int value = 0;
            Node prev = null;
            Node next = null;

            Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        // data members of doubly linked list 
        private Node head = null;
        private Node tail = null;
        private int maxSize = 0;
        private int size = 0; 

        private HashMap<Integer,Node> map = new HashMap<>();

        private void addLast(Node node){
            if(this.head ==null){
                this.head = this.tail = node;
            } else{
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }
            this.size++;
        }

        private void removeNode(Node node){
            if(this.head == this.tail){
                this.head = this.tail = null; 
            } else if(this.head == node){
                this.head = this.head.next;
                this.head.prev = null;
                node.next = null;
            } else if(this.tail == node){
                this.tail = this.tail.prev;
                this.tail.next = null;
                node.prev = null;
            } else {
                Node prev = node.prev;
                Node next = node.next;

                prev.next = next;
                next.prev = prev;

                node.next = null;
                node.prev = null;
            }
            this.size--;
        }

        public LRUCache(int capacity) {
            this.maxSize = capacity; 
        }
        
        public int get(int key) {
            if(!map.containsKey(key)) return -1;

            Node node = map.get(key);
            removeNode(node);
            addLast(node);
            return node.value;
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node oldValue = get(key);
                if(value!=oldValue){
                    // the opened node is already in first position or (tail), view from last.
                    this.tail.value = value;
                }
            } else {
                Node node = new Node(key,value);
                map.put(key,node);
                addLast(node);
                if(this.size > this.maxSize){
                    Node rnode = this.head;
                    removeNode(this.head); // removeFirst()
                    map.remove(rnode.key);
                }
            }
        }
    }


}