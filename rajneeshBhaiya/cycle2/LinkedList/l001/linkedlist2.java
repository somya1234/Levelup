public class linkedlist2 {
    private class Node{
        int data = 0;
        Node next = null;
        Node(int data){
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    //this fn is used by user outside the class, so make it public 
    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    //=========
    public int getFirst() throws Exception{
        if(this.size==0){
            throw new Exception("Empty LL");
        }
        return this.head.data;
    }

    public int getLast() throws Exception{
        if(this.size()==0){
            throw new Exception("Empty LL");
        }
        return this.tail.data;
    }
    
    public int getAt(int idx) throws Exception{
        if(idx<0 || idx>=this.size){
            throw new Exception("NULL Pointer");
        }
        Node gnode = getNodeAt(idx);
        return gnode.data;
    }

    private Node getNodeAt(int idx){
        Node gnode = this.head;
        if(idx == this.size-1){
            return this.tail;
        }
        while(idx-->0){
            gnode = gnode.next;
        }
        return gnode;
    }

    //==== add
    public void addFirst(int data){
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addFirstNode(Node node){
        if(this.size==0){
            this.head = node;
            this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }


    public void addLast(int data){
        Node node = new Node(data);
        addLastNode(node);
    }

    private void addLastNode(Node node){
        if(this.size==0){
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addAt(int idx, int data) throws Exception{
        if(idx<0 || idx>this.size){
            throw new Exception("Null Pointer");
        }
        Node node = new Node(data);
        addNodeAt(idx, node);
    }

    private void addNodeAt(int idx, Node node){
        if(idx == 0){
            addFirstNode(node);
        } else if(idx == this.size){
            addLastNode(node);
        } else {
            Node prev = getNodeAt(idx-1);
            Node forw = prev.next;
            prev.next = node;
            node.next = forw;
            this.size++;
        }
    }

    
    //=================

    public int removeFirst() throws Exception{
        if(this.size==0){
            throw new Exception("Empty LL");
        }
        Node rnode = removeFirstNode();
        return rnode.data;
    }

    private Node removeFirstNode(){
        Node rnode = this.head;
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
        }
        this.size--;
        return rnode;
    }

    public int removeLast() throws Exception{
        if(this.size==0){
            throw new Exception("Empty LL");
        }
        Node rnode = removeLastNode();
        return rnode.data;
    }

    private Node removeLastNode(){ // O(n)
        Node rnode = this.tail;
        if(this.head == this.tail){
            this.head = this.tail = null;          
        } else {
            Node prev = getNodeAt(this.size-2); // n 
            prev.next = null;
            this.tail = prev;
        }
        this.size--;
        return rnode;
    }

    
    public int removeAt(int idx) throws Exception{
        if(idx<0 || idx>=this.size){
            throw new Exception("NULL Pointer");
        }
        Node rnode = removeNodeAt(idx);
        return rnode.data;
    }

    private Node removeNodeAt(int idx){
        Node rnode = this.head;
        if(idx == 0){
            rnode = removeFirstNode();
        } else if(idx == this.size-1){
            //though, this case can be covered in removeAt, but here it sets tail also.
            rnode = removeLastNode();
        } else {
            Node prev = getNodeAt(idx-1);
            Node forw = prev.next.next;
            rnode = prev.next;
            prev.next = forw;
            rnode.next = null;
            this.size--;
        }
        return rnode;
    }

/********************************************************************************************** */
}