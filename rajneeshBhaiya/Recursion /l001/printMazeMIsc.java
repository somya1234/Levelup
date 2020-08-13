import java.util.*;

public class printMazeMIsc {
    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        // int ans1 = printIn4Dir(0,0,n,m, new int[n][m]);
        // int ans2 = printIn8Dir(0,0,n,m, new int[n][m]);
        // System.out.println(ans1);
        // System.out.println(ans2);

        // floodFill();
        // printIn8Dirs();
        printKnight();
    }
    public static int printIn4Dir(int i, int j, int n, int m, int[][] visited){
        if(i==n-1 && j==m-1){
            return 1;
        }

        visited[i][j] = 1;
        int count = 0;
        if(j+1<m && visited[i][j+1]!=1 ){
            count+= printIn4Dir(i, j+1, n, m, visited);
        }
        if(i+1<n && visited[i+1][j]!=1){
            count+=printIn4Dir(i+1, j, n, m, visited);
        }
        if(i-1>=0 && visited[i-1][j]!=1){
            count+=printIn4Dir(i-1, j, n, m, visited);
        }
        if(j-1>=0 && visited[i][j-1]!=1){
            count+= printIn4Dir(i, j-1, n, m, visited);
        }
        visited[i][j] = 0;
        return count;
    }
    public static int printIn8Dir(int i, int j, int n, int m, int[][] visited){
        if(i==n-1 && j==m-1){
            return 1;
        }

        visited[i][j] = 1;
        int count = 0;
        if(j+1<m && visited[i][j+1]!=1 ){
            count+= printIn8Dir(i, j+1, n, m, visited);
        }
        if(i+1<n && visited[i+1][j]!=1){
            count+=printIn8Dir(i+1, j, n, m, visited);
        }
        if(i-1>=0 && visited[i-1][j]!=1){
            count+=printIn8Dir(i-1, j, n, m, visited);
        }
        if(j-1>=0 && visited[i][j-1]!=1){
            count+= printIn8Dir(i, j-1, n, m, visited);
        }
        if(i-1>=0 && j+1<m && visited[i-1][j+1]!=1 ){
            count+=printIn8Dir(i-1, j+1, n, m, visited);
        }
        if(i+1<n && j+1<m && visited[i+1][j+1]!=1){
            count+= printIn8Dir(i+1, j+1, n, m, visited);
        }
        if(i+1<n && j-1>=0 && visited[i+1][j-1]!=1){
            count+= printIn8Dir(i+1, j-1, n, m, visited);
        }
        if(i-1>=0 && j-1>=0 && visited[i-1][j-1]!=1){
            count+=printIn8Dir(i-1, j-1, n, m, visited);
        }
        visited[i][j]= 0;
        return count;
    }
    
    /******************************************************************************************** */
    //New method by Sir
    public static int floodFill_(int sr, int sc, int er, int ec,int[][] visited, int[][] dir, String[] dirS, String ans){
        if(sr==er-1 && sc==ec-1){
            System.out.println(ans);
            return 1;
        }
        visited[sr][sc] = 1;
        int count = 0;
        for(int d=0;d<4;d++){
            int r = sr+dir[d][0];
            int c = sc+dir[d][1];

            if(r>=0 && c>=0  && r<er && c<ec && visited[r][c] == 0){
                count+=floodFill_(r, c, er, ec, visited, dir, dirS, ans+dirS[d]+" ");
            }
        }
        visited[sr][sc] = 0;
        return count;
    }

    public static void floodFill(){
        int sr = 0,sc = 0,er = 3, ec = 3;
        int[][] dirFour = {{1,0},{-1,0},{0,1},{0,-1}};
        String[] dirS = {"D","U","R","L"};
        int[][] visited = new int[er+1][ec+1];
        System.out.println(floodFill_(sr, sc, er+1, ec+1, visited, dirFour, dirS, ""));
    }
    /******************************************************************************************** */
    /******************************************************************************************** */

    public static void printIn8Dirs(){
        int n = 3;
        int[][] dir = {{-1,1},{0,1},{1,1},{-1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
        String[] dirS = {"N","R","E","D","S","L","W","U"};
        int[][] visited = new int[n][n];
        System.out.println(printIn8Dirs_(0,0,n,dir,dirS,visited,""));
    }

    public static int printIn8Dirs_(int i, int j, int n, int[][] dir, String[] dirS, int[][] visited, String psf){
        if(i==n-1 && j==n-1){
            System.out.println(psf);
            return 1;
        }

        int count = 0;
        visited[i][j] = 1; //mark the cell
        for(int d=0;d<dir.length;d++){
            int r = i+dir[d][0];
            int c = j+dir[d][1];
            if(r>=0 && c>=0 && r<n && c<n && visited[r][c] == 0){
                count+= printIn8Dirs_(r, c, n, dir, dirS, visited, psf+dirS[d]+" ");
            }
        }
        visited[i][j] = 0;
        return count;
    }

    /******************************************************************************************** */
    /******************************************************************************************** */

    public static void printKnight(){
        int n = 3;
        int m = 2;
        int[][] arr = {{0,1},{0,0},{0,0}};
        int[][] dir = {{-2,1},{2,1},{2,-1},{-2,-1}};
        //SL -> south left 
        //NR -> North right
        String[] dirS = {"NR","SR","SL","NL"};
        System.out.println(printKnight_(0,0,n,m,dir,arr,dirS,""));
    }

    public static int printKnight_(int i, int j, int n,int m, int[][] dir, int[][] arr, String[] dirS, String path){
        if(i==n-1 && j==m-1){
            System.out.println(path);
            return 1;
        }

        int count = 0;
        arr[i][j] = 1; //mark
        for(int d=0;d<dir.length;d++){
            int r = i+dir[d][0];
            int c = j+dir[d][1];
            //checks for cell is blocked as well as visited
            if(r>=0 && c>=0 && r<n && c<m && arr[r][c]!=1){
                count+= printKnight_(r, c, n,m, dir, arr, dirS, path+dirS[d]+" ");
            }
        }
        arr[i][j] = 0;
        return count;
    }
    /******************************************************************************************** */
   
}