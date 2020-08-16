import javax.swing.border.Border;

public class l005Sir {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        crossWord();
    }

    // crossWord.===============================================================================
    public static void dislayCrossWord(char[][] board){
        for(char[] bo: board){
            for(char b: bo){
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    public static boolean canPlaceH(char[][] board,int r,int c,String word){
        int len = word.length();
        int m = board[0].length;
        for(int i=c;i<m && i<(c+len);i++){
            char ch = word.charAt(i-c);
            if(board[r][i] != ch && board[r][i] != '-'){
                return false;
            }
        }
        return true;
    }

    public static boolean[] PlaceH(char[][] board,int r,int c,String word){
        boolean[] loc=new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(board[r][c+i]=='-'){
                loc[i]=true;
                board[r][c+i]=word.charAt(i);
            }
        }

        return loc;
    }

    public static void unPlaceH(char[][] board,int r,int c,String word,boolean[] loc){
        for(int i=0;i<loc.length;i++){
            if(loc[i]){
                board[r][c+i]='-';
            }
        }
    }

    public static boolean canPlaceV(char[][] board,int r,int c,String word){
        int len = word.length();
        int n = board.length;
        for(int i=r;i<n && i<(r+len);i++){
            char ch = word.charAt(i-r);
            if(board[i][c] != ch && board[i][c] != '-'){
                return false;
            }
        }
        return true;
    }

    public static boolean[] PlaceV(char[][] board,int r,int c,String word){
        boolean[] loc=new boolean[word.length()];
        for(int i=0;i<word.length();i++){
            if(board[r+i][c]=='-'){
                loc[i]=true;
                board[r+i][c]=word.charAt(i);
            }
        }

        return loc;
    }

    public static void unPlaceV(char[][] board,int r,int c,String word,boolean[] loc){
        for(int i=0;i<loc.length;i++){
            if(loc[i]){
                board[r+i][c]='-';
            }
        }
    }


    public static int crossWord_(char[][] board,String[] words,int vidx){
        if(vidx == words.length){
            dislayCrossWord(board);
            return 1;
        }


        String word = words[vidx];
        int count=0;

        for(int i=0;i<board.length; i++){
            for(int j=0;j<board[0].length; j++){
                if(board[i][j]=='-' || board[i][j]==word.charAt(0)){
                    if(canPlaceH(board,i,j,word)){
                        boolean[] loc = PlaceH(board,i,j,word);
                        count+=crossWord_(board,words,vidx + 1);
                        unPlaceH(board,i,j,word,loc);
                    }

                    if(canPlaceV(board,i,j,word)){
                        boolean[] loc = PlaceV(board,i,j,word);
                        count+=crossWord_(board,words,vidx + 1);
                        unPlaceV(board,i,j,word,loc);
                    }
                }
            }
        }
        return count;
    }

    public static void crossWord(){
    char[][] board = {
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
        {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
        {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
        {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};

    String[] words = {"agra", "norway", "england", "gwalior"};
    System.out.println(crossWord_(board, words, 0));
}

}