import java.util.ArrayDeque;
import java.util.ArrayList;

public class l003 {
    public static void main(String[] args) {
        
        solve();
    }


    public static void topoDFS(int src, boolean[] vis, ArrayList<Integer> ans){
        vis[src] = true; 
        for(Edge e: graph[src]){
            if(!vis[e.v]){
                topoDFS(e.v, vis, ans);
            }
        }
        ans.add(src);
    }

    public static void topologicalOrder(){
        boolean[] vis = new boolean[N]; 
        ArrayList<Integer> ans  = new ArrayList<>();

        for(int i =0; i<N; i++){
            if(!vis[i]) topoDFS(i, vis, ans);
        }
    }

    //  Kahn's algo for cycle detection 

    public static void topologicalOrder(){
        int[] indegree = new int[N];
        for(int i =0; i<N; i++){
            for(int e:graph[i]) indegree[e]++;
        }   

        ArrayDeque<Integer> que = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i =0; i<N; i++) if(indegree[i]==0) que.add(i); 

        while(que.size()!=0){
            int vtx = que.removeFirst();
            ans.add(vtx);

            for(int e: graph[vtx]){
                if(--indegree[e]==0){
                    que.add(e);
                }
            }
        }

        if(ans.size()!=N)
            System.out.println("cycle");
        else
            System.out.println(ans);

    }


    /************************************************************************************/

    // union Find 

    static int[] par; 
    static int[] size; 

    public static int findPar(int u ){
        if(u == par[u]) return u ; 
        // path compression 
        return par[u] = findPar(par[u]); 
    }

    public static void merge(int p1, int p2){
        if(size[p1]<size[p2]){
            par[p1] = p2; 
            size[p2] += size[p1]; 
        } else {
            par[p2] = p1; 
            size[p1]+=size[p2];
        }
    }

    // {{u,v,w}}
    public static void unionFind(int[][] gp){
        // new graph creation 
        for(int i =0; i<N; i++) graph[i] = new ArrayList<>(); 

        Arrays.sort(gp, (a,b)->{
            // sort on basis of weight .
            return a[2] - b[2]; // this- other // Increasing 
            // return b[2] - a[2]; // other- this// reverse of default // decreasing 
        })

        // initially, all are their own parent.
        for(int i =0; i<N; i++){
            par[i] = i; 
        }

        for(int[] a : gp){
            int u = a[0]; 
            int v = a[1]; 
            int w = a[2];

            int p1 = findPar(u); 
            int p2 = findPar(v);

            if(p1!=p2){
                // add edge in graph when their parent are not equal, otherwise cycle is forming.
                addEdge(u,v,w);


                // smaller size ko add krdo bigger me -> is merge fn 
                merge(p1, p2);

                // but let's not write that here 
                // uske bina bhi kaam chl jayega 
                // par[p1] = p2; 
            }
        }
    }

    
    /************************************************************************************/

}
