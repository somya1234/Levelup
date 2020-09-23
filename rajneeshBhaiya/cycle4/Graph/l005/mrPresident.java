// Hackerearth - Mr. President 

// https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/submissions/



/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
import java.util.*; 
import java.lang.StringBuilder; 

class TestClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here

        Scanner scn = new Scanner(System.in); 
        int N = scn.nextInt(); 
        int M = scn.nextInt(); 
        int K = scn.nextInt(); 

        ArrayList<pair>[] graph = new ArrayList[N]; 

        for(int i =0; i<N; i++){
            graph[i] = new ArrayList<>(); 
        }

        for(int i = 0; i<M; i++){
            int u = scn.nextInt(); 
            int v = scn.nextInt(); 
            int w = scn.nextInt(); 
            u--; v--; 
            addEdge(u,v,w,graph); 
        }

        // display(graph);

        boolean[] vis = new boolean[N]; 
        PriorityQueue<pair> pq = new PriorityQueue<>(); 
        pq.add(new pair(0,-1,0)); 

        // ArrayList<pair>[] ans = new 
        int count = 0;

        while(pq.size()!=0){
            pair top = pq.remove(); 
            
            int vtx = top.u; 
            // System.out.println(vtx);
            if(vis[vtx]==true) {
                count++; 
                continue;
            }

            vis[vtx] = true;

            for(pair p : graph[vtx]){
                    // System.out.println("aaya");
                if(!vis[p.v]){
                    pq.add(new pair(p.v,vtx,p.w));
                }
            }

        }

        System.out.println(count);

    }

    public static void addEdge(int u, int v, int w, ArrayList<pair>[] graph){
        graph[u].add(new pair(u,v,w));
        graph[v].add(new pair(v,u,w));
    }

    public static void display(ArrayList<pair>[] graph){
        StringBuilder sb = new StringBuilder(); 
        for(int i =0; i<graph.length; i++){
            for(pair p : graph[i]){
                sb.append("( "+ p.u+"->"+p.v+","+p.w+" ) ");

            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    } 

    public static class pair implements Comparable<pair>{
        int u; 
        int v; 
        int w; 
        pair(int u, int v, int w){
            this.u = u; 
            this.v = v; 
            this.w = w; 
        }

        public int compareTo(pair o){
            return this.w - o.w;
        }
    }
}
