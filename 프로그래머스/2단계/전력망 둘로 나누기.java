import java.util.*;

/*
    풀이
    1. 처음에 완전한 트리가 1개 주어진다.
    2. 트리에서 간선을 하나씩 빼고 두 트리의 노드 개수 차이를 센다
    

*/

class Solution {
    
    List<Integer>[] graph;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < wires.length; i++){
            graph[wires[i][0]].add(wires[i][1]);
            graph[wires[i][1]].add(wires[i][0]);
        }
        
        for(int i = 0 ; i < wires.length; i++){
            boolean[] visited = new boolean[n + 1];
            int[] cnt = new int[2];
            cnt[0] = bfs(wires[i][0], wires[i][1], visited);
            cnt[1] = bfs(wires[i][1], wires[i][0], visited);
            answer = Math.min(answer, Math.abs(cnt[0] - cnt[1]));
        }
        
        return answer;
    }
    
    int bfs(int number, int removeNumber, boolean[] visited){
        Queue<Integer> q = new ArrayDeque<>();
        visited[number] = true;
        q.add(number);
        
        int cnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next: graph[cur]){
                if(!visited[next] && next != removeNumber){
                    visited[next] = true;
                    q.add(next);
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}
