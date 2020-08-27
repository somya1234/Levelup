public class Linkedlist {
    private class Node {
        int data = 0;
        Node next = null;

        // doesn't matter with public here
        public Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return this.size();
    }

    public boolean isEmpty(){
        return this.size()==0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node curr = this.head;
        sb.append("[");

        while(curr!=null){
            sb.append(curr.data);
            if(curr.next!=null){ sb.append(", "); }
            curr = curr.next;
        }
        sb.append("]");
          return sb.toString(); //convert string builder to string.
    }

    // Get. ============================================================

    public int getFirst() throws Exception{
        if(this.size==0){
            throw new Exception("EmptyList");
        }
        return this.head.data;
    }

    public int getLast() throws Exception{
        if(this.size==0){
            throw new Exception("EmptyList");
        }
        return this.tail.data;
    }

    //== getAt
    public int getAt(int idx) throws Exception{
        if(idx<0 || idx>=this.size){
            throw new Exception("NULLPointer");
        }
        Node gnode = getNodeAt(idx);
        return gnode.data;
    }

    public Node getNodeAt(int idx){
        Node gnode = this.head;
        //tail 
        if(idx==this.size-1){
            return this.tail;
        } 
        while(idx-->0){
            gnode = gnode.next;
        }
        return gnode;
    }

    //Add. =========================================================================
    
    public void aaddFirst(int data){
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addFirstNode(Node node){
        if(this.head==null){
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }
    
    //=== addLast
    public void addLast(int data){
        Node node = new Node(data);
        addLastNode(node);
    }

    private void addLastNode(Node node){
        if(this.head==null){
            this.head = this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++; //increment size 
    }

    //==== addAt
    
    public void addAt(int data, int idx) throws Exception{
        if(idx<0 || idx>this.size){
            throw new Exception("NULLPointer");
        }
        Node node = new Node(data);
        addNodeAt(node, idx);
    }

    public void addNodeAt(Node node, int idx){
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

    // Remove ===========================================================================

    public int removeFirst() throws Exception{
        if(this.size==0){
            throw new Exception("EmptyList");
        }

        Node rnode = removeFirstNode();
        return rnode.data;
    }

    private Node removeFirstNode(){
        Node rnode = null;
        if(this.size==1){
            rnode = this.head;
            this.head = this.tail = null;
        } else {
            rnode = this.head;
            this.head = this.head.next;
        }
        this.size--;
        return rnode;
    }

    //====== removeLast
    public int removeLast() throws Exception{
        if(this.size==0){
            throw new Exception("EmptyList");
        } 
        Node rnode = removeLastNode();
        return rnode.data;
    }

    public Node removeLastNode(){
        Node rnode = this.head;
        if(this.size==1){
            rnode = this.head;
            this.head = this.tail = null;
        } else {
            rnode = this.tail;
            Node secondLastNode = getNodeAt(this.size-2);
            secondLastNode.next = null;
            this.tail = secondLastNode;
        }
        this.size--;
        return rnode;
    }

    //===== removeAt

    public int removeAt(int idx) throws Exception{
        if(idx<0 || idx>=this.size){
            throw new Exception("NULLPointer");
        }

        Node rnode = removeNodeAt(idx);
        return rnode.data;
    }

    private Node removeNodeAt(int idx){
        if(idx == 0){
            return removeFirstNode();
        } else if(idx == this.size-1){
            return removeLastNode();
        }else{
            Node prev = getNodeAt(idx-1);
            Node rnode = prev.next;
            Node next = rnode.next;
            prev.next = next;
            rnode.next = null;
            this.size--;
            return rnode;
        }
    }
    
}
