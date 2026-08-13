import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0, 0};
        graph = new ArrayList[1000001];
        for(int i = 0; i <= 1000000; i++){
            graph[i] = new ArrayList<>();
        }
        
        int[] degree = new int[1000001];
        for(int i = 0 ; i < edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        
        boolean[] visited = new boolean[1000001];
        for(int node: nodes){
            if(visited[node]){
                continue;
            }
            
            Queue<Integer> q = new LinkedList<>();
            q.add(node);
            visited[node] = true;
            
            int nodeCnt = 0;
            int edgeCnt = 0;
            int group1Cnt = 0;
            int group2Cnt = 0;
            
            while(!q.isEmpty()){
                int cur = q.poll();
                nodeCnt++;
                edgeCnt += degree[cur];
                
                if(cur % 2 == degree[cur] % 2){
                    group1Cnt++;
                }
                else{
                    group2Cnt++;
                }
                
                for(int next:graph[cur]){
                    if(!visited[next]){
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
            
            if(edgeCnt / 2 == nodeCnt - 1){
                if(group1Cnt == 1){
                    answer[0]++;
                }
                if(group2Cnt == 1){
                    answer[1]++;
                }
            }
        }
        
        return answer;
    }
    
    
}
