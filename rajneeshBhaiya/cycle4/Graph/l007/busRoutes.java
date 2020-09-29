class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T) return 0; 
        
        int n = routes.length; 
        
        HashMap<Integer, ArrayList<Integer>> BusStandToBus = new HashMap<>(); 
        for(int i =0; i<n ; i++){
            for(int ele : routes[i]){
                BusStandToBus.putIfAbsent(ele, new ArrayList<>());
                BusStandToBus.get(ele).add(i); 
            }
        }
        
        HashSet<Integer> busStandVis = new HashSet<>();
        boolean[] busVis = new boolean[n];
        
        ArrayDeque<Integer> que = new ArrayDeque<>(); 
        
        que.addLast(S); 
        busStandVis.add(S); 
        int count = 0; 
        
        while(que.size()!=0){
            int size = que.size(); 
            while(size-->0){
                int busStand= que.removeFirst(); 
                
                for(int buses: BusStandToBus.get(busStand)){
                    if(!busVis[buses]){
                        for(int bs : routes[buses]){
                            if(!busStandVis.contains(bs)){
                                que.add(bs);
                                busStandVis.add(bs); 
                                if(bs == T){
                                    // no need to process all other buses, 
//                                     just return 1 more level adding in count.
                                    return count+1; 
                                }
                            }
                        }
                    }
                    // after adding all bus stands of that bus.
                    busVis[buses] = true; 
                }
            }
            count++; 
        }
        
        return -1;
    }
}



// ===========================
// similar question - books and genre (Amazon 2020 online test.)
// https://leetcode.com/discuss/interview-question/373006