public class printMazePathsNJumps {
    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        int ans = findWaysWithJumps(0,0,n,m,"");
        System.out.println(ans);
    }

    //Recursion 
    public static int findWaysWithJumps(int row,int col, int n, int m , String str){
        if(row == n-1 && col == m-1){
            System.out.println(str);
            return 1;
        }

        int count = 0;
        for(int idx = 1;col+idx<m;idx++){
            count+= findWaysWithJumps(row, col+idx, n, m, str+"h"+idx);
        }
        for(int idx = 1;row+idx<n;idx++){
            // if(row+idx<n){
                count+= findWaysWithJumps(row+idx, col, n, m, str+"v"+idx);
            // } else {
            //     break;
            // }
        }
        //diagonal movement
        for(int idx = 1;row+idx<n && col+idx<m ;idx++){
            count+= findWaysWithJumps(row+idx, col+idx, n, m, str+"d"+idx);
        }
        return count;
    }
}