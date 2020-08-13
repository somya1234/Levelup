import java.util.*;

public class subsequences {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        allsubsequences(str);
        uniquesubsequences(str);
    }
    public static void allsubsequences(String str){
        ArrayList<String> list = new ArrayList<>();
        allsubsequences_(str,"",list);
        System.out.println(list);
    }
    public static void allsubsequences_(String str, String ans,ArrayList<String> list){
        if(str.length()==0){
            list.add(ans);
            return;
        }

        char ch = str.charAt(0);
        allsubsequences_(str.substring(1), ans,list);
        allsubsequences_(str.substring(1), ans+ch,list);

    }

    public static void uniquesubsequences(String str){
        //better to use hashSet in place of arraylist
        // as hashSet complexity contains method is O(1)
        HashSet<String> hm = new HashSet<>();
        uniquesubsequences_(str,"",hm);
        System.out.println(hm);
    }
    public static void uniquesubsequences_(String str, String ans,HashSet<String> hm){
        if(str.length()==0){
            if(!hm.contains(ans))
                hm.add(ans);
            return;
        }

        char ch = str.charAt(0);
        uniquesubsequences_(str.substring(1), ans,hm);
        uniquesubsequences_(str.substring(1), ans+ch,hm);

    }
    
}