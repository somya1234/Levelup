public class DStack extends Stack{
    private int[] st;
    private int tos;
    private int size;
    private int maxSize;

    // constructors are always public.
    public DStack(){
        super(10);
    }

    DStack(int n ){
        super(n);
    }

    DStack(int[] arr){
        // it calls Stack(int n ) constructor. 
        super(arr.length*2);
        for(int ele:arr){
            super.push(ele);
        }
    }

    @Override
    public void push(int val){
        if(super.size()==super.capacity()){
            int[] temp = new int[super.size()];
            int i = super.size()-1;
            while(super.size()!=0){
                temp[i--] = super.pop_();
            }
            super.setValues(temp.length*2);
            for(int ele:temp){
                super.push_(ele);
            }
        }
        super.push_(val);
    }



    // public void push(int val){
    //     if(this.size==this.maxSize){
    //         int[] newStack = new int[2*this.size];
    //         for(int i=0;i<this.size;i++){
    //             newStack[i] = st[i];
    //         }
    //         st = newStack;
    //     }
    //     push_(val);
    // }
    // private void push_(int val){
    //     this.st[++this.tos] = val;
    //     this.size++;
    }

}