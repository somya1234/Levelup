import java.util.ArrayList;
public class l001 {
    public static class Edge{
        int v = 0; 
        int w = 0;

        Edge(int v, int w){
            this.v = v; 
            this.w = w;

        }
    }

    static int N = 7; 
    static ArrayList<Edge>[] graph = new ArrayList[n];

    public static void addEdge(int u, int v , int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(i+" -> ");
            for(Edge e:graph[i]){
                sb.append("("+e.v+", "+e.w+") ");
            }
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    public static int findEdge(int u, int v){
        int idx = -1;
        for(int i =0; i<graph[u].size(); i++){
            Edge e = graph[u].get(i); 
            if(e.v == v){
                idx = i; 
            }
        }
        return idx; 
    }

    public static void removeEdge(int u, int v){
        int idx = findEdge(u, v); 
        graph[u].remove(idx);

        idx = findEdge(v, u);
        graph[v].remove(u);
    }

    public static void removeVtx(int u){
        for(int i = graph[u].size()-1; i>=0; i--){
            // graph[u].remove(i);
            // but we need to remove from the other vertex also.

            Edge e = graph[u].get(i);
            removeEdge(u, e.v);
        }
    }

    public static boolean hasPath(int src, int dest, boolean[] vis, ArrayList<Edge>[] graph){
        if(src == dest )
            return true;
        
        vis[src] = true;
        for(Edge e: graph[src]){
            if(!vis[e.v])
                if(hasPath(e.v, dest, vis, graph)) return true;
        }
        return false;
    }

    public static int allPath(int src, int dest, boolean[] vis, ArrayList<Edge>[] graph, String ans, int weight){
        if(src == dest ){
            System.out.println(ans+src+" -> "+weight);
            return 1;
        }
        
        vis[src] = true;
        int count = 0;

        for(Edge e: graph[src]){
            if(!vis[e.v])
                count+= allPath(e.v, dest, vis, graph, ans+src, weight+e.w);
        }
        
        vis[src] = false;
        return count;
    }

    public static class pair{
        int weight = 0;
        String path = "";

        pair(int weight, String path){
            this.weight = weight; 
            this.path = path; 
        }
    }

    public static pair heavyWeightPath(int src, int dest , boolean[] vis){
        if(src == dest){
            return new pair(0, src+"");
        }

        vis[src] = true; 

        pair myans = new pair(0,"");
        for(Edge e: graph[src]){
            if(!vis[e.v]){
                pair res = heavyWeightPath(e.v, dest, vis);
                if(res.weight + e.w > myans.weight ){
                    myans.weight = res.weight + e.w; 
                    myans.path = res.path + src;
                }
            }
        }

        vis[src] = false;
        return myans;

    }


    public static void constructGraph(){
        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(0,1,10);
        addEdge(0,3,10);
        addEdge(1,2,10);
        addEdge(2,3,40);
        addEdge(3,4,2);
        addEdge(4,5,2);
        addEdge(5,6,3);
        addEdge(4, 6, 8);

        display();
    }

    public static void solve(){
        constructGraph();
    }
}
