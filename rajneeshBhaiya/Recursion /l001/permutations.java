public class permutations {
    public static void main(String[] args) {
        System.out.println(allPermutation("abc", ""));
        uniquePermutations();
    }

    public static int allPermutation(String que, String ans){
        if(que.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=0;i<que.length();i++){
            char ch = que.charAt(i);
            //String are made in Heap, so can;t change in que.
            String str = que.substring(0, i)+ que.substring(i+1);
            count += allPermutation(str, ans+ch);
        }
        return count;
    }

    /****************************************************************************************** */
    //very important question 

    public static void uniquePermutations(){
        String str = "aba";
        System.out.println(uniquePermutations_(str,""));
    }

    public static int uniquePermutations_(String str, String ans){
        if(str.length()==0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean[] vis = new boolean[26]; //boolean array of size 26.
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(!vis[ch-'a']){ 
    //         String temp = str.substring(0, i)+str.substring(i+1); //or                
                count+= uniquePermutations_(str.substring(0, i)+str.substring(i+1), ans+ch);
                vis[ch-'a'] = true;
            }
        }
        return count;
    }
    /********************************************************************************************** */
}