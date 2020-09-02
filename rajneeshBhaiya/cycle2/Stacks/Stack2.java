public class Stack2 {
    private int[] st;
    private int tos;
    private int size;
    private int maxSize;

    public Stack2(){
        setValues(10);
    }

    public Stack2(int n ){
        setValues(n);
    }

    protected void setValues(int n){
        this.st = new int[n];
        tos = -1;
        size = 0;
        maxSize = n;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=this.tos;i>=0;i--){
            sb.append(this.st[i]);
            if(i!=0) { sb.append(", "); }
        }
        sb.append("]");
        // convert stringBuilder to string 
        return sb.toString();
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public int capacity(){
        return this.maxSize;
    }

    public void push(int val) throws Exception{
        if(this.size==this.maxSize){
            throw new Exception("Overflow!");
        }
        push_(val);
    }


    private void push_(int val){
        this.st[++this.tos] = val;
        this.size++;
    }

    public int top() throws Exception{
        if(this.size==0){
            throw new Exception("StackIsEmpty!");
        }
        return top_();
    }

    private int top_(){
        return this.st[this.tos];
    }

    public int pop() throws Exception{
        if(this.size==0){
            throw new Exception("StackIsEmpty");
        }
        return pop_();
    }

    private int pop_(){
        int rv = this.top_();
        //decrease tos after putting it 0 
        this.st[this.tos--] = 0;
        this.size--;
        return rv;
    }
}