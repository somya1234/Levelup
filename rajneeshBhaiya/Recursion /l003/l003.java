import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public class l003 {
    public static void main(String[] args) {
        solve();
    }
    public static void solve(){
        sudoku();
    }

    /************************************************************************************* */
    //Q1. -> Sudoku 
    public static void sudoku(){
        int[][] board = {{3, 0, 0, 6, 0, 0, 0, 9, 2},  
                      {5, 2, 0, 0, 0, 0, 4, 0, 8},  
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},  
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},  
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},  
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},  
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},  
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},  
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        //arraylist stores location of zeroes 
        ArrayList<Integer> locOfZeros = new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]==0){
                    //idx according to 1D array 
                    locOfZeros.add(i*9 + j);
                }
            }
        }
        sudokuSolver02(board, 0 , locOfZeros);
    }
    public static boolean helper(int[][] board, int r, int c, int num){
        for(int i=0;i<9;i++){
            //check in complete row
            if(board[r][i]==num){
                return false;
            } 
            //check in complete column 
            if(board[i][c]==num){
                return false;
            }
        }
            //check in that box
        int row = r/3; // get idx of that box 
        int col = c/3;
        row = row*3; //get actual idx of the box 
        col = col*3;
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(board[i][j]==num){
                    return false;
                }
            }
        }
        return true;
    }

    public static int sudokuSolver(int[][] board, int vidx){
        if(vidx == 81){
            display2D(board);
            return 1;
        }

        int r = vidx/9;
        int c = vidx%9;

        if(board[r][c]!=0){
            //there is already a number 
            return sudokuSolver(board, vidx+1);
        }
        int count = 0;
        for(int num = 1;num<=9; num++){
            if(helper(board, r, c, num)){
                board[r][c] = num;
                //as it is a 1D array, so increment vidx+1
                count+=sudokuSolver(board, vidx+1);
                board[r][c] = 0;
            }
        }
        return count;
    }

    public static int sudokuSolver02(int[][] board, int vidx, ArrayList<Integer> locOfZeros){
        if(vidx == locOfZeros.size()){
            // processed all 0's locations of the ArrayList.
            display2D(board);
            return 1;
        }

        int twoDloc = locOfZeros.get(vidx);
        int r = twoDloc / 9; //retreive r and c from the location stored in the arraylist/
        int c = twoDloc % 9;

        int count = 0;
        for(int num = 1;num<=9; num++){
            if(helper(board,r,c,num)){
                board[r][c] = num;
                count+=sudokuSolver02(board, vidx+1, locOfZeros);
                board[r][c] = 0;
            }
        }
        return count;
    }

    //====================== using bit masking 
    static int[] rows;
    static int[] cols;
    static int[][] mat;

    public static int sudokuSolver_BitMasking(int[][] board, int vidx, ArrayList<Integer> locOfZeros){
        if(vidx == locOfZeros.size()){
            display2D(board);
            return 1;
        }
        int twoDloc = locOfZeros.get(vidx);
        int r = twoDloc / 9;
        int c = twoDloc % 9;
        int count = 0;
        for(int num = 1;num<=9;num++){
            int mask = (1<<num);
            if((rows[r] & mask) == 0 && (cols[c]& mask) == 0 && (mat[r/3][c/3] & mask)==0){
                rows[r] ^= mask;
                cols[c] ^=mask;
                mat[r/3][c/3] ^= mask;
                board[r][c] = num;
                count+=sudokuSolver_BitMasking(board, vidx+1, locOfZeros);
                rows[r]^=mask;
                cols[c]^=mask;
                mat[r/3][c/3] ^=mask;
                board[r][c] =0;
            }
        }
        return count;
    }

    public static void display2D(int[][] board){
        for(int[] a:board){
            display(a);
        }
        System.out.println();
    }

    public static void display(int[] board){
        for(int ele:board){
            System.out.print(ele+" ");
        }
        System.out.println();
    }
    /************************************************************************************* */

}