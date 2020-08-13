public class leetcode {
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        validSudoku();
        solveSudoku();
    }

    /************************************************************************************************ */
    //Leetocde 36. -> Valid Sudoku
    public boolean isValidSudoku(char[][] board) {
        if(board == null){
            return false;
        }
        int n = board.length;
        int m = board[0].length;
        if(n!=9 || m!=9){
            return false;
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]!='.'){
                    int num = board[i][j]-'0';
                    if(!isValid(board,i,j,num)){
                        System.out.println("heh");
                        return false;
                    }
                    System.out.println("haha");
                }
            }
        }
        return true;
    }
    
    public boolean isValid(char[][] board, int r, int c, int num){
        int n = board.length;
        int m = board[0].length;
        //check in all rows 
        for(int i=0;i<n;i++){
            if(board[i][c] - '0' == num){
                return false;
            }
             //check in column 
            if(board[r][i] - '0'== num){
                return false;
            }
        }
        int row = r/3;
        int col = c/3;
        row = row*3;
        col = col*3;
        //check in that designated box
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(board[i][j]-'0'==num){
                    return false;
                }
            }
        }
       return true;       
    }
    /************************************************************************************************ */

    /******************************************************************************************* */
    //Question 37. Leetcode -> Sudoku Solver 
    public static void solveSudoku(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if(m!=9 || n!=9){
            return;
        }
        ArrayList<Integer> locOfZeros = new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    locOfZeros.add(i*9+j);
                }
            }
        }
        sudokuSolver(board,0,locOfZeros);
    }
    public static boolean sudokuSolver(char[][] board, int idx,ArrayList<Integer> locOfZeros){
        if(idx == locOfZeros.size()){
            // display(board);
            return true;
        }
        
        // int r = idx/9;
        // int c = idx%9;
        // if(board[r][c]!='.'){
        //     //already number is stored there, so return whatever may come.
            // return sudokuSolver(board,idx+1);
        // }
        int loc = locOfZeros.get(idx);
        int r = loc/9;
        int c = loc%9;
        
        for(int num = 1;num<=9;num++){
            if(isValid(board,r,c,num)){
                board[r][c] = (char)(num+'0');
                //assume sudoku to have a single one solution 
                if(sudokuSolver(board,idx+1,locOfZeros)){ return true; }
                board[r][c] = '.';
            }
        }
        return false;
    }
    public static boolean isValid(char[][] board, int r, int c, int num){
        for(int i=0;i<9;i++){
            if(board[r][i] - '0'== num){
                return false;
            } else if(board[i][c]-'0'==num){
                return false;
            }
        }
        int row = (r/3)*3;
        int col = (c/3)*3;
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(board[i][j]-'0'==num){
                    return false;
                }
            }
        }
        return true;
    }
    /******************************************************************************************* */
    /******************************************************************************************* */
    //Bits 
    //Leetcode 191
    public int hammingWeight(int n) {
        int counter = 32;
        int countOne = 0;
        while(counter>0){
            if((1&n)==1){
                countOne++;
            }
            n = (n>>1);
            counter--;
        }
        return countOne;
    }
    /******************************************************************************************* */


}