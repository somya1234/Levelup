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
            sb.append("\n");
            System.out.println(sb.toString());
        }
    }

    public static int findEdge(int u, int v){
        int idx = -1;
        for(int i =0; i<graph[u].size(); i++){
            Edge e = graph[u].get(i); 
            if(e.v == v){
                idx = i; 
            }
        }
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
