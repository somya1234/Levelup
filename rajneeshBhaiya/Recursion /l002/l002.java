public class l002 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // int n = 4;
        // int[] coin = {2,3,5,7};
        // int tar = 10;
        // boolean[] vis = new boolean[n];
        // System.out.println(coinChangePermutationInfinite(coin, 0, tar, ""));
        // System.out.println(coinChangeCombinationInfinite(coin, 0, tar, ""));
        // System.out.println(coinChangePermutationSingle(coin, 0, tar, "", vis));
        // System.out.println(coinChangeCombinationSingle(coin, 0, tar, ""));
        // System.out.println(coinChangeInfinitePermutationSubsq_(coin, tar, 0, ""));
        // System.out.println(coinChangeInfiniteCommbinationSubsq_(coin, tar, "", 0));
        // System.out.println(coinChangeSinglePermutationSubsq_(coin, tar, 0, "", vis));
        // System.out.println(coinChangeSingleCommbinationSubsq_(coin, tar, "", 0));

        int nBox = 6;
        boolean[] visited = new boolean[nBox];
        int tnq = 3; //total number of queens
        System.out.println(oneDQueenPermutation(visited,tnq,0,0, ""));
        // System.out.println(oneDQueenPermutationSubsq(visited, 0,tnq, 0, ""));
        // System.out.println(oneDQueenCombination(nBox, tnq, 0, 0, ""));

        // int m = 4;
        // int n = 4;
        // int tnq  = 4;
        // System.out.println(twoDQueenCombination(m, n, tnq,0, 0, ""));
        // boolean[][] visited = new boolean[m][n];
        // System.out.println(twoDQueenPermutation(m, n, visited, tnq, 0, 0, ""));
    }

    public static int coinChangePermutationInfinite(int[] arr, int idx,int target, String ans){
        //though, here, no role of idx .
        if(target == 0){
            System.out.println(ans);
            return 1;
        }

        int perm = 0;
        for(int i=0;i<arr.length;i++){
            //use pre-active approach and 2 condition coin is not zero
            if(target-arr[i]>=0 && arr[i]!=0){
                perm+=coinChangePermutationInfinite(arr, 0, target-arr[i],ans+arr[i]);
            }
        }
        return perm;
    }

    public static int coinChangeCombinationInfinite(int[] arr, int idx,int tar, String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=idx;i<arr.length;i++){
            if(tar-arr[i]>=0){
                count+=coinChangeCombinationInfinite(arr, i, tar-arr[i],ans+arr[i]);
            }
        }
        return count;
        
    }

    public static int coinChangeCombinationSingle(int[] arr, int idx,int target, String ans){
        if(target==0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=idx;i<arr.length;i++){
            if(target-arr[i]>=0){
                count+=coinChangeCombinationSingle(arr, i+1, target-arr[i], ans+arr[i]);
            }
        }
        return count;
    }

    public static int coinChangePermutationSingle(int[] arr, int idx,int tar, String ans,boolean[] vis){
        //we can keep negative value instead of visited array here.
       if(tar==0){
           System.out.println(ans);
           return 1;
       }

        int n = arr.length;
        //or you can do negative of array element instead of using visited array,
        int count = 0;
        for(int i=idx;i<n;i++){
            if(!vis[i]){
                vis[i] = true;
                count+=coinChangePermutationSingle(arr, 0, tar-arr[i], ans+arr[i],vis);
                vis[i] = false;
            }
        }
        return count;
    }
    
    public static int coinChangeSingleCommbinationSubsq_(int[] arr, int tar, String ans, int idx){
        if(tar==0){
            System.out.println(ans);
            return 1;
        } else if(idx == arr.length){
            //eg -> target 20, coins are [2,3,5] then target >0 , but we don't have any coin in array.
            return 0;
        }

        int count = 0;
        if(tar-arr[idx]>=0){
            count+=coinChangeSingleCommbinationSubsq_(arr, tar-arr[idx], ans+arr[idx], idx+1);
        }
        count+=coinChangeSingleCommbinationSubsq_(arr, tar, ans, idx+1);
        return count;
    }
    public static int coinChangeInfiniteCommbinationSubsq_(int[] arr, int tar, String ans, int idx){
        if(tar==0){
            System.out.println(ans);
            return 1;
        } else if(idx == arr.length){
            //eg -> target 20, coins are [2,3,5] then target >0 , but we don't have any coin in array.
            return 0;
        }

        int count = 0;
        if(tar-arr[idx]>=0){
            // count+=coinChangeInfiniteCommbinationSubsq_(arr, tar-arr[idx], ans+arr[idx], idx);
        }
        // count+=coinChangeInfiniteCommbinationSubsq_(arr, tar, ans, idx+1);
        return count;
    }

    public static int coinChangeInfinitePermutationSubsq_(int[] arr, int tar, int idx, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        } else if(idx == arr.length){
            return 0;
        }

        int count = 0;
        if(tar-arr[idx]>=0){
            count+=coinChangeInfinitePermutationSubsq_(arr, tar-arr[idx], 0, ans+arr[idx]);
        } 
        count+=coinChangeInfinitePermutationSubsq_(arr, tar, idx+1, ans);
        return count;
    }
    public static int coinChangeSinglePermutationSubsq_(int[] arr, int tar, int idx, String ans,boolean[] vis){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        } else if(idx == arr.length){
            return 0;
        }

        int count = 0;
        if(tar-arr[idx]>=0 && !vis[idx]){
            vis[idx] = true;
            count+=coinChangeSinglePermutationSubsq_(arr, tar-arr[idx], 0, ans+arr[idx],vis);
            vis[idx] = false;
        } 
        //for not include case, it is called everytime at idx+1, but in (include) case, we call from 0
        //so need to check which element is blocked and which not.
        count+=coinChangeSinglePermutationSubsq_(arr, tar, idx+1, ans,vis);
        return count;
    }
    public static int oneDQueenPermutation(boolean[] visited, int tnq, int qno, int idx, String ans){
        //e.g of Single Permutations
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = visited.length; //gives total boxes.
        for(int i=idx;i<n;i++){
            if(!visited[i]){
                visited[i] = true; //visit mark 
                count+=oneDQueenPermutation(visited, tnq , qno+1, 0, ans+"b"+i+"q"+qno+" ");
                visited[i] = false;
            }
        }
        return count;
    }

    public static int oneDQueenPermutationSubsq(boolean[] visited, int idx, int tnq, int qno ,String ans){
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        } else if( idx == visited.length){
            return 0;
        }

        int count = 0;
        //box1 allowed to sit the queen
        if(!visited[idx]){
            visited[idx] = true; // so that a box doesn't allow 2 queens to sit.
            count+= oneDQueenPermutationSubsq(visited, 0, tnq, qno+1, ans+"b"+idx+"q"+qno+" ");
            visited[idx] = false; //so that more permuatations can be considered.
        }
        //box did not allowed the queen to sit. 
        count+= oneDQueenPermutationSubsq(visited, idx+1, tnq, qno, ans);
        return count;
    }

    public static int oneDQueenCombination(int nBox, int tnq, int qno, int idx, String ans){
        // e.g of Single Combination 
        if(qno == tnq){
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        for(int i=idx;i<nBox;i++){
            count+=oneDQueenCombination(nBox, tnq, qno+1, i+1, ans+"b"+i+"q"+qno+" ");
        }
        return count;
    }
    
    public static int twoDQueenCombination(int m, int n,int tnq,int qno, int idx, String ans){
        // 1820 combination comes.
        // a queen can sit in a box, so we will find single combinations
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=idx;i<m*n;i++){
            //find r and c by using column
            int r = i/n;
            int c = i%n;
            count+=twoDQueenCombination(m, n,tnq, qno+1,i+1, ans+"b["+r+"]["+c+"] q"+qno+" ");
        }
        return count;
    }

    public static int twoDQueenPermutation(int m, int n,boolean[][] visited,int tnq,int qno, int idx, String ans){
        // 43680 permutation comes.
        if(qno==tnq){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i=idx;i<m*n;i++){
            //find r and c by using column
            int r = i/n;
            int c = i%n;
            if(!visited[r][c]){
                visited[r][c] = true;
                count+=twoDQueenPermutation(m, n,visited,tnq, qno+1,0, ans+"b["+r+"]["+c+"] q"+qno+" ");
                visited[r][c] = false;
            }
        }
        return count;
    }

}