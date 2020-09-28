// leetcode 1192 

class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        low = new int[n]; 
        discover = new int[n]; 
        vis = new boolean[n]; 
        AP = new boolean[n];
        ans = new ArrayList<>(); 
        graph = new ArrayList[n]; 
        
        for(int i =0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i<connections.size(); i++){
            List<Integer> list = connections.get(i); 
            addEdge(list.get(0), list.get(1));
        }
        rootCount = 0; 
        
        for(int i =0; i<n ; i++){
            if(!vis[i]){
                dfs(i, -1);
                if(rootCount == 1) AP[i]= false; 
                rootCount = 1; 
            }
        }
        
        return ans; 
    }
    
    public void dfs(int src, int par){
        low[src] = discover[src] = timeCount++; 
        vis[src] = true; 
        for(int vtx : graph[src]){
            if(!vis[vtx]){
                if(par == -1) rootCount++;
                
                dfs(vtx, src);
                
                if(discover[src]<=low[vtx]) AP[src] = true; 
                if(discover[src]<low[vtx]){
                  List<Integer> sans = new ArrayList<>(); 
                    sans.add(src); sans.add(vtx); 
                    ans.add(sans); 
                }
                
                low[src] = Math.min(low[src],low[vtx]);
            } else if(vtx!=par) {
                low[src] = Math.min(low[src], discover[vtx]);
            }
        }
    }
    
    public void addEdge(int u, int v){
        graph[u].add(v); 
        graph[v].add(u);
    }
    
    int[] low; 
    int[] discover; 
    boolean[] vis; 
    boolean[] AP; 
    List<List<Integer>> ans; 
    ArrayList<Integer>[] graph; 
    int rootCount; 
    int timeCount = 0; 
}