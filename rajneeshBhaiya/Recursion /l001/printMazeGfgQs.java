import java.util.ArrayList;

public class printMazeGfgQs {
    public static void main(String[] args) {
        // ratInAMaze();
        // ratInAMazeMultipleJumps();
        // specialMatrix();
        knightsTour();
    }

    /************************************************************************************************ */
    // question 1 -> GFG
    // Link -> https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

    public static void ratInAMaze() {
        int n = 4;
        int[][] arr = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 0, 1, 1, 1 } };
        if (n == 0 || arr[0][0] == 0) {
            System.out.println(-1);
            return;
        }
        int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        String[] dirS = { "D", "L", "R", "U" };
        ArrayList<String> res = new ArrayList<>();
        findPaths(0, 0, n, arr, dir, dirS, "", res);
        System.out.println(res);
    }

    public static void findPaths(int i, int j, int n, int[][] arr, int[][] dir, String[] dirS, String psf,
            ArrayList<String> ans) {
        if (i == n - 1 && j == n - 1) {
            ans.add(psf);
            return;
        }
        // better to take visited here, as if an element has already 0, then it will be
        // freed at time
        // of backtracking .
        arr[i][j] = 0;
        for (int d = 0; d < dir.length; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if (r >= 0 && c >= 0 && r < n && c < n && arr[r][c] != 0) {
                findPaths(r, c, n, arr, dir, dirS, psf + dirS[d], ans);
            }
        }
        arr[i][j] = 1;
        return;
    }

    /************************************************************************************************ */
    /************************************************************************************************ */
    // Question 2 -> gfg Rat in a maze with multiple Jumps or steps allowed.
    // https://www.geeksforgeeks.org/rat-in-a-maze-with-multiple-steps-jump-allowed/?ref=rp

    public static void ratInAMazeMultipleJumps() {
        int n = 4;
        int[][] dir = { { 0, 1 }, { 1, 0 } };
        String[] dirS = { "R", "D" };
        int[][] arr = { { 1, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 0, 0, 1 } };
        int[][] visited = new int[n][n];
        System.out.println(ratInAMazeMultipleJumps_(0, 0, n, n, arr, visited, dir, dirS, ""));
        // here, we can also take visited array, better to take as we have values with
        // all numbers in it
        // wh
    }

    public static int ratInAMazeMultipleJumps_(int i, int j, int n, int m, int[][] arr, int[][] visited, int[][] dir,
            String[] dirS, String path) {
        if (i == n - 1 && j == m - 1) {
            System.out.println(path);
            return 1;
        }

        int count = 0;
        visited[i][j] = 1; // mark it (blocking here by 0)
        for (int d = 0; d < dir.length; d++) {
            for (int jump = 1; jump <= Math.max(n - 1, m - 1); jump++) {
                int r = i + dir[d][0] * jump;
                int c = j + dir[d][1] * jump;
                if (r >= 0 && c >= 0 && r < n && c < m && arr[r][c] != 0 && visited[r][c] != 1) {
                    count += ratInAMazeMultipleJumps_(r, c, n, m, arr, visited, dir, dirS, path + dirS[d] + jump + " ");
                }
            }
        }
        visited[i][j] = 0;
        return count;
    }

    /************************************************************************************************ */
    // Question 3-> Special Matrix

    public static void specialMatrix() {
        int test = 1;
        int n = 3;
        int m = 3;
        int k = 2;
        int[][] arr = new int[n + 1][m + 1];
        arr[1][2] = 1;
        arr[3][2] = 1;
        // to block elements when take inut
        // for(int idx = 0;idx<k;idx++){
        // int r = scn.nextInt();
        // int c = scn.nextInt();
        // arr[r][c] = 1;
        // }
        // long mod =(int) Math.pow(10,9);
        long mod = 1000000007;
        // mod = mod+7;
        System.out.println(findPaths(arr, 1, 1) % mod);
    }

    public static long findPaths(int[][] arr, int r, int c) {
        int n = arr.length;
        int m = arr[0].length;
        if (r == n - 1 && c == m - 1) {
            return 1;
        }

        long count = 0;
        // horizontal call
        if (r >= 1 && r <= n - 1 && c + 1 >= 1 && c + 1 <= m - 1 && arr[r][c + 1] != 1) {
            count += findPaths(arr, r, c + 1);
        }
        if (r + 1 >= 1 && r + 1 <= n - 1 && c >= 1 && c <= m - 1 && arr[r + 1][c] != 1) {
            count += findPaths(arr, r + 1, c);
        }
        return count;
    }

    /************************************************************************************************** */
    /****************************************************************************************************/
    //Question 4. Knight's Tour 
    // https://www.geeksforgeeks.org/the-knights-tour-problem-backtracking-1/

    public static void knightsTour(){
        int[][] dir = {{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1}};
        int n = 5;
        int[][] arr = new int[n][n];
        int[][] vis = new int[n][n];
        System.out.println(placeKnights(dir,0,0,0,arr,vis));
    }

    public static int placeKnights(int[][] dir, int r, int c, int val, int[][] arr, int[][] vis){

        int n = arr.length;
        arr[r][c] = val;
        if(val == (n*n)-1){
            display2D(arr);
            arr[r][c] = 0;
            return 1;
        }
        vis[r][c] = 1;
        int count = 0;

        for(int i=0;i<dir.length;i++){
            int row = r + dir[i][0];
            int col = c + dir[i][1];
            if(row>=0 && col>=0 && row<n && col<n && vis[row][col]!=1){
                count+=placeKnights(dir, row, col, val+1, arr, vis);
            }
        }

        arr[r][c] = 0; //unmark it while backtracking.
        vis[r][c] = 0;

        return count;
    }

    public static void display2D(int[][] arr){
        for(int[] a:arr){
            display(a);
            System.out.println();
        }
        System.out.println();
    }

    public static void display(int[] a){
        for(int val:a){
            System.out.print(val+" ");
        }
    }

    /****************************************************************************************************/

}