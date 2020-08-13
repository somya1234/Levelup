public class gfg {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {

    }

    public static int wordBreak(){
        String[] ques = "ilikesamsungandmango";
        String[] word = {"mobile","samsung","sam","sung","man","mango","icecream","and","go","i","like",
        "ice","cream","ilike"};
        HashSet<String> words = new HashSet(word);
        System.out.println(wordBreak());
    }

    public static int wordBreakProblem(String ques, int idx, String ans, int maxLenWord, HashSet<String> words) {
        // link -> https://www.geeksforgeeks.org/word-break-problem-dp-32/
        if(idx == ques.length()){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx + 1; i<=maxLenWord+1;i++){
            //do it maxWord.length();
            String str = ques.substring(idx,i);
            if(word.contains(str)){
                count+=wordBreakProblem(ques, i, ans+str+" ", maxLenWord, words);
            }
        }
        return count;
    }

    public static void wordBreakTry() {
        HashSet<String> hs = new HashSet<>();
        hs.add("i");
        hs.add("like");
        hs.add("sam");
        hs.add("sung");
        hs.add("samsung");
        hs.add("mobile");
        hs.add("ice");
        hs.add("cream");
        hs.add("icecream");
        hs.add("man");
        hs.add("go");
        hs.add("mango");

        // System.out.println(hs);
        Scanner scn = new Scanner(System.in);
        int test = scn.nextInt();
        for (int t = 0; t < test; t++) {
            String str = scn.nextLine();
            String temp = "";
            for (int i = 0; i < str.length(); i++) {
                temp = temp + str.charAt(i) + "";
                if (hs.contains(temp)) {
                    temp = "";
                }
            }
            if (temp.length() == 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }
    /******************************************************************************************* */
    static String a = "send";
    static String b = "more";
    static String c = "money";

    public static void cryptoCurrency(){
        // link -> https://www.geeksforgeeks.org/solving-cryptarithmetic-puzzles-backtracking-8/
    }
}