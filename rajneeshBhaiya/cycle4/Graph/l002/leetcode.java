public class leetcode {
    public static void main(String[] args) {
        questions(); 
    }


public static void questions(){
    surroundedRegions(); 
}

/***************************************************************************************/

// leetcode 785. Is Graph Bipartite?

// method 1 - using pair class , by Somya.
public boolean isBipartite(int[][] graph) {
    if(graph.length == 0)
        return true;
    
    boolean[] vis = new boolean[graph.length];
    for(int i =0; i<vis.length; i++){
        if(vis[i]==false){
            boolean ans = isBipartite_(i, graph, vis);
            if(!ans) return false;
        }
    }
    return true;
}

public boolean isBipartite_(int src, int[][] graph, boolean[] vis){
    LinkedList<pair> que = new LinkedList<>(); 
    int level = 0;
    que.add(new pair(src,0));
    while(que.size()!=0){
        pair top = que.remove(); 
        
        if(vis[top.val]==true){
            if(top.level != level){
                return false;
            }
        }
        vis[top.val] = true;
        level = Math.max(level, top.level);
        
        for(int nbr : graph[top.val]){
            if(!vis[nbr])
                que.add(new pair(nbr, top.level+1));
        }
    }
    return true;
}

public class pair {
    int val; 
    int level; 
    pair(int val, int level){
        this.val = val; 
        this.level = level;
    }
}

// /=========== method 2 -> By Level wise BFS, storing level in visited int array , (Sir's method )
public boolean isBipartite2(int[][] graph) {
    if(graph.length==0) return true;
    
    int[] vis = new int[graph.length]; 
    Arrays.fill(vis, -1);
    // bipartite call for all components.
    for(int i =0; i<vis.length; i++){
        if(vis[i]==-1)
            if(!isBipartite(i, graph, vis)) return false; 
    }
    return true;
}

public boolean isBipartite(int src, int[][] graph, int[] vis){
    Queue<Integer> que = new ArrayDeque<>();
    que.add(src); 
    int level = 0; 
    
    while(que.size()!=0){
        int size = que.size(); 
        while(size-->0){
            int top = que.remove(); 
            
            // odd cycle found.
            if(vis[top]!=-1 && vis[top]!=level)
                return false;
            // mark it visited by storing level into it.
            vis[top] = level; 
            
            for( int  nbr : graph[top]){
                if(vis[nbr]==-1){
                    que.add(nbr);
                }
            }
        }
        level = (level+1)%2;
    }
    return true;
}

//-==== method 3 -> earlier given by sir (less optimized), but good to explain in Interview 

public boolean isBipartite3(int[][] graph) {
    if(graph.length==0) return true;
    
    int[] vis = new int[graph.length]; 
    Arrays.fill(vis, -1);
    // bipartite call for all components.
    for(int i =0; i<vis.length; i++){
        if(vis[i]==-1)
            if(!isBipartite(i, graph, vis)) return false; 
    }
    return true;
}

// sir earlier method good for explaining.
public boolean isBipartite(int src, int[][] graph, int[] vis){
    Queue<int[]> que = new ArrayDeque<>();
    que.add(new int[]{src, 0}); 
    
    while(que.size()!=0){
        int size = que.size(); 
        while(size-->0){
            int[] top = que.remove(); 
            int vtx = top[0];
            int topLevel = top[1];
            
            // odd cycle found.
            if(vis[vtx]!=-1 && vis[vtx]!=topLevel)
                return false;
            
            // mark it visited by storing level into it.
            vis[vtx] = topLevel; 
            
            for( int  nbr : graph[vtx]){
                if(vis[nbr]==-1){
                    que.add(new int[]{nbr, (topLevel+1)%2});
                }
            }
        }
    }
    return true;
}



/***************************************************************************************/

// leetcode 994. Rotting Oranges-> V.Imp (AMAZON)

public int orangesRotting(int[][] grid) {
    // write this , whenever you find m in next line, otherwise error.
    if(grid.length==0 || grid[0].length==0) return 0;
    
    int n = grid.length; 
    int m = grid[0].length; 
    
    int ones = 0; 
    
    Queue<Integer> que = new ArrayDeque<>(); 
    
    for(int i=0; i<n; i++){
        for(int j =0; j<m ;j++){
            if(grid[i][j]==2)
                que.add(i*m+j);
            else if(grid[i][j]==1)
                ones++;
        }
    }
    
    // no fresh orange 
    if(ones == 0) return 0;
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    int time = 0;
    
    while(que.size()!=0){
        int size = que.size(); 
        while(size-->0){
            int top = que.remove(); 
            int x = top/m; 
            int y = top%m; 
            
            for(int idx = 0; idx<dir.length; idx++){
                int row = x+ dir[idx][0]; 
                int col = y + dir[idx][1]; 
                
                if(row>=0 && col>=0 && row<n && col<m && grid[row][col]==1){
                    
                    // very imp to mark here -  mark visited., otherwise add same 
//                         element twice and cyclic elements gets added repetitively , 
//                         which can effect count of ones.
                    grid[row][col] = 2; 
                    ones--;
                    que.add(row*m+col);
                }

            }
        }
        time++;
    }
    
    if(ones == 0) return time-1; 
    return -1;
    
}

/********************************************************************************************************/

    // leetcode 1091. Shortest Path in Binary Matrix

    public int shortestPathBinaryMatrix(int[][] grid) {
        // starting m hi 1 milgya ya destination pe.
        if(grid.length == 0 || (grid[0][0] == 1) || grid[grid.length-1][grid[0].length-1]==1){
            return -1;
        }
        
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0,0});
        grid[0][0] = 1; 
        int steps = 0; 
        int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        
//         Non-cyclic BFS.
        while(que.size()!=0){
            int size = que.size();
            while(size-->0){
                int[] top = que.remove(); 
         
            if(top[0]==grid.length-1 && top[1]==grid[0].length-1){
                return steps+1; 
            }
            
            for(int i = 0; i<dir.length; i++){
                int row = top[0]+dir[i][0]; 
                int col = top[1]+dir[i][1];
                
                if(row>=0 && col>=0 && row<grid.length && col<grid[0].length && grid[row][col]==0){
                    // mark visited.
                    grid[row][col] = 1; 
                    que.add(new int[]{row,col});
                }
            }
            }
            
            steps++;
        }
        return -1;
    }

/********************************************************************************************************/

//  leetcode 542. 01 Matrix (V.imp)

 // better way, not to change original array.
 public int[][] updateMatrix(int[][] matrix) {
    if(matrix.length==0) return matrix;
    
    int n = matrix.length; 
    int m = matrix[0].length; 
    
    int[][] ans = new int[n][m]; 
    // arrays.fill in 1D array.
        for(int[] a: ans){
             Arrays.fill(a, -1);
        }
            
   
    Queue<Integer> que = new ArrayDeque<>();
    
    for(int i = 0; i<n; i++){
        for(int j = 0; j<m ; j++){
            // put all 0s in queue
            if(matrix[i][j]==0){
                que.add(i*m+j);
                // fill all 0 in ans = 0.
                ans[i][j] = 0;
            }
        }
    }
    int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
    
    int d = 0;
    while(que.size()!=0){
        int size = que.size(); 
        while(size-->0){
            int top = que.remove();
            int x = top/m; int y = top%m; 
            
            for(int idx = 0; idx<dir.length; idx++){
                int row = x + dir[idx][0]; 
                int col = y + dir[idx][1]; 
                
                if(row>=0 && col>=0 && row<n && col<m && ans[row][col] == -1){
                    // mark visited in non-cyclic BFS.
                    // matrix[row][col] = 0;  (let's not change original array).
                    
                    // store distance in ans, it will come for all 1's.
                    ans[row][col] = d+1;
                    que.add(row*m+col);
                }
            }
        }
        d++; 
    }
    
    return ans;
}

/****************************************************************************************************/

// 130. Surrounded Regions

public void solve(char[][] board) {
    // it is necessary to check this, because otherwise it will find m , and then error.
    if(board.length == 0)
        return;
    
    int n = board.length; 
    int m = board[0].length; 
    
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    for(int i =0; i<n;i++){
        for(int j =0; j<m; j++){
            if((i==0 || j==0 || i==n-1 || j==m-1) && (board[i][j] == 'O')){
                gcc(board, i, j, dir);
            }
        }
    }
    
    for(int i =0; i<n;i++){
        for(int j =0; j<m; j++){
            if(board[i][j] == 'O'){
                board[i][j] = 'X';
            } else if(board[i][j] == '#'){
                board[i][j] = 'O';
            }
        }
    }
    
    
}

public void gcc(char[][] board, int i, int j, int[][] dir){
    int n = board.length; 
    int m = board[0].length; 
    
    board[i][j] = '#';
    
    for(int idx = 0; idx<dir.length; idx++){
        int row = i + dir[idx][0]; 
        int col = j + dir[idx][1];
        if(row>=0 && col>=0 && row<n && col<m && board[row][col] == 'O')
            gcc(board, row, col, dir);
    }
}

/***************************************************************************************/
// 1020. Number of Enclaves

public int numEnclaves(int[][] A) {
    int count = 0; 
    
    for(int i =0; i<A.length; i++){
        for(int j = 0; j<A[0].length; j++){
            if((i==0 || j==0 || i==A.length-1 || j==A[0].length-1) &&  (A[i][j]==1)){
                gcc(A, i, j);
            } 
        }
    }
    
    for(int i =0; i<A.length; i++){
        for(int j = 0; j<A[0].length; j++){
            if((A[i][j]==1)){
               count++;
            } 
        }
    }
    
    
    return count;
}

// DFS - recursive fn 
public void gcc(int[][] A, int i, int j ){
    A[i][j] = 0; //mark visited.
    
    int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
  
        for(int idx=0; idx<dir.length; idx++){
            int row = i + dir[idx][0]; 
            int col = j + dir[idx][1];
            if(row>=0 && col>=0 && row<A.length && col<A[0].length && A[row][col] == 1){
               gcc(A, row, col);
            }
                
        }

}


// method 2 -> using BFS 

public int numEnclaves(int[][] A) {
    if(A.length==0)
        return 0; 
    
    int n = A.length ; 
    int m = A[0].length;
    Queue<int[]> que = new ArrayDeque<>();
    for(int i = 0; i<n ; i++){
        for(int j =0; j<m; j++){
            if((i==0 || j==0 || i==A.length-1 || j==A[0].length-1) &&  (A[i][j]==1)){
                 que.add(new int[]{i,j});
                A[i][j] = 0;
            }
               
        }
    }
    
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    while(que.size()!=0){
        int[] top = que.remove(); 
        for(int idx = 0; idx<dir.length; idx++){
            int row = top[0]+dir[idx][0]; 
            int col = top[1]+dir[idx][1];
            if(row>=0 && col>=0 && row<n && col<m && A[row][col]==1){
                que.add(new int[]{row,col});
                A[row][col] = 0;
            }
        }
        
    }
    
    int count = 0; 
    for(int i = 0; i<n ; i++){
        for(int j =0; j<m; j++){
            if((A[i][j]==1)){
                 count++;
            }
               
        }
    }
    
    return count;
    
}


// sir bfs method - time complexity - O(nm)

public int numEnclaves(int[][] A) {
    if(A.length==0)
        return 0; 
    
    int n = A.length ; 
    int m = A[0].length;
    int one = 0;
    ArrayDeque<Integer> que = new ArrayDeque<>();
    for(int i = 0; i<n ; i++){
        for(int j =0; j<m; j++){
            if(A[i][j]==1){
                one++;
            if((i==0 || j==0 || i==A.length-1 || j==A[0].length-1)){
                 que.add(i*m+j);
                A[i][j] = 0;
                one--;
            }
            }
            
               
        }
    }
    
    if(one == 0) return one;
    
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    while(que.size()!=0){
        int top = que.remove(); 
        int x = top/m;
        int y = top%m;
        
        for(int idx = 0; idx<dir.length; idx++){
            int row = x +dir[idx][0]; 
            int col = y +dir[idx][1];
            if(row>=0 && col>=0 && row<n && col<m && A[row][col]==1){
                que.add(row*m+col);
                one--;
                A[row][col] = 0;
                if(one == 0)
                    return 0;
            }
        }
        
    }
    
    return one;
    
}
/***************************************************************************************/
}