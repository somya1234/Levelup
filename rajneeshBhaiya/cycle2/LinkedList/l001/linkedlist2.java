public class linkedlist2 {
    private class Node{
        int data = 0;
        Node next = null;
        Node(int data, Node next){
            this.data = data;
            this.next = next;
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

    
    //=================

    public int removeFirst() throws Exception{
        if(this.size==0){
            throw new Exception("Empty LL");
        }
        Node rnode = removeFirstNode();
        return rnode.data;
    }

    private Node removeFirsNode(){
        Node rnode = this.head;
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
        }
        this.size--;
    }

    public int removeLast(){
        if(this.size==0){
            throw new Exception("Empty LL");
        }
        Node rnode = removeLastNode();
        return rnode.data;
    }

    private Node removeLastNode(){
        Node rnode = this.head;
        if(this.head == this.tail){
            this.head = this.tail = null;          
        } else {
            int size = this.size-1;
            while(size-->0){
                rnode = rnode.next;
            }
        }
        return rnode;
    }

    

}