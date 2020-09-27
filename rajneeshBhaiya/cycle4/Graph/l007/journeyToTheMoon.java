// Link -     https://www.hackerrank.com/challenges/journey-to-the-moon/problem
// Hackerrank - Journey To The Moon 


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the journeyToMoon function below.

    private static final Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // taking input
        int n = scn.nextInt(); 
        int p = scn.nextInt(); 
        int[] par = new int[n]; 

        for(int i =0; i<n; i++) par[i] = i; 
        int[] size = new int[n]; 
        Arrays.fill(size, 1);

        int[][] arr = new int[p][2];
        for(int i = 0; i<p ; i++){
            int u = scn.nextInt(); 
            int v = scn.nextInt(); 

            arr[i][0] = u; 
            arr[i][1] = v; 
        }

        // input over and basic initialization of parent and size array.

        findComp(par, size, arr); 
        long res = 0, sum = 0; 

        for(int i = 0; i<par.length; i++){
            if(par[i]==i){
                res += sum * size[i]; 
                sum += size[i];
            }
        }

        System.out.println(res);

    }

    public static void findComp(int[] par, int[] size, int[][] arr){
        for(int i = 0; i<arr.length; i++){
            int p1 = findPar(arr[i][0], par);
            int p2 = findPar(arr[i][1], par);

            if(p1!=p2){
                par[p2] = p1; 
                size[p1]+= size[p2]; // here, don't add 1 to the size. 
            } 
        }
    }

    public static int findPar(int u , int[] par){
        if(par[u]==u) return u; 
        return par[u] = findPar(par[u] , par);
    }
}
