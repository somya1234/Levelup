// https://www.geeksforgeeks.org/box-stacking-problem-dp-22/
// https://practice.geeksforgeeks.org/problems/box-stacking/1
import java.util.Arrays; 
class boxStacking 
{
    
    public static class pair{
        int h; 
        int w; 
        int l;
        pair(){
            
        }
        pair(int h , int w, int l){
            this.h = h; 
            this.w = w; 
            this.l = l; 
        }
    }
    public static void main(String[] args)
   {
       // your code here
    //    Scanner scn = new Scanner(System.in); 
    //    int n = scn.nextInt(); 
    //    int[] height = new int[n]; 
    //    int[] width = new int[n]; 
    //    int[] length = new int[n]; 
    int n = 4; 
    int height[] = {4,1,4,10};
    int width[] = {6,2,5,12};
    int length[] = {7,3,6,32};
    //    for(int i=0; i<n; i++){
    //        height[i] = scnnextInt();
    //    }
    //    for(int i=0; i<n; i++){
    //        width[i] = scnnextInt();
    //    }
    //    for(int i=0; i<n; i++){
    //        length[i] = scnnextInt();
    //    }
       pair[] arr = new pair[n*3]; 
       int j =0; 
       for(int i =0; i<n; i++){
           arr[j++] = new pair(height[i],width[i], length[i]);
           arr[j++] = new pair(length[i], height[i],width[i] );
           arr[j++] = new pair(width[i] ,  height[i],length[i]);
       }
       Arrays.sort(arr, (p, o)->{
        return (p.w*p.h) - (o.w*o.h); 
    });
    
    int maxLen = 0; 
    int[] dp = new int[n*3];
    int[] strg = new int[n*3];
    dp[0] = 1; 
    for(int i=1; i<n*3; i++){
        int max = 0; 
        strg[i]= arr[i].h; 
        int totalH = 0; 
        for( j =i-1; j>=0; j--){
            if(arr[j].h<arr[i].h){
                max = Math.max(max, dp[j]);
                totalH = Math.max(totalH, strg[j]); 
            }
        }
        dp[i] = max+1; 

        strg[i]+=totalH; 
    }
    
    int idx = -1; 
    for(int i=0; i<n*3; i++){
        if(dp[i]>maxLen){
            maxLen = dp[i]; 
            idx = i; 
        }
    }
    
    System.out.println(strg[idx]);
   }
}

