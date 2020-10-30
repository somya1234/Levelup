import java.util.ArrayList;
import java.util.LinkedList;

public class hashMap<K,V>{
    
    private class Node{
        K key = null; 
        V val = null; 

        Node(K key , V val){
            this.key = key; 
            this.val = val; 
        }
    }

    private LinkedList<E> groupArray; 
    private int size = 0; 
    private int capacity = 0; 

    public void assign(int capacity){
        this.capacity = capacity; 
        this.size = 0; 

        groupArray = new LinkedList[capacity]; 
        for(int i=0; i<capacity; i++)
            groupArray[i] = new LinkedList<>();
    }

    public hashMap(){
        assign(15);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        sb.append("["); 
        for(int i=0; i<capacity; i++){
            LinkedList<Node> gr = groupArray[i];
            int gsize = groupArray[i].size(); 
            while(gsize-->0){
                Node node = groupArray[i].getFirst(); 
                sb.append("("+node.key+" = "+node.val+"),"); 
                // if(i!=capacity-1 && gsize == 0 ) break; 
                //  sb.append(",");
                gr.addLast(gr.removeFirst());
            }
        }
        String str = sb.toString();
         str = sb.substring(0, sb.size()-1); 
        return str + "]";
    }

    public int size(){
        return this.size; 
    }

    public boolean isEmpty(){
        return this.size==0; 
    }

    public void put(K key, V val){
        Node node = foundInGroup(key); 
        if(node!=null){
            node.val = val; 
        } else{
            LinkedList<Node> gr = group(key); 
            key.addLast(new Node(key, val)); 
            this.size++;

            double lambda = gr.size() / (groupArray.length * 1.0);  
            if(lambda>=0.4) rehash();
        }
    }

    public V get(K key){
        Node node = foundInGroup(key); 
        return node == null ? null : node.val; 
    }

    public boolean containsKey(K key){
        Node node = foundInGroup(key); 
        return node!=null; 
    }

    public V getOrDefault(K key, V defaultVal){
        Node node = foundInGroup(key); 
        return node == null ? defaultVal : node.val; 
    }

    public V remove(K key){
        Node node = foundInGroup(key); 
        if(node == null) return null; 
        this.size--; 
        LinkedList<Node> gr = group(key); 
        return gr.removeFirst().val;
    }

    public ArrayList<K> keySet(){
        ArrayList<K> list = new ArrayList<>(); 
        for(int i=0; i<capacity; i++){
            LinkedList<Node> gr = groupArray[i]; 
            while(gr.size()>0){
                list.add(gr.getFirst().key); 
                gr.addLast(gr.removeFirst()); 
            }
        }
        return list; 
    }

    private void rehash(){
        LinkedList<Node>[] oldArray = groupArray; 
        assign(capacity*2);

        for(int i=0; i<capacity; i++){
             LinkedList<Node> gr  = oldArray[i]; 
            while(gr.size()!=0){
                Node node = gr.removeFirst(); 
                put(node.key, node.val); 
            }
        }
    }

    private Node foundInGroup(K key){
        LinkedList<Node> gr = group(key); 
        int gsize = gr.size(); 
        while(gsize-->0){
            if(gr.getFirst().key == key) {
                return gr.getFirst();
            }
            gr.addLast(gr.removeFirst());
        }
        return null; 
    }

    private LinkedList<Node> group(K key){
        int gidx = hashing(key);
        return groupArray[gidx];
    }

    public int hashing(K key){
        int val = key.hashCode(); 
        val = Math.abs(val); 
        return val % groupArray.length; 
    }


}
