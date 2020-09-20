public class leetcode {
    
    public static void main(String[] args) {
        
    }

    /**********************************************************************************/

    // leetcode 207. Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        if(numCourses==0) return true;
        
        int[] indegree = new int[numCourses]; 
        int N = prerequisites.length; 
        
        for(int i =0; i<N ; i++){
            int val = prerequisites[i][0];
            indegree[val]++;
        }
        
        ArrayDeque<Integer> que = new ArrayDeque<>(); 
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i<indegree.length; i++){
            if(indegree[i]==0) que.add(i); 
        }
        
        //already cycle forming.
        if(que.size()==0) return false;
        
        while(que.size()!=0){
            int top = que.remove(); 
            ans.add(top);
            
            for(int i=0; i<N; i++){
                if(prerequisites[i][1]==top){
                    int val = prerequisites[i][0]; 
                    indegree[val]--; 
                    if(indegree[val]==0)
                        que.add(val);
                }
            }
        }
        
        if(ans.size()==numCourses) return true; 
        return false;
    }

    /**********************************************************************************/

    // leetcode 210 Course Schedule II

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses==0) return new int[]{};
       
       int[] indegree = new int[numCourses]; 
       int N = prerequisites.length; 
       
       for(int i =0; i<N ; i++){
           int val = prerequisites[i][0];
           indegree[val]++;
       }
       
       ArrayDeque<Integer> que = new ArrayDeque<>(); 
       ArrayList<Integer> ans = new ArrayList<>();
       
       for(int i = 0; i<indegree.length; i++){
           if(indegree[i]==0) que.add(i); 
       }
       
       //already cycle forming.
       if(que.size()==0) return new int[]{};
       
       while(que.size()!=0){
           int top = que.remove(); 
           ans.add(top);
           
           for(int i=0; i<N; i++){
               if(prerequisites[i][1]==top){
                   int val = prerequisites[i][0]; 
                   indegree[val]--; 
                   if(indegree[val]==0)
                       que.add(val);
               }
           }
       }
       
       if(ans.size()==numCourses){
           int[] res = new int[numCourses];
           for(int i=numCourses-1;  i>=0; i--){
               res[i] = ans.remove(ans.size()-1);
           }
           return res;
       }
       return new int[]{};
   }

   /**********************************************************************************/
}
