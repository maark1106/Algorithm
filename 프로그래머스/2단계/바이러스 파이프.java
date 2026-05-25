import java.util.*;
import java.io.*;

/*
    시간 복잡도
    3^k x N -> 완탐으로 풀이 가능
    
    풀이
    1. depth 0 ~ 10까지 dfs로 1 ~ 3 중 하나 선택해서 열기
    2. 연 거와 인접한 노드는 어떻게 구분?
        - q에 감염된 노드들을 넣기
        - 감염 노드와 인접한 것들 중 해당 열리는 지점이 있으면 q에 넣기
        - depth 10에서 q의 사이즈 구하기
    
*/

class Solution {
    
    int res = 1;
    List<Node>[] graph;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length; i++){
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            int type = edges[i][2];
            graph[n1].add(new Node(n2, type));
            graph[n2].add(new Node(n1, type));
        }
        
        List<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        visited[infection] = true;
        list.add(infection);
        dfs(0, list, visited, k);
        
        return res;
    }
    
    void dfs(int depth, List<Integer> list, boolean[] visited, int k){
        if(depth == k){
            res = Math.max(res, list.size());            
            return;
        }
        
        for(int type = 1; type <= 3; type++){ // type별로
            List<Integer> nextBucket = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){ // 한번에 다 담고 그 다음 depth 가야됨
                int cur = list.get(i);
                for(Node next: graph[cur]){
                    if(next.type == type && !visited[next.e]){ // 열리는 type이고 방문하지 않았다면
                        nextBucket.add(next.e);
                        visited[next.e] = true;
                        list.add(next.e);
                    }
                }
            }
            
            dfs(depth + 1, list, visited, k);
            for(int i = 0 ; i < nextBucket.size(); i++){
                list.remove(list.size() - 1);
                visited[nextBucket.get(i)] = false;
            }
        }
    }
    
    class Node{
        int e;
        int type;
        
        public Node(int e, int type){
            this.e = e;
            this.type = type;
        }
    }
    
}
