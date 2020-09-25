public class leetcode {
    // Union Find Questions 
    
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    /******************************************************************************/

    // leetcode 200 - using Union Find

    int[] par; 
    
    public int numIslands(char[][] grid) {
        if(grid.length == 0 ) return 0;
        
        int n = grid.length; 
        int m = grid[0].length; 
        
         par = new int[n*m]; 
        int count = 0 ;
        
        for(int i =0; i<n*m; i++) {
            par[i] = i;
            if(grid[i/m][i%m]=='1') count++;
        }
        
        for(int i = 0; i<n ; i++){
            for(int j = 0; j<m ; j++){
                if(grid[i][j] == '1'){
                    // taking 1 parent common by making it global for right and down.
                    int p1 = findPar(i*m+j); 
                    // downwards 
                    if(j+1<m && grid[i][j+1]=='1'){
                        int p2 = findPar(i*m+(j+1));
                        if(p1!=p2){
                            count--;
                             par[p2] = p1;
                        }
                           
                    }
                    // right dir
                    if(i+1<n && grid[i+1][j]=='1'){
                        int p2 = findPar((i+1)*m+j); 
                      if(p1!=p2){
                            count--;
                             par[p2] = p1;
                        }
                           
                    }
                }
            }
        }
        // count was the no of 1's , at the end, it tells the no of global parent left.
        return count;
    }
    
    public  int findPar(int u ){
        if(par[u] == u ) return u ;
        // update gloabl parent of u , by finding parent of its parent
        return par[u] = findPar(par[u]);
    }

    /******************************************************************************/


}
