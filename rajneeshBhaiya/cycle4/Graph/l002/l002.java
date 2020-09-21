import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.StringBuilder;


public class l002 {
    public static void main(String[] args) {
        construct();
        display();
        solve();

    }

    /********************************************************************* */
    // construct graph 

    public static class Edge{
        int v; 
        int w; 
        Edge(int v, int w){
            this.v = v; 
            this.w = w; 
        }
    }

    static int N = 7; 
    static ArrayList<Edge>[] graph = new ArrayList[N]; 

    public static void addEdge(int u, int v, int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static int findEdge(int u, int v){
        int idx = 0; 
        for(Edge e : graph[u]){
            if(e.v == v)
                return idx;
            idx++;
        }
        return -1; 
    }

    public static void removeEdge(int u, int v){
        int idx = findEdge(u,v);
        if(idx!=-1)
            graph[u].remove(idx);

        idx = findEdge(v, u);
        if(idx!=-1)
            graph[v].remove(idx);
    }

    public static void removeVtx(int u){
        // instead of creating a new arraylist a t that idx, we can do 
        
        // important to remove in reverse order 
        for(int i = graph[u].size()-1; i>=0; i--){
            // graph[u].remove(i);  - can't do this, we have to remove edge from both sides 
            Edge e = graph[u].get(i); 
            removeEdge(u, e.v);
        }
    }

    public static void construct(){
        for(int i =0; i<N; i++){
            graph[i] = new ArrayList<>();  
        }

        addEdge(0,1,10); 
        addEdge(1,2,10);
        addEdge(0,3,10);
        addEdge(3,2,10);
        addEdge(3,4,10);
        addEdge(4,5,10);
        addEdge(4,6,10);
        addEdge(5,6,10);
    }


    public static void display(){
        StringBuilder sb = new StringBuilder(); 
        for(int i = 0; i<graph.length; i++){
            sb.append(i+" -> ");
            for(Edge e : graph[i]){
                sb.append("("+ e.v+", "+e.w+") ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /*********************************************************************/


    public static void solve(){
        boolean[] vis = new boolean[N]; 
        // BFS_01(0,vis);
        // BFS_02(0, 6, vis);
        BFS_03(0, vis);
    }

    /*********************************************************************/

    // 1. To detect cycle in BFS 
    public static void BFS_01(int src, boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src); 
        
        while(que.size()!=0){
            int vtx = que.removeFirst(); 
            if(vis[vtx]){
                System.out.println("cycle" + vtx);
                // imp step.
                continue;
            }

            vis[vtx] = true; 
            for(Edge e : graph[vtx]){
                if(!vis[e.v])
                    que.addLast(e.v);
            }
        }

    }

    /*********************************************************************/

    // 2. BFS for shortest path to destination, ie. level order BFS. (though this can be made non-cyclic.)
    // it prints all the length of path to reach destination.
    public static void BFS_02(int src,int dest,  boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>(); 
        que.addLast(src); 

        int level = 0; 
        while(que.size()!=0){
            int size = que.size(); 
            while(size-->0){
                int vtx  = que.removeFirst(); 

                if(vis[vtx])
                    continue;
                if(vtx==dest){
                    System.out.println(level);
                    break;
                }

                vis[vtx] = true;
                for(Edge e: graph[vtx]){
                    if(!vis[e.v])
                        que.addLast(e.v);
                }
            }
            level++;
        }
    }

    /*********************************************************************/

    // 3. Non-cyclic BFS, where cycle doesn't matter (better complexity).
    // here, level order to find shortest path can be best done with non-cyclic BFS, 
    // because BFS travels same distance nodes firt, then further level.
    public static void BFS_03(int src, boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>(); 
        que.add(src); 
        vis[src] = true; 
        while(que.size()!=0){
            int vtx = que.removeFirst(); 

            for(Edge e : graph[vtx]){
                if(!vis[e.v]){
                    // mark true while adding in queue, do not let any other nbr add it, because we don't 
                    // need to detect cycles here.
                    vis[e.v] = true; 
                    que.addLast(e.v);
                }
            }
        }
    }


    /*********************************************************************/

}
