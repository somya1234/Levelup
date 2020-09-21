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
/***************************************************************************************/

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