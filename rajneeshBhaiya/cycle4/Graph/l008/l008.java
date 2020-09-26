public class l008 {
    // ARTICULATION POINT 

    public static void main(String[] args) {
        solve();
    }


    public static void solve(){

    }

    /*************************************************************************************/
    
    static int[] low; 
    static int[] discover;   
    static boolean[] AP;  // articulation point 

    static boolean[] vis; 
    static int timeCount = 0; 
    static int rootCount = 0; 


    public static void DFS_APB(int src, int par){
        low[src] = discover[src] = timeCount++; // put timecount and then increment it.
        vis[src] = true; 

        for(Edge e : graph[src]){
            if(!vis[e.v]){
                // to count the number of calls from root.
                if(par==-1) rootCount++;

                DFS_APB(e.v, src);
                
                if(discover[src]<=low[e.v]) AP[src] = true;

                if(discover[src]<low[e.v]) {
                    System.out.println("Articulation Bridge : "+ src+" -> "+e.v);
                }


                low[src] = Math.min(low[src],low[e.v]);

            } else if(e.v!=par){
                // check already visited, and not their own parents (only when cycle is forming.)
                low[src] = Math.min(low[src], discover[e.v]); 
            }
        }

    }


    public static void APoint_Bridges(){
        low = new int[N];
        discover = new int[N]; 
        AP = new boolean[N]; 
        vis = new boolean[N]; 

        // works in case of many components of a graph 
        for(int i  = 0; i<N ; i++){
            if(!vis[i]){
                DFS_APB(i, -1);
                // then root is not an articulation point if its calls are only 1.
                if(rootCount==1) AP[i] = false; 
                else{
                    // vahi pe true hogya tha
                }

                // do rootCount = 1; for futther components
                rootCount = 1; 
            }
        }
    }



}
