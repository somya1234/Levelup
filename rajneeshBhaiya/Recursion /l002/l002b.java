import java.util.HashSet;

public class l002b {
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        // tnq -> total no of queens 
        // int n = 4, m = 4, tnq = 4;
        // boolean[][] box = new boolean[n][m];
        // System.out.println(NQueenCombination(box, 0, tnq, ""));
        // System.out.println(NQueenCombination_best(box, 0, tnq, ""));
        wordBreak();
    }

    /*************************************************************************************** */
    public static int NQueenCombination(boolean[][] box, int idx, int tnq, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = box.length;
        int m = box[0].length;

        for(int i=idx;i<n*m;i++){
            int r = i/m;
            int c = i%m;
            if(isSafeToPlaceQueen(box,r,c)){
                box[r][c] = true;
                count+=NQueenCombination(box, i+1 , tnq-1, ans+"("+r+","+c+")");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] box, int r, int c){
        // int[][] dirP = {{-1,0},{0,-1},{1,0},{-1,-1},{1,1},{0,1},{-1,1},{1,-1}};
        int[][] dirC = {{-1,0},{0,-1},{-1,1},{-1,-1}}; //combinations -> only 4 directions

        for(int[] d:dirC){
            for(int rad = 1;rad<=box.length;rad++){
                int x = r + rad * d[0];
                int y = c + rad * d[1];
                if(x>=0 && x<box.length && y>=0 && y<box[0].length && box[x][y]){
                    //if box[x][y] is true , then 
                    return false;
                    //can be done using if - else condition 
                }
            }
        }
        return true;
    }


    /******************************************************************************************** */
    public static int NQueenCombination_best(boolean[][] box, int row, int tnq, String ans){
        if(tnq == 0 || row==box.length){
            if(tnq == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int m = box[0].length;
        int count = 0;
        for(int i=0;i<m;i++){
            if(isSafeToPlaceQueen(box, row, i)){
                box[row][i] = true;
                count+=NQueenCombination_best(box, row+1, tnq-1, ans+"("+row+","+i+")");
                box[row][i] = false;
            }
        }
        return count;
    }

    /********************************************************************************************* */
    //wordBreak 
    public static void wordBreak(){
        String ques = "ilikesamsungandmango";
        String[] word = {"mango","samsung","sam","sung","man","mango","icecream","and","go","i","like"
    ,"cream","ilike"};

    HashSet<String> words = new HashSet<>();
    int maxLenWord = 0;
    for(String s:word){
        maxLenWord = Math.max(maxLenWord,s.length());
        words.add(s);
    }
    System.out.println(wordBreak(ques, 0, "", maxLenWord, words));
    }

    public static int wordBreak(String ques, int idx, String ans,int maxLenWord, HashSet<String> words){
        if(idx == ques.length()){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=(idx+1); i<= (idx+1+maxLenWord) && i<=ques.length();i++ ){
            String str = ques.substring(idx,i);
            if(words.contains(str)){
                count+=wordBreak(ques, i, ans+str+" ", maxLenWord, words);
            }
        }
        return count;
    }
}