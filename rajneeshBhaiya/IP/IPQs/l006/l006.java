import java.util.Arrays;

public class l006 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    public static void bellmanFord(int N,  ArrayList<int[]> edges){
        int[] prevDis = new int[N]; 
        int[] currDis = new int[N]; 

        Arrays.fill(prevDis, (int)1e9);
        prevDis[src] = 0; 

        int vtx = 1; 
        while(vtx<=N){
            for(int i=0; i<N ; i++) currDis[i] = prevDis[i]; 
            boolean isAnyUpdate = false; 
            for(int[] e: edges){
                int u = e[0], v = e[1], w = e[2]; 
                if(prevDis[u] + w < currDis[v]){
                    currDis[v] = prevDis[u]+ w; 
                    isAnyUpdate = true; 
                }
            }

            if(!isAnyUpdate) break ; 
            if(isAnyUpdate && vtx==N) 
                System.out.println("Negative cycle");
        }

    }
}
