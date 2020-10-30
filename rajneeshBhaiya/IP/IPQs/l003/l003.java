import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class l003 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){

    }

    public static void dijkstra(int src, ArrayList<int[]>[] graph, int[] dis){

        //{vtx, dis (here, time)}

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            return a[1] - b[1]; 
        }); 

        pq.add(new int[]{src,0}); 
        dis[src] = 0; 

        while(pq.size()>0){
            int[] vtx = pq.remove();

            if(dis[vtx[0]]< vtx[1]) continue; 

            for(int e: graph[vtx[0]]){
                if(vtx[1]+e[1] < dis[e[[0]]]){
                    dis[e[0]] = vtx[1] + e[1];
                    pq.add(new int[]{e[0], dis[e[0]]});
                }
            }
        }

    }

    public static void chocolateJourney(){
        Scanner scn = new Scanner(System.in); 
        int n = scn.nextInt(); 
        int m = scn.nextInt(); 
        int k = scn.nextInt(), x = scn.nextInt(); 

        boolean[] chocolateCity = new boolean[n]; 
        for(int i =0; i<k; i++) chocolateCity[scn.nextInt() - 1] = true; 

        ArrayList<int[]>[] graph = new ArrayList[n]; 
        for(int i=0; i<graph.length; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<m; i++) {
            int u = scn.nextInt() - 1, v = scn.nextInt() - 1, d = scn.nextInt(); 

            graph[u].add(new int[]{v,d});
            graph[v].add(new int[]{u,d});

        }

        int src = scn.nextInt() - 1; 
        int dest = scn.nextInt()-1 ; 

        int[] sdis = new int[n]; 
        int[] ddis = new int[n];

        Arrays.fill(sdis, (int)1e9);
        Arrays.fill(ddis, (int)1e9);

        dijkstra(src, graph, sdis);
        dijkstra(dest, graph, ddis);

        int ans = (int)1e9; 
        for(int i=0; i<n; i++){
            if(chocolateCity[i]){
                if(ddis[i]< x && sdis[i] != (int)1e9){
                    ans= Math.min(ans, sdis[i]+sdis[i]);
                }
            }
        }

        if(ans!=(int)1e9) System.out.println(ans); 
        else System.out.println(-1);

    }
}
