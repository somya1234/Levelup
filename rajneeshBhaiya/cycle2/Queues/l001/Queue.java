public class Queue{
    private int[] arr;
    private int head;
    private int tail;
    private int size;
    private int maxSize;

    Queue(){
        // default size passed in default constructor of Queue.
        setValues(10);
    }

    Queue(int n ){
        setValues(n);
    }

    protected void setValues(int n ){
        this.arr = new int[n];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.maxSize = n;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<this.size;i++){
            int idx = (this.head + i) % this.maxSize;
            sb.append(this.arr[idx]);
            if(i!=this.size-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public int size(){
        return this.size;
    }

    public int capacity(){
        return this.maxSize;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public void add(int val) throws Exception{
        if(this.size==this.maxSize){
            throw new Exception("Queue Overflow");
        }
        add_(val);
    }

    protected void add_(int val){
        this.arr[this.tail] = val;
        this.tail = (this.tail+1)%this.maxSize;
        this.size++;
    }

    public int peek() throws Exception{
        if(this.size == 0){
            throw new Exception("QueueIsEmpty");
        }
        return peek_();
    }

    protected int peek_(){
        // return this.st[0];
        return this.arr[this.head];
    }

    public int remove() throws Exception{
        if(this.size==0){
            throw new Exception("QueueIsEmpty");
        }
        return remove_();
    }

    protected int remove_(){
        int rv = peek_();
        this.arr[this.head] = 0;
        this.head = (this.head+1)%this.maxSize;
        this.size--;
        return rv;
    }


}