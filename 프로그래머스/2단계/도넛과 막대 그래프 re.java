import java.util.*;

/*
    풀이
    도넛, 막대, 8자
    
    정점 개수 N
    도넛 간선 : N
    막대 간선 : N - 1
    8자  간선 : N + 1
    
    간선 개수 체크
    
    0. 그래프 만들기
    1. 생성한 정점의 조건 : 들어오는 간선이 아예 없는 것
    2. 해당 정점에서 그래프 탐색하여 정점 개수, 간선 개수 세기
    3. 정점 개수, 간선 개수에 따라 종류 분리하기
    
*/

class Solution {
    
    int[] answer;
    List<Integer>[] graph;
    
    public int[] solution(int[][] edges) {
        
        answer = new int[4];
        graph = new ArrayList[1000001];
        
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        
        // edges[][1]에는 없어야 됨
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0 ; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            
            graph[a].add(b);
            set.add(a);
            set.add(b);
        }
        
        for(int i = 0 ; i < edges.length; i++){
            set.remove(edges[i][1]);
        }
        
        int start = 0;
        int maxEdge = -1;
        for(int num: set){
            if(maxEdge < graph[num].size()){
                start = num;
                maxEdge = graph[num].size();
            }
        }
        
        for(int next: graph[start]){
            searchGraph(next);
        }
        
        answer[0] = start;
        return answer;
    }
    
    void searchGraph(int start){
        HashSet<Integer> visited = new HashSet<>();
        visited.add(start);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int v = 1; 
        int edge = 0;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next: graph[cur]){
                edge++;
                if(!visited.contains(next)){
                    visited.add(next);
                    q.add(next);
                    v++;
                }
            }
        }
        
        // 도넛 간선 : N
        // 막대 간선 : N - 1
        // 8자  간선 : N + 1
        if(v == edge){
            answer[1]++;
        }
        else if(v - 1 == edge){
            answer[2]++;
        }
        else{
            answer[3]++;
        }        
    }

}
