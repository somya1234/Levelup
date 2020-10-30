import java.util.HashMap;

public class client {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(); 
        map.put("a", 45); 
        map.put("B",46); 
        map.put("C", 48);
        map.put("C", 456); 
        map.remove("a"); 
        map.getOrDefault("B",210);
        System.out.println(map);
    }
}
