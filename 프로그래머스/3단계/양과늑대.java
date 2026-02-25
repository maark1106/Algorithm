import java.util.*;

class Solution {
    
    static List<Integer>[] graph;
    static int res;
    
    public int solution(int[] info, int[][] edges) {
        res = 0;
        graph = new ArrayList[info.length];
        for(int i = 0 ; i < info.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
        }
        
        List<Integer> reach = new ArrayList<>();
        reach.add(0);
        dfs(0, 0, 0, info, reach);
        
        return res;
    }
    
    void dfs(int sheep, int wolf, int cur, int[] info, List<Integer> reach){
        if(info[cur] == 0){
            sheep++;
        }
        else{
            wolf++;
        }
        
        if(sheep <= wolf){
            return;
        }
        
        res = Math.max(res, sheep);
        
        List<Integer> next = new ArrayList<>(reach);
        next.remove(Integer.valueOf(cur));
        for(int num: graph[cur]){
            next.add(num);
        }
        
        for(int num: next){
            dfs(sheep, wolf, num, info, next);
        }
    }
        
        
}
