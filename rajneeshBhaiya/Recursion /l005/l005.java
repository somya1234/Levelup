public class l005 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        crossword();
    }

    /****************************************************************************************** */
    public static void crossword(){
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
        
        String[] words = {"agra","norway","england","gwalior"};
        System.out.println(crossword_(board, 0, words));
    }

    public static int crossword_(char[][] board, int vidx, String[] words){

        int count = 0;
        String word = words[vidx];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == '-' || board[i][j]  == word.charAt(0)){
                    if(canPlaceH(board,i,j,word)){
                        
                    }
                }
            }
        }
    }
    /****************************************************************************************** */
}