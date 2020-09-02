public class DQueue extends Queue{
    private int[] arr;
    private int head;
    private int tail;
    private int size;
    private int maxSize;

    DQueue(){
        super(10);
    }

    DQueue(int n ){
        super(n);
    }

    public void push(int val ){
        if(super.size()==super.capacity()){
            int[] temp = new int[super.size()];
            int i = 0;
            while(super.size()!=0){
                temp[i++] = super.remove_();
            }

            super.setValues(temp.length*2);
            for(int ele:temp){
                super.add_(ele);
            }
        }
        super.add_(val);
    }
}