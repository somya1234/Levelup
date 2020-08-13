import java.util.*;

public class printMazeLeetcode {
    public static void main(String[] args) {
        uniquePathIII();
        uniquePathIII2();
    }

    /********************************************************************************************* */
    //Question1 -> UniquePathIII-> question no 980
    public static void uniquePathIII(){
        int n = 3;
        int m = 4;
        int[][] arr = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        //imp check , which we forget most of time
        if(n==0 || m==0){
            //single empty array or 2d empty array
            System.out.println(0);
            return;
        }
        int[][] vis = new int[n][m];
        int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}};
        String[] dirS = {"R","U","L","D"};
        int count = 0;
        int st = 0;
        int ed = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j] == 0){
                    count++;
                } else if(arr[i][j]==1){
                    st = i;
                    ed = j;
                }
            }
        }
        int ans = uniquePathIII_(st,ed,n,m,arr,vis,dir,dirS,count,"");
        System.out.println(ans);
    }
    public static int uniquePathIII_(int i, int j, int n, int m, int[][] arr, int[][] vis, int[][] dir,String[] dirS,int count,String path ){
        if(arr[i][j]==2 && count==0){
            // System.out.println("count is "+count);
            System.out.println(path);
            return 1;
        } else if(arr[i][j]==2){
            return 0;
        }

        int ans = 0;
        vis[i][j] = 1;
        for(int d=0;d<dir.length;d++){
            int r = i + dir[d][0];
            int c = j+ dir[d][1];
            if(r>=0 && c>=0 && r<n && c<m && vis[r][c]==0 && arr[r][c]!=-1){
                if(arr[r][c]==0) { count--;  }
                ans+=uniquePathIII_(r, c, n, m, arr, vis, dir, dirS, count,path+dirS[d]+" ");
                if(arr[r][c]==0) { count++;  } //very critical step 
            }
        }
        vis[i][j] = 0;
        return ans;
    }

    /************* */
    //approach 2 - count all non-negative numbers.
    public static void uniquePathIII2(){
        int n = 3;
        int m = 4;
        int[][] arr = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        if(n==0 || m==0){
            System.out.println(0);
            return;
        }
        int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
        int count = 0;
        int sr = 0, sc = 0, er = 0, ec = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]!=-1){
                    count++;
                } 
                //no else loop here
                if(arr[i][j]==1){
                    sr = i;
                    sc = j;
                } else if(arr[i][j]==2){
                    er = i; ec = j;
                }
            }
        }
        System.out.println(uniquePathIII2_(sr,sc,er,ec,arr,dir,count));
    }
    public static int uniquePathIII2_(int sr, int sc,int er, int ec, int[][] arr, int[][] dir, int count){
        if(sr == er && sc == ec){
            // System.out.println(count);
            return (count==1) ? 1 : 0;
        }

        int ans = 0;
        int temp = arr[sr][sc];
        arr[sr][sc] = -2; //mark it for blocked
        int n = arr.length;
        int m = arr[0].length;
        for(int d=0;d<dir.length;d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            //if cell value is positive -> safe to go
            if(r>=0 && c>=0 && r<n && c<m && arr[r][c]>=0){
                //doing count-- in the call
                ans+=uniquePathIII2_(r, c, er, ec, arr, dir, count-1);
            }
        }
        arr[sr][sc] = temp;
        return ans;
    }

    /************************************************************************************************ */
    /************************************************************************************************ */
    //Question 2 -> Path with maximum Gold -> Question 1219
    //  eg -> grid -> [[0,6,0],[5,8,7],[0,9,0]]
    //I thing T.C -> n2*(4^n)
    public int getMaximumGold(int[][] grid) {
        int[][] dir = {{0,-1},{0,1},{1,0},{-1,0}};
        int maxGold = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]!=0){
                    int res = getMaximumGold_(i, j,grid, dir );
                    maxGold = Math.max(maxGold,res);
                }
            }
        }
        return maxGold;
    }
    public int getMaximumGold_(int i, int j, int[][] grid,int[][] dir){
        
        int n = grid.length;
        int m = grid[0].length;
        int gold = 0;
        int temp = grid[i][j];
        grid[i][j] = 0; //works as visited array by marking it 
        for(int d=0;d<dir.length;d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];
            if(r>=0 && c>=0 && r<n && c<m && grid[r][c]!=0){
                int res = getMaximumGold_(r,c,grid,dir);
                gold = Math.max(gold,res);
            }
        }
        grid[i][j] = temp;
        //return my value 
        return gold + grid[i][j];
    }
    /********************************************************************************************* */
}