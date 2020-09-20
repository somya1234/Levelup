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

    


}
