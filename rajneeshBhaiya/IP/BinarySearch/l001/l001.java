public class l001 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve(){
        // int[] arr = {10,10,10,20,20,20,30,30,30,30,30,30,30,30,32,33,34,45}; 
        int[] arr = {10,10,10,20,20,20,30,30,30,30,30,30,30,32,34,35}; // last occurence no output.
        // int[] arr = {30, 30};
        System.out.println(BS01(arr,30));  
        System.out.println(firstOcc(arr, 30));
        System.out.println(lastOcc(arr, 30));
    }

    //search using binary search in a sorted array.
    public static int BS01(int[] arr, int ele){
        int si = 0, ei = arr.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 

            if(arr[mid] == ele) return mid; 
            else if(arr[mid]<ele) si = mid+1; 
            else ei = mid-1; 
        }
        return -1; 
    }

    //first occurence  idx of repeating elements
    public static int firstOcc(int[] arr, int ele){
        int si = 0, ei = arr.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 

            if(arr[mid]==ele){
                if(mid-1>=0 && arr[mid-1]==ele) ei = mid - 1;
                else return mid; 
            } else if(arr[mid]<ele) si = mid+1; 
            else ei = mid-1; 
        }
        return -1; 
    }

    // last occurence idx of repeating elemenets
    public static int lastOcc(int[] arr, int ele){
        int si = 0, ei = arr.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 

            if(arr[mid]==ele){
                //mid +1 , kyuki ab last occ aage hi aayega na , don't think about phli baar m laana h
              if(mid+1<arr.length && arr[mid+1]==ele) si = mid+1; 
                else return mid; 
            } else if(arr[mid]<ele) si = mid+1; 
            else ei = mid-1; 
        }
        return -1; 
    }

    public static int nearestElement(int[] arr, int ele){
        int si = 0, ei = arr.length-1; 
        while(si<=ei){
            int mid = (si+ei) >> 1; 

            if(arr[mid] == ele) return mid; 
            else if(arr[mid]<ele) si = mid+1; 
            else ei = mid-1; 
        }

        if(ei<0) return si; 
        else if(si>=arr.length) return ei;
        else return ((ele - arr[ei]) < (arr[si] -ele)) ?  ei :  si; 
    }

    // index to insert element.
    public static int binarySearchExpectedPosition(int[] arr, int ele){
        int si = 0, ei = arr.length; 
        while(si<ei){
            int mid = (si+ei) >> 1; 

            if(arr[mid]<ele) si = mid+1; 
            else ei = mid; 
        }
        return ei; 
    }

}
