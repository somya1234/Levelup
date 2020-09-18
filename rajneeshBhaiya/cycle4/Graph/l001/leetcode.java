public class leetcode {
    
}


// leetcode 200 
public int numIslands(char[][] grid) {
    int count = 0; 
    for(int i = 0; i<grid.length; i++){
        for(int j = 0; j<grid[0].length; j++){
            if(grid[i][j] == '1'){
                count++; 
                gcc(grid, i, j ); 
            }
        }
    }
    return count; 
}

public void gcc(char[][] grid, int i , int j ){
    grid[i][j] = '0'; 
    
    // vertical and horizontal , only two ways to connect lands.
    if(i+1<grid.length && grid[i+1][j]=='1')
        gcc(grid, i+1, j);
    if(j+1<grid[0].length && grid[i][j+1]=='1')
        gcc(grid, i, j+1);
    if(i-1>=0 && grid[i-1][j]=='1')
        gcc(grid, i-1, j);
    if(j-1>=0 && grid[i][j-1]=='1')
        gcc(grid, i, j-1);
}


// leetcode 695 
public int maxAreaOfIsland(int[][] grid) {
    boolean[][] vis = new boolean[grid.length][grid[0].length];
    int area = 0; 
    for(int i = 0; i<grid.length; i++){
        for(int j = 0; j<grid[0].length; j++){
            if(grid[i][j] ==  1 && vis[i][j] == false){ 
                int a = gcc(grid, i, j , vis); 
                area = Math.max(area, a);
            }
        }
    }
    return area; 
}

public int gcc(int[][] grid, int i , int j , boolean[][] vis){
    vis[i][j] = true; 
    
    int area = 1; 
    // vertical and horizontal , only two ways to connect lands.
    if(i+1<grid.length && grid[i+1][j]==1 && vis[i+1][j] == false)
       area += gcc(grid, i+1, j, vis);
    if(j+1<grid[0].length && grid[i][j+1]==1 && vis[i][j+1] == false)
       area += gcc(grid, i, j+1, vis);
    if(i-1>=0 && grid[i-1][j]==1 && vis[i-1][j] == false)
        area += gcc(grid, i-1, j, vis);
    if(j-1>=0 && grid[i][j-1]==1 && vis[i][j-1] == false)
       area += gcc(grid, i, j-1, vis);
    return area;
}